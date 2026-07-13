import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const login = curl(
  `-X POST "https://mobile.28zhe.com/api/v1/user/login" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
const j = JSON.parse(login);
const cookies = j.data?.cookie || [];
const cookieStr = cookies.map(c => `${c.name}=${c.value}`).join('; ');
const token = j.data?.token || '';
const hdr = `-H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}" -H "Authorization: Bearer ${token}"`;

const candidates = [
  'https://www.28zhe.com/index.php?ac=game&op=home',
  'https://www.28zhe.com/index.php?ac=game&op=list',
  'https://mobile.28zhe.com/api/v1/game/home',
  'https://mobile.28zhe.com/api/v1/game/list',
  'https://mobile.28zhe.com/api/v1/game/search?keyword=%E9%BB%84%E9%87%91%E9%AD%82%E7%8E%AF',
  'https://mobile.28zhe.com/api/v1/game/getHomeGameList',
];
for (const u of candidates) {
  console.log('\n===== ' + u);
  const r = curl(`-s -m 15 -i "${u}" ${hdr}`);
  const sc = (r.match(/HTTP\/\d\.\d (\d+)/) || [])[1];
  const body = r.split('\r\n\r\n').slice(1).join('\r\n\r\n');
  const hasGame = /黄金魂环|魂环|game|gid|play|斗罗/.test(body);
  console.log('status', sc, 'len', body.length, '含游戏字段:', hasGame);
  console.log(body.slice(0, 260).replace(/\s+/g,' '));
  if (/黄金魂环/.test(body)) console.log('  ★ 命中黄金魂环!');
}
