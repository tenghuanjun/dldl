// 用真实数据验证响应解密
import { createDecipheriv } from 'node:crypto';
import { Buffer } from 'node:buffer';

const GATEWAY_DEFAULT_KEY = 'soC2GAr8jN2fsbry';

function fromBase64Url(s) {
  s = s.replace(/-/g, '+').replace(/_/g, '/');
  while (s.length % 4) s += '=';
  return s;
}

// 真实抓包数据
const requestNonce = '191f98499f09e553cb7aaedf0b03bd8d';
const responseNonce = '7267e5f95da845c6e2c7770fd74eb8bc';
const encryptedRespBody = 'uPAmRtYHpB--OG9a_UAypCxq1NOrzJlYC8eMwQlUxokVUKEdIQeuqapzI7Ua64lLSP7jWP2ywjA3qFaeH-R6ed7iR_kn64CCI2iBcKbZW_MRHW_qA7Upl5iZopDmkaXY4oaqAOFQP_ZPI0Po8UjQagvAaG1L1pI2Irn84zmxd3gV6FEhjIxraz-dcho-MNe9lmOs7IWJ9TctTbLBQKMkQJVhftHbiExsHX-rzb0oxUdGIli-oJH0Mmkhm89qcaXE5qwa9GRAfHM6GKPyMs30TMkXzam-7PdgirEraRFphXQJw0ZzQ-O6TnTDRgNRCK0yde_FN1pZ4Vv94u-nB0n33IEbEVV9sm51r0PvFVjugoIdjEZax9z2-h_0-QuAB-O2kGSquW3DTd3cFe7SMq2ENIYVTkr52q81hQCPSpBwKENkCvkbtxzaRglKfJbPaJAuGXKmPzorL2JZyopH1K0d4NmjJwZUtQZ72i9udKOErppIOW2qHeJnoZgUzCr71NyBXoYSSc8HZTlFGD1v4oGubKJqRehndEaBDh8QoZOqK6ZnJyt240pzp3QxEjpgOHvMSCj3IYXCi4qKhJiUB9un_zn8FyX2KVAndeogRT1cwVD9_avSLhpyVsh3HUiNYN5kapaiphmn9uskWQXwNEzjUA';

// SDK源码中的响应解密:
// decryptKey = provideKey() + requestNonce[0:8] + responseNonce[0:8]
// iv = responseNonce[8:24]

const decryptKey = GATEWAY_DEFAULT_KEY + requestNonce.substring(0, 8) + responseNonce.substring(0, 8);
const iv = responseNonce.substring(8, 24);

console.log('=== 响应解密测试 ===');
console.log('Request Nonce:', requestNonce);
console.log('Response Nonce:', responseNonce);
console.log('Decrypt Key:', decryptKey, '(len=' + decryptKey.length + ')');
console.log('Decrypt IV:', iv, '(len=' + iv.length + ')');
console.log('Encrypted Body长度:', encryptedRespBody.length, 'chars');

try {
  const b64Body = fromBase64Url(encryptedRespBody);
  console.log('Base64解码后长度:', b64Body.length, 'bytes');
  
  const decipher = createDecipheriv('aes-256-cbc', Buffer.from(decryptKey, 'utf8'), Buffer.from(iv, 'utf8'));
  decipher.setAutoPadding(true);
  let decrypted = decipher.update(b64Body, 'base64', 'utf8');
  decrypted += decipher.final('utf8');
  
  console.log('\n✅ 解密成功!');
  console.log('明文内容:');
  // 尝试格式化JSON
  try {
    console.log(JSON.stringify(JSON.parse(decrypted), null, 2));
  } catch {
    console.log(decrypted);
  }
} catch (e) {
  console.log('❌ 解密失败:', e.message);
}
