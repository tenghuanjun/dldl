import { execSync } from 'child_process';
import { chromium } from 'playwright';

const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const login = curl(
  `-X POST "https://mobile.28zhe.com/api/v1/user/login" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
const j = JSON.parse(login);
const cookies = j.data?.cookie || [];

const browser = await chromium.launch({ headless: false });
const ctx = await browser.newContext({ userAgent: UA });
for (const c of cookies) for (const dom of ['28zhe.com','www.28zhe.com','mobile.28zhe.com','oss.28zhe.com']) {
  try { await ctx.addCookies([{ name: c.name, value: c.value, domain: dom, path: '/' }]); } catch(e){}
}
const page = await ctx.newPage();
const apiCalls = [];
page.on('request', req => {
  const u = req.url();
  if (/28zhe\.com\/api|28zhe\.com\/(dist|index|home)|game|search/i.test(u) && !/js|css|png|jpg|font/.test(u))
    apiCalls.push(u);
});
page.on('response', async resp => {
  const u = resp.url();
  if (/game|search|28zhe\.com\/api/i.test(u) && !/js|css|png|jpg|font/.test(u)) {
    try { const t = await resp.text();
      if (/黄金魂环|魂环/.test(t)) {
        console.log('★ 命中 API:', u);
        // 提取含 黄金魂环 的片段
        const idx = t.indexOf('黄金魂环');
        console.log(t.slice(Math.max(0,idx-200), idx+300));
      }
    } catch(e){}
  }
});

await page.goto('https://www.28zhe.com/dist/search', { waitUntil:'networkidle', timeout:20000 }).catch(()=>{});
await page.waitForTimeout(2000);
// 找搜索输入框
const inputs = await page.evaluate(() => [...document.querySelectorAll('input')].map(i=>({ph:i.placeholder, id:i.id, cls:i.className, type:i.type})));
console.log('INPUTS:', JSON.stringify(inputs));
// 尝试填充并回车
for (const sel of ['input[type=search]','input[placeholder*="搜索"]','input[placeholder*="游戏"]','input']) {
  const el = await page.$(sel);
  if (el) {
    console.log('fill', sel);
    await el.click(); await el.fill('黄金魂环'); await el.press('Enter');
    break;
  }
}
await page.waitForTimeout(3000);
console.log('CAPTURED API CALLS:');
[...new Set(apiCalls)].forEach(u=>console.log('  ', u));
await browser.close();
