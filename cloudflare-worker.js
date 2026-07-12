// Cloudflare Worker - DLDL API Proxy
// 处理 /api/app-login（SDK 登录 + PC 扫码流程），以及通用代理

import { createCipheriv, randomBytes } from 'node:crypto';
import { Buffer } from 'node:buffer';

// ==================== 配置常量 ====================

const APP_KEY = 'CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1';
const AES_KEY = APP_KEY.substring(0, 16); // 'CR.wdPyFoanb6Thv'
const SDK_LOGIN_URL = 'http://s-api.37.com.cn/sdk/login/';
const PC_HOST = 'app.xxh5.z7xz.com';
const PC_SIGN_KEY = 'pcjgv587!?';
const H5SDK_API_KEY = 'Jp*4Y8vQOYck2*&Z';
const H5SDK_LOGIN_URL = 'https://s-api.37.com.cn/h5sdk/login';
const SDK_GID = '1002997';
const SDK_PID = '1';
const SDK_REFER = '1_1002997_11327_1001';
const SDK_DEV = '9552cfd00bfed7dbd9f8133a0fc9b03e';
const SDK_SVERSION = '3.7.9.6.1';
const SDK_GWVERSION = '4.6.7';
const SDK_FROM = 'android';
const SDK_HOST_SDK_VERSION = '3.7.9.6.1';
const SDK_SCUT = '1';
const GAME_GID = '1003279';
const GAME_PID = '46';
const GAME_APPVER = '134';
const GAME_PLATCODE = '37wan';
const GAME_IMEI = 'DCEADE00-A9B3-42F2-B4EB-8C766C0DD7A4';

// ==================== MD5 (Pure JS) ====================

