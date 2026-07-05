// 测试 GET 请求的 nonce 生成
import { createCipheriv } from 'node:crypto';
import { Buffer } from 'node:buffer';

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

function toBase64Url(b64) { return b64.replace(/\+/g, '-').replace(/\//g, '_').replace(/\n/g, '').replace(/=+$/g, ''); }
function fromBase64Url(s) { s = s.replace(/-/g, '+').replace(/_/g, '/'); while (s.length % 4) s += '='; return s; }

const GATEWAY_DEFAULT_KEY = 'soC2GAr8jN2fsbry';
const realXRequestId = '291fc81cf64afbb0265f3ea01e5e3160';
const realNonce = 'f7033722849e4fab4ab480c9faa64029';

// 这个GET请求的query是加密的，我们需要猜测plaintext query
// game_url_list 可能的原始query: "gid=1003279&pid=46&..." 类似
// 尝试几种可能的plaintext query

const testQueries = [
  '',
  'gid=1003279',
  'gid=1003279&pid=46',
  'gid=1002997&pid=1',
  'gid=1003279&pid=46&os=android',
];

console.log('=== 测试 GET game_url_list 的 nonce ===');
console.log('真实 Nonce:', realNonce);
console.log('真实 X-Request-Id:', realXRequestId);

for (const q of testQueries) {
  const n = md5('GET' + q + realXRequestId);
  const match = n === realNonce ? ' ✅ MATCH!' : '';
  console.log(`Query="${q}" => ${n}${match}`);
}

// 也试试：query参数中没有key，只有value的情况
// 如果原始query是类似 "key=value" 格式，被加密后的格式是整个query加密
// nonce = MD5("GET" + original_plaintext_query + xRequestId)
// 我们不知道original_plaintext_query

// 试试空query（query参数可能是path的一部分）
const nEmpty = md5('GET' + '' + realXRequestId);
console.log(`Query="" => ${nEmpty}${nEmpty === realNonce ? ' ✅' : ''}`);

// 试试只包含 METHOD
const nMethodOnly = md5('GET' + realXRequestId);
console.log(`只有 "GET"+rid => ${nMethodOnly}${nMethodOnly === realNonce ? ' ✅' : ''}`);
