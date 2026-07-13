import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }
const base = 'http://xplay.5144wan.com';
for (const id of ['1023329','10233298']) {
  for (const sid of ['164','165']) {
    const url = `${base}/${id}/?sid=${sid}`;
    const r = curl(`-i -L --max-redirs 3 "${url}" -H "User-Agent: ${UA}"`);
    const sc = (r.match(/HTTP\/\d\.\d (\d+)/)||[])[1];
    const loc = (r.match(/location: ([^\r\n]+)/i)||[])[1];
    const t = (r.match(/<title>([^<]*)<\/title>/i)||[])[1];
    console.log(`id=${id} sid=${sid} -> status=${sc} title=${t||'(无)'} ${loc?('redirect='+loc.trim()):''}`);
  }
}