function md5(string) {
  function rotateLeft(lValue, iShiftBits) {
    return (lValue << iShiftBits) | (lValue >>> (32 - iShiftBits));
  }
  function addUnsigned(lX, lY) {
    const lX4 = lX & 0x40000000;
    const lY4 = lY & 0x40000000;
    const lX8 = lX & 0x80000000;
    const lY8 = lY & 0x80000000;
    const lResult = (lX & 0x3FFFFFFF) + (lY & 0x3FFFFFFF);
    if (lX4 & lY4) return lResult ^ 0x80000000 ^ lX8 ^ lY8;
    if (lX4 | lY4) {
      if (lResult & 0x40000000) return lResult ^ 0xC0000000 ^ lX8 ^ lY8;
      else return lResult ^ 0x40000000 ^ lX8 ^ lY8;
    } else {
      return lResult ^ lX8 ^ lY8;
    }
  }
  function F(x, y, z) { return (x & y) | ((~x) & z); }
  function G(x, y, z) { return (x & z) | (y & (~z)); }
  function H(x, y, z) { return x ^ y ^ z; }
  function I(x, y, z) { return y ^ (x | (~z)); }
  function FF(a, b, c, d, x, s, ac) {
    a = addUnsigned(a, addUnsigned(addUnsigned(F(b, c, d), x), ac));
    return addUnsigned(rotateLeft(a, s), b);
  }
  function GG(a, b, c, d, x, s, ac) {
    a = addUnsigned(a, addUnsigned(addUnsigned(G(b, c, d), x), ac));
    return addUnsigned(rotateLeft(a, s), b);
  }
  function HH(a, b, c, d, x, s, ac) {
    a = addUnsigned(a, addUnsigned(addUnsigned(H(b, c, d), x), ac));
    return addUnsigned(rotateLeft(a, s), b);
  }
  function II(a, b, c, d, x, s, ac) {
    a = addUnsigned(a, addUnsigned(addUnsigned(I(b, c, d), x), ac));
    return addUnsigned(rotateLeft(a, s), b);
  }
  function convertToWordArray(string) {
    // 使用 UTF-8 编码，确保中文字符等非ASCII字符的MD5与Android/Node.js一致
    const utf8Bytes = new TextEncoder().encode(string);
    const lMessageLength = utf8Bytes.length;
    const lNumberOfWordsTemp1 = lMessageLength + 8;
    const lNumberOfWordsTemp2 = (lNumberOfWordsTemp1 - (lNumberOfWordsTemp1 % 64)) / 64;
    const lNumberOfWords = (lNumberOfWordsTemp2 + 1) * 16;
    const lWordArray = new Array(lNumberOfWords - 1);
    let lBytePosition = 0;
    let lByteCount = 0;
    while (lByteCount < lMessageLength) {
      const lWordCount = (lByteCount - (lByteCount % 4)) / 4;
      lBytePosition = (lByteCount % 4) * 8;
      lWordArray[lWordCount] = (lWordArray[lWordCount] | (utf8Bytes[lByteCount] << lBytePosition));
      lByteCount++;
    }
    const lWordCount = (lByteCount - (lByteCount % 4)) / 4;
    lBytePosition = (lByteCount % 4) * 8;
    lWordArray[lWordCount] = lWordArray[lWordCount] | (0x80 << lBytePosition);
    lWordArray[lNumberOfWords - 2] = lMessageLength << 3;
    lWordArray[lNumberOfWords - 1] = lMessageLength >>> 29;
    return lWordArray;
  }
  function wordToHex(lValue) {
    let wordToHexValue = "", wordToHexValueTemp = "", lByte, lCount;
    for (lCount = 0; lCount <= 3; lCount++) {
      lByte = (lValue >>> (lCount * 8)) & 255;
      wordToHexValueTemp = "0" + lByte.toString(16);
      wordToHexValue = wordToHexValue + wordToHexValueTemp.substr(wordToHexValueTemp.length - 2, 2);
    }
    return wordToHexValue;
  }

  const x = convertToWordArray(string);
  let a = 0x67452301, b = 0xEFCDAB89, c = 0x98BADCFE, d = 0x10325476;
  for (let k = 0; k < x.length; k += 16) {
    const AA = a, BB = b, CC = c, DD = d;
    a = FF(a, b, c, d, x[k + 0], 7, 0xD76AA478);
    d = FF(d, a, b, c, x[k + 1], 12, 0xE8C7B756);
    c = FF(c, d, a, b, x[k + 2], 17, 0x242070DB);
    b = FF(b, c, d, a, x[k + 3], 22, 0xC1BDCEEE);
    a = FF(a, b, c, d, x[k + 4], 7, 0xF57C0FAF);
    d = FF(d, a, b, c, x[k + 5], 12, 0x4787C62A);
    c = FF(c, d, a, b, x[k + 6], 17, 0xA8304613);
    b = FF(b, c, d, a, x[k + 7], 22, 0xFD469501);
    a = FF(a, b, c, d, x[k + 8], 7, 0x698098D8);
    d = FF(d, a, b, c, x[k + 9], 12, 0x8B44F7AF);
    c = FF(c, d, a, b, x[k + 10], 17, 0xFFFF5BB1);
    b = FF(b, c, d, a, x[k + 11], 22, 0x895CD7BE);
    a = FF(a, b, c, d, x[k + 12], 7, 0x6B901122);
    d = FF(d, a, b, c, x[k + 13], 12, 0xFD987193);
    c = FF(c, d, a, b, x[k + 14], 17, 0xA679438E);
    b = FF(b, c, d, a, x[k + 15], 22, 0x49B40821);
    a = GG(a, b, c, d, x[k + 1], 5, 0xF61E2562);
    d = GG(d, a, b, c, x[k + 6], 9, 0xC040B340);
    c = GG(c, d, a, b, x[k + 11], 14, 0x265E5A51);
    b = GG(b, c, d, a, x[k + 0], 20, 0xE9B6C7AA);
    a = GG(a, b, c, d, x[k + 5], 5, 0xD62F105D);
    d = GG(d, a, b, c, x[k + 10], 9, 0x2441453);
    c = GG(c, d, a, b, x[k + 15], 14, 0xD8A1E681);
    b = GG(b, c, d, a, x[k + 4], 20, 0xE7D3FBC8);
    a = GG(a, b, c, d, x[k + 9], 5, 0x21E1CDE6);
    d = GG(d, a, b, c, x[k + 14], 9, 0xC33707D6);
    c = GG(c, d, a, b, x[k + 3], 14, 0xF4D50D87);
    b = GG(b, c, d, a, x[k + 8], 20, 0x455A14ED);
    a = GG(a, b, c, d, x[k + 13], 5, 0xA9E3E905);
    d = GG(d, a, b, c, x[k + 2], 9, 0xFCEFA3F8);
    c = GG(c, d, a, b, x[k + 7], 14, 0x676F02D9);
    b = GG(b, c, d, a, x[k + 12], 20, 0x8D2A4C8A);
    a = HH(a, b, c, d, x[k + 5], 4, 0xFFFA3942);
    d = HH(d, a, b, c, x[k + 8], 11, 0x8771F681);
    c = HH(c, d, a, b, x[k + 11], 16, 0x6D9D6122);
    b = HH(b, c, d, a, x[k + 14], 23, 0xFDE5380C);
    a = HH(a, b, c, d, x[k + 1], 4, 0xA4BEEA44);
    d = HH(d, a, b, c, x[k + 4], 11, 0x4BDECFA9);
    c = HH(c, d, a, b, x[k + 7], 16, 0xF6BB4B60);
    b = HH(b, c, d, a, x[k + 10], 23, 0xBEBFBC70);
    a = HH(a, b, c, d, x[k + 13], 4, 0x289B7EC6);
    d = HH(d, a, b, c, x[k + 0], 11, 0xEAA127FA);
    c = HH(c, d, a, b, x[k + 3], 16, 0xD4EF3085);
    b = HH(b, c, d, a, x[k + 6], 23, 0x4881D05);
    a = HH(a, b, c, d, x[k + 9], 4, 0xD9D4D039);
    d = HH(d, a, b, c, x[k + 12], 11, 0xE6DB99E5);
    c = HH(c, d, a, b, x[k + 15], 16, 0x1FA27CF8);
    b = HH(b, c, d, a, x[k + 2], 23, 0xC4AC5665);
    a = II(a, b, c, d, x[k + 0], 6, 0xF4292244);
    d = II(d, a, b, c, x[k + 7], 10, 0x432AFF97);
    c = II(c, d, a, b, x[k + 14], 15, 0xAB9423A7);
    b = II(b, c, d, a, x[k + 5], 21, 0xFC93A039);
    a = II(a, b, c, d, x[k + 12], 6, 0x655B59C3);
    d = II(d, a, b, c, x[k + 3], 10, 0x8F0CCC92);
    c = II(c, d, a, b, x[k + 10], 15, 0xFFEFF47D);
    b = II(b, c, d, a, x[k + 1], 21, 0x85845DD1);
    a = II(a, b, c, d, x[k + 8], 6, 0x6FA87E4F);
    d = II(d, a, b, c, x[k + 15], 10, 0xFE2CE6E0);
    c = II(c, d, a, b, x[k + 6], 15, 0xA3014314);
    b = II(b, c, d, a, x[k + 13], 21, 0x4E0811A1);
    a = II(a, b, c, d, x[k + 4], 6, 0xF7537E82);
    d = II(d, a, b, c, x[k + 11], 10, 0xBD3AF235);
    c = II(c, d, a, b, x[k + 2], 15, 0x2AD7D2BB);
    b = II(b, c, d, a, x[k + 9], 21, 0xEB86D391);
    a = addUnsigned(a, AA);
    b = addUnsigned(b, BB);
    c = addUnsigned(c, CC);
    d = addUnsigned(d, DD);
  }
  return (wordToHex(a) + wordToHex(b) + wordToHex(c) + wordToHex(d)).toLowerCase();
}

// ==================== AES-128-ECB ====================

/**
 * AES-128-ECB 加密
 */
function aes128EcbEncrypt(plaintext, keyStr) {
  const cipher = createCipheriv('aes-128-ecb', Buffer.from(keyStr, 'utf8'), Buffer.alloc(0));
  cipher.setAutoPadding(true);
  let encrypted = cipher.update(plaintext, 'utf8', 'base64');
  encrypted += cipher.final('base64');
  return encrypted;
}

// ==================== SDK Gateway 请求体加密 ====================
// 反编译自 sq_plugin → GateWayEncryptInterceptor + GateWayUtils

const GATEWAY_DEFAULT_KEY = 'soC2GAr8jN2fsbry'; // GateWayManager 硬编码默认密钥
const GATEWAY_XVERSION = '1';
const SECURE_BASE = 'https://s-api-secure.37.com.cn';
const SDK_APIX_BASE = 'https://sdk-apix-secure.37.com.cn';

/** 生成随机 hex 字符串 */
function randomHex(len) { return randomBytes(Math.ceil(len/2)).toString('hex').slice(0, len); }

/** 模拟 Request-Id 生成（格式: platform-pid-timestamp-random） */
function generateSecRequestId() { return 'android-' + SDK_PID + '-' + Date.now() + '-' + randomHex(16); }
/** 生成 Request-LiveId（格式: platform-pid-timestamp-random） */
function generateLiveId() { return 'Android-' + SDK_PID + '-' + Date.now() + '-' + randomHex(16).toUpperCase(); }

