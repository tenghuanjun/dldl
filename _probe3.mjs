import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const base = 'http://xplay.5144wan.com';
// 服列表页
const listUrl = `${base}/server/?gid=10233298&pid=787`;
console.log('===== 服列表页:', listUrl, '=====');
const html = curl(`-L "${listUrl}" -H "User-Agent: ${UA}"`);
const title = (html.match(/<title>([^<]*)<\/title>/i) || [])[1];
console.log('TITLE:', title);
for (const l of html.split('\n')) {
  if (/gameName|斗罗|gid|pid|server|服|进入|play|gameid/i.test(l)) console.log('  ', l.trim().slice(0, 300));
}
// 找 JS 文件
const js = [...html.matchAll(/(?:src|href)="([^"]+\.js[^"]*)"/g)].map(m => m[1]);
console.log('JS:', js);

// 下载 GameServer.js 找进入游戏 URL 模板
for (const j of js) {
  const ju = j.startsWith('http') ? j : (j.startsWith('/') ? base + j : base + '/' + j);
  try {
    const s = curl(`-L "${ju}" -H "User-Agent: ${UA}"`);
    const hits = s.split('\n').filter(l => /gid|pid|server|play|game|location|href|url|sid|role|enter/i.test(l));
    if (hits.length) { console.log(`\n##### ${j} #####`); for (const h of hits) console.log('   ', h.trim().slice(0, 400)); }
  } catch (e) {}
}

// 尝试访问带 sid 的进入游戏地址（常见格式）
for (const u of [
  `${base}/play/?gid=10233298&pid=787`,
  `${base}/?gid=10233298&pid=787&sid=1`,
  `${base}/game/?gid=10233298&pid=787`,
]) {
  console.log(`\n===== ${u} =====`);
  const r = curl(`-i -L --max-redirs 8 "${u}" -H "User-Agent: ${UA}"`);
  const loc = (r.match(/location: ([^\r\n]+)/i) || [])[1];
  if (loc) console.log('REDIRECT ->', loc.trim());
  const b = r.split('\r\n\r\n').slice(1).join('\r\n\r\n');
  const t = (b.match(/<title>([^<]*)<\/title>/i) || [])[1];
  console.log('TITLE:', t || '(无)', ' LEN', b.length);
}
