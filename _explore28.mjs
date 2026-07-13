import { execSync } from 'child_process';

const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';

function curl(args, body) {
  let cmd = `curl.exe -s ${args}` + (body ? ` --data-urlencode "${body}"` : '');
  return execSync(cmd, { encoding: 'utf8', maxBuffer: 1<<26 });
}

// 1) 登录 28 拿 cookie + token
const loginHtml = curl(
  `-i -X POST "https://mobile.28zhe.com/api/v1/user/login" ` +
  `-H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
const headerPart = loginHtml.split('\r\n\r\n')[0];
const setCookie = (headerPart.match(/set-cookie: ([^\r\n]+)/gi) || []).map(s => s.replace(/set-cookie: /i, '').split(';')[0]);
const cookieStr = setCookie.join('; ');
console.log('=== SET-COOKIE ===');
console.log(cookieStr);

// 2) 抓取 play 页 HTML，找真实游戏地址
for (const gid of [343, 10274, 8653, 10233298]) {
  console.log(`\n===== play/${gid}.htm =====`);
  const html = curl(
    `-L --max-redirs 5 -i "https://www.28zhe.com/play/${gid}.htm" ` +
    `-H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`
  );
  // 打印响应头 location
  const loc = (html.match(/location: ([^\r\n]+)/i) || [])[1];
  if (loc) console.log('REDIRECT ->', loc.trim());
  // 提取含 gid/pid/10233298/787/iframe/src/.htm/.php 的行
  const lines = html.split(/\r?\n/);
  for (const l of lines) {
    if (/10233298|pid=787|gid=|pid=|\.htm|\.php|iframe|src=|webview|location\.|window\.location|game/i.test(l)) {
      console.log(l.trim().slice(0, 400));
    }
  }
}

// 3) 直接试带参数的组合
for (const url of [
  'https://www.28zhe.com/play/343.htm?gid=10233298&pid=787',
  'https://www.28zhe.com/play/10233298.htm?pid=787',
  'https://www.28zhe.com/play/10233298.htm',
]) {
  console.log(`\n===== ${url} =====`);
  const r = curl(`-L --max-redirs 5 -i "${url}" -H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}"`);
  const loc = (r.match(/location: ([^\r\n]+)/i) || [])[1];
  if (loc) console.log('REDIRECT ->', loc.trim());
  const title = (r.match(/<title>([^<]*)<\/title>/i) || [])[1];
  if (title) console.log('TITLE:', title);
  const lines = r.split(/\r?\n/);
  for (const l of lines) {
    if (/10233298|pid=787|gid=|pid=|\.htm|\.php|iframe|src=|location\.|game/i.test(l)) {
      console.log(l.trim().slice(0, 400));
    }
  }
}