/** MD5 URL-safe Base64 编解码 */
function toBase64Url(b64) { return b64.replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/g, ''); }
function fromBase64Url(s) {
  s = s.replace(/-/g, '+').replace(/_/g, '/');
  while (s.length % 4) s += '=';
  return s;
}

/** AES-CBC 加密 → URL-safe Base64（key=32字节，用AES-256） */
function gatewayEncrypt(plaintext, key, iv) {
  const cipher = createCipheriv('aes-256-cbc', Buffer.from(key, 'utf8'), Buffer.from(iv, 'utf8'));
  let enc = cipher.update(plaintext, 'utf8', 'base64');
  enc += cipher.final('base64');
  return toBase64Url(enc);
}

/** 生成 Nonce-Str: MD5(method + query + body + cookie + auth + xRequestId) */
function buildNonceStr(method, query, body, xRequestId) {
  return md5((method || '') + (query || '') + (body || '') + xRequestId);
}

/** 发加密请求到 s-api-secure 的端点 */
async function securePost(path, params, bodyObj, optKey) {
  const reqId = generateSecRequestId();
  const xRequestId = md5(reqId);
  const formBody = Object.keys(params).map(k => encodeURIComponent(k) + '=' + encodeURIComponent(String(params[k]))).join('&');
  const nonce = buildNonceStr('POST', '', formBody, xRequestId);
  const baseKey = optKey || GATEWAY_DEFAULT_KEY;
  const encKey = baseKey + nonce.substring(0, 16);
  const iv = nonce.substring(nonce.length - 16);
  const encBody = gatewayEncrypt(formBody, encKey, iv);

  const resp = await fetch(SECURE_BASE + path, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      'Accept': '*/*',
      'X-Request-Id': xRequestId,
      'X-Request-Nonce-Str': nonce,
      'X-Request-Version': GATEWAY_XVERSION,
      'Request-LiveId': generateLiveId(),
      'User-Agent': 'H5App/136 (com.m37.dldlsy.sy37; build:136; Android 10) Dalvik/2.1.0',
    },
    body: encBody,
  });

  console.log('[secure]', path, 'HTTP', resp.status, 'respNonce:', (resp.headers.get('x-response-nonce-str')||'').slice(0,10)+'...');

  // 解密响应
  const respBody = await resp.text();
  const respNonce = resp.headers.get('x-response-nonce-str') || '';
  if (respNonce) {
    const dKey = baseKey + nonce.substring(0, 8) + respNonce.substring(0, 8);
    const dIv = respNonce.substring(8, 24);
    const b64Body = fromBase64Url(respBody);
    try {
      const decipher = createCipheriv('aes-256-cbc', Buffer.from(dKey, 'utf8'), Buffer.from(dIv, 'utf8'));
      decipher.setAutoPadding(true);
      let decrypted = decipher.update(b64Body, 'base64', 'utf8');
      decrypted += decipher.final('utf8');
      return { status: resp.status, body: decrypted };
    } catch (_) {
      return { status: resp.status, body: respBody };
    }
  }
  return { status: resp.status, body: respBody };
}

/** 发加密 POST 请求并解析 JSON */
async function securePostJson(path, params, optKey) {
  const result = await securePost(path, params, null, optKey);
  return { status: result.status, json: safeJsonParse(result.body) };
}

/** 发加密 GET 请求（query参数加密） */
async function secureGet(path, queryParams, optKey) {
  const reqId = generateSecRequestId();
  const xRequestId = md5(reqId);
  const plainQuery = Object.keys(queryParams).map(k => encodeURIComponent(k) + '=' + encodeURIComponent(String(queryParams[k]))).join('&');
  const nonce = buildNonceStr('GET', plainQuery, '', xRequestId);
  const baseKey = optKey || GATEWAY_DEFAULT_KEY;
  const encKey = baseKey + nonce.substring(0, 16);
  const iv = nonce.substring(nonce.length - 16);
  const encQuery = gatewayEncrypt(plainQuery, encKey, iv);

  const resp = await fetch(SDK_APIX_BASE + path + '?' + encQuery, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      'Accept': '*/*',
      'X-Request-Id': xRequestId,
      'X-Request-Nonce-Str': nonce,
      'X-Request-Version': GATEWAY_XVERSION,
      'Request-LiveId': generateLiveId(),
      'User-Agent': 'H5App/136 (com.m37.dldlsy.sy37; build:136; Android 10) Dalvik/2.1.0',
    },
  });

  console.log('[secureGet]', path, 'HTTP', resp.status);

  const respBody = await resp.text();
  const respNonce = resp.headers.get('x-response-nonce-str') || '';
  if (respNonce) {
    const dKey = baseKey + nonce.substring(0, 8) + respNonce.substring(0, 8);
    const dIv = respNonce.substring(8, 24);
    const b64Body = fromBase64Url(respBody);
    try {
      const decipher = createCipheriv('aes-256-cbc', Buffer.from(dKey, 'utf8'), Buffer.from(dIv, 'utf8'));
      decipher.setAutoPadding(true);
      let decrypted = decipher.update(b64Body, 'base64', 'utf8');
      decrypted += decipher.final('utf8');
      return { status: resp.status, body: decrypted };
    } catch (_) {
      return { status: resp.status, body: respBody };
    }
  }
  return { status: resp.status, body: respBody };
}

/** 从 get-url 响应中解密动态密钥 (getFixedKey) */
function computeDynamicKey(jsonResp) {
  let json;
  try { json = JSON.parse(jsonResp); } catch (_) { console.log('[computeDynamicKey] JSON解析失败'); return null; }
  
  // findSecureKey: 在 api_infos 数组中找 api_key == "x_secure_key"
  const apiInfos = json.api_infos;
  if (!apiInfos || !Array.isArray(apiInfos)) {
    console.log('[computeDynamicKey] 无 api_infos 数组');
    return null;
  }
  
  for (const item of apiInfos) {
    if (item.api_key === 'x_secure_key' && item.api_info) {
      let secJson;
      try { secJson = JSON.parse(item.api_info); } catch (_) { continue; }
      
      const appKey = secJson['X-Request-AppKey'];
      const appSecret = secJson['X-Request-AppSecret'];
      if (!appKey || !appSecret) continue;
      
      console.log('[computeDynamicKey] appKey:', appKey.slice(0, 30), 'len:', appKey.length);
      
      // getFixedKey = decryptDefault(appSecret, appKey, iv=appKey.getBytes())
      // Java: new SecretKeySpec(appKey.getBytes(), "AES") → 根据key长度选AES-128/192/256
      const keyBytes = Buffer.from(appKey, 'utf8');
      const algo = keyBytes.length === 32 ? 'aes-256-cbc' : keyBytes.length === 24 ? 'aes-192-cbc' : 'aes-128-cbc';
      // Base64.decode(cipherText, 0) → DEFAULT mode (标准base64)
      const secret = Buffer.from(String(appSecret), 'base64');
      
      try {
        const decipher = createDecipheriv(algo, keyBytes, keyBytes);
        decipher.setAutoPadding(true);
        let decrypted = Buffer.concat([decipher.update(secret), decipher.final()]);
        const result = decrypted.toString('utf8');
        console.log('[computeDynamicKey] ✅ 密钥解密成功, len:', result.length, 'algo:', algo);
        return result;
      } catch (e) {
        console.log('[computeDynamicKey] 解密失败:', e.message, 'algo:', algo);
        return null;
      }
    }
  }
  
  console.log('[computeDynamicKey] 未找到 x_secure_key');
  return null;
}

