import { execSync } from 'child_process';
import fs from 'fs';

const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

// 1) 登录并取 28zhe_auth
const login = curl(
  `-X POST "https://mobile.28zhe.com/api/v1/user/login" ` +
  `-H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
const j = JSON.parse(login);
const cookieArr = j.data?.cookie || [];
const auth = (cookieArr.find(c => c.name === '28zhe_auth') || {}).value || '';
console.log('28zhe_auth len=', auth.length);
const cookieStr = `28zhe_auth=${auth}`;
console.log('cookie:', cookieStr.slice(0, 60) + '...');

// 2) 带 cookie 抓 play/343.htm 完整 HTML
for (const gid of [343, 10274, 8653, 10233298]) {
  console.log(`\n======== play/${gid}.htm (带cookie) ========`);
  const html = curl(`-L --max-redirs 8 -i "https://www.28zhe.com/play/${gid}.htm" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
  const loc = (html.match(/location: ([^\r\n]+)/gi) || []).map(x => x.trim());
  if (loc.length) console.log('REDIRECTS:', loc.join('  ->  '));
  const body = html.split('\r\n\r\n').slice(1).join('\r\n\r\n');
  const title = (body.match(/<title>([^<]*)<\/title>/i) || [])[1];
  if (title) console.log('TITLE:', title);
  // 找所有 url
  const urls = [...body.matchAll(/(?:href|src|location|url|=)\s*[:=]?\s*["']?([^"'\s>]+)/gi)]
    .map(m => m[1]).filter(u => /http|\.htm|\.php|\.html|gid=|pid=|game|play|index/i.test(u));
  console.log('URLS:', [...new Set(urls)].slice(0, 30).join('\n  '));
  if (gid === 343) fs.writeFileSync('_p343_login.html', body);
}

// 3) 也尝试 tel-login 登录页，看跳转地址
console.log('\n======== tel-login?type=old&gameId=343 ========');
const tl = curl(`-L --max-redirs 8 -i "https://www.28zhe.com/dist/tel-login?type=old&gameId=343" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
const tlloc = (tl.match(/location: ([^\r\n]+)/gi) || []).map(x => x.trim());
if (tlloc.length) console.log('REDIRECTS:', tlloc.join('  ->  '));
const tlbody = tl.split('\r\n\r\n').slice(1).join('\r\n\r\n');
const tlurls = [...tlbody.matchAll(/(?:href|src|location|url)\s*[:=]?\s*["']?([^"'\s>]+)/gi)].map(m => m[1]).filter(u => /http|\.htm|\.php|\.html|gid=|pid=|game|play|index/i.test(u));
console.log('URLS:', [...new Set(tlurls)].slice(0, 30).join('\n  '));
