import { execSync } from 'child_process';
import fs from 'fs';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const login = curl(`-X POST "https://mobile.28zhe.com/api/v1/user/login" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" -H "Authorization: bearer " -H "User-Agent: ${UA}" --data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`);
const auth = ((JSON.parse(login).data?.cookie || []).find(c => c.name === '28zhe_auth') || {}).value || '';
const cookieStr = `28zhe_auth=${auth}`;

// 访问"进入游戏"链接，跟踪重定向
const urls = [
  'https://www.28zhe.com/index.php?ac=play&id=10274&aid=4689470&t=1008',
  'https://www.28zhe.com/index.php?ac=play&id=10274',
];
for (const u of urls) {
  console.log(`\n===== 跟踪: ${u} =====`);
  let cur = u, hops = [];
  for (let i = 0; i < 10; i++) {
    const r = curl(`-i -L --max-redirs 0 "${cur}" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
    const loc = (r.match(/location: ([^\r\n]+)/i) || [])[1];
    if (loc) {
      const next = loc.trim();
      hops.push(next);
      cur = next.startsWith('http') ? next : 'https://www.28zhe.com' + (next.startsWith('/') ? '' : '/') + next;
      console.log('  redirect ->', next);
      if (!next.startsWith('http') && !next.startsWith('/')) break;
    } else {
      console.log('  终态 (无重定向)');
      // 打印页面里的 URL / gid / pid / iframe / engine
      const body = r.split('\r\n\r\n').slice(1).join('\r\n\r\n');
      const title = (body.match(/<title>([^<]*)<\/title>/i) || [])[1];
      if (title) console.log('  TITLE:', title);
      for (const l of body.split('\n')) {
        if (/iframe|src=|gid=|pid=|10233298|787|\.htm|\.php|engine|cocos|laya|egret|game|location|http/i.test(l)) {
          console.log('  ', l.trim().slice(0, 500));
        }
      }
      break;
    }
  }
  console.log('  HOPS:', hops.join('  ->  '));
}
