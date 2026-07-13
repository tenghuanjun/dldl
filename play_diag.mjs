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
for (const c of cookies) for (const dom of ['28zhe.com','www.28zhe.com','mobile.28zhe.com','oss.28zhe.com','5144wan.com','xplay.5144wan.com']) {
  try { await ctx.addCookies([{ name: c.name, value: c.value, domain: dom, path: '/' }]); } catch(e){}
}
const page = await ctx.newPage();
page.on('response', async resp => {
  const u = resp.url();
  if (/get\.server\.list|get\.rz\.list/.test(u)) {
    try {
      const t = await resp.text();
      const code = (t.match(/"code"\s*:\s*(\d+)/)||[])[1];
      console.log('RESP', u.slice(0,50), 'code=', code);
      if (/server|gname|gameid|黄金|魂环|164|165/.test(t)) {
        // 提取 gameid 和服务器
        const gid = (t.match(/"gameid"\s*:\s*"?(\d+)/)||[])[1];
        const gname = (t.match(/"gname"\s*:\s*"([^"]*)"/)||[])[1];
        console.log('  gameid=', gid, ' gname=', gname);
        const srv = [...t.matchAll(/"servername"\s*:\s*"([^"]*)"[^}]*?"serverid"\s*:\s*"?(\d+)/g)];
        srv.forEach(m=>{ if(/164|165|黄金|魂环/.test(m[1])||[164,165].includes(+m[2])) console.log('   ★', m[1], 'sid=', m[2]); });
        if (srv.length<30) srv.slice(0,12).forEach(m=>console.log('    srv:', m[1], m[2]));
      }
    } catch(e){}
  }
});

for (const id of [10274, 191986]) {
  const url = `https://www.28zhe.com/play/${id}.htm`;
  console.log('\n======== OPEN ' + url);
  await page.goto(url, { waitUntil:'networkidle', timeout:30000 }).catch(e=>console.log('goto err',e.message));
  await page.waitForTimeout(7000);
  console.log('FINAL URL:', page.url());
  const txt = await page.evaluate(()=>document.body?document.body.innerText.slice(0,200):'');
  console.log('TEXT:', txt.replace(/\n+/g,' | '));
  await page.screenshot({ path: `_play_${id}.png` });
}
await browser.close();
