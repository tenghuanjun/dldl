#!/usr/bin/env python3
"""
Proxyman 抓包日志分析 + Gateway 解密脚本
=============================================
功能：
1. 解析 .proxymanlogv2 文件（实质是 ZIP 包含 JSON 格式请求/响应）
2. 自动识别 -secure 请求（Gateway 加密请求）
3. 使用 Android 反编译获取的密钥尝试解密请求/响应 body
4. 输出分析报告

Gateway 加密机制（来自 Android SDK 反编译）：
- 算法: AES/CBC/PKCS7Padding
- 默认密钥: soC2GAr8jN2fsbry (GateWayManager.DEFAULT_KEY)
- IV: nonceStr 的后 16 字节
- 加密 Key: DEFAULT_KEY + nonceStr 的前 16 字节
- 输出: Base64 URL-safe (将 +/ 替换为 -_, 去掉末尾换行)

请求解密：
  Key = DEFAULT_KEY + nonceStr[0:16]
  IV  = nonceStr[16:32]
  解密 Base64 URL-safe 编码的 ciphertext

响应解密：
  Key = DEFAULT_KEY + requestNonce[0:8] + responseNonce[0:8]
  IV  = responseNonce[8:24]
"""

import zipfile
import json
import base64
import hashlib
import os
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad

# ============================================
# 配置
# ============================================
LOG_FILE = "ta.shan-yu-tech.com_07-07-2026-13-53-28.proxymanlogv2"
OUTPUT_DIR = "proxyman_analysis"
GATEWAY_KEY = "soC2GAr8jN2fsbry"  # 来自 GateWayManager.DEFAULT_KEY

os.makedirs(OUTPUT_DIR, exist_ok=True)


def base64_url_decode(s: str) -> bytes:
    """解码 Base64 URL-safe 编码（用 -_ 替代 +/）"""
    s = s.replace('-', '+').replace('_', '/')
    # 补齐 padding
    padding = 4 - len(s) % 4
    if padding != 4:
        s += '=' * padding
    return base64.b64decode(s)


def base64_url_encode(data: bytes) -> str:
    """编码为 Base64 URL-safe"""
    return base64.b64encode(data).decode().rstrip('=').replace('+', '-').replace('/', '_')


def aes_decrypt(ciphertext: bytes, key: str, iv: str) -> bytes:
    """AES/CBC/PKCS7Padding 解密"""
    cipher = AES.new(key.encode('utf-8'), AES.MODE_CBC, iv.encode('utf-8'))
    try:
        return unpad(cipher.decrypt(ciphertext), AES.block_size)
    except Exception:
        # 尝试不 unpad
        return cipher.decrypt(ciphertext)


def decrypt_request_body(body_data: str, nonce_str: str):  # -> bytes or None
    """
    解密 Gateway 加密的请求 body
    
    Key = GATEWAY_KEY + nonceStr[0:16]
    IV  = nonceStr[16:32]
    """
    if not body_data or not nonce_str or len(nonce_str) < 32:
        return None
    try:
        key = GATEWAY_KEY + nonce_str[:16]
        iv = nonce_str[16:32]
        ciphertext = base64_url_decode(body_data)
        return aes_decrypt(ciphertext, key, iv)
    except Exception as e:
        return None


def decrypt_response_body(body_data: str, req_nonce: str, resp_nonce: str):  # -> bytes or None
    """
    解密 Gateway 加密的响应 body
    
    Key = GATEWAY_KEY + requestNonce[0:8] + responseNonce[0:8]
    IV  = responseNonce[8:24]
    """
    if not body_data or not req_nonce or not resp_nonce:
        return None
    try:
        key = GATEWAY_KEY + req_nonce[:8] + resp_nonce[:8]
        iv = resp_nonce[8:24]
        ciphertext = base64_url_decode(body_data)
        return aes_decrypt(ciphertext, key, iv)
    except Exception as e:
        return None


def get_headers(entry: dict) -> dict:
    """从 Proxyman 的 header 结构中提取 headers 字典"""
    h = entry.get('header')
    if h is None:
        return {}
    entries = h.get('entries')
    if entries is None:
        return {}
    return {e['key']['nameInLowercase']: e['value'] for e in entries}


