// 正向加密测试 —— 验证我们的加密是否能被真实APP/服务器认可
import { createCipheriv, createDecipheriv } from 'node:crypto';
import { Buffer } from 'node:buffer';

const GATEWAY_DEFAULT_KEY = 'soC2GAr8jN2fsbry';
const APP_KEY = 'CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1';

// ==================== MD5 ====================
function md5(string) {
  function rotateLeft(lV, iS) { return (lV << iS) | (lV >>> (32 - iS)); }
  function addUnsigned(lX, lY) {
    const lX4 = lX & 0x40000000, lY4 = lY & 0x40000000, lX8 = lX & 0x80000000, lY8 = lY & 0x80000000;
    const lR = (lX & 0x3FFFFFFF) + (lY & 0x3FFFFFFF);
    if (lX4 & lY4) return lR ^ 0x80000000 ^ lX8 ^ lY8;
    if (lX4 | lY4) { if (lR & 0x40000000) return lR ^ 0xC0000000 ^ lX8 ^ lY8; else return lR ^ 0x40000000 ^ lX8 ^ lY8; }
    else return lR ^ lX8 ^ lY8;
  }
  function F(x,y,z) { return (x&y) | ((~x)&z); }
  function G(x,y,z) { return (x&z) | (y&(~z)); }
  function H(x,y,z) { return x^y^z; }
  function I(x,y,z) { return y^(x|(~z)); }
  function FF(a,b,c,d,x,s,ac) { a = addUnsigned(a, addUnsigned(addUnsigned(F(b,c,d), x), ac)); return addUnsigned(rotateLeft(a, s), b); }
  function GG(a,b,c,d,x,s,ac) { a = addUnsigned(a, addUnsigned(addUnsigned(G(b,c,d), x), ac)); return addUnsigned(rotateLeft(a, s), b); }
  function HH(a,b,c,d,x,s,ac) { a = addUnsigned(a, addUnsigned(addUnsigned(H(b,c,d), x), ac)); return addUnsigned(rotateLeft(a, s), b); }
  function II(a,b,c,d,x,s,ac) { a = addUnsigned(a, addUnsigned(addUnsigned(I(b,c,d), x), ac)); return addUnsigned(rotateLeft(a, s), b); }
  function cvt(string) {
    const b = new TextEncoder().encode(string), w = new Array(((((b.length+8)-(b.length+8)%64)/64)+1)*16-1);
    for (let i=0;i<b.length;i++) { const wc=(i-(i%4))/4;w[wc]=(w[wc]|(b[i]<<((i%4)*8))); }
    w[(b.length-(b.length%4))/4] |= 0x80 << ((b.length%4)*8);
    w[w.length-2]=b.length<<3; w[w.length-1]=b.length>>>29; return w;
  }
  function wth(v) { let r="",t;for(let i=0;i<=3;i++){t="0"+((v>>>(i*8))&255).toString(16);r+=t.substr(t.length-2,2);}return r; }
  const x=cvt(string); let a=0x67452301,b=0xEFCDAB89,c=0x98BADCFE,d=0x10325476;
  for(let k=0;k<x.length;k+=16){
    const A=a,B=b,C=c,D=d;
    a=FF(a,b,c,d,x[k+0],7,0xD76AA478);d=FF(d,a,b,c,x[k+1],12,0xE8C7B756);c=FF(c,d,a,b,x[k+2],17,0x242070DB);b=FF(b,c,d,a,x[k+3],22,0xC1BDCEEE);
    a=FF(a,b,c,d,x[k+4],7,0xF57C0FAF);d=FF(d,a,b,c,x[k+5],12,0x4787C62A);c=FF(c,d,a,b,x[k+6],17,0xA8304613);b=FF(b,c,d,a,x[k+7],22,0xFD469501);
    a=FF(a,b,c,d,x[k+8],7,0x698098D8);d=FF(d,a,b,c,x[k+9],12,0x8B44F7AF);c=FF(c,d,a,b,x[k+10],17,0xFFFF5BB1);b=FF(b,c,d,a,x[k+11],22,0x895CD7BE);
    a=FF(a,b,c,d,x[k+12],7,0x6B901122);d=FF(d,a,b,c,x[k+13],12,0xFD987193);c=FF(c,d,a,b,x[k+14],17,0xA679438E);b=FF(b,c,d,a,x[k+15],22,0x49B40821);
    a=GG(a,b,c,d,x[k+1],5,0xF61E2562);d=GG(d,a,b,c,x[k+6],9,0xC040B340);c=GG(c,d,a,b,x[k+11],14,0x265E5A51);b=GG(b,c,d,a,x[k+0],20,0xE9B6C7AA);
    a=GG(a,b,c,d,x[k+5],5,0xD62F105D);d=GG(d,a,b,c,x[k+10],9,0x2441453);c=GG(c,d,a,b,x[k+15],14,0xD8A1E681);b=GG(b,c,d,a,x[k+4],20,0xE7D3FBC8);
    a=GG(a,b,c,d,x[k+9],5,0x21E1CDE6);d=GG(d,a,b,c,x[k+14],9,0xC33707D6);c=GG(c,d,a,b,x[k+3],14,0xF4D50D87);b=GG(b,c,d,a,x[k+8],20,0x455A14ED);
    a=GG(a,b,c,d,x[k+13],5,0xA9E3E905);d=GG(d,a,b,c,x[k+2],9,0xFCEFA3F8);c=GG(c,d,a,b,x[k+7],14,0x676F02D9);b=GG(b,c,d,a,x[k+12],20,0x8D2A4C8A);
    a=HH(a,b,c,d,x[k+5],4,0xFFFA3942);d=HH(d,a,b,c,x[k+8],11,0x8771F681);c=HH(c,d,a,b,x[k+11],16,0x6D9D6122);b=HH(b,c,d,a,x[k+14],23,0xFDE5380C);
    a=HH(a,b,c,d,x[k+1],4,0xA4BEEA44);d=HH(d,a,b,c,x[k+4],11,0x4BDECFA9);c=HH(c,d,a,b,x[k+7],16,0xF6BB4B60);b=HH(b,c,d,a,x[k+10],23,0xBEBFBC70);
    a=HH(a,b,c,d,x[k+13],4,0x289B7EC6);d=HH(d,a,b,c,x[k+0],11,0xEAA127FA);c=HH(c,d,a,b,x[k+3],16,0xD4EF3085);b=HH(b,c,d,a,x[k+6],23,0x4881D05);
    a=HH(a,b,c,d,x[k+9],4,0xD9D4D039);d=HH(d,a,b,c,x[k+12],11,0xE6DB99E5);c=HH(c,d,a,b,x[k+15],16,0x1FA27CF8);b=HH(b,c,d,a,x[k+2],23,0xC4AC5665);
    a=II(a,b,c,d,x[k+0],6,0xF4292244);d=II(d,a,b,c,x[k+7],10,0x432AFF97);c=II(c,d,a,b,x[k+14],15,0xAB9423A7);b=II(b,c,d,a,x[k+5],21,0xFC93A039);
    a=II(a,b,c,d,x[k+12],6,0x655B59C3);d=II(d,a,b,c,x[k+3],10,0x8F0CCC92);c=II(c,d,a,b,x[k+10],15,0xFFEFF47D);b=II(b,c,d,a,x[k+1],21,0x85845DD1);
    a=II(a,b,c,d,x[k+8],6,0x6FA87E4F);d=II(d,a,b,c,x[k+15],10,0xFE2CE6E0);c=II(c,d,a,b,x[k+6],15,0xA3014314);b=II(b,c,d,a,x[k+13],21,0x4E0811A1);
    a=II(a,b,c,d,x[k+4],6,0xF7537E82);d=II(d,a,b,c,x[k+11],10,0xBD3AF235);c=II(c,d,a,b,x[k+2],15,0x2AD7D2BB);b=II(b,c,d,a,x[k+9],21,0xEB86D391);
    a=addUnsigned(a,A);b=addUnsigned(b,B);c=addUnsigned(c,C);d=addUnsigned(d,D);
  }
  return (wth(a)+wth(b)+wth(c)+wth(d)).toLowerCase();
}