/** 获取动态加密密钥（get-url → 解析 → 返回key） */
let cachedDynamicKey = null;
let debugInfo = {};
async function getDynamicKey() {
  if (cachedDynamicKey) { debugInfo.keySource = 'cached'; return cachedDynamicKey; }

  const params = {
    gid: GAME_GID, pid: GAME_PID, refer: SDK_REFER,
    version: '1.0.0', time: String(Math.floor(Date.now() / 1000)),
    dev: SDK_DEV, oaid: '', sversion: SDK_SVERSION,
    gwversion: '4.6.7', is_root: '0', is_simulator: '0',
  };
  params.sign = signV3(params, APP_KEY);

  debugInfo.getUrlStatus = 'sending';
  console.log('[getDynamicKey] 请求 get-url...');
  const result = await secureGet('/server-info-service/get-url', params, GATEWAY_DEFAULT_KEY);
  debugInfo.getUrlStatus = result.status;
  
  if (result.status === 200 && result.body) {
    debugInfo.getUrlBodyLen = result.body.length;
    const dynamicKey = computeDynamicKey(result.body);
    if (dynamicKey) {
      console.log('[getDynamicKey] ✅ 动态密钥获取成功');
      debugInfo.keySource = 'get-url';
      cachedDynamicKey = dynamicKey;
      return dynamicKey;
    }
    debugInfo.keyError = 'computeDynamicKey failed';
    debugInfo.getUrlBody = result.body.slice(0, 300);
    console.log('[getDynamicKey] 解析密钥失败');
  } else {
    debugInfo.keyError = 'HTTP ' + result.status;
    debugInfo.getUrlBody = String(result.body || '').slice(0, 200);
    console.log('[getDynamicKey] HTTP', result.status);
  }
  return null;
}

/** 修改 securePost 支持可选密钥参数 */

// ==================== 签名算法 ====================

/**
 * signV3: MD5(sorted(key=value) + appKey)
 */
function signV3(params, appKey) {
  const sortedKeys = Object.keys(params).filter(k => k !== 'sign').sort();
  let str = '';
  for (const key of sortedKeys) str += key + '=' + params[key];
  return md5(str + appKey);
}

/**
 * PC getId/getCodeInfo sign
 */
function pcSign(time) {
  return md5(String(time) + PC_SIGN_KEY);
}
function pcCodeInfoSign(sessionId, time) {
  return md5(String(sessionId) + String(time) + PC_SIGN_KEY);
}

/**
 * h5sdk 签名
 */
function h5sdkSign(params, apiKey) {
  const sorted = Object.keys(params).sort();
  let str = '';
  for (const k of sorted) str += k + '=' + params[k];
  return md5(str + apiKey);
}

// ==================== HTTP 辅助 ====================

function corsHeaders(extra = {}) {
  return {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type',
    ...extra,
  };
}

function jsonResponse(data, status = 200) {
  return new Response(JSON.stringify(data), {
    status,
    headers: corsHeaders({ 'Content-Type': 'application/json' }),
  });
}

function safeJsonParse(text) {
  try { return JSON.parse(text); } catch (_) { return null; }
}

// ==================== API 调用 ====================

/**
 * SDK 登录：POST s-api.37.com.cn/sdk/login/
 */
async function sdkLogin(uname, encryptedPwd) {
  const timestamp = String(Math.floor(Date.now() / 1000));
  const params = {
    uname,
    upwd: encryptedPwd,
    signType: 'all',
    display_name: '斗罗大陆',
    trans_info: '',
    pid: SDK_PID,
    gid: SDK_GID,
    refer: SDK_REFER,
    dev: SDK_DEV,
    sversion: SDK_SVERSION,
    version: '1.0.0',
    gwversion: SDK_GWVERSION,
    time: timestamp,
    scut: SDK_SCUT,
    oaid: '',
    from: SDK_FROM,
    host_sdk_version: SDK_HOST_SDK_VERSION,
    is_root: '0',
    is_simulator: '0',
  };
  params.sign = signV3(params, APP_KEY);

  const formBody = Object.keys(params)
    .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
    .join('&');

  console.log('[app-login] SDK登录请求:', uname);

  const body = await safeFetch(SDK_LOGIN_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      'User-Agent': 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)',
    },
    body: formBody,
  });

  const json = safeJsonParse(body);
  if (!json) throw new Error('SDK 登录响应解析失败: ' + body.slice(0, 200));

  console.log('[app-login] SDK响应 state=' + json.state + ', uid=' + (json.data && json.data.uid));
  return json;
}

/**
 * PC getId：获取扫码会话 ID
 */
async function pcGetId() {
  const time = Date.now();
  const sign = pcSign(time);
  const url = `https://${PC_HOST}/pc/getId?time=${time}&sign=${sign}`;

  const body = await safeFetch(url, {
    headers: { 'User-Agent': '37MobileGame/4.6.7 (Android)' },
  });

  const json = safeJsonParse(body);
  if (!json || json.state !== 1 || !json.data) {
    throw new Error('pc/getId 失败: ' + body.slice(0, 120));
  }
  return String(json.data);
}

/**
 * h5sdk/login（直连模式）：获取游戏入口 token/sign
 */
async function h5sdkLogin(uname, upwd) {
  const time = '4129596000';
  const params = {
    uname,
    upwd,
    autoLogin: 'true',
    pid: GAME_PID,
    gid: GAME_GID,
    sversion: 'undefined',
    version: '1.0.4',
    time,
    dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
    os: 'iOS',
    over: '18.5',
  };
  const sign = h5sdkSign(params, H5SDK_API_KEY);
  const qs = new URLSearchParams({ ...params, sign }).toString();
  const url = H5SDK_LOGIN_URL + '?' + qs;

  console.log('[app-login] h5sdk/login 请求...');

  const text = await safeFetch(url, {
    headers: { 'User-Agent': 'Mozilla/5.0' },
  });

  const match = text.match(/^callback\(([\s\S]+)\);?$/);
  const jsonText = match ? match[1] : text;
  const payload = safeJsonParse(jsonText);
  if (!payload || payload.state !== 1 || !payload.data || !payload.data.token) {
    throw new Error('h5sdk/login 失败: ' + (payload && payload.msg || text.slice(0, 200)));
  }
  const result = {
    token: String(payload.data.token),
    time: String(payload.data.time || time),
    sign: String(payload.data.sign || sign),
  };
  console.log('[app-login] h5sdk/login 成功');
  return result;
}

