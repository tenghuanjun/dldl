import { execSync } from 'child_process';
import fs from 'fs';
import { chromium } from 'playwright';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args){return execSync(`curl.exe -s ${args}`,{encoding:'utf8',maxBuffer:1<<26});}
const log=(...a)=>{const s=a.join(' ');console.log(s);fs.appendFileSync('_playpage.log',s+'\n');};
const login=curl(`-X POST "https://mobile.28zhe.com/api/v1/user/login" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" -H "Authorization: bearer " -H "User-Agent: ${UA}" --data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`);
const cookies=JSON.parse(login).data?.cookie||[];
const browser=await chromium.launch({headless:true});
const ctx=await browser.newContext({userAgent:UA});
for(const c of cookies)for(const dom of ['28zhe.com','www.28zhe.com','mobile.28zhe.com','oss.28zhe.com','5144wan.com','xplay.5144wan.com','xauth.5144wan.com']){try{await ctx.addCookies([{name:c.name,value:c.value,domain:dom,path:'/'}]);}catch(e){}}
const page=await ctx.newPage();
const seen=new Set();
page.on('response',async resp=>{const u=resp.url();if(/5144wan|xplay|ac=play|get\.server\.list|get\.rz\.list/.test(u)){if(!seen.has(u)){seen.add(u);log('RESP',resp.status(),u.slice(0,120));}}});
page.on('request',req=>{const u=req.url();if(/5144wan|xplay|ac=play/.test(u))log('REQ',u.slice(0,120));});
try{
  await page.goto('https://www.28zhe.com/play/10274.htm',{waitUntil:'networkidle',timeout:30000});
  await page.waitForTimeout(2000);
  for(const sel of ['a:has-text("进入游戏")','button:has-text("进入游戏")','text=进入游戏']){try{const el=await page.$(sel);if(el){await el.click();log('clicked',sel);break;}}catch(e){}}
  await page.waitForTimeout(8000);
  log('URL now:',page.url());
  const frames=await page.evaluate(()=>[...document.querySelectorAll('iframe')].map(i=>i.src).concat([...document.querySelectorAll('frame')].map(i=>i.src)));
  log('iframes:',JSON.stringify(frames));
  const html=await page.content();
  const m=html.match(/xplay\.5144wan\.com[^"'\s]*/g)||html.match(/5144wan\.com[^"'\s]*/g);
  if(m)log('found in html:',[...new Set(m)].slice(0,10).join('\n  '));
}catch(e){log('ERR',e.message);}
await browser.close();
log('DONE');
