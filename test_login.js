const start = Date.now();
console.log('开始测试...');

const controller = new AbortController();
setTimeout(() => controller.abort(), 120000); // 2分钟超时

const url = 'https://red-moon-ff95.tenghuanjun.workers.dev/api/app-login';
const body = JSON.stringify({ uname: 'ymzpjh1984', upwd: '789789L' });

fetch(url, {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body,
  signal: controller.signal
})
.then(r => r.text())
.then(text => {
  console.log('耗时:', (Date.now() - start) / 1000, '秒');
  console.log('响应:', text.slice(0, 500));
  try {
    const json = JSON.parse(text);
    console.log('结果:', json.ok ? '✅ 成功' : '❌ 失败');
    console.log('message:', json.message);
    console.log('token:', json.token ? json.token.slice(0, 30) + '...' : '无');
  } catch {
    console.log('响应(原文):', text);
  }
})
.catch(e => console.log('错误:', e.message));