def parse_proxyman_log(log_path: str):  # -> list of dicts
    """
    解析 Proxyman 日志文件，提取所有请求
    返回请求列表，每个元素包含所有关键字段
    """
    requests = []
    z = zipfile.ZipFile(log_path)
    
    for name in z.namelist():
        try:
            with z.open(name) as f:
                raw = f.read()
            if not raw.strip():
                continue
            data = json.loads(raw)
        except Exception:
            continue
        
        if 'request' not in data:
            continue
        
        req = data['request']
        resp = data.get('response') or {}
        req_h = get_headers(req)
        resp_h = get_headers(resp)
        
        method = req['method']
        if isinstance(method, dict):
            method = method.get('name', 'GET')
        
        body_data = req.get('bodyData') or ''
        resp_body_data = resp.get('bodyData') or ''
        
        entry = {
            'id': data['id'],
            'method': method,
            'host': req['host'] or '',
            'path': req['uri'],
            'scheme': req['scheme'],
            'is_secure': '-secure' in (req['host'] or ''),
            'status': resp.get('status', ''),
            # Gateway headers
            'x_request_id': req_h.get('x-request-id', ''),
            'x_nonce': req_h.get('x-request-nonce-str', ''),
            'x_version': req_h.get('x-request-version', ''),
            'x_resp_nonce': resp_h.get('x-response-nonce-str', ''),
            # Content
            'content_type': req_h.get('content-type', ''),
            'user_agent': req_h.get('user-agent', ''),
            'cookies': req_h.get('cookie', ''),
            # Bodies (raw base64 from Proxyman)
            'body_data': body_data,
            'resp_body_data': resp_body_data,
            'body_len': len(body_data),
            'resp_body_len': len(resp_body_data),
            # Extra
            'is_ssl': data.get('isSSL', False),
            'timing': data.get('timing', {}),
            'summary': data.get('summary', {}),
        }
        requests.append(entry)
    
    requests.sort(key=lambda x: x['id'])
    return requests


def analyze_and_decrypt(requests: list[dict]):
    """分析所有请求并尝试解密 -secure 请求"""
    secure = [r for r in requests if r['is_secure']]
    nonsecure = [r for r in requests if not r['is_secure']]
    
    print("=" * 70)
    print("Proxyman 抓包日志分析报告")
    print("=" * 70)
    print(f"总请求数: {len(requests)}")
    print(f"Gateway 加密 (-secure) 请求: {len(secure)}")
    print(f"非加密请求: {len(nonsecure)}")
    print()
    
    # --- 非加密请求概览 ---
    print("─" * 70)
    print("非加密请求列表:")
    print("─" * 70)
    for r in nonsecure:
        full_url = f"{r['scheme']}://{r['host']}{r['path']}"
        print(f"  [{r['id']}] {r['method']:4s} {full_url}")
    print()
    
    # --- 加密请求概览 + 解密尝试 ---
    print("─" * 70)
    print("Gateway 加密请求 (-secure) 列表 & 解密结果:")
    print("─" * 70)
    
    decrypt_results = []
    
    for r in secure:
        full_url = f"{r['scheme']}://{r['host']}{r['path']}"
        print(f"\n{'='*70}")
        print(f"[{r['id']}] {r['method']} {full_url}")
        print(f"  Status: {r['status']}")
        print(f"  x-request-id: {r['x_request_id']}")
        print(f"  X-Request-Nonce-Str: {r['x_nonce'][:40]}...")
        print(f"  x-response-nonce-str: {r['x_resp_nonce'][:40]}..." if r['x_resp_nonce'] else "  x-response-nonce-str: (none)")
        print(f"  Content-Type: {r['content_type']}")
        print(f"  User-Agent: {r['user_agent'][:80]}...")
        
        decrypted = False
        result = {
            'id': r['id'],
            'url': full_url,
            'method': r['method'],
            'req_decrypted': None,
            'resp_decrypted': None,
        }
        
        # 解密请求 body
        if r['body_data'] and r['method'] in ('POST', 'PUT', 'PATCH'):
            plaintext = decrypt_request_body(r['body_data'], r['x_nonce'])
            if plaintext:
                try:
                    result['req_decrypted'] = plaintext.decode('utf-8', errors='replace')
                    print(f"  ✅ 请求解密成功! ({len(plaintext)} bytes)")
                    body_preview = result['req_decrypted'][:300]
                    # 尝试美化 JSON
                    try:
                        parsed = json.loads(result['req_decrypted'])
                        print(f"  📋 Body (JSON): {json.dumps(parsed, ensure_ascii=False, indent=2)[:500]}")
                    except:
                        print(f"  📋 Body: {body_preview}...")
                    decrypted = True
                except Exception as e:
                    print(f"  ⚠️ 请求明文解码失败: {e}")
            else:
                print(f"  ❌ 请求解密失败（密钥可能不对）")
                # 打印原始数据供分析
                print(f"     body_data (前100字): {r['body_data'][:100]}...")
        
        # 解密响应 body
        if r['resp_body_data'] and r['x_resp_nonce']:
            plaintext = decrypt_response_body(r['resp_body_data'], r['x_nonce'], r['x_resp_nonce'])
            if plaintext:
                try:
                    result['resp_decrypted'] = plaintext.decode('utf-8', errors='replace')
                    print(f"  ✅ 响应解密成功! ({len(plaintext)} bytes)")
                    try:
                        parsed = json.loads(result['resp_decrypted'])
                        print(f"  📋 Response (JSON): {json.dumps(parsed, ensure_ascii=False, indent=2)[:500]}")
                    except:
                        print(f"  📋 Response: {result['resp_decrypted'][:300]}")
                    decrypted = True
                except Exception as e:
                    print(f"  ⚠️ 响应明文解码失败: {e}")
            else:
                # 非 -secure 路径的响应可能不需要解密
                pass
        
        if not decrypted and r['method'] == 'GET':
            # GET 请求的查询参数也在 URL 中加密了
            query = r['path'].split('?')[-1] if '?' in r['path'] else ''
            if query and len(query) > 50 and r['x_nonce']:
                print(f"  🔍 GET 查询参数已加密，尝试解密...")
                try:
                    key = GATEWAY_KEY + r['x_nonce'][:16]
                    iv = r['x_nonce'][16:32]
                    ciphertext = base64_url_decode(query)
                    plaintext = aes_decrypt(ciphertext, key, iv)
                    decrypted_query = plaintext.decode('utf-8', errors='replace')
                    print(f"  ✅ 查询参数解密成功: {decrypted_query[:300]}")
                    result['req_decrypted'] = f"QUERY: {decrypted_query}"
                    decrypted = True
                except Exception as e:
                    print(f"  ❌ 查询参数解密失败: {e}")
        
        decrypt_results.append(result)
    
    # --- 汇总 ---
    print("\n" + "=" * 70)
    print("解密汇总")
    print("=" * 70)
    success = [r for r in decrypt_results if r['req_decrypted'] or r['resp_decrypted']]
    failed = [r for r in decrypt_results if not r['req_decrypted'] and not r['resp_decrypted']]
    print(f"成功解密: {len(success)}/{len(decrypt_results)}")
    print(f"解密失败: {len(failed)}/{len(decrypt_results)}")
    
    # 保存结果
    save_results(requests, decrypt_results, secure, nonsecure)
    
    return decrypt_results


