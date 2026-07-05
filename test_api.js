const https = require('https');
const data = JSON.stringify({ uname: 'ymzpjh1984', upwd: '789789L' });
const opts = {
  hostname: 'red-moon-ff95.tenghuanjun.workers.dev',
  path: '/api/app-login',
  method: 'POST',
  headers: { 'Content-Type': 'application/json', 'Content-Length': data.length },
  timeout: 90000
};
const req = https.request(opts, res => {
  let body = '';
  res.on('data', d => body += d);
  res.on('end', () => {
    console.log('Status:', res.statusCode);
    console.log('Body:', body.slice(0, 600));
    try { const j = JSON.parse(body); console.log('ok:', j.ok, 'message:', j.message, 'token:', j.token ? 'YES' : 'NO'); } catch {}
  });
});
req.on('error', e => console.log('Error:', e.message));
req.on('timeout', () => { req.destroy(); console.log('TIMEOUT'); });
req.write(data);
req.end();
