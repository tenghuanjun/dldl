import { execSync } from 'child_process';
import { chromium } from 'playwright';

const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

// 1) 登录拿 28zhe_auth
const login = curl(
  `-X POST "https://mobile.28zhe.com/api/v1/user/login" ` +
  `-H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
const j = JSON.parse(login);
const cookies = j.data?.cookie || [];
const auth = (cookies.find(c => c.name === '28zhe_auth') || {}).value || '';
console.log('28zhe_auth len =', auth.length);

// 2) 启动浏览器
const browser = await chromium.launch({ headless: false });
const ctx = await browser.newContext({ userAgent: UA });
// 注入 28 域 cookie
for (const c of cookies) {
  for (const dom of ['28zhe.com', 'www.28zhe.com', 'mobile.28zhe.com', 'oss.28zhe.com']) {
    try { await ctx.addCookies([{ name: c.name, value: c.value, domain: dom, path: '/' }]); } catch(e){}
  }
}
const page = await ctx.newPage();
page.on('response', async (resp) => {
  const u = resp.url();
  if (/get\.server\.list|get\.rz\.list|api\/system|api\/center/.test(u)) {
    try {
      const t = await resp.text();
      let code = (t.match(/"code"\s*:\s*(\d+)/) || [])[1];
      console.log('RESP', resp.status(), u.slice(0,70), 'code=', code, ' body=', t.slice(0,160));
    } catch(e){}
  }
});
page.on('console', m => { if (/server|rz|gameid|code|error|token/i.test(m.text())) console.log('CONSOLE:', m.text().slice(0,200)); });

// 3) 直接打开 xplay 服列表页
const target = 'http://xplay.5144wan.com/server/?gid=1023329&pid=787';
console.log('NAV ->', target);
await page.goto(target, { waitUntil: 'networkidle', timeout: 30000 }).catch(e=>console.log('goto err', e.message));
await page.waitForTimeout(6000);
const txt = await page.evaluate(() => document.body ? document.body.innerText.slice(0,400) : '(no body)');
console.log('PAGE TEXT:', txt.replace(/\n+/g,' | '));
// 看 Vue 是否渲染出服务器（tableData/serverOk）
const hasServer = await page.evaluate(() => /服|server|黄金魂环|魂环|164|165/.test(document.body?.innerText||''));
console.log('PAGE 含服信息:', hasServer);
await page.screenshot({ path: '_diag.png' });
console.log('截图已存 _diag.png');
await browser.close();
