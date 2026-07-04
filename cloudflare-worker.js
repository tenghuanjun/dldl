// Cloudflare Worker - DLDL API Proxy
// 处理 /api/app-login（SDK 登录 + PC 扫码流程），以及通用代理

import { createCipheriv } from 'node:crypto';
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
 * AES-128-ECB 加密（使用 node:crypto，与 proxy.js 完全一致）
 * @param {string} plaintext - 明文
 * @param {string} keyStr - 16字节密钥
 * @returns {string} base64 密文
 */
function aes128EcbEncrypt(plaintext, keyStr) {
  const cipher = createCipheriv('aes-128-ecb', Buffer.from(keyStr, 'utf8'), Buffer.alloc(0));
  cipher.setAutoPadding(true);
  let encrypted = cipher.update(plaintext, 'utf8', 'base64');
  encrypted += cipher.final('base64');
  return encrypted;
}

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

  const resp = await fetch(SDK_LOGIN_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      'User-Agent': 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)',
    },
    body: formBody,
  });

  const body = await resp.text();
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

  const resp = await fetch(url, {
    headers: { 'User-Agent': '37MobileGame/4.6.7 (Android)' },
  });
  const body = await resp.text();
  if (resp.status !== 200) throw new Error('pc/getId HTTP ' + resp.status);

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

  const resp = await fetch(url, {
    headers: { 'User-Agent': 'Mozilla/5.0' },
  });
  const text = await resp.text();

  const match = text.match(/^callback\(([\s\S]+)\);?$/);
  const jsonText = match ? match[1] : text;
  const payload = safeJsonParse(jsonText);
  if (!payload || payload.state !== 1 || !payload.data || !payload.data.token) {
    throw new Error('h5sdk/login 失败: ' + (payload && payload.msg || text.slice(0, 200)));
  }
  console.log('[app-login] h5sdk/login 成功');
  return {
    token: String(payload.data.token),
    time: String(payload.data.time || time),
    sign: String(payload.data.sign || sign),
  };
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

  const resp = await fetch('https://' + PC_HOST + '/pc/postCodeInfo', {
    method: 'POST',
    headers: {
      'User-Agent': '37MobileGame/4.6.7 (Android)',
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: formBody,
  });
  const body = await resp.text();
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

  const resp = await fetch(url, {
    headers: { 'User-Agent': '37MobileGame/4.6.7 (Android)' },
  });
  const body = await resp.text();
  if (resp.status !== 200) throw new Error('pc/getCodeInfo HTTP ' + resp.status);

  const json = safeJsonParse(body);
  if (!json || json.state !== 1 || !json.data) {
    throw new Error('pc/getCodeInfo 失败: ' + body.slice(0, 120));
  }
  console.log('[app-login] getCodeInfo 成功: gid=' + json.data.gid + ', pid=' + json.data.pid);
  return json.data;
}

// ==================== /api/pass-code（扫码通行证） ====================

async function handlePassCode(request) {
  let body;
  try { body = await request.json(); } catch (_) {
    return jsonResponse({ ok: false, message: '请求体必须是 JSON' }, 400);
  }

  const sessionId = String(body.sessionId || body.code || '').trim();
  const uname = String(body.uname || '').trim();
  const upwd = String(body.upwd || '').trim();

  if (!sessionId) return jsonResponse({ ok: false, message: '缺少通行证码(sessionId)' }, 400);
  if (!uname || !upwd) return jsonResponse({ ok: false, message: '缺少账号密码' }, 400);

  try {
    console.log('[pass-code] 开始处理:', uname, 'sessionId:', sessionId.slice(0, 10) + '...');

    // 1. AES 加密密码
    const encryptedPwd = aes128EcbEncrypt(upwd, AES_KEY);

    // 2. SDK 登录
    const sdkResp = await sdkLogin(uname, encryptedPwd);
    if (sdkResp.state !== 1) {
      throw new Error(sdkResp.msg || 'SDK 登录失败');
    }

    // 3. h5sdk/login 获取游戏入口 token
    const h5sdkInfo = await h5sdkLogin(uname, upwd);

    // 4. postCodeInfo 写入扫码的 sessionId
    const gameParams = {
      gid: GAME_GID,
      pid: GAME_PID,
      token: h5sdkInfo.token,
      time: h5sdkInfo.time,
      sign: h5sdkInfo.sign,
      appVer: GAME_APPVER,
      platCode: GAME_PLATCODE,
      IMEI: GAME_IMEI,
    };
    await pcPostCodeInfo(sessionId, gameParams);

    console.log('[pass-code] ✅ 完成:', uname);
    return jsonResponse({ ok: true, state: 1, message: '通行证验证成功' });
  } catch (error) {
    console.error('[pass-code] 失败:', error.message);
    return jsonResponse({ ok: false, message: error.message }, 500);
  }
}

