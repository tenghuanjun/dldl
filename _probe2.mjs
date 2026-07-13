import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const candidates = [
  'http://xplay.5144wan.com/?gid=10233298&pid=787',
  'http://xplay.5144wan.com/10233298/?pid=787',
  'http://xplay.5144wan.com/server/?gid=10233298',
  'https://jehe.5144wan.com/10233298/?pid=787',
  'https://jehe.5144wan.com/play/10233298/?pid=787',
  'http://xplay.5144wan.com/index.html?gid=10233298&pid=787',
];
for (const u of candidates) {
  console.log(`\n===== ${u} =====`);
  try {
    const r = curl(`-i -L --max-redirs 8 "${u}" -H "User-Agent: ${UA}"`);
    const loc = (r.match(/location: ([^\r\n]+)/i) || [])[1];
    if (loc) console.log('REDIRECT ->', loc.trim());
    const body = r.split('\r\n\r\n').slice(1).join('\r\n\r\n');
    const title = (body.match(/<title>([^<]*)<\/title>/i) || [])[1];
    console.log('TITLE:', title || '(无)');
    const eng = /cocos|laya|egret|phaser|canvas|window\.game|createGame|loadGame|10233298|斗罗|5144wan/i.test(body);
    console.log('游戏特征:', eng ? '是' : '否', ' LEN', body.length);
    for (const l of body.split('\n')) {
      if (/gid=|pid=|10233298|787|5144wan|\.html|\.htm|game|engine|cocos|laya|xplay/i.test(l)) {
        console.log('   ', l.trim().slice(0, 300));
      }
    }
  } catch (e) { console.log('ERR', e.message); }
}
