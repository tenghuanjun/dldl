// 验证加密/解密算法与真实APP是否一致
import { createCipheriv, createDecipheriv } from 'node:crypto';
import { Buffer } from 'node:buffer';

const GATEWAY_DEFAULT_KEY = 'soC2GAr8jN2fsbry';

// ==================== MD5 ====================
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

// ==================== 工具函数 ====================
function fromBase64Url(s) {
  s = s.replace(/-/g, '+').replace(/_/g, '/');
  while (s.length % 4) s += '=';
  return s;
}

// ==================== 真实APP抓包数据 ====================
const realNonceStr = '191f98499f09e553cb7aaedf0b03bd8d';
const realRequestId = 'cb47c85abba6a5ee6484a49860a8e222';
const realEncryptedBody = 'nFKwmofGk2xJqnZyu1Zmg-xjGsJhvj576OVij6e9mHNbqRGykzPYCcg3QJ8OJ3s7E8e3KRi34ksUf9Q42kDmXWvNf9PqDF9OCYAc6L-K5BNgEvWILEac6gv0MjlPfnnxv1blC9bhinMMTS8rMNLFh60RMw6sZNWdVPc3Ur4dAOZXOP2kFK4LG1Z9nt_lSEB9u5-FHi3MO-WbiyHZ2TPQvT9TQHKORu7jcIilcmrj-innHmbSjRiCcdADDhMowb9ZQpm68H6tNTF_ysacxnSRF-EBWSvVLOWPsJTQb-gdn2px7fot3ghcAnGu2s3vliSDmlURj9nDHbiAQ8SNbS--v7PuKc_5ZJlCCHzqphghbZpuIStB3pW6iz6cKRgv-02hxzRj8wjVd4ecnj1L97f5bPnOftFn5Gw2fckoJ_03sQsaey-qIbJfmUx966R1Qg04uXBCr5hEqDKBZZVpxEI2XQGd-6646rVDB53Wx9ONBR34fCYvxKJmH6OGXAe0hwccE8csTDQLMq0dXAlauAZLQj2yV4TzJr_EUjq5JP6REiFox3sfbrVAolDd9QPyOUiNARgNLkDb_Rbcr7DiDYgmA8l_mEQw1h_V1jrTWlzrHKa3IEg5PC-N6kqUUyxNNprO1H84FQRAyQwHcXAw-kdu7HdyIE_sdJ58vcYvNsm0MLFqbmlQgG4hZ5Eqb7pMTTzY1vTPJNuyVWWRQ1N_H8xWxfEC0Shw-LfEsQlWGw-nF2w6axCFGqrlTjIRHcblX0sA7wt3Zuz5MrfdG90xCbo-Lo3AIZImG8ppXKaPFqaUxVegmI1jAe-BdCJddtQQ0FBArlOEvkzzG0BQFchsdF2Y9RJwpRZVe2ImTEFgy44s5de7qO4wdFpHphi5Boj7eM-YOwaX3zsTJqqZuSTr6fOK2D0yCHRutWjOYXqvXnOYbqHoViRh9g8Kr6oLPuYFpbcKIclMtyw43x8PItNM8A0t6EdBA8y1IIrO7FMYDTLKnBW_7qM-jzdMzMTXSiRSs7JXmRwQqdlfRpVR0Sk0emKX-cCySI3FXG6J-lfHd7s9txamIlROI7xZazVUOf64y9gTjmkQSaJrun8Sorno-BoLQ0NaqvtTwUnykaKUkrjwtIPHcXoRdHzTcSBlerarg3dXSYs7EToDK1pQ9zdh0QrgNKg4q_j2tMxOHb4fKXZKSbN7ASiw__SzHPuDbd2qXGHUQ_zyxlnshjweQLY42qOnewUmIBhFUVlT2Dg6LyUK13w30BGOpVA0xlMBa-umWUAw9sDX8GfHDP7rRINlXjUsK8FcoeE3A_FcQZgcofzJUYBVwnkcc9LI62H3O7dgmfKEey0BxzclR1hZjTpvEJKglxpfBnbUh0UaZK5Xd7040B7R2y-Xfmg_v6kStGFEpPz90aekBQ6NHQJFj9eaEcpoNUk79INX3BYMvDwgitc23wnuKlJZErMz3OdQlOXCIg--00MtErHPqJgfKMQuWLSEGCqAjvp1NAnBnU9qIdWpsryMI6h3oyHI-I-qfzEcNC7H7QEbx5impNnE5CFVRK5vwNAlh5tV1muh0U0NYHBto-NMxx4Nt7rs3IzPO4Do1EorosrSkFOmMYyfM3gi-bk-pq3i8fQHTELcbunzjVdnxENRup7RFkX4U3DArso7DpXwooPjPapq3vdifMH2Lif2ThyUxTpYHUB57rZ1s6iHkZ2OYJeXKlB1_fTUsI1A24yPIDUv-q536DbMzeTYKEn_gTXRcZqLiH_AA94UBbaTLfU9X23UL1RZHdmXo2eN1I1FzNNGRfCT9HVYBMAhbGl9zmTG2B525T-MPYhYaOQqTs60VxPmbZKYIHC_8JjtZ2D9WtQVsmNO28MnslNbTuLFwVjQA2o5iCIZ4uZqtMHkm5xwklM4OTKmqzdCCyDO6DggodeWPTMGJdVn_aWHiiJvODVfbLIzb2JtiwYBll2Yc49aCozuoc5WFAXUMw5XonE_ptByip-FBP1LOR8wtTXmK--58p2CPMOxbkdPebCdR0crrKFJunM5-Fi6njone_F5UYM2IueoeurvilBVlBt8QcS6klIQKTmApwHdqw04VReCx5vNAOfGdkO3bYoNdBPIdCQ8pfCqoRNKapNt5JSCrSxZgG9yYckzx9B7aqsbmoqVX7pNmgPDUQEeMhXYzsjKuRZnWcgB_KHCje69FdJ-FS4hgXatQl2QIzkcEfySsLdMO7qlB8EHTkogDuQsHJ7QSOPEvwPzm9fStBuKqAbAfw';