/**
 * PC postCodeInfo：写入游戏入口参数
 */
async function pcPostCodeInfo(sessionId, gameParams) {
  const dataJson = JSON.stringify(gameParams);
  const now = Date.now();
  const sign = pcCodeInfoSign(sessionId, now);
  const formBody =
    'id=' + encodeURIComponent(sessionId) +
    '&data=' + encodeURIComponent(dataJson) +
    '&time=' + now +
    '&sign=' + sign;

  console.log('[app-login] postCodeInfo 写入参数: gid=' + gameParams.gid);

  const body = await safeFetch('https://' + PC_HOST + '/pc/postCodeInfo', {
    method: 'POST',
    headers: {
      'User-Agent': '37MobileGame/4.6.7 (Android)',
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: formBody,
  });
  const json = safeJsonParse(body);
  if (!json || json.state !== 1) {
    throw new Error('pc/postCodeInfo 失败: ' + body.slice(0, 200));
  }
  console.log('[app-login] postCodeInfo 成功');
  return true;
}

/**
 * PC getCodeInfo：取回游戏入口参数
 */
async function pcGetCodeInfo(sessionId) {
  const time = Date.now();
  const sign = pcCodeInfoSign(sessionId, time);
  const url = `https://${PC_HOST}/pc/getCodeInfo?id=${encodeURIComponent(sessionId)}&time=${time}&sign=${sign}`;

  const body = await safeFetch(url, {
    headers: { 'User-Agent': '37MobileGame/4.6.7 (Android)' },
  });

  const json = safeJsonParse(body);
  if (!json || json.state !== 1 || !json.data) {
    throw new Error('pc/getCodeInfo 失败: ' + body.slice(0, 120));
  }
  console.log('[app-login] getCodeInfo 成功: gid=' + json.data.gid + ', pid=' + json.data.pid);
  return json.data;
}

// ==================== 请求缓存 ====================

const sdkLoginCache = new Map();
const h5sdkCache = new Map();
const CACHE_SDK_TTL = 10 * 60 * 1000;
const CACHE_H5SDK_TTL = 5 * 60 * 1000;

function getCache(cache, key, ttl) {
  const entry = cache.get(key);
  if (entry && entry.expireAt > Date.now()) return entry.data;
  cache.delete(key);
  return null;
}

function setCache(cache, key, data, ttl) {
  cache.set(key, { data, expireAt: Date.now() + ttl });
  if (cache.size > 50) cache.delete(cache.keys().next().value);
}

async function safeFetch(url, options) {
  const resp = await fetch(url, options);
  const text = await resp.text();
  if (resp.status !== 200) throw new Error(url.split('/')[2] + ' HTTP ' + resp.status + ': ' + text.slice(0, 100));
  return text;
}

async function handlePassCode(request) {
  let body;
  try { body = await request.json(); } catch (_) {
    return jsonResponse({ ok: false, message: '请求体必须是 JSON' }, 400);
  }

  const sessionId = String(body.sessionId || body.code || '').trim();
  // 桌面端逻辑：优先使用已存储的 token/sign
  const passToken = String(body.token || '').trim();
  const passSign = String(body.sign || '').trim();
  const uname = String(body.uname || '').trim();
  const upwd = String(body.upwd || '').trim();

  if (!sessionId) return jsonResponse({ ok: false, message: '缺少通行证码(sessionId)' }, 400);

  try {
    console.log('[pass-code] 开始处理:', uname || '(token mode)', 'sessionId:', sessionId.slice(0, 10) + '...');

    // 1. SDK 登录（尝试获取 uid，失败不影响主流程）
    let sdkUid = '';
    if (uname && upwd) {
      try {
        const pwd = aes128EcbEncrypt(upwd, AES_KEY);
        const r = await sdkLogin(uname, pwd);
        if (r.state === 1 && r.data) sdkUid = r.data.uid || '';
      } catch (_) { /* SDK登录非必须 */ }
    }

    // 2. 写入通行证（与桌面端 sendPassCode 逻辑一致）
    if (passToken && passSign) {
      // 直接使用已有的 token/sign（桌面端逻辑）
      await pcPostCodeInfo(sessionId, {
        gid: GAME_GID, pid: GAME_PID, token: passToken,
        time: Math.floor(Date.now() / 1000), sign: passSign,
        appVer: GAME_APPVER, platCode: GAME_PLATCODE, IMEI: GAME_IMEI,
      });
    } else if (uname && upwd) {
      // 兜底：重新登录获取 token/sign
      const h5sdkInfo = await h5sdkLogin(uname, upwd);
      await pcPostCodeInfo(sessionId, {
        gid: GAME_GID, pid: GAME_PID, token: h5sdkInfo.token,
        time: h5sdkInfo.time, sign: h5sdkInfo.sign,
        appVer: GAME_APPVER, platCode: GAME_PLATCODE, IMEI: GAME_IMEI,
      });
    } else {
      return jsonResponse({ ok: false, message: '缺少 token/sign 或账号密码' }, 400);
    }

    console.log('[pass-code] ✅ 完成:', uname || '(token)', 'uid:', sdkUid);
    return jsonResponse({ ok: true, state: 1, uid: sdkUid, message: '通行证验证成功' });
  } catch (error) {
    console.error('[pass-code] 失败:', error.message);
    return jsonResponse({ ok: false, message: error.message }, 500);
  }
}

// ==================== 登录核心（Cron 和 HTTP handler 共用） ====================

async function doLoginCore(uname, upwd) {
  console.log('[login] 开始:', uname);
  let sdkUid = '', sdkUname = '';
  try {
    const pwd = aes128EcbEncrypt(upwd, AES_KEY);
    const r = await sdkLogin(uname, pwd);
    if (r.state === 1 && r.data) {
      sdkUid = r.data.uid || '';
      sdkUname = r.data.uname || '';
    }
  } catch (_) { /* SDK登录非必须 */ }

  const sessionId = await pcGetId();
  console.log('[login] sessionId:', sessionId.slice(0, 10) + '...');

  const h5sdkInfo = await h5sdkLogin(uname, upwd);
  await pcPostCodeInfo(sessionId, {
    gid: GAME_GID, pid: GAME_PID, token: h5sdkInfo.token,
    time: h5sdkInfo.time, sign: h5sdkInfo.sign,
    appVer: GAME_APPVER, platCode: GAME_PLATCODE, IMEI: GAME_IMEI,
  });

  const entryParams = await pcGetCodeInfo(sessionId);

  return {
    ok: true, state: 1,
    uid: sdkUid || '', uname: sdkUname || uname,
    token: entryParams.token, sign: entryParams.sign,
    entryTime: entryParams.time,
    appVer: entryParams.appVer || GAME_APPVER,
    platCode: entryParams.platCode || GAME_PLATCODE,
    IMEI: entryParams.IMEI || GAME_IMEI,
    entryGid: entryParams.gid || GAME_GID,
    entryPid: entryParams.pid || GAME_PID,
    _debug: { flow: 'h5sdk' },
  };
}

// ==================== /api/app-login ====================

async function handleAppLogin(request) {
  let body;
  try { body = await request.json(); } catch (_) { return jsonResponse({ ok: false, message: '请求体必须是 JSON' }, 400); }

  const uname = String(body.uname || '').trim();
  const upwd = String(body.upwd || '').trim();
  if (!uname || !upwd) return jsonResponse({ ok: false, message: '缺少 uname 或 upwd' }, 400);

  try {
    const result = await doLoginCore(uname, upwd);
    console.log('[app-login] ✅ 完成:', uname);
    return jsonResponse(result);
  } catch (error) {
    console.error('[app-login] 失败:', error.message);
    return jsonResponse({ ok: false, message: error.message }, 500);
  }
}

// ==================== 定时刷新过期账号 ====================

const SUPABASE_URL = 'https://xywlbjsyhpyyxboznmct.supabase.co';

async function refreshExpiredAccounts(env) {
  const SUPABASE_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh5d2xianN5aHB5eXhib3pubWN0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkwODgwNzIsImV4cCI6MjA5NDY2NDA3Mn0.Q0KzoMgwNInH4gi30DEK_d1NbZCwl5yFjnTjubm_gYs';
  console.log('[cron] 开始扫描过期账号...');
  const results = { scanned: 0, refreshed: 0, errors: 0, detail: [] };

  try {
    // 查全量有URL的账号，按创建时间升序（最旧的优先）
    let resp = await fetch(SUPABASE_URL + '/rest/v1/accounts?select=id,uname,upwd,url,url_created_at&url=not.is.null&order=url_created_at.asc', {
      headers: { 'apikey': SUPABASE_KEY, 'Authorization': 'Bearer ' + SUPABASE_KEY }
    });
    if (!resp.ok) { console.log('[cron] 查询失败:', resp.status); return results; }

    const accounts = await resp.json();
    results.scanned = accounts.length;

    // 筛选过期账号（>=60h），按最旧优先
    const expired = [];
    for (const a of accounts) {
      const t = a.url_created_at ? new Date(a.url_created_at) : null;
      const ageH = t && !isNaN(t.getTime()) ? (Date.now() - t.getTime()) / 3600000 : -1;
      if (ageH >= 60 && a.uname && a.upwd) expired.push({ acc: a, ageH });
    }
    console.log('[cron] 过期账号:', expired.length, '→ 分发给 dlapi Worker 并行处理');
    results.detail.push({ totalWithUrl: accounts.length, totalExpired: expired.length });

    if (expired.length === 0) return results;

    // 分批发给 dlapi Worker 并行刷新（每个 Worker 处理 7 个）
    const BATCH_SIZE = 7;
    const DLAPI_COUNT = 99;  // 使用全部 dlapi-1 ~ dlapi-99
    const batches = [];
    for (let i = 0; i < expired.length; i += BATCH_SIZE) {
      batches.push(expired.slice(i, i + BATCH_SIZE));
    }

    const workerResults = await Promise.all(
      batches.map((batch, idx) => {
        const preferredWorker = (idx % DLAPI_COUNT) + 1;
        return dispatchToDlapi(batch.map(b => ({ id: b.acc.id, uname: b.acc.uname, upwd: b.acc.upwd })), preferredWorker, 3, 'cron');
      })
    );

    for (let wi = 0; wi < workerResults.length; wi++) {
      const wr = workerResults[wi];
      if (wr.error) {
        results.errors += batches[wi].length;
        results.detail.push({ batch: wi + 1, error: wr.error });
      }
      if (typeof wr.refreshed === 'number') results.refreshed += wr.refreshed;
      if (typeof wr.errors === 'number') results.errors += wr.errors;
      if (wr.detail && Array.isArray(wr.detail)) results.detail.push(...wr.detail.slice(0, 5));
    }

    // 兜底：如果全部分发失败，主 Worker 亲自刷新前 7 个
    if (results.refreshed === 0 && results.errors > 0 && expired.length > 0) {
      console.log('[cron] 分发全部失败，主 Worker 兜底刷新前7个');
      const fallback = expired.slice(0, 7);
      for (const { acc } of fallback) {
        try {
          const result = await doLoginCore(acc.uname, acc.upwd);
          if (result.ok && result.token) {
            await fetch(SUPABASE_URL + '/rest/v1/accounts?id=eq.' + acc.id, {
              method: 'PATCH',
              headers: { 'apikey': SUPABASE_KEY, 'Authorization': 'Bearer ' + SUPABASE_KEY, 'Content-Type': 'application/json' },
              body: JSON.stringify({
                url: 'https://dldl.50pk.com/login.php?' + new URLSearchParams({
                  gid: result.entryGid || '1003279', pid: result.entryPid || '46',
                  token: result.token, time: result.entryTime || '', sign: result.sign,
                  appVer: result.appVer || '134', platCode: result.platCode || '37wan',
                  IMEI: result.IMEI || '', isPcLauncher: 'true'
                }).toString(),
                app_token: result.token, app_sign: result.sign,
                url_created_at: new Date().toISOString()
              })
            });
            results.refreshed++;
          } else {
            results.errors++;
          }
        } catch (e) { results.errors++; }
        await new Promise(r => setTimeout(r, 3000));
      }
    }
  } catch (e) {
    console.log('[cron] 异常:', e.message);
  }

  console.log('[cron] 完成:', results.scanned, '扫描', results.refreshed, '刷新', results.errors, '错误');
  return results;
}

// ===== 通用：向 dlapi Worker 派发一批账号（带重试/fallback） =====
// batch: [{id, uname, upwd}], preferredWorker: 首选 worker 编号, maxRetries: 最多尝试的 Worker 数量
async function dispatchToDlapi(batch, preferredWorker, maxRetries, label) {
  const DLAPI_COUNT = 99;
  let lastError = null;
  for (let attempt = 0; attempt < maxRetries; attempt++) {
    const workerNum = ((preferredWorker - 1 + attempt) % DLAPI_COUNT) + 1;
    const workerUrl = `https://dlapi-${workerNum}.tenghuanjun.workers.dev/api/batch-refresh`;
    console.log(`[${label}] → dlapi-${workerNum} (批${batch.length}个${attempt > 0 ? ', 重试' + attempt : ''})`);
    try {
      const r = await fetch(workerUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ accounts: batch.map(b => ({ id: b.id, uname: b.uname, upwd: b.upwd })) })
      });
      const text = await r.text();
      try { return JSON.parse(text); }
      catch (_) {
        lastError = text.slice(0, 200);
        console.log(`[${label}] dlapi-${workerNum} 返回非JSON:`, lastError);
      }
    } catch (e) {
      lastError = e.message;
      console.log(`[${label}] dlapi-${workerNum} fetch失败:`, e.message);
    }
    // 本 Worker 失败，换下一个重试
    await new Promise(resolve => setTimeout(resolve, 500));
  }
  // 全部尝试失败
  console.log(`[${label}] 全部${maxRetries}个Worker失败:`, lastError);
  return { error: lastError || 'unknown' };
}

