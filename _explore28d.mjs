import { execSync } from 'child_process';
const UA = 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)';
function curl(args) { return execSync(`curl.exe -s ${args}`, { encoding: 'utf8', maxBuffer: 1<<26 }); }

const files = [
  'https://oss.28zhe.com/template/d_common/js/play.js',
  'https://oss.28zhe.com/template/d_common/js/role_sdk.js',
  'https://oss.28zhe.com/template/d_common/js/pay_sdk.js',
  'https://www.28zhe.com/static/template/d_common/js/common.js?v=2',
  'https://www.28zhe.com/index.php?ac=play&id=10274&aid=4689470&t=1008',
];
for (const f of files) {
  console.log(`\n================ ${f} ================`);
  let content;
  try { content = curl(`-L "${f}" -H "User-Agent: ${UA}"`); }
  catch (e) { console.log('ERR', e.message); continue; }
  console.log('LEN', content.length);
  const lines = content.split('\n');
  for (const l of lines) {
    if (/10233298|pid=787|gid=|pid=|gameUrl|game_url|gameH5|h5Url|\.htm|\.php|iframe|src=|location|api|ajax|getGame|playUrl|serverUrl|cdn/i.test(l)) {
      console.log(l.trim().slice(0, 600));
    }
  }
}
