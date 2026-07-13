import { execSync } from 'child_process';
import fs from 'fs';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const login = curl(
  `-X POST "https://mobile.28zhe.com/api/v1/user/login" ` +
  `-H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
const j = JSON.parse(login);
const auth = ((j.data?.cookie || []).find(c => c.name === '28zhe_auth') || {}).value || '';
const cookieStr = `28zhe_auth=${auth}`;

// 抓 play/10274.htm 完整 HTML，找 gameInfoJs / 内联配置
const playHtml = curl(`-L "https://www.28zhe.com/play/10274.htm" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
fs.writeFileSync('_p10274.html', playHtml);
console.log('=== play/10274.htm 中 gameInfoJs / url / gid / pid ===');
for (const l of playHtml.split('\n')) {
  if (/gameInfoJs|var gameInfo|gid|pid|gameUrl|game_url|10233298|787|window\.location|var url|serverUrl/i.test(l)) {
    console.log(l.trim().slice(0, 600));
  }
}

// 调 play&op=account 接口拿 url
for (const id of [10274, 8653]) {
  console.log(`\n=== /index.php?ac=play&op=account&inajax=1&id=${id} ===`);
  const r = curl(`-L "https://www.28zhe.com/index.php?ac=play&op=account&inajax=1&id=${id}" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
  console.log(r.slice(0, 2000));
}