def save_results(requests, decrypt_results, secure, nonsecure):
    """保存分析结果到文件"""
    
    # 1. 完整请求概览 (JSON)
    summary = {
        'total': len(requests),
        'secure_count': len(secure),
        'nonsecure_count': len(nonsecure),
        'gateway_key': GATEWAY_KEY,
        'gateway_algorithm': 'AES/CBC/PKCS7Padding',
        'non_secure_requests': [
            {
                'id': r['id'],
                'method': r['method'],
                'url': f"{r['scheme']}://{r['host']}{r['path']}",
                'status': r['status'],
            }
            for r in nonsecure
        ],
        'secure_requests': [
            {
                'id': r['id'],
                'method': r['method'],
                'url': f"{r['scheme']}://{r['host']}{r['path']}",
                'status': r['status'],
                'x_nonce': r['x_nonce'][:20] + '...' if r['x_nonce'] else '',
                'x_version': r['x_version'],
            }
            for r in secure
        ],
        'decrypt_attempts': [
            {
                'id': r['id'],
                'url': r['url'],
                'req_decrypted': r['req_decrypted'],
                'resp_decrypted': r['resp_decrypted'],
            }
            for r in decrypt_results
        ],
    }
    
    with open(f'{OUTPUT_DIR}/summary.json', 'w', encoding='utf-8') as f:
        json.dump(summary, f, ensure_ascii=False, indent=2)
    print(f"\n✅ 概览已保存: {OUTPUT_DIR}/summary.json")
    
    # 2. 解密后的明文内容
    with open(f'{OUTPUT_DIR}/decrypted_bodies.txt', 'w', encoding='utf-8') as f:
        f.write("Gateway 解密结果\n")
        f.write("=" * 70 + "\n\n")
        for r in decrypt_results:
            if r['req_decrypted'] or r['resp_decrypted']:
                f.write(f"\n{'─'*70}\n")
                f.write(f"[{r['id']}] {r['method']} {r['url']}\n\n")
                if r['req_decrypted']:
                    f.write(f"--- 解密请求 ---\n{r['req_decrypted']}\n\n")
                if r['resp_decrypted']:
                    f.write(f"--- 解密响应 ---\n{r['resp_decrypted']}\n\n")
    print(f"✅ 解密内容已保存: {OUTPUT_DIR}/decrypted_bodies.txt")


def main():
    log_path = os.path.join(os.path.dirname(__file__), LOG_FILE)
    if not os.path.exists(log_path):
        # 尝试当前目录
        log_path = LOG_FILE
    
    print(f"📦 正在解析: {log_path}")
    requests = parse_proxyman_log(log_path)
    print(f"✅ 解析完成，共 {len(requests)} 个请求\n")
    
    decrypt_results = analyze_and_decrypt(requests)
    return decrypt_results


if __name__ == '__main__':
    main()
