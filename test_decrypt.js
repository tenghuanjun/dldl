/**
 * 最后综合破解尝试
 * 已知算法: AES-128-ECB-PKCS7 (via CryptoJS)
 * 精确还原 bundle.js 的 decrypt 函数
 */

const CryptoJS = require('crypto-js');
const crypto = require('crypto');

// ---- 数据 ----
const body1 = 's7rBVLGlm_w-Es3gmis8K5TnfLxNYEKPmKXK6BSGPqZ0-KO9NFyf4uLpYtYDkg1fVqbiutc9MtfVSwT9cDsprGvFM4FICAa5SH77skNZ57pJQgTAxRhRbqtZFrxI55bBjZIYk72sTMcyTG8KdQ6w4t4xgdoych2M2Ix6zopGcZuoqvJGOHPkcMUAzfgSzq_6wURnZC2YRAikmzjah8QMKGTmmkxep6O7uPXDeE-hPPnS1RXcLLcLYVetIQ2RaKOb_4x6NQ57tLQ-grqJKaM2SzQyY_qIu-TpbCKiESpH1C9mafpqfm2ujTNETL6fpcz6z0i6rB5LV9fLhPq5LQVoCrk7WdNEjmCwKQzKbRj1PSzEFzArC3tKVkCNuWBF3T_PLuq5ngbsRiq_dQGg70fEtGZCDxB_Hjh7fWGJ2zRo6Uit9ux4pp4HzFhbsWruBCND9hybeq9jef1QzyTb4H0lcC-ioU_jBpOWXFpynNBxNZ2Y4_LEiE_h5jcnZ6hrguRGKo-QI01NoOvKrBE_BcFo0u2uspAUaSxWM3yr4F8Xy86JqPjcfF1kL9HFDjz6FR_yNkJL7hW9SkoCbGXJ0ns1sLawD2Rki-j2VPeVNBAIGieRdzpUJSsQ4YNSCzTHGkoTrMKfUSOTJ9kiLFy1H-Dyk-2mgYbzKVzEjbsM2xtiSKdPIgFVZEyDly9PIrTG2YbVhCbsc-wjM-8k-fDNO8saRWrPSxzXaE_AIJGgsxmg3OGl9m6o_TL7UXxL5XMYVaTpSMOT4aqepU0ETmpQIgU5IirxuXkbQG5T_dR3gYe7g8nZWjdW3P9wP4fuoLnG5WnZzu-UM62mPFPk_xn4paHE-O5SHYjAvziPrVwjnz19RVZnNlsyeW81FmsEhIZh0PhntGEQ89OlXjVDS5rF-ZKh6H5O9Kf01iHRDVDbCUhkB3o8obGv8FF7ZpcZYE0Xw5bfT3iPX_nB2Ecsj1xaSUQG64K0TnCkSvl_K-bnagJ2XZtDWPl6Oxoz5umEpOrzXKlHvz-4aTofyev8YlwKaMI7fNY72GD86QMdYGaUjkYX2fkGPVVAJG_qQxviz0hXgV8ijuCFwES6gTXkFTPl60kmp0B-_4V2e3442hKR0eNh4D4fYKt5qeeEFRG2TrXaPEY9wYwkKzgqfFtADJ_o6Rkjji-JPIuK60lvJvy1RyxewWRPBxVc-sOiZSGXDiMY3GIK0eoHiO1JhVtnfRmguqc-S6Y01OjUUrmT7JjgE266mP0rfT2EyH9ekwgJysJ35JM-tsj2gjElBr0jLlKNQOPD4LQuiEFf7gzPpfFQJxNrJrocza9DI3CjXLA9oDBjhbQztBkL2r8EsNjxlItL3Z6klP7nZvoEpnhbwy5qHXjduS-1lVESYpFxzmH5JuwF4pdqsgTzNbGGAzZxqq00sIrsFOEf-GPfJjLmEqtrTyLPFOt34tTm5SyLqIuiCoC5FTYwkTloC0rUKZ9H1ORzQQAQNkYvsIz5G_ish7dYajU3tXFPiuEuTzpZ8l_-2hUBneMn';
const body2 = '_W45gnnB6opG4TUDScqgNXKjI6X9XyqPwDbV7-H15RtTR2hhdtdgJIy-ywuv1cRqha9iVVVihbLaXWrILeyyI4ejFDXpgVmjl1nXHHHLiW672Qj5cCZAHlC4Rxx48YWJ6mwXARVloJ2vFXKABgfQJeEb7nyw2EoAOY5a0N4GD_vaG8B3TgIpTHIL0gWZ90W48k2KMzAOpha15-GLWYfsNwjcEFIi2I0b0mYc5IDKWby_oUJaUF5iLGfu5KRDKvt0yYz9DQnMQ0ysIu35ixX6z7rwKdkrYt3DR6xRZCQWnzNhxTbI4gqcSP65hYw7Ano5DOtFbTNqraTBnX6ls9kCsOCAIZ7Rc4RgpLFe0Jzcn-Li-ObU3nzpwmfWZkmSG_PTYr2NqoqtUoenam7zwWBSNY-WUWz1LsXcIR3CvKkk1mtN56vQuIpanPn6OqUDz_C2vMJMBDRFTwh0qkq92KjBFxaq039ar6GQp0t4BEDTUmgq9BY6RE1cROEHjrhNylM-doVBCp6nbWXKbjMifJI56Luf35M_eeu3WuULgs12afCPFZAmR__XU9IQ-B266lZ5z38dZoOgkLhHM9VVSYjp7ZB870pB3h4loHs7A0VNrM_26nBrrTuE2YK5seV4vFfNp0RtduLsyxeDG67m6bnamJQd5u7oiU19aIGcYkBrIatUc6ItPeTGfs_o6w3BjzMZy5WzK4OLxYZjmdAPpbzBgvJXiOqvbFVWdMs73qfMhuf-tF_Vju29Il6ajetJlkI16-9WniSAMsELym8GxAH7Yc-QZaEh4pEmcCguA8OFEcgz4pOCXoMuEk2x8S0bhwAk4b5IjPFeMFR-QwKW5QtqoZ9PEzQIoM9bEPu7Zn-P1M7F8KI3BcDs1Sv_YuHU_pmOXvlg8hSuIuZchFOY03AJ30OMrC8Q0vlTgXkGOQZFxNun2NIE4ERMj-1tpC0Ba8Cl4iqetFUzZRd3XaXuWXkcmd-NG2eZ0fqVQIjJ3AtWP77yUbAuV2fuL-e_4Txhf6S0F6AAkD6g84Izz2Z9TmVNX0cfcbmnikC4r1IFyj6dCXCMDrl1APJZfm67DzWi7tyg0UtdWgp-nepEQ1W6vgR_hxQHGdGkrmGEFjNybvmjKqZOFY5qA4Z10gzJKB3TUyJR7p-9nC-hbnsW7V1xQQQxImF11i5CidrRbP5ZuHR45O0Wq4tqiwZUqbPMz9h2b5h9Au6sdlABrO5qwD5tvvZCanniwOX1yXcVf4ekEAr3at9yT5jbKFSKN4rZIWtVybKC93JkzpQ8koI6kjrCjIwUrJXYMz6JTv5D3lYUvVrxMwVjY5X-u9eSXm_X4UrUkDfSb40C3VIU1HLewCE790hTJSJZ6YzJgiJbc3lHa5ZnL6Sn6_yNZnO-uc7LgQU4zbxJccBTJKDFUhgcsPQLYe9GMw_9IdgFtqzm4HyDT-OylJdTXV38rl0c0iaFaaSklXhe2MxhVFyoz1EWB-Eo7Ku7ec-zi34jPdSGZHJnJlkAdrZdS0MyIiPb8fXIpNjPfotE0oa_6pxYFQLbqwWToS7ZjPnCgYky0VH1ZJcVG1ZNSTkl-H02Ez7ow9KQ_eEjPn1TICF_3MvaiZuI0SMAZVitnKxUPWxI8SQU7Ju7QEoy6iq0zXyNvZrVVCoXE7mwGfUALuRVMM7ONAWZZ4sV8Gt7Hok-614L5XtUBPx1eJnzzeOpLs9ocqCsTnQIJw3XxoKXlshplE-zLVH78PV8Y2DDgluDSEHd4964COxVRL3i50Rv_874hUfUfTwTQmkUpV_hO0RKLHBno3n5JxH_22nStgft65MidNke-cHnqImaMoHg7NgnLDmeelI96-DCbqfR80qqwd-fBdZT9-2itiM89fAZa05jY_ywzTyFEIlB14l-lYBvhUZclY308yLAo-EwJkJxsnr56TOaBlrMgACw3NiBM-onQUh7HlwX55lljTCdbB1ycJRL_UtpdxleVc1ITr57E1CPOjVyfrga8ywpv77JLdpFcWGzqDYdPWhOXlrgEX8EbCODkxdb7L2S6ZoGuO3UH3sqcU56UlY7PJU8piRMbX3tbtZPHthf9dNaQMEJHhXFjbH5TMt7e-bY84rPl0alON2lLy13duOSsd5txoyHQa_VcU24U_VGmI7OP7VRoOcFTThYHZyO-9kPCW9TdxTZGtS3zNqe0_UsolG624T9f-NT-ylebBiyy4G4Zf1fvw7EHKjDrZ6EoyZKQ8TDKuxWCJowH6L51LHF-FZgeBlsjm8V7VAuBGgdkTCAb6I';