// ==================== 加密（完全按SDK源码） ====================
function toBase64Url(b64) {
  return b64.replace(/\+/g, '-').replace(/\//g, '_').replace(/\n/g, '').replace(/=+$/g, '');
}

function fromBase64Url(s) {
  s = s.replace(/-/g, '+').replace(/_/g, '/');
  while (s.length % 4) s += '=';
  return s;
}

function gatewayEncrypt(plaintext, keyStr, ivStr) {
  const key = Buffer.from(keyStr, 'utf8');
  const iv = Buffer.from(ivStr, 'utf8');
  const cipher = createCipheriv('aes-256-cbc', key, iv);
  cipher.setAutoPadding(true);
  let enc = cipher.update(plaintext, 'utf8', 'base64');
  enc += cipher.final('base64');
  return toBase64Url(enc);
}

// ==================== 测试加密 ====================

// 模拟一个 quick_login 的 plaintext body（假设格式是 form-urlencoded）
const testBody = 'code=test123&token=mytoken&gid=1002997&pid=1&time=1783185304&sign=abc123';

// 生成 Request-Id 和 xRequestId（按SDK方式）
const reqId = 'iOS-46221-' + Date.now() + '-' + Math.random().toString(36).slice(2, 18);
const xRequestId = md5(reqId);

// 按SDK源码方式生成 nonce: MD5(method + query + body_plaintext + Cookie + Authorization + xRequestId)
const nonce = md5('POST' + '' + testBody + '' + '' + xRequestId);

// 加密
const encKey = GATEWAY_DEFAULT_KEY + nonce.substring(0, 16);
const encIv = nonce.substring(nonce.length - 16);
const encBody = gatewayEncrypt(testBody, encKey, encIv);

console.log('=== 正向加密测试 ===');
console.log('Plaintext Body:', testBody);
console.log('Plaintext 长度:', testBody.length, 'bytes');
console.log('Request-Id:', reqId);
console.log('x-request-id:', xRequestId);
console.log('Nonce:', nonce);
console.log('Nonce 长度:', nonce.length);
console.log('加密Key:', encKey, '(len=' + encKey.length + ')');
console.log('加密IV:', encIv, '(len=' + encIv.length + ')');
console.log('密文:', encBody.substring(0, 80) + '...');
console.log('密文长度:', encBody.length, 'chars');

// 解密验证
const decKey = GATEWAY_DEFAULT_KEY + nonce.substring(0, 16);
const decIv = nonce.substring(nonce.length - 16);
const b64Body = fromBase64Url(encBody);
const decipher = createDecipheriv('aes-256-cbc', Buffer.from(decKey, 'utf8'), Buffer.from(decIv, 'utf8'));
decipher.setAutoPadding(true);
let decrypted = decipher.update(b64Body, 'base64', 'utf8');
decrypted += decipher.final('utf8');

console.log('\n=== 解密验证 ===');
console.log('解密结果:', decrypted);
console.log('匹配?', decrypted === testBody ? '✅ YES' : '❌ NO');

// ==================== 测试直接用真实 nonce 解密真实密文 ====================
// 真实nonce和密文（可能密文是多段？检查是否有换行问题）
const realNonce = '191f98499f09e553cb7aaedf0b03bd8d';
const realBody = 'nFKwmofGk2xJqnZyu1Zmg-xjGsJhvj576OVij6e9mHNbqRGykzPYCcg3QJ8OJ3s7E8e3KRi34ksUf9Q42kDmXWvNf9PqDF9OCYAc6L-K5BNgEvWILEac6gv0MjlPfnnxv1blC9bhinMMTS8rMNLFh60RMw6sZNWdVPc3Ur4dAOZXOP2kFK4LG1Z9nt_lSEB9u5-FHi3MO-WbiyHZ2TPQvT9TQHKORu7jcIilcmrj-innHmbSjRiCcdADDhMowb9ZQpm68H6tNTF_ysacxnSRF-EBWSvVLOWPsJTQb-gdn2px7fot3ghcAnGu2s3vliSDmlURj9nDHbiAQ8SNbS--v7PuKc_5ZJlCCHzqphghbZpuIStB3pW6iz6cKRgv-02hxzRj8wjVd4ecnj1L97f5bPnOftFn5Gw2fckoJ_03sQsaey-qIbJfmUx966R1Qg04uXBCr5hEqDKBZZVpxEI2XQGd-6646rVDB53Wx9ONBR34fCYvxKJmH6OGXAe0hwccE8csTDQLMq0dXAlauAZLQj2yV4TzJr_EUjq5JP6REiFox3sfbrVAolDd9QPyOUiNARgNLkDb_Rbcr7DiDYgmA8l_mEQw1h_V1jrTWlzrHKa3IEg5PC-N6kqUUyxNNprO1H84FQRAyQwHcXAw-kdu7HdyIE_sdJ58vcYvNsm0MLFqbmlQgG4hZ5Eqb7pMTTzY1vTPJNuyVWWRQ1N_H8xWxfEC0Shw-LfEsQlWGw-nF2w6axCFGqrlTjIRHcblX0sA7wt3Zuz5MrfdG90xCbo-Lo3AIZImG8ppXKaPFqaUxVegmI1jAe-BdCJddtQQ0FBArlOEvkzzG0BQFchsdF2Y9RJwpRZVe2ImTEFgy44s5de7qO4wdFpHphi5Boj7eM-YOwaX3zsTJqqZuSTr6fOK2D0yCHRutWjOYXqvXnOYbqHoViRh9g8Kr6oLPuYFpbcKIclMtyw43x8PItNM8A0t6EdBA8y1IIrO7FMYDTLKnBW_7qM-jzdMzMTXSiRSs7JXmRwQqdlfRpVR0Sk0emKX-cCySI3FXG6J-lfHd7s9txamIlROI7xZazVUOf64y9gTjmkQSaJrun8Sorno-BoLQ0NaqvtTwUnykaKUkrjwtIPHcXoRdHzTcSBlerarg3dXSYs7EToDK1pQ9zdh0QrgNKg4q_j2tMxOHb4fKXZKSbN7ASiw__SzHPuDbd2qXGHUQ_zyxlnshjweQLY42qOnewUmIBhFUVlT2Dg6LyUK13w30BGOpVA0xlMBa-umWUAw9sDX8GfHDP7rRINlXjUsK8FcoeE3A_FcQZgcofzJUYBVwnkcc9LI62H3O7dgmfKEey0BxzclR1hZjTpvEJKglxpfBnbUh0UaZK5Xd7040B7R2y-Xfmg_v6kStGFEpPz90aekBQ6NHQJFj9eaEcpoNUk79INX3BYMvDwgitc23wnuKlJZErMz3OdQlOXCIg--00MtErHPqJgfKMQuWLSEGCqAjvp1NAnBnU9qIdWpsryMI6h3oyHI-I-qfzEcNC7H7QEbx5impNnE5CFVRK5vwNAlh5tV1muh0U0NYHBto-NMxx4Nt7rs3IzPO4Do1EorosrSkFOmMYyfM3gi-bk-pq3i8fQHTELcbunzjVdnxENRup7RFkX4U3DArso7DpXwooPjPapq3vdifMH2Lif2ThyUxTpYHUB57rZ1s6iHkZ2OYJeXKlB1_fTUsI1A24yPIDUv-q536DbMzeTYKEn_gTXRcZqLiH_AA94UBbaTLfU9X23UL1RZHdmXo2eN1I1FzNNGRfCT9HVYBMAhbGl9zmTG2B525T-MPYhYaOQqTs60VxPmbZKYIHC_8JjtZ2D9WtQVsmNO28MnslNbTuLFwVjQA2o5iCIZ4uZqtMHkm5xwklM4OTKmqzdCCyDO6DggodeWPTMGJdVn_aWHiiJvODVfbLIzb2JtiwYBll2Yc49aCozuoc5WFAXUMw5XonE_ptByip-FBP1LOR8wtTXmK--58p2CPMOxbkdPebCdR0crrKFJunM5-Fi6njone_F5UYM2IueoeurvilBVlBt8QcS6klIQKTmApwHdqw04VReCx5vNAOfGdkO3bYoNdBPIdCQ8pfCqoRNKapNt5JSCrSxZgG9yYckzx9B7aqsbmoqVX7pNmgPDUQEeMhXYzsjKuRZnWcgB_KHCje69FdJ-FS4hgXatQl2QIzkcEfySsLdMO7qlB8EHTkogDuQsHJ7QSOPEvwPzm9fStBuKqAbAfw';

console.log('\n=== 尝试用真实nonce解密真实密文 ===');

// 方式1: 标准key = defaultKey + nonce前16, iv = nonce后16
try {
  const key = GATEWAY_DEFAULT_KEY + realNonce.substring(0, 16);
  const iv = realNonce.substring(realNonce.length - 16);
  console.log('Key:', key);
  console.log('IV:', iv);
  const b64 = fromBase64Url(realBody);
  console.log('Base64长度:', b64.length);
  const d = createDecipheriv('aes-256-cbc', Buffer.from(key, 'utf8'), Buffer.from(iv, 'utf8'));
  d.setAutoPadding(true);
  let r = d.update(b64, 'base64', 'utf8');
  r += d.final('utf8');
  console.log('✅ 解密成功:', r.substring(0, 200));
} catch(e) {
  console.log('❌ 标准解密失败:', e.message);
}

// 方式2: 尝试 AES-128 (只用key前16字节)
try {
  const key16 = (GATEWAY_DEFAULT_KEY + realNonce.substring(0, 16)).substring(0, 16);
  const iv16 = realNonce.substring(realNonce.length - 16);
  console.log('\n方式2 AES-128 Key:', key16);
  const d = createDecipheriv('aes-128-cbc', Buffer.from(key16, 'utf8'), Buffer.from(iv16, 'utf8'));
  d.setAutoPadding(true);
  let r = d.update(fromBase64Url(realBody), 'base64', 'utf8');
  r += d.final('utf8');
  console.log('✅ 解密成功:', r.substring(0, 200));
} catch(e) {
  console.log('❌ AES-128解密失败:', e.message);
}

// 方式3: body中有换行符？试试拼接
console.log('\n密文是否含换行?:', realBody.includes('\n'));
console.log('密文长度字符:', realBody.length);

// 方式4: 检查Java getBytes() vs Node Buffer.from('utf8') 是否有差异
// 都是ASCII字符应该没区别
console.log('\nKey bytes (UTF8):', Buffer.from(GATEWAY_DEFAULT_KEY + realNonce.substring(0, 16), 'utf8').length, 'bytes');
console.log('IV bytes (UTF8):', Buffer.from(realNonce.substring(realNonce.length - 16), 'utf8').length, 'bytes');
