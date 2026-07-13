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
for (const c of cookies) {
  for (const dom of ['28zhe.com', 'www.28zhe.com', 'mobile.28zhe.com', 'oss.28zhe.com']) {
    try { await ctx.addCookies([{ name: c.name, value: c.value, domain: dom, path: '/' }]); } catch(e){}
  }
}
const page = await ctx.newPage();
page.on('console', m => { if (/黄金魂环|魂环|game-detail|play|1023329/.test(m.text())) console.log('C:', m.text().slice(0,200)); });

// 1) 直接试搜索页
for (const url of [
  'https://www.28zhe.com/dist/search?keyword=%E9%BB%84%E9%87%91%E9%AD%82%E7%8E%AF',
  'https://www.28zhe.com/search?keyword=%E9%BB%84%E9%87%91%E9%AD%82%E7%8E%AF',
  'https://www.28zhe.com/dist/game?keyword=%E9%BB%84%E9%87%91%E9%AD%82%E7%8E%AF',
]) {
  console.log('\n===== ' + url);
  await page.goto(url, { waitUntil: 'networkidle', timeout: 20000 }).catch(e=>console.log('err',e.message));
  await page.waitForTimeout(2500);
  const links = await page.evaluate(() => {
    const a = [...document.querySelectorAll('a')].map(x=>({h:x.href, t:x.innerText})).filter(x=>/game-detail|play|黄金|魂环|\/game/i.test(x.h+x.t));
    const txt = document.body? document.body.innerText.slice(0,300):'';
    return {a: a.slice(0,20), txt};
  });
  console.log('links:', JSON.stringify(links.a));
  console.log('text:', links.txt.replace(/\n+/g,' | '));
}
await browser.close();