// ===== 通用：将一批账号分发到 dlapi Worker 并发刷新（含兜底） =====
// accounts: [{id, uname, upwd}], 由调用方决定筛选条件
async function dispatchAndRefresh(accounts, label) {
  const results = { refreshed: 0, errors: 0, detail: [] };
  if (!accounts || accounts.length === 0) return results;

  const BATCH_SIZE = 7;
  const DLAPI_COUNT = 99;
  const batches = [];
  for (let i = 0; i < accounts.length; i += BATCH_SIZE) {
    batches.push(accounts.slice(i, i + BATCH_SIZE));
  }

  const workerResults = await Promise.all(
    batches.map((batch, idx) => {
      const preferredWorker = (idx % DLAPI_COUNT) + 1;
      return dispatchToDlapi(batch.map(b => ({ id: b.id, uname: b.uname, upwd: b.upwd })), preferredWorker, 3, label);
    })
  );

  for (let wi = 0; wi < workerResults.length; wi++) {
    const wr = workerResults[wi];
    if (wr.error) {
      results.errors += batches[wi].length;
      results.detail.push({ batch: wi + 1, error: wr.error });
    }
    if (typeof wr.refreshed === 'number') results.refreshed += wr.refreshed;
    if (typeof wr.errors === 'number') results.errors += wr.errors;
    if (wr.detail && Array.isArray(wr.detail)) results.detail.push(...wr.detail.slice(0, 10));
  }

  // 兜底：如果所有 dlapi Worker 都失败了，主 Worker 亲自刷
  if (results.refreshed === 0 && results.errors > 0 && accounts.length > 0) {
    console.log('[' + label + '] dlapi 全部分发失败，主 Worker 兜底直接刷新 ' + accounts.length + ' 个账号');
    results.refreshed = 0;
    results.errors = 0;
    results.detail = [];
    const SUPABASE_KEY_FALLBACK = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh5d2xianN5aHB5eXhib3pubWN0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkwODgwNzIsImV4cCI6MjA5NDY2NDA3Mn0.Q0KzoMgwNInH4gi30DEK_d1NbZCwl5yFjnTjubm_gYs';
    for (const acc of accounts) {
      try {
        const result = await doLoginCore(acc.uname, acc.upwd);
        if (result.ok && result.token) {
          await fetch(SUPABASE_URL + '/rest/v1/accounts?id=eq.' + acc.id, {
            method: 'PATCH',
            headers: { 'apikey': SUPABASE_KEY_FALLBACK, 'Authorization': 'Bearer ' + SUPABASE_KEY_FALLBACK, 'Content-Type': 'application/json' },
            body: JSON.stringify({
              url: 'https://dldl.50pk.com/login.php?' + new URLSearchParams({
                gid: result.entryGid || '1003279', pid: result.entryPid || '46',
                token: result.token, time: result.entryTime || '', sign: result.sign,
                appVer: result.appVer || '134', platCode: result.platCode || '37wan',
                IMEI: result.IMEI || '', isPcLauncher: 'true'
              }).toString(),
              app_token: result.token, app_sign: result.sign,
              url_created_at: new Date().toISOString()
            })
          });
          results.refreshed++;
        } else {
          results.errors++;
          results.detail.push({ uname: acc.uname, error: '兜底: ' + (result.message || '登录返回异常') });
        }
      } catch (e) {
        results.errors++;
        results.detail.push({ uname: acc.uname, error: e.message });
      }
      await new Promise(r => setTimeout(r, 3000));
    }
  }
  return results;
}

