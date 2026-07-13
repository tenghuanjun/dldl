import { execSync } from 'child_process';
import fs from 'fs';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const login = curl(`-X POST "https://mobile.28zhe.com/api/v1/user/login" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" -H "Authorization: bearer " -H "User-Agent: ${UA}" --data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`);
const auth = ((JSON.parse(login).data?.cookie || []).find(c => c.name === '28zhe_auth') || {}).value || '';
const cookieStr = `28zhe_auth=${auth}`;

// op=account 接口完整响应
const r = curl(`-X POST "https://www.28zhe.com/index.php?ac=play&op=account&inajax=1&id=10274" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
fs.writeFileSync('_opaccount.xml', r);
console.log('LEN', r.length);
// 提取 CDATA 内 HTML
const m = r.match(/<!\[CDATA\[(.*?)\]\]>/s);
const html = m ? m[1] : r;
fs.writeFileSync('_opaccount.html', html);
console.log('=== iframe/src/gid/pid/游戏地址 ===');
for (const l of html.split('\n')) {
  if (/iframe|src=|gid=|pid=|10233298|787|\.htm|\.php|game|cdn|oss|location|engine|cocos|laya|egret/i.test(l)) {
    console.log(l.trim().slice(0, 600));
  }
}