// ==================== /api/app-login 完整流程 ====================

async function handleAppLogin(request) {
  let body;
  try {
    body = await request.json();
  } catch (_) {
    return jsonResponse({ ok: false, message: '请求体必须是 JSON' }, 400);
  }

  const uname = String(body.uname || '').trim();
  const upwd = String(body.upwd || '').trim();
  if (!uname || !upwd) {
    return jsonResponse({ ok: false, message: '缺少 uname 或 upwd' }, 400);
  }

  try {
    // 1. AES 加密密码
    console.log('[app-login] 开始处理:', uname);
    const encryptedPwd = aes128EcbEncrypt(upwd, AES_KEY);

    // 2. SDK 登录
    const sdkResp = await sdkLogin(uname, encryptedPwd);
    if (sdkResp.state !== 1) {
      throw new Error(sdkResp.msg || 'SDK 登录失败');
    }
    const sdkData = sdkResp.data || sdkResp;
    const sdkToken = sdkData.token;
    if (!sdkToken) throw new Error('SDK 登录未返回 token');

    // 判断 loginType
    const rawLt = String(sdkData.login_type || '');
    let loginType = rawLt === '2' ? 'phone' : rawLt === '3' ? 'wx' : 'common';
    if (!rawLt) {
      loginType = /^1[3-9]\d{9}$/.test(uname) ? 'phone' : 'common';
    }
    console.log('[app-login] loginType:', loginType);

    // 3. 获取 PC 扫码会话 ID
    const sessionId = await pcGetId();
    console.log('[app-login] sessionId:', sessionId.slice(0, 10) + '...');

    // 4. h5sdk/login 获取游戏入口 token
    const h5sdkInfo = await h5sdkLogin(uname, upwd);

    // 5. 构造游戏入口参数并写入
    const gameParams = {
      gid: GAME_GID,
      pid: GAME_PID,
      token: h5sdkInfo.token,
      time: h5sdkInfo.time,
      sign: h5sdkInfo.sign,
      appVer: GAME_APPVER,
      platCode: GAME_PLATCODE,
      IMEI: GAME_IMEI,
    };
    await pcPostCodeInfo(sessionId, gameParams);

    // 6. 取回参数
    const entryParams = await pcGetCodeInfo(sessionId);

    // 7. 返回结果
    const result = {
      ok: true,
      state: 1,
      uid: sdkData.uid,
      uname: sdkData.uname || uname,
      token: entryParams.token,
      sign: entryParams.sign,
      entryTime: entryParams.time,
      appVer: entryParams.appVer || GAME_APPVER,
      platCode: entryParams.platCode || GAME_PLATCODE,
      IMEI: entryParams.IMEI || GAME_IMEI,
      entryGid: entryParams.gid || GAME_GID,
      entryPid: entryParams.pid || GAME_PID,
    };

    console.log('[app-login] ✅ 完成:', uname);
    return jsonResponse(result);
  } catch (error) {
    console.error('[app-login] 失败:', error.message);
    return jsonResponse({ ok: false, message: error.message }, 500);
  }
}

// ==================== 主入口 ====================

export default {
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
