// 桌面端混淆构建脚本：把 account.html 的主逻辑内联脚本抽出混淆为 account.min.js，
// 并生成 account.min.html 引用它；同时把两个 preload 脚本混淆为 .min.js。
// 安全设置与移动端一致：不重命名全局/属性（Vue / CryptoJS / Electron 桥接 / DB 字段不受影响），
// 但启用 RC4 字符串加密 + 十六进制标识符重命名，达到“混淆加密”效果。
// 用法：node tools/obfuscate-desktop.mjs
import { readFileSync, writeFileSync, existsSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';
import pkg from 'javascript-obfuscator';
const { obfuscate } = pkg;

const __dirname = dirname(fileURLToPath(import.meta.url));
const root = resolve(__dirname, '..');

const OBF_OPTS = {
  compact: true,
  controlFlowFlattening: false,
  deadCodeInjection: false,
  stringArray: true,
  stringArrayEncoding: ['rc4'],
  stringArrayThreshold: 1,
  renameGlobals: false,      // 不重命名全局（Vue / CryptoJS / ElementPlus / Sortable / supabase 等）
  renameProperties: false,  // 不重命名属性名（否则 Vue 模板绑定 / DB 字段 / contextBridge 暴露名会断）
  identifierNamesGenerator: 'hexadecimal',
  selfDefending: false,
  debugProtection: false,
  disableConsoleOutput: false
};

function obf(code) {
  const r = obfuscate(code, OBF_OPTS);
  const out = r.getObfuscatedCode();
  if (!out || out.length < code.length * 0.2) {
    throw new Error('混淆产物异常（过短），已中止以防破坏源码');
  }
  return out;
}

// ---------- 1) account.html -> account.min.html + account.min.js ----------
{
  const SRC = resolve(root, 'account.html');
  const OUT_HTML = resolve(root, 'account.min.html');
  const OUT_JS = resolve(root, 'account.min.js');
  let html = readFileSync(SRC, 'utf8').replace(/\r\n/g, '\n');

  // 匹配没有 src 属性的 inline <script> 块
  const re = /<script(?![^>]*\bsrc=)[^>]*>([\s\S]*?)<\/script>/g;
  let match, mainBlock = null, mainCode = '';
  while ((match = re.exec(html)) !== null) {
    if (!/createApp\(\{/.test(match[1])) continue; // 只处理主应用脚本
    mainBlock = match[0];
    mainCode = match[1];
    break;
  }
  if (!mainBlock) {
    console.error('account.html 未找到主应用脚本（含 createApp({ 的内联 script）');
    process.exit(1);
  }

  const obfuscated = obf(mainCode);
  writeFileSync(OUT_JS, obfuscated, 'utf8');
  const outHtml = html.replace(mainBlock, '<script src="account.min.js"></script>');
  writeFileSync(OUT_HTML, outHtml, 'utf8');
  console.log('✔ account.min.html / account.min.js 生成完毕（含 supabase key 的主逻辑已加密混淆）');
}

// ---------- 2) preload 脚本混淆 ----------
const preloads = ['preload.js', 'quick-login-preload.js'];
for (const f of preloads) {
  const SRC = resolve(root, f);
  if (!existsSync(SRC)) {
    console.warn('跳过（不存在）: ' + f);
    continue;
  }
  const OUT = resolve(root, f.replace(/\.js$/, '.min.js'));
  const code = readFileSync(SRC, 'utf8');
  writeFileSync(OUT, obf(code), 'utf8');
  console.log('✔ ' + f + ' -> ' + f.replace(/\.js$/, '.min.js'));
}

console.log('桌面端混淆完成。运行 electron-builder 打包即可。');
