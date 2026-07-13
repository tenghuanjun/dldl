import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const js = curl(`"http://xplay.5144wan.com/api/skin/js/GameServer.js" -H "User-Agent: ${UA}"`);
const fs = await import('fs');
fs.writeFileSync('_GameServer.js', js);
console.log('len', js.length);
// 找所有 ajax / fetch / get.server.list / enter / token / sign / uid / gid / pid
for (const kw of ['get.server.list','enter','token','sign','uid','gid','pid','ajax','location','gameid','sid','window.location','cookie']) {
  const re = new RegExp('.{0,40}'+kw+'.{0,80}', 'g');
  const ms = js.match(re);
  if (ms) { console.log('\n### '+kw+' ###'); ms.slice(0,8).forEach(m=>console.log('  ', m.trim())); }
}