// 管理员触发：初始化某用户下全部账号（走 dlapi Worker 并发，与 cron-refresh 同源机制）
// 过期规则与移动端定时刷新一致：有 url 且 url_created_at >= 60h 才刷新；无 url 的必刷
async function initUserAccounts(userId) {
  const SUPABASE_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh5d2xianN5aHB5eXhib3pubWN0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkwODgwNzIsImV4cCI6MjA5NDY2NDA3Mn0.Q0KzoMgwNInH4gi30DEK_d1NbZCwl5yFjnTjubm_gYs';
  console.log('[init] 开始初始化用户账号:', userId);
  const results = { scanned: 0, refreshed: 0, errors: 0, skipped: 0, detail: [] };
  try {
    const resp = await fetch(SUPABASE_URL + '/rest/v1/accounts?user_id=eq.' + encodeURIComponent(userId) + '&select=id,uname,upwd,url,url_created_at', {
      headers: { 'apikey': SUPABASE_KEY, 'Authorization': 'Bearer ' + SUPABASE_KEY }
    });
    if (!resp.ok) { console.log('[init] 查询失败:', resp.status); return results; }
    const accounts = await resp.json();
    results.scanned = accounts.length;

    // 分类：无 url → 必刷；有 url 且 >= 60h → 过期需刷新；有 url 且 < 60h → 跳过
    const toRefresh = [];
    for (const a of accounts) {
      if (!a.url) {
        toRefresh.push(a);
      } else {
        const t = a.url_created_at ? new Date(a.url_created_at) : null;
        const ageH = t && !isNaN(t.getTime()) ? (Date.now() - t.getTime()) / 3600000 : 999;
        if (ageH >= 60) toRefresh.push(a); else results.skipped++;
      }
    }
    console.log('[init] 该用户账号数:', accounts.length, '需刷新:', toRefresh.length, '跳过:', results.skipped);
    if (toRefresh.length === 0) return results;

    const dr = await dispatchAndRefresh(toRefresh, 'init');
    results.refreshed = dr.refreshed;
    results.errors = dr.errors;
    results.detail = dr.detail;
  } catch (e) {
    console.log('[init] 异常:', e.message);
  }
  console.log('[init] 完成:', results.scanned, '扫描', results.refreshed, '刷新', results.errors, '错误', results.skipped, '跳过');
  return results;
}

