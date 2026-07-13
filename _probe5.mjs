import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const base = 'http://xplay.5144wan.com';
// 取服列表页，找 GameServer.js 等脚本
const page = curl(`"${base}/server/?gid=10233298&pid=787" -H "User-Agent: ${UA}"`);
const scripts = [...page.matchAll(/<script[^>]+src=["']([^"']+)["']/gi)].map(m=>m[1]);
console.log('=== script srcs ===');
scripts.forEach(s=>console.log(' ', s));

// 尝试带 referer 再调一次 get.server.list，看 code:10 是否因缺 referer/cookie
console.log('\n=== get.server.list 带 referer ===');
const api2 = curl(
  `-X POST "${base}/api/center/" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: ${UA}" ` +
  `-H "Referer: ${base}/server/?gid=10233298&pid=787" ` +
  `--data-urlencode "cmd=get.server.list" --data-urlencode "gid=10233298" --data-urlencode "pid=787"`
);
console.log('RAW:', api2.slice(0,500));
