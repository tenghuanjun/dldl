// 混淆构建脚本：把 mobile.html 里的主要逻辑脚本抽出混淆为 app.min.js，
// 并在 mobile.min.html 中用 <script src="app.min.js"> 引用。
// 用法：node tools/obfuscate.mjs
// 说明：mobile.html 保持为可读、可编辑的源码；mobile.min.html + app.min.js 为部署用混淆产物。
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';
import pkg from 'javascript-obfuscator';
const { obfuscate } = pkg;

const __dirname = dirname(fileURLToPath(import.meta.url));
const root = resolve(__dirname, '..');
const SRC = resolve(root, 'mobile.html');
const OUT_HTML = resolve(root, 'mobile.min.html');
const OUT_JS = resolve(root, 'app.min.js');

// 读源码并统一为 LF，避免 Windows 换行符干扰
let html = readFileSync(SRC, 'utf8').replace(/\r\n/g, '\n');

// 匹配没有 src 属性的 inline <script> 块
const re = /<script(?![^>]*\bsrc=)[^>]*>([\s\S]*?)<\/script>/g;
let match;
let mainBlock = null;
let mainCode = '';

while ((match = re.exec(html)) !== null) {
  const code = match[1];
  if (!/createApp\(\{/.test(code)) continue; // 只处理主应用脚本
  mainBlock = match[0];
  mainCode = code;
  break;
}

if (!mainBlock) {
  console.error('未找到主应用脚本（包含 createApp({ 的内联 script）');
  process.exit(1);
}

// setup() return 对象中暴露给 Vue 模板的全部 property 名白名单，
// 强制保留原名，确保 @click="foo" / :checked="bar(g)" 等模板绑定不因混淆断裂。
// 新增方法/属性时，在此列表追加即可。
const reservedNames = [
  'isLoggedIn', 'currentUser', 'loginForm',
  'globalLoading', 'globalLoadingText',
  'loginLoading', 'loginError',
  'loginShowPwd', 'addShowPwd',
  'areas', 'visibleAreas', 'loading', 'loadError',
  'refreshLoading', 'cronRefreshLoading',
  'handleCronRefresh', 'lastRefresh',
  'actionLoading', 'urlFilter',
  'filteredAccounts',
  'hashPassword', 'handleLogin', 'handleRegister', 'handleLogout',
  'loadAccounts', 'handleRefresh',
  'hasUrl', 'getStatus', 'getUrlAge',
  'getUrlRemaining', 'urlRemainingState',
  'doAppLogin', 'doRefreshToken',
  'batchSelected', 'selectedCount', 'batchRefreshing', 'batchProgress',
  'isSelected', 'toggleSelect', 'isAreaAllSelected', 'toggleSelectArea',
  'clearSelection', 'handleBatchRefresh',
  'addVisible', 'addLoading', 'addError', 'addForm', 'editAccountId',
  'openAddModal', 'closeAddModal', 'submitAddAccount', 'deleteAccount',
  'copyAccountUrl',
  'getPassCodeValue', 'getPassCodeStatus', 'isPassCodeSending',
  'onPassCodeInput', 'onPassCodeBlur', 'clearPassCode',
  'scrollToTop',
  'toastVisible', 'toastMsg', 'showToast',
];
console.log('reservedNames:', reservedNames.length, '个');

const result = obfuscate(mainCode, {
  compact: true,
  controlFlowFlattening: false,
  deadCodeInjection: false,
  stringArray: true,
  stringArrayEncoding: ['rc4'],
  stringArrayThreshold: 1,
  renameGlobals: false,      // 不重命名全局（Vue / CryptoJS / VConsole 等）
  renameProperties: false,   // 不重命名属性名（否则 Vue 模板绑定 / DB 字段会断）
  identifierNamesGenerator: 'hexadecimal',
  reservedNames,             // Vue 模板引用的方法/属性名白名单，强制保留
  selfDefending: false,      // 设为 false 以免静态文件被二次压缩/格式化后失效
  debugProtection: false,
  disableConsoleOutput: false
});

const obfuscated = result.getObfuscatedCode();
writeFileSync(OUT_JS, obfuscated, 'utf8');

// 用外部脚本引用替换原来的内联主脚本
const outHtml = html.replace(mainBlock, '<script src="app.min.js"></script>');
writeFileSync(OUT_HTML, outHtml, 'utf8');

console.log('混淆完成：');
console.log('  - ' + OUT_JS + '（主逻辑混淆产物，纯 JS）');
console.log('  - ' + OUT_HTML + '（已改为引用 app.min.js）');