// dlapi Worker 处理批量刷新任务
async function handleBatchRefresh(request) {
  let body;
  try { body = await request.json(); } catch (_) {
    return jsonResponse({ ok: false, message: '请求体必须是 JSON' }, 400);
  }
  const accounts = body.accounts || [];
  if (!Array.isArray(accounts) || accounts.length === 0) {
    return jsonResponse({ ok: false, message: '缺少 accounts 数组' }, 400);
  }

  const SUPABASE_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh5d2xianN5aHB5eXhib3pubWN0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkwODgwNzIsImV4cCI6MjA5NDY2NDA3Mn0.Q0KzoMgwNInH4gi30DEK_d1NbZCwl5yFjnTjubm_gYs';
  const results = { refreshed: 0, errors: 0, detail: [] };

  for (const acc of accounts) {
    try {
      console.log('[batch] 刷新:', acc.uname);
      const result = await doLoginCore(acc.uname, acc.upwd);
      if (result.ok && result.token) {
        await fetch(SUPABASE_URL + '/rest/v1/accounts?id=eq.' + acc.id, {
          method: 'PATCH',
          headers: { 'apikey': SUPABASE_KEY, 'Authorization': 'Bearer ' + SUPABASE_KEY, 'Content-Type': 'application/json' },
          body: JSON.stringify({
            url: 'https://dldl.50pk.com/login.php?' + new URLSearchParams({
              gid: result.entryGid || '1003279', pid: result.entryPid || '46',
              token: result.token, time: result.entryTime || '', sign: result.sign,
              appVer: result.appVer || '134', platCode: result.platCode || '37wan',
              IMEI: result.IMEI || '', isPcLauncher: 'true'
            }).toString(),
            app_token: result.token, app_sign: result.sign,
            url_created_at: new Date().toISOString()
          })
        });
        results.refreshed++;
      } else {
        results.errors++;
        results.detail.push({ uname: acc.uname, error: result.message || '未知错误' });
      }
    } catch (e) {
      results.errors++;
      results.detail.push({ uname: acc.uname, error: e.message });
    }
    await new Promise(r => setTimeout(r, 3000));
  }
  return jsonResponse(results);
}

// ==================== 主入口 ====================

export default {
  // Cron Trigger: 每6小时自动刷新过期账号
  async scheduled(event, env, ctx) {
    console.log('[cron] Cron Trigger fired:', new Date().toISOString());
    ctx.waitUntil(refreshExpiredAccounts(env));
  },

  async fetch(request, env) {
    // CORS 预检
    if (request.method === 'OPTIONS') {
      return new Response(null, { headers: corsHeaders() });
    }

    try {
      const url = new URL(request.url);

      // 根路径
      if (url.pathname === '/') {
        return jsonResponse({ message: 'DLDL Worker is running', timestamp: Date.now() });
      }

      // 测试
      if (url.pathname === '/test') {
        return jsonResponse({ status: 'ok', timestamp: Date.now() });
      }

      // 手动触发定时刷新
      if (url.pathname === '/api/cron-refresh') {
        const result = await refreshExpiredAccounts(env);
        return jsonResponse(result);
      }

      // 管理员触发：初始化某用户下全部账号（走 dlapi Worker 并发）
      if (url.pathname === '/api/admin-init-refresh') {
        let body;
        try { body = await request.json(); } catch (_) { body = {}; }
        const userId = body.userId || body.user_id;
        if (!userId) return jsonResponse({ ok: false, message: '缺少 userId' }, 400);
        const result = await initUserAccounts(userId);
        return jsonResponse(result);
      }

      // 通用批量刷新代理：接收账号列表，分发到 dlapi Worker（mobile 批量刷新 + 数据初始化同源流程）
      if (url.pathname === '/api/batch-refresh-proxy' && request.method === 'POST') {
        let body;
        try { body = await request.json(); } catch (_) { body = {}; }
        const accounts = Array.isArray(body.accounts) ? body.accounts : [];
        if (!accounts.length) return jsonResponse({ ok: false, message: '缺少 accounts 数组' }, 400);
        const cleaned = accounts.map(a => ({
          id: a.id, uname: String(a.uname || '').trim(), upwd: String(a.upwd || '').trim()
        })).filter(a => a.uname && a.upwd);
        if (!cleaned.length) return jsonResponse({ ok: false, message: '所有账号均缺少凭据' }, 400);
        console.log('[proxy] mobile 批量刷新请求:', cleaned.length, '个账号');
        const result = await dispatchAndRefresh(cleaned, 'proxy');
        return jsonResponse({ scanned: cleaned.length, refreshed: result.refreshed, errors: result.errors, detail: result.detail });
      }

      // dlapi Worker 批量刷新
      if (url.pathname === '/api/batch-refresh') {
        return handleBatchRefresh(request);
      }

      // 获取 IP
      if (url.pathname === '/get-ip') {
        try {
          const ipResp = await fetch('https://api.ipify.org?format=json');
          const ipData = await ipResp.json();
          return jsonResponse({ ip: ipData.ip });
        } catch (e) {
          return jsonResponse({ error: 'Failed to get IP', details: e.message }, 500);
        }
      }

      // APP 登录接口
      if (url.pathname === '/api/app-login' && request.method === 'POST') {
        return handleAppLogin(request);
      }

      // 通行证扫码接口
      if (url.pathname === '/api/pass-code' && request.method === 'POST') {
        return handlePassCode(request);
      }

      // 其他路径：代理到 s-api.37.com.cn
      const targetUrl = 'https://s-api.37.com.cn' + url.pathname + url.search;
      console.log('Proxy:', targetUrl);

      const response = await fetch(targetUrl, {
        method: request.method,
        headers: {
          'User-Agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 18_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.5 Mobile/15E148 Safari/604.1',
          'Accept': '*/*',
          'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8',
        },
      });

      const body = await response.text();
      return new Response(body, {
        status: response.status,
        statusText: response.statusText,
        headers: corsHeaders({
          'Content-Type': response.headers.get('Content-Type') || 'application/javascript; charset=utf-8',
        }),
      });
    } catch (error) {
      return jsonResponse({ error: error.message }, 500);
    }
  },
};
