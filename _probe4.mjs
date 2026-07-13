import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const base = 'http://xplay.5144wan.com';
// 1) 调服务器列表 API
console.log('===== get.server.list API =====');
const api = curl(
  `-X POST "${base}/api/center/" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: ${UA}" ` +
  `--data-urlencode "cmd=get.server.list" --data-urlencode "gid=10233298" --data-urlencode "pid=787"`
);
console.log('RAW:', api.slice(0, 1500));

let gameid = null, serverid = null, gname = null, servers = [];
try {
  const j = JSON.parse(api);
  gname = j?.data?.gname;
  gameid = j?.data?.gameid;
  servers = j?.data?.server || [];
  console.log('\ngameName:', gname, ' gameID:', gameid);
  console.log('servers count:', servers.length);
  servers.slice(0, 5).forEach(s => console.log('  server:', s.servername, 'id=', s.serverid, 'type=', s.servertype));
  serverid = servers[0]?.serverid;
} catch (e) { console.log('parse err', e.message); }

// 2) 构造真正进游戏 URL 并验证
if (gameid && serverid) {
  const engineUrl = `${base}/${gameid}/?sid=${serverid}`;
  console.log(`\n===== 进游戏 engine URL: ${engineUrl} =====`);
  const r = curl(`-i -L --max-redirs 8 "${engineUrl}" -H "User-Agent: ${UA}"`);
  const loc = (r.match(/location: ([^\r\n]+)/i) || [])[1];
  if (loc) console.log('REDIRECT ->', loc.trim());
  const b = r.split('\r\n\r\n').slice(1).join('\r\n\r\n');
  const t = (b.match(/<title>([^<]*)<\/title>/i) || [])[1];
  const eng = /cocos|laya|egret|phaser|canvas|window\.game|loadGame|createGame|斗罗/i.test(b);
  console.log('TITLE:', t || '(无)', ' LEN', b.length, ' 游戏引擎特征:', eng ? '是' : '否');
  for (const l of b.split('\n')) {
    if (/gid=|pid=|sid=|10233298|787|cocos|laya|egret|\.html|\.htm|game|engine|src=/i.test(l)) console.log('   ', l.trim().slice(0, 300));
  }
} else {
  console.log('无法获取 gameid/serverid');
}
