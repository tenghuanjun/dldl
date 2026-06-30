// 测试 APP 端参数混淆：解密 + 加密比对
const CryptoJS = require('crypto-js');
const crypto = require('crypto');

const ENCRYPT_KEY = '0123456789012345';
const userEncrypted = 's7rBVLGlm_w-Es3gmis8K5TnfLxNYEKPmKXK6BSGPqZ0-KO9NFyf4uLpYtYDkg1fVqbiutc9MtfVSwT9cDsprGvFM4FICAa5SH77skNZ57pJQgTAxRhRbqtZFrxI55bBjZIYk72sTMcyTG8KQd6w4t4xgdoych2M2Ix6zopGcZuoqvJGOHPkcMUAzfgSzq_6wURnZC2YRAikmzjah8QMKGTmmkxep6O7uPXDeE-hPPnS1RXcLLcLYVetIQ2RaKOb_4x6NQ57tLQ-grqJKaM2SzQyY_qIu-TpbCKiESpH1C9mafpqfm2ujTNETL6fpcz6z0i6rB5LV9fLhPq5LQVoCrk7WdNEjmCwKQzKbRj1PSzEFzArC3tKVkCNuWBF3T_PLuq5ngbsRiq_dQGg70fEtGZCDxB_Hjh7fWGJ2zRo6Uit9ux4pp4HzFhbsWruBCND9hybeq9jef1QzyTb4H0lcC-ioU_jBpOWXFpynNBxNZ2Y4_LEiE_h5jcnZ6hrguRGKo-QI01NoOvKrBE_BcFo0u2uspAUaSxWM3yr4F8Xy86JqPjcfF1kL9HFDjz6FR_yNkJL7hW9SkoCbGXJ0ns1sLawD2Rki-j2VPeVNBAIGieRdzpUJSsQ4YNSCzTHGkoTrMKfUSOTJ9kiLFy1H-Dyk-2mgYbzKVzEjbsM2xtiSKdPIgFVZEyDly9PIrTG2YbVhCbsc-wjM-8k-fDNO8saRWrPSxzXaE_AIJGgsxmg3OGl9m6o_TL7UXxL5XMYVaTpSMOT4aqepU0ETmpQIgU5IirxuXkbQG5T_dR3gYe7g8nZWjdW3P9wP4fuoLnG5WnZzu-UM62mPFPk_xn4paHE-O5SHYjAvziPrVwjnz19RVZnNlsyeW81FmsEhIZh0PhntGEQ89OlXjVDS5rF-ZKh6H5O9Kf01iHRDVDbCUhkB3o8obGv8FF7ZpcZYE0Xw5bfT3iPX_nB2Ecsj1xaSUQG64K0TnCkSvl_K-bnagJ2XZtDWPl6Oxoz5umEpOrzXKlHvz-4aTofyev8YlwKaMI7fNY72GD86QMdYGaUjkYX2fkGPVVAJG_qQxviz0hXgV8ijuCFwES6gTXkFTPl60kmp0B-_4V2e3442hKR0eNh4D4fYKt5qeeEFRG2TrXaPEY9wYwkKzgqfFtADJ_o6Rkjji-JPIuK60lvJvy1RyxewWRPBxVc-sOiZSGXDiMY3GIK0eoHiO1JhVtnfRmguqc-S6Y01OjUUrmT7JjgE266mP0rfT2EyH9ekwgJysJ35JM-tsj2gjElBr0jLlKNQOPD4LQuiEFf7gzPpfFQJxNrJrocza9DI3CjXLA9oDBjhbQztBkL2r8EsNjxlItL3Z6klP7nZvoEpnhbwy5qHXjduS-1lVESYpFxzmH5JuwF4pdqsgTzNbGGAzZxqq00sIrsFOEf-GPfJjLmEqtrTyLPFOt34tTm5SyLqIuiCoC5FTYwkTloC0rUKZ9H1ORzQQAQNkYvsIz5G_ish7dYajU3tXFPiuEuTzpZ8l_-2hUBneMn';

const key = CryptoJS.enc.Utf8.parse(ENCRYPT_KEY);

console.log('========== 尝试解密用户提供的密文 ==========');
console.log('密文长度:', userEncrypted.length);

try {
  // 方法1: 标准 CryptoJS AES-ECB 解密
  const decrypted = CryptoJS.AES.decrypt(userEncrypted, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  });
  const plainText = CryptoJS.enc.Utf8.stringify(decrypted);
  console.log('方法1 (ECB直接解密):');
  console.log(plainText.substring(0, 500));
  console.log('---');
} catch(e) {
  console.log('方法1 失败:', e.message);
}

try {
  // 方法2: 使用 Node crypto 模块直接 AES-ECB 解密
  const rawKey = Buffer.from(ENCRYPT_KEY, 'utf8');
  const ciphertext = Buffer.from(userEncrypted, 'base64');
  const decipher = crypto.createDecipheriv('aes-128-ecb', rawKey, null);
  decipher.setAutoPadding(true);
  let decrypted = decipher.update(ciphertext);
  decrypted = Buffer.concat([decrypted, decipher.final()]);
  console.log('方法2 (Node crypto AES-ECB):');
  console.log(decrypted.toString('utf8').substring(0, 500));
  console.log('---');
} catch(e) {
  console.log('方法2 失败:', e.message);
}
