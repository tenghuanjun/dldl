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
const H = `-H "Cookie: ${cookieStr}" -H "User-Agent: ${UA}" -H "Authorization: bearer ${token}"`;
for (const kw of ['黄金魂环','黄金','魂环','斗罗大陆H5']) {
  const u = `https://mobile.28zhe.com/api/v1/search/list?keyword=${encodeURIComponent(kw)}&page=1&limit=20`;
  const r = curl(`"${u}" ${H}`);
  let o; try{o=JSON.parse(r);}catch(e){console.log(kw,'RAW',r.slice(0,300));continue;}
  console.log(`\n=== keyword=${kw} code=${o.code} total=${o.data?.total} ===`);
  (o.data?.data||[]).forEach(g=>console.log('  id=',g.id,'name=',g.name, ' playurl=',g.playurl||g.url||'', ' gid=',g.gid||g.gameid||''));
}
