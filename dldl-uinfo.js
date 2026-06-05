/**
 * 与 bundle.js User.saveUserInfo / SQUtil.encode 一致的 UINFO cookie 工具。
 */
const UINFO_COOKIE_MAX_AGE_YEARS = 100;

/**
 * @param {string} plain
 * @returns {string}
 */
function encodeUinfoPlain(plain) {
  const code = String(plain);
  let c = String.fromCharCode(code.charCodeAt(0) + code.length);
  for (let i = 1; i < code.length; i++) {
    c += String.fromCharCode(code.charCodeAt(i) + code.charCodeAt(i - 1));
  }
  return encodeURIComponent(c);
}

/**
 * 按 bundle.js 登录成功后的写法生成 UINFO、HISTORY cookie 值。
 * @param {{ uname: string, upwd: string, autoLogin?: boolean }} account
 * @returns {{ uinfo: string, history: string }}
 */
function build37CookieValues(account) {
  const uname = String(account.uname || '').trim();
  const upwd = String(account.upwd || '').trim();
  const uinfoObj = { uname, upwd, autoLogin: account.autoLogin !== false };
  const uinfo = encodeUinfoPlain(JSON.stringify(uinfoObj));
  const history = JSON.stringify([{ uname, upwd }]);
  return { uinfo, history };
}

/**
 * 生成在 37.com.cn 页面 document.start 注入的脚本（Expires 约 100 年）。
 * @param {string} uinfoValue UINFO cookie 的 value 部分（已编码）
 * @param {string} historyValue HISTORY cookie 的 value 部分
 * @returns {string}
 */
function buildCookieInjectionScript(uinfoValue, historyValue) {
  const expMs = Date.now() + UINFO_COOKIE_MAX_AGE_YEARS * 365.25 * 24 * 60 * 60 * 1000;
  const u = JSON.stringify(String(uinfoValue || ''));
  const h = JSON.stringify(String(historyValue || ''));
  return `(function(){try{var e=new Date(${expMs}).toUTCString();document.cookie="UINFO="+${u}+"; path=/; expires="+e+"; SameSite=Lax";document.cookie="HISTORY="+${h}+"; path=/; expires="+e+"; SameSite=Lax";}catch(_){}})();`;
}

module.exports = {
  UINFO_COOKIE_MAX_AGE_YEARS,
  encodeUinfoPlain,
  build37CookieValues,
  buildCookieInjectionScript
};
