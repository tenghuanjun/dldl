import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const base = 'http://xplay.5144wan.com';
for (const gid of ['1023329','10233298']) {
  console.log('\n===== gid='+gid+' =====');
  const page = curl(`"${base}/server/?gid=${gid}&pid=787" -H "User-Agent: ${UA}"`);
  const t = (page.match(/<title>([^<]*)<\/title>/i)||[])[1];
  const hasJs = /GameServer\.js/.test(page);
  const err = /错误|不存在|error|gameid/i.test(page);
  console.log('title:', t||'(无)', ' hasGameServerJs:', hasJs, ' 含错误:', err);
  // 看页面里有没有游戏名/服务器信息
  const names = [...page.matchAll(/(黄金魂环|斗罗|魂环|gameName|gname)[^<]{0,40}/g)].map(m=>m[0]);
  names.slice(0,5).forEach(n=>console.log('   ', n));
}
