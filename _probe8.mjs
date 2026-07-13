import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const base = 'http://xplay.5144wan.com';
for (const gid of ['1023329','10233298']) {
  console.log('\n===== gid='+gid+' (POST /api/system/) =====');
  const api = curl(
    `-X POST "${base}/api/system/" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: ${UA}" ` +
    `--data-urlencode "cmd=get.server.list" --data-urlencode "gid=${gid}"`
  );
  let j; try { j = JSON.parse(api); } catch(e){ console.log('RAW:', api.slice(0,300)); continue; }
  if (j.code === 1) {
    console.log('gameName:', j.data.gname, ' gameID:', j.data.gameid, ' 服务器数:', (j.data.server||[]).length);
    (j.data.server||[]).forEach(s=>{
      if (/164|165|黄金魂环|魂环/.test(s.servername) || [164,165].includes(s.serverid))
        console.log('  ★匹配:', s.servername, 'id=', s.serverid, 'open=', s.opentime);
    });
    // 打印最后几个服（看最大服号）
    const sv = j.data.server||[];
    console.log('  最大服号样本:', sv.slice(-3).map(s=>s.servername+'('+s.serverid+')').join(', '));
  } else {
    console.log('code:', j.code, 'msg:', j.msg);
  }
}
