import { execSync } from 'child_process';
import fs from 'fs';

const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) {
  return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 });
}

const html = curl(`-L "https://www.28zhe.com/play/10233298.htm" -H "User-Agent: ${UA}"`);
fs.writeFileSync('_p10233298.html', html);
console.log('=== play/10233298.htm 完整内容 ===');
console.log(html.slice(0, 3000));

const jsFiles = [...html.matchAll(/(?:src|href)="([^"]+\.js[^"]*)"/g)].map(m => m[1]);
console.log('\n=== JS 文件 ===', jsFiles);

for (const j of jsFiles) {
  const url = j.startsWith('http') ? j : (j.startsWith('/') ? 'https://www.28zhe.com' + j : 'https://www.28zhe.com/' + j);
  try {
    const js = curl(`-L "${url}" -H "User-Agent: ${UA}"`);
    const hits = js.split('\n').filter(l => /10233298|pid=787|gid=|pid=|location|redirect|href|gameId|\.htm|\.php/i.test(l));
    if (hits.length) {
      console.log(`\n##### ${j} #####`);
      for (const h of hits) console.log(h.trim().slice(0, 500));
    }
  } catch (e) { console.log('ERR', j, e.message); }
}
