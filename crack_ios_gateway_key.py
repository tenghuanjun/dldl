#!/usr/bin/env python3
"""
尝试破解 iOS 版本的 Gateway 加密密钥

策略：
1. server-info-service/get-url 响应包含加密的 AppKey/AppSecret
2. 用已知密钥尝试解密该响应
3. 如果能拿到 AppKey/AppSecret，就能得到真正的 Gateway 密钥
4. 然后用该密钥去解密其他请求
"""

import zipfile
import json
import base64
import hashlib
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad

LOG_FILE = "ta.shan-yu-tech.com_07-07-2026-13-53-28.proxymanlogv2"


def get_headers(entry):
    h = entry.get('header')
    if h is None: return {}
    entries = h.get('entries')
    if entries is None: return {}
    return {e['key']['nameInLowercase']: e['value'] for e in entries}


def aes_cbc_decrypt(ciphertext: bytes, key: str, iv: str) -> bytes:
    cipher = AES.new(key.encode('utf-8'), AES.MODE_CBC, iv.encode('utf-8'))
    return unpad(cipher.decrypt(ciphertext), AES.block_size)


def base64_url_decode(s: str) -> bytes:
    s = s.replace('-', '+').replace('_', '/')
    padding = 4 - len(s) % 4
    if padding != 4:
        s += '=' * padding
    return base64.b64decode(s)


def decrypt_response(body_b64: str, req_nonce: str, resp_nonce: str, gateway_key: str) -> bytes:
    """解密 Gateway 响应"""
    key = gateway_key + req_nonce[:8] + resp_nonce[:8]
    iv = resp_nonce[8:24]
    ct = base64_url_decode(body_b64)
    return aes_cbc_decrypt(ct, key, iv)


def decrypt_request_body(body_b64: str, nonce: str, gateway_key: str) -> bytes:
    """解密 Gateway 请求 body"""
    key = gateway_key + nonce[:16]
    iv = nonce[16:32]
    ct = base64_url_decode(body_b64)
    return aes_cbc_decrypt(ct, key, iv)


def decrypt_query(query: str, nonce: str, gateway_key: str) -> bytes:
    """解密 Gateway 加密的查询参数"""
    key = gateway_key + nonce[:16]
    iv = nonce[16:32]
    ct = base64_url_decode(query)
    return aes_cbc_decrypt(ct, key, iv)


def derive_fixed_key(app_key: str, app_secret: str) -> str:
    """
    从 AppKey/AppSecret 推导真正的 Gateway 密钥
    对应 GateWayUtils.getFixedKey()
    """
    from Crypto.Cipher import AES as AES2
    try:
        cipher = AES2.new(app_key.encode('utf-8'), AES2.MODE_CBC, app_key.encode('utf-8'))
        # app_secret 是 Base64 编码的
        decoded = base64.b64decode(app_secret)
        result = cipher.decrypt(decoded)
        # 去除 padding
        result = unpad(result, AES2.block_size)
        return result.decode('utf-8')
    except Exception as e:
        print(f"  derive_fixed_key failed: {e}")
        return None


def main():
    # 候选密钥
    candidate_keys = [
        'soC2GAr8jN2fsbry',       # Android DEFAULT_KEY
        '37wanGameSDK2024',       # 常见变体
        '37iOSGatewayKeyV1',      # iOS 可能密钥
        '37sdkgateway2023',       # 另一变体
    ]
    
    z = zipfile.ZipFile(LOG_FILE)
    
    # === 第1步：获取 server-info-service/get-url 的请求/响应数据 ===
    print("=" * 70)
    print("第1步：分析 server-info-service/get-url（动态密钥下发请求）")
    print("=" * 70)
    
    server_info_data = None
    for name in z.namelist():
        try:
            with z.open(name) as f:
                data = json.loads(f.read())
            if 'request' not in data: continue
            path = data['request'].get('uri', '')
            if 'server-info-service' in path:
                server_info_data = data
                break
        except:
            pass
    
    if not server_info_data:
        print("❌ 未找到 server-info-service 请求")
        return
    
    req = server_info_data['request']
    resp = server_info_data.get('response', {})
    req_h = get_headers(req)
    resp_h = get_headers(resp)
    
    req_nonce = req_h.get('x-request-nonce-str', '')
    resp_nonce = resp_h.get('x-response-nonce-str', '')
    resp_body_b64 = resp.get('bodyData', '')
    query_encrypted = req['uri'].split('?')[-1] if '?' in req['uri'] else ''
    
    print(f"请求 nonce: {req_nonce}")
    print(f"响应 nonce: {resp_nonce}")
    print(f"加密的查询参数: {query_encrypted[:80]}...")
    print(f"响应 body (b64): {resp_body_b64[:80]}...")
    print()
    
    # === 第2步：尝试用候选密钥解密响应 ===
    print("第2步：尝试采用候选密钥解密 server-info 响应...")
    
    found_key = None
    for key in candidate_keys:
        try:
            pt = decrypt_response(resp_body_b64, req_nonce, resp_nonce, key)
            text = pt.decode('utf-8', errors='replace')
            if '{' in text and ('api_infos' in text or 'code' in text):
                print(f'\n✅ 发现有效密钥: "{key}"')
                print(f'   响应内容: {text[:500]}')
                found_key = key
                
                # 解析响应中的 AppKey/AppSecret
                try:
                    resp_json = json.loads(text)
                    api_infos = resp_json.get('api_infos', [])
                    for info in api_infos:
                        if info.get('api_key') == 'x_secure_key':
                            api_info_str = info.get('api_info', '')
                            secure_data = json.loads(api_info_str)
                            app_key = secure_data.get('X-Request-AppKey', '')
                            app_secret = secure_data.get('X-Request-AppSecret', '')
                            x_version = secure_data.get('X-Request-Version', '')
                            
                            print(f'\n   📋 X-Request-AppKey: {app_key}')
                            print(f'   📋 X-Request-AppSecret: {app_secret}')
                            print(f'   📋 X-Request-Version: {x_version}')
                            
                            # 推导真正的 Gateway 密钥
                            real_key = derive_fixed_key(app_key, app_secret)
                            if real_key:
                                print(f'\n   🔑 真正的 Gateway 密钥: "{real_key}"')
                            break
                except Exception as e:
                    print(f'   解析 JSON 失败: {e}')
                break
            else:
                print(f'  ❌ 密钥 "{key}" 解密结果不像是 JSON: {text[:80]}')
        except Exception as e:
            print(f'  ❌ 密钥 "{key}" 解密异常: {e}')
    
    if found_key:
        print(f"\n{'='*70}")
        print(f"✅ iOS Gateway 密钥已确认: {found_key}")
        print(f"{'='*70}")
    else:
        print(f"\n{'='*70}")
        print(f"❌ 所有候选密钥均失败，iOS 和 Android 使用不同的 DEFAULT_KEY")
        print(f"   需要从 iOS IPA 反编译获取真实密钥")
        print(f"{'='*70}")
        
        # 保存失败信息供后续分析
        print("\n保存关键数据到 proxyman_analysis/ios_key_unknown.json")
        import os
        os.makedirs('proxyman_analysis', exist_ok=True)
        with open('proxyman_analysis/ios_key_unknown.json', 'w') as f:
            json.dump({
                'request_nonce': req_nonce,
                'response_nonce': resp_nonce,
                'query_encrypted': query_encrypted,
                'response_body_b64': resp_body_b64,
                'note': 'iOS Gateway 密钥与 Android 不同，需要从 iOS IPA 反编译获取'
            }, f, indent=2)


if __name__ == '__main__':
    main()
