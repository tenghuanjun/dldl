import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const login = curl(
  `-X POST "https://mobile.28zhe.com/api/v1/user/login" ` +
  `-H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ` +
  `-H "Authorization: bearer " -H "User-Agent: ${UA}" ` +
  `--data-urlencode "username=665094722" --data-urlencode "password=a13846026511" --data-urlencode "version=2"`
);
let j; try { j = JSON.parse(login); } catch(e){ console.log('RAW', login.slice(0,300)); process.exit(1); }
console.log('code:', j.code, 'msg:', j.msg);
console.log('cookie 数组:');
console.log(JSON.stringify(j.data?.cookie, null, 2));
console.log('token:', j.data?.token);
