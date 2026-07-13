import { execSync } from 'child_process';
import fs from 'fs';
import { chromium } from 'playwright';

const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args){return execSync(`curl.exe -s ${args}`,{encoding:'utf8',maxBuffer:1<<26});}
const log=(...a)=>{const s=a.join(' ');console.log(s);fs.appendFileSync('_launch.log',s+'\n');};

// 用法: node launch.mjs [164|165] [stay]
const TARGET = process.argv[2] || '164';
const STAY = process.argv[3] === 'stay';

const login=curl(`-X POST "https://mobile.28zhe.com/api/v1/user/login" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" -H "Authorization: bearer " -H "User-Agent: ${UA}" --data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`);
const cookies=JSON.parse(login).data?.cookie||[];

const browser=await chromium.launch({ headless: STAY?false:true, args:['--no-sandbox'] });
const ctx=await browser.newContext({ userAgent: UA });
for(const c of cookies) for(const dom of ['28zhe.com','www.28zhe.com','mobile.28zhe.com','oss.28zhe.com','5144wan.com','xplay.5144wan.com','xauth.5144wan.com','600uu.com','applyapi.600uu.com','admin.m.5144wan.com']){ try{await ctx.addCookies([{name:c.name,value:c.value,domain:dom,path:'/'}]);}catch(e){} }
const page=await ctx.newPage();

try{
  log('打开 28 play 页 (10274 = 斗罗大陆H5 / 黄金魂环)...');
  await page.goto('https://www.28zhe.com/play/10274.htm',{waitUntil:'networkidle',timeout:30000});
  await page.waitForTimeout(2000);
  log('点击「进入游戏」...');
  for(const sel of ['a:has-text("进入游戏")','button:has-text("进入游戏")','text=进入游戏']){ try{const el=await page.$(sel); if(el){await el.click(); log('clicked',sel); break;}}catch(e){} }
  // 等待引擎 iframe 出现
  await page.waitForTimeout(6000);
  const frames=await page.evaluate(()=>[...document.querySelectorAll('iframe')].map(i=>i.src));
  const engine=frames.find(f=>/600uu\.com\/h5game|5144wan|entrance/.test(f));
  log('引擎 iframe:', engine||'(未找到)');
  if(engine){
    log('直接加载引擎页(顶层)...');
    await page.goto(engine,{waitUntil:'networkidle',timeout:30000}).catch(e=>log('goto err',e.message));
    await page.waitForTimeout(8000);
    await page.screenshot({path:'_engine.png', fullPage:false});
    log('引擎页 URL:', page.url());
    log('截图 -> _engine.png');
    const txt=await page.evaluate(()=>document.body?document.body.innerText.slice(0,300):'');
    log('引擎页文本:', txt.replace(/\n+/g,' | '));
  } else {
    await page.screenshot({path:'_engine.png'});
    log('截图 -> _engine.png (未找到引擎iframe, 见页面)');
  }
}catch(e){ log('ERROR', e.message); }
if(STAY){ log('保持打开，可游玩。关闭浏览器窗口即结束。'); await new Promise(r=>browser.on('disconnected',r)); }
else { await browser.close(); log('DONE (已退出)'); }
