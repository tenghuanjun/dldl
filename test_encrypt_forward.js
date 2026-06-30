const c = require('crypto');

// 模拟 bundle.js 中的加密流程
const ENCRYPT_KEY = '0123456789012345';
const API_KEY = 'Jp*4Y8vQOYck2*&Z';

// 模拟 md5
function md5(str) {
  return c.createHash('md5').update(str).digest('hex');
}

// 模拟 getSign: 参数按key排序，拼接 key=value，最后加 API_KEY，md5
function getSign(params, apiKey) {
  const sorted = {};
  Object.keys(params).sort().forEach(k => { sorted[k] = params[k]; });
  let str = '';
  for (const k in sorted) {
    str += k + '=' + sorted[k];
  }
  str += apiKey;
  return md5(str);
}

// 模拟 buildQuery: 按插入顺序拼接 key=value，encodeURIComponent
function buildQuery(params) {
  const parts = [];
  for (const k in params) {
    if (params.hasOwnProperty(k)) {
      parts.push(encodeURIComponent(k) + '=' + encodeURIComponent(params[k]));
    }
  }
  return parts.join('&');
}

// 模拟 AES-128-ECB-Pkcs7 加密，输出 base64url
function encrypt(plainText, key) {
  const keyBuf = Buffer.from(key, 'utf8');
  // Pkcs7 padding
  const blockSize = 16;
  const padLen = blockSize - (plainText.length % blockSize);
  const padded = Buffer.concat([
    Buffer.from(plainText, 'utf8'),
    Buffer.alloc(padLen, padLen)
  ]);
  const cipher = c.createCipheriv('aes-128-ecb', keyBuf, null);
  cipher.setAutoPadding(false);
  let encrypted = cipher.update(padded);
  encrypted = Buffer.concat([encrypted, cipher.final()]);
  // base64url encode
  return encrypted.toString('base64')
    .replace(/\+/g, '-')
    .replace(/\//g, '_')
    .replace(/=/g, '');
}

// 构建登录参数（模拟 APP 端流程）
const time = Math.floor(Date.now() / 1000).toString();
const params = {
  uname: 'laogao666666',
  upwd: '1234561239',
  autoLogin: 'true',
  pid: '46',
  gid: '1003279',
  sversion: 'undefined',
  version: '1.0.4',
  time: time,
  dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
  os: 'iOS',
  over: '18.5'
};

// Add sign
params.sign = getSign(params, API_KEY);

// Add random callback (like jsonpEncrypt does)
params.callback = 'jsonp_callback_' + Math.round(100000 * Math.random());

// Build query string
const qs = buildQuery(params);

console.log('=== 参数 ===');
console.log('time:', time);
console.log('完整 query string:');
console.log(qs);
console.log('\nquery string 长度:', qs.length);

// Encrypt
const encrypted = encrypt(qs, ENCRYPT_KEY);
console.log('\n=== 加密结果 (base64url) ===');
console.log('长度:', encrypted.length);
console.log('前 80 字符:', encrypted.substring(0, 80));
console.log('\n完整:');
console.log(encrypted);

// User's encrypted for comparison
const userEnc = 's7rBVLGlm_w-Es3gmis8K5TnfLxNYEKPmKXK6BSGPqZ0-KO9NFyf4uLpYtYDkg1fVqbiutc9MtfVSwT9cDsprGvFM4FICAa5SH77skNZ57pJQgTAxRhRbqtZFrxI55bBjZIYk72sTMcyTG8KQd6w4t4xgdoych2M2Ix6zopGcZuoqvJGOHPkcMUAzfgSzq_6wURnZC2YRAikmzjah8QMKGTmmkxep6O7uPXDeE-hPPnS1RXcLLcLYVetIQ2RaKOb_4x6NQ57tLQ-grqJKaM2SzQyY_qIu-TpbCKiESpH1C9mafpqfm2ujTNETL6fpcz6z0i6rB5LV9fLhPq5LQVoCrk7WdNEjmCwKQzKbRj1PSzEFzArC3tKVkCNuWBF3T_PLuq5ngbsRiq_dQGg70fEtGZCDxB_Hjh7fWGJ2zRo6Uit9ux4pp4HzFhbsWruBCND9hybeq9jef1QzyTb4H0lcC-ioU_jBpOWXFpynNBxNZ2Y4_LEiE_h5jcnZ6hrguRGKo-QI01NoOvKrBE_BcFo0u2uspAUaSxWM3yr4F8Xy86JqPjcfF1kL9HFDjz6FR_yNkJL7hW9SkoCbGXJ0ns1sLawD2Rki-j2VPeVNBAIGieRdzpUJSsQ4YNSCzTHGkoTrMKfUSOTJ9kiLFy1H-Dyk-2mgYbzKVzEjbsM2xtiSKdPIgFVZEyDly9PIrTG2YbVhCbsc-wjM-8k-fDNO8saRWrPSxzXaE_AIJGgsxmg3OGl9m6o_TL7UXxL5XMYVaTpSMOT4aqepU0ETmpQIgU5IirxuXkbQG5T_dR3gYe7g8nZWjdW3P9wP4fuoLnG5WnZzu-UM62mPFPk_xn4paHE-O5SHYjAvziPrVwjnz19RVZnNlsyeW81FmsEhIZh0PhntGEQ89OlXjVDS5rF-ZKh6H5O9Kf01iHRDVDbCUhkB3o8obGv8FF7ZpcZYE0Xw5bfT3iPX_nB2Ecsj1xaSUQG64K0TnCkSvl_K-bnagJ2XZtDWPl6Oxoz5umEpOrzXKlHvz-4aTofyev8YlwKaMI7fNY72GD86QMdYGaUjkYX2fkGPVVAJG_qQxviz0hXgV8ijuCFwES6gTXkFTPl60kmp0B-_4V2e3442hKR0eNh4D4fYKt5qeeEFRG2TrXaPEY9wYwkKzgqfFtADJ_o6Rkjji-JPIuK60lvJvy1RyxewWRPBxVc-sOiZSGXDiMY3GIK0eoHiO1JhVtnfRmguqc-S6Y01OjUUrmT7JjgE266mP0rfT2EyH9ekwgJysJ35JM-tsj2gjElBr0jLlKNQOPD4LQuiEFf7gzPpfFQJxNrJrocza9DI3CjXLA9oDBjhbQztBkL2r8EsNjxlItL3Z6klP7nZvoEpnhbwy5qHXjduS-1lVESYpFxzmH5JuwF4pdqsgTzNbGGAzZxqq00sIrsFOEf-GPfJjLmEqtrTyLPFOt34tTm5SyLqIuiCoC5FTYwkTloC0rUKZ9H1ORzQQAQNkYvsIz5G_ish7dYajU3tXFPiuEuTzpZ8l_-2hUBneMn';
console.log('\n用户密文长度:', userEnc.length);
console.log('匹配?', encrypted === userEnc);
