import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const base = 'http://xplay.5144wan.com';
// 1) 带 pid 再试一次
console.log('=== 带 pid=787 ===');
const a = curl(`-X POST "${base}/api/system/" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: ${UA}" --data-urlencode "cmd=get.server.list" --data-urlencode "gid=1023329" --data-urlencode "pid=787"`);
console.log('RAW:', a.slice(0,300));
// 2) 下 SDK 看 Ajax 注入什么
const sdk = curl(`"${base}/api/center/XYUS.js" -H "User-Agent: ${UA}"`);
const fs = await import('fs'); fs.writeFileSync('_XYUS.js', sdk);
console.log('\n=== XYUS.js len', sdk.length, '===');
for (const kw of ['token','sign','pid','uid','Ajax','cookie','getCookie','localStorage','session','encrypt','md5','gameid']) {
  const ms = sdk.match(new RegExp('.{0,30}'+kw+'.{0,60}','g'));
  if (ms) { console.log('\n## '+kw); ms.slice(0,5).forEach(m=>console.log('  ',m.trim())); }
}