const nonce1 = '8421467abfc18ab5fa0c00d47ed3f751';
const nonce2 = 'ecdd790d189cd176f698b866c52037de';
const reqId1 = 'b85a1ce9d9b773e393985d40c51c685a';
const reqId2 = '6a2280d772866d05051d286e1e1dd305';

const b64_1 = body1.replace(/-/g, '+').replace(/_/g, '/') + '==';
const b64_2 = body2.replace(/-/g, '+').replace(/_/g, '/') + '==';
const raw1 = Buffer.from(b64_1, 'base64');
const raw2 = Buffer.from(b64_2, 'base64');

// ============ 精确还原 bundle.js 的 decrypt 函数 ============
// decrypt: function (encryptText, key) {
//     var key = CryptoJS.enc.Utf8.parse(key);
//     var decrypt = CryptoJS.AES.decrypt(encryptText.toString(), key, {
//       mode: CryptoJS.mode.ECB,
//       padding: CryptoJS.pad.Pkcs7,
//     });
//     return CryptoJS.enc.Utf8.stringify(decrypt).toString();
// }
function exactDecrypt(encryptedBase64, keyStr) {
  try {
    const key = CryptoJS.enc.Utf8.parse(keyStr);
    const decrypted = CryptoJS.AES.decrypt(encryptedBase64, key, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    const result = CryptoJS.enc.Utf8.stringify(decrypted).toString();
    return result;
  } catch(e) {
    return null;
  }
}

function isValidDecryption(result) {
  if (!result || result.length < 5) return false;
  // query string pattern: key1=val1&key2=val2
  if (result.includes('&') && result.includes('=')) return true;
  // JSON pattern
  if (result.startsWith('{') && result.includes(':')) return true;
  // High ratio of printable ASCII in first 100 chars
  const sample = result.substring(0, Math.min(100, result.length));
  const printable = [...sample].filter(c => c.charCodeAt(0) >= 0x20 && c.charCodeAt(0) <= 0x7E).length;
  return printable / sample.length > 0.85 && result.length > 8;
}

function tryDecryptBoth(key) {
  const r1 = exactDecrypt(b64_1, key);
  const r2 = exactDecrypt(b64_2, key);
  if (isValidDecryption(r1) || isValidDecryption(r2)) {
    console.log(`✅ KEY="${key}"`);
    console.log(`  body1 (${r1?r1.length:0}B):`, r1 ? r1.substring(0, 150) : 'null');
    console.log(`  body2 (${r2?r2.length:0}B):`, r2 ? r2.substring(0, 150) : 'null');
    return true;
  }
  return false;
}

// ============ 大型密钥字典 ============
const keyDict = new Set();

// 1. 游戏相关
['dldlsy', 'DLDLSY', 'Dldlsy', 'dldlsy37', '37dldlsy', 'm37dldlsy',
 'sy37', 'SY37', 'dldlsy.sy37', 'com.m37.dldlsy',
 '37wan', '37WAN', '37Wan', '37.com', '37.com.cn',
 'dldl', 'DLDL', 'Dldl',
 'doulaodalu', '斗罗大陆', 'douluodalu',
].forEach(s => {
  // 补齐到16字节
  if (s.length < 16) s = s.padEnd(16, '0');
  if (s.length > 16) s = s.substring(0, 16);
  keyDict.add(s);
  // 变体
  keyDict.add(s.replace(/0+$/, '1234567890').substring(0, 16));
  keyDict.add(s.replace(/0+$/, '0123456789').substring(0, 16));
  keyDict.add(s.replace(/0+$/, '!@#$%^&*()').substring(0, 16));
  keyDict.add(s.toUpperCase().substring(0, 16));
  keyDict.add(s.toLowerCase().substring(0, 16));
});

// 2. 常见SDK密钥
['0123456789012345', '1234567890123456', 'abcdefghijklmnop',
 'a1b2c3d4e5f6g7h8', '1q2w3e4r5t6y7u8i', '9i8u7y6t5r4e3w2q',
 'qwertyuiopasdfgh', 'zxcvbnmasdfghjkl', 'asdfghjklqwertyui',
 'Jp*4Y8vQOYck2*&Z'.substring(0, 16), 'Jp*4Y8vQOYck2*&Z',
 '0123456789abcdef', 'fedcba9876543210',
 '37wan0123456789', '37wan!@#$%^&*(', 
 '@37wan_dldl_sy37', '37WAN_DLDL_SY37',
 'dldl-sy37-37wan', 'dldl_sy37_37wan',
 'm37.dldlsy.sy37'.substring(0, 16), 'com.m37.dldlsy.',
 '37.com.cndldlsy', 'dldlsy37.com.cn',
 'dl_dl_sy_37_wan', 'dldlsy2024!!!!',
 '2024dldlsy!!!!', 'dldlsy2024****',
].forEach(s => {
  if (s.length > 16) s = s.substring(0, 16);
  if (s.length < 16) s = s.padEnd(16, '0');
  keyDict.add(s);
});

// 3. MD5派生
const md5Sources = [
  '37wan', 'dldlsy', 'dldl', 'com.m37.dldlsy.sy37', '37.com.cn',
  's-api-secure.37.com.cn', 'doulaodalu', '斗罗大陆',
  'H5App', 'Alamofire', 'iOS',
  'D84AP', 'iPhone16',
];
for (const src of md5Sources) {
  const md5 = CryptoJS.MD5(src).toString();
  keyDict.add(md5.substring(0, 16));
  keyDict.add(md5.substring(16, 32));
  // MD5(MD5)
  const md5_2 = CryptoJS.MD5(md5).toString();
  keyDict.add(md5_2.substring(0, 16));
}

// 4. SHA系列
for (const src of ['37wan', 'dldlsy', 'com.m37.dldlsy.sy37']) {
  keyDict.add(crypto.createHash('sha256').update(src).digest('hex').substring(0, 16));
  keyDict.add(crypto.createHash('sha1').update(src).digest('hex').substring(0, 16));
}

// 5. nonce/reqId 相关
keyDict.add(nonce1.substring(0, 16));
keyDict.add(nonce2.substring(0, 16));
keyDict.add(reqId1.substring(0, 16));
keyDict.add(reqId2.substring(0, 16));

// nonce/reqId 的 MD5
for (const src of [nonce1, nonce2, reqId1, reqId2]) {
  keyDict.add(CryptoJS.MD5(src).toString().substring(0, 16));
}

// 6. 设备标识
['D84AP', 'iPhone16,2', 'DEFF745C2AD241D5', '5721C90E5F224673',
 'dafb9ed848bfb382', '867e57bd062c71699',
 'D882E598F7A5FEE7', 'com.m37.dldlsy',
].forEach(s => {
  if (s.length > 16) s = s.substring(0, 16);
  if (s.length < 16) s = s.padEnd(16, '0');
  keyDict.add(s);
});

// 7. HMAC派生
for (const [data, key] of [
  [nonce1, '37wan'], [nonce2, '37wan'],
  [nonce1, 'dldlsy'], [nonce2, 'dldlsy'],
  [nonce1, 'Jp*4Y8vQOYck2*&Z'], [nonce2, 'Jp*4Y8vQOYck2*&Z'],
]) {
  keyDict.add(CryptoJS.HmacMD5(data, key).toString().substring(0, 16));
  keyDict.add(CryptoJS.HmacSHA1(data, key).toString().substring(0, 16));
  keyDict.add(CryptoJS.HmacSHA256(data, key).toString().substring(0, 16));
}

// 8. XOR组合
const n1 = Buffer.from(nonce1, 'hex');
const n2 = Buffer.from(nonce2, 'hex');
const r1 = Buffer.from(reqId1, 'hex');
const r2 = Buffer.from(reqId2, 'hex');

const xorKey1 = Buffer.alloc(16);
const xorKey2 = Buffer.alloc(16);
for (let i = 0; i < 16; i++) {
  xorKey1[i] = n1[i] ^ r1[i];
  xorKey2[i] = n2[i] ^ r2[i];
}
keyDict.add(xorKey1.toString('latin1'));
keyDict.add(xorKey2.toString('latin1'));

console.log(`共准备了 ${keyDict.size} 个候选密钥\n`);

// 开始暴力破解
let found = 0;
for (const key of keyDict) {
  if (tryDecryptBoth(key)) found++;
}
if (found === 0) {
  console.log('❌ 所有候选密钥均失败');
}

// ============ 额外尝试: DES / 3DES ============
console.log('\n=== 尝试 DES / 3DES ===');
function tryDESDecrypt(b64, keyStr, algo) {
  try {
    const key = CryptoJS.enc.Utf8.parse(keyStr);
    let d;
    if (algo === 'DES') {
      d = CryptoJS.DES.decrypt(b64, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
    } else {
      d = CryptoJS.TripleDES.decrypt(b64, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
    }
    return CryptoJS.enc.Utf8.stringify(d).toString();
  } catch(e) { return null; }
}

const desKeys = ['01234567', '37wan123', 'dldlsy12', 'com.m37.'];
for (const algo of ['DES', 'TripleDES']) {
  for (const k of desKeys) {
    const key3DES = algo === 'TripleDES' ? k + k + k.substring(0, 8) : k;
    const r1 = tryDESDecrypt(b64_1, key3DES, algo);
    if (isValidDecryption(r1)) {
      console.log(`✅ ${algo} key="${key3DES}"`);
      console.log('  body1:', r1.substring(0, 150));
    }
  }
}

console.log('\ndone.');