console.log('=== 真实APP加密信息 ===');
console.log('NonceStr:', realNonceStr);
console.log('RequestId:', realRequestId);
console.log('Nonce长度:', realNonceStr.length, '(MD5=32 hex chars)');

// 尝试多种Nonce生成方式
const method = 'POST';
const query = '';
const path = '/go/sdk/quick_login';

// 方式1: nonce = MD5(method + query + body_plaintext + requestId) 
//   但不知道body_plaintext，跳过

// 方式2: nonce = MD5(method + query + body_ciphertext + requestId)
const nonce2 = md5(method + query + realEncryptedBody + realRequestId);
console.log('\n方式2 (body=ciphertext):', nonce2);
console.log('匹配?', nonce2 === realNonceStr);

// 方式3: nonce = MD5(method + path + query + body_ciphertext + requestId)
const nonce3 = md5(method + path + query + realEncryptedBody + realRequestId);
console.log('\n方式3 (method+path+query+body+rid):', nonce3);
console.log('匹配?', nonce3 === realNonceStr);

// 方式4: nonce = MD5(method + query + requestId) — 不包含body
const nonce4 = md5(method + query + realRequestId);
console.log('\n方式4 (不含body):', nonce4);
console.log('匹配?', nonce4 === realNonceStr);

// 方式5: nonce = MD5(requestId) 本身
const nonce5 = md5(realRequestId);
console.log('\n方式5 (仅requestId):', nonce5);
console.log('匹配?', nonce5 === realNonceStr);

// 方式6: nonce = MD5(method + path + query + requestId)
const nonce6 = md5(method + path + query + realRequestId);
console.log('\n方式6 (method+path+query+rid):', nonce6);
console.log('匹配?', nonce6 === realNonceStr);

// 找到匹配的nonce生成方式后再尝试解密
// 先假设方式2是对的，尝试解密
console.log('\n=== 尝试解密（用方式2的key） ===');
try {
  const encKey = GATEWAY_DEFAULT_KEY + nonce2.substring(0, 16);
  const iv = nonce2.substring(nonce2.length - 16);
  console.log('Key:', encKey, '(len=' + encKey.length + ')');
  console.log('IV:', iv, '(len=' + iv.length + ')');
  
  const b64Body = fromBase64Url(realEncryptedBody);
  const decipher = createDecipheriv('aes-256-cbc', Buffer.from(encKey, 'utf8'), Buffer.from(iv, 'utf8'));
  decipher.setAutoPadding(true);
  let decrypted = decipher.update(b64Body, 'base64', 'utf8');
  decrypted += decipher.final('utf8');
  console.log('\n✅ 解密成功! 明文:');
  console.log(decrypted);
} catch (e) {
  console.log('❌ 解密失败:', e.message);
}

// 用真实NonceStr再试
console.log('\n=== 尝试解密（用真实NonceStr的key） ===');
try {
  const encKey = GATEWAY_DEFAULT_KEY + realNonceStr.substring(0, 16);
  const iv = realNonceStr.substring(realNonceStr.length - 16);
  console.log('Key:', encKey, '(len=' + encKey.length + ')');
  console.log('IV:', iv, '(len=' + iv.length + ')');
  
  const b64Body = fromBase64Url(realEncryptedBody);
  const decipher = createDecipheriv('aes-256-cbc', Buffer.from(encKey, 'utf8'), Buffer.from(iv, 'utf8'));
  decipher.setAutoPadding(true);
  let decrypted = decipher.update(b64Body, 'base64', 'utf8');
  decrypted += decipher.final('utf8');
  console.log('\n✅ 解密成功! 明文:');
  console.log(decrypted);
} catch (e) {
  console.log('❌ 解密失败:', e.message);
}
