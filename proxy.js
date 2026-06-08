const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const https = require('https');
const http = require('http');
const path = require('path');
const fs = require('fs');
const { getInstance: getProxyPool } = require('./proxy-pool');
const CryptoJS = require('crypto-js');
const appConfig = require('./config');
const { build37CookieValues } = require('./dldl-uinfo');
const app = express();
const PORT = Number(process.env.DLDL_PORT || process.env.PORT || 8080);
const TARGET = 'https://dldl.50pk.com';
const fixedQrTimeMs = String(process.env.DLDL_FIXED_TIME_MS || '1828368000000');
/** 静态资源目录（打包后只读，位于 asar 内） */
const staticRoot = process.env.DLDL_STATIC_ROOT || __dirname;
/** 用户可写目录：Electron 下为 app.getPath('userData')；直接 node proxy.js 时默认与脚本同目录 */
const userDataDir = process.env.DLDL_USER_DATA || __dirname;
app.use(express.json({ limit: '1mb' }));

/** Tauri 轮询直到 200；listen 后即可标记就绪 */
let proxyReady = false;
app.get('/__dldl_ready', (req, res) => {
  if (proxyReady) {
    res.status(200).type('text/plain').send('ok');
  } else {
    res.status(503).type('text/plain').send('starting');
  }
});
app.use((req, res, next) => {
  if (proxyReady) {
    return next();
  }
  res.status(503).type('text/plain').send('DLDL proxy is starting, retry shortly');
});

const accountConfig = {
  uname: process.env.DLDL_UNAME,
  upwd: process.env.DLDL_UPWD
};

// 初始化代理池
const proxyPool = getProxyPool({
  validateUrl: 'https://dldl.50pk.com',
  validateTimeout: 5000,
  maxProxies: 30,
  refreshInterval: 300000
});

// 代理池状态 API
app.get('/proxy-pool/status', (req, res) => {
  res.json(proxyPool.getStatus());
});

// 手动刷新代理池
app.post('/proxy-pool/refresh', async (req, res) => {
  try {
    await proxyPool.init();
    res.json({ ok: true, ...proxyPool.getStatus() });
  } catch (error) {
    res.status(500).json({ ok: false, message: error.message });
  }
});

// 切换IP（获取一个与当前不同的IP）
app.post('/proxy-pool/switch', async (req, res) => {
  try {
    const newProxy = await proxyPool.switchProxy();
    const lastUsed = proxyPool.getLastUsedProxy();
    res.json({ 
      ok: true, 
      currentProxy: newProxy,
      lastUsedProxy: lastUsed,
      ...proxyPool.getStatus() 
    });
  } catch (error) {
    res.status(500).json({ ok: false, message: error.message });
  }
});

const tokenApiBaseParams = {
  autoLogin: 'true',
  pid: '46',
  gid: '1003279',
  sversion: 'undefined',
  version: '1.0.4',
  time: '1778137876',
  dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
  os: 'iOS',
  over: '18.5',
  sign: '865145b213b92f565e24b8022ad18ace'
};
function ensureUserDataLayout() {
  fs.mkdirSync(userDataDir, { recursive: true });
}

/**
 * 计算 h5sdk/login 签名（与 account.html 一致）。
 * @param {Record<string, string>} params
 * @param {string} apiKey
 * @returns {string}
 */
function h5sdkSign(params, apiKey) {
  const sorted = Object.keys(params).sort();
  let str = '';
  for (const k of sorted) str += `${k}=${params[k]}`;
  return CryptoJS.MD5(str + apiKey).toString();
}

/**
 * 请求 h5sdk/login，首次生成 UINFO 前校验账号。
 * @param {string} uname
 * @param {string} upwd
 * @returns {Promise<{ token: string, time: string, sign: string }>}
 */
async function fetchH5sdkLoginData(uname, upwd) {
  const time = String(4129596000);
  const signParams = {
    uname,
    upwd,
    autoLogin: 'true',
    pid: appConfig.scanLogin.pid,
    gid: appConfig.scanLogin.gid,
    sversion: 'undefined',
    version: '1.0.4',
    time,
    dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
    os: 'iOS',
    over: '18.5'
  };
  const sign = h5sdkSign(signParams, appConfig.h5sdk.apiKey);
  const url = `${appConfig.h5sdk.loginUrl}?${new URLSearchParams({ ...signParams, sign }).toString()}`;
  const text = await fetchText(url, false);
  const match = text.match(/^callback\(([\s\S]+)\);?$/);
  const jsonText = match ? match[1] : text;
  const payload = JSON.parse(jsonText);
  if (!payload || payload.state !== 1 || !payload.data || !payload.data.token) {
    throw new Error((payload && payload.msg) || 'h5sdk/login failed');
  }
  return {
    token: String(payload.data.token),
    time: String(payload.data.time || time),
    sign: String(payload.data.sign || sign)
  };
}

function fetchText(url, useProxy = false) {
  return new Promise(async (resolve, reject) => {
    let proxyConfig = null;
    if (useProxy) {
      const pool = getProxyPool();
      const proxyUrl = await pool.getProxy(); // 使用当前代理
      if (proxyUrl) {
        proxyConfig = pool.createProxyAgent(proxyUrl);
        console.log(`[Proxy] 使用代理: ${proxyUrl}`);
      }
    }

    const urlObj = new URL(url);
    const isHttps = urlObj.protocol === 'https:';
    
    const options = {
      hostname: urlObj.hostname,
      port: urlObj.port || (isHttps ? 443 : 80),
      path: urlObj.pathname + urlObj.search,
      method: 'GET',
      headers: {
        'User-Agent': 'Mozilla/5.0'
      }
    };

    // 如果有代理配置，通过代理连接
    if (proxyConfig) {
      options.hostname = proxyConfig.host;
      options.port = proxyConfig.port;
      options.path = url; // 代理模式下 path 是完整 URL
      options.headers['Host'] = urlObj.hostname;
    }

    const protocol = (proxyConfig ? http : (isHttps ? https : http));
    
    const req = protocol.request(options, (resp) => {
      let data = '';
      resp.on('data', (chunk) => {
        data += chunk.toString();
      });
      resp.on('end', () => resolve(data));
    });

    req.on('error', reject);
    req.on('timeout', () => {
      req.destroy();
      reject(new Error('Request timeout'));
    });

    req.end();
  });
}

function parseCookies(req) {
  const header = req.headers && req.headers.cookie;
  const out = {};
  if (!header) return out;
  for (const part of String(header).split(';')) {
    const idx = part.indexOf('=');
    if (idx < 0) continue;
    const key = part.slice(0, idx).trim();
    const val = part.slice(idx + 1).trim();
    if (!key) continue;
    try {
      out[key] = decodeURIComponent(val);
    } catch (_) {
      out[key] = val;
    }
  }
  return out;
}

function getAccountFromRequest(req) {
  const cookies = parseCookies(req);
  const cookieUname = String(cookies.dldl_uname || '').trim();
  const cookieUpwd = String(cookies.dldl_upwd || '').trim();
  const referer = req.get('referer') || '';
  let refererUname = '';
  let refererUpwd = '';
  try {
    if (referer) {
      const refererUrl = new URL(referer);
      refererUname = refererUrl.searchParams.get('uname') || '';
      refererUpwd = refererUrl.searchParams.get('upwd') || '';
    }
  } catch (error) {
    // ignore invalid referer
  }
  return {
    // Priority: explicit query > cookie (set by /login.php) > referer > env
    uname: req.query.uname || cookieUname || refererUname || accountConfig.uname,
    upwd: req.query.upwd || cookieUpwd || refererUpwd || accountConfig.upwd
  };
}

async function getDynamicTokenInfo(account) {
  const callback = `jsonp_callback_${Date.now()}`;
  const params = new URLSearchParams({
    ...tokenApiBaseParams,
    uname: account.uname,
    upwd: account.upwd,
    callback
  });
  const url = `https://s-api.37.com.cn/h5sdk/login?${params.toString()}`;
  const responseText = await fetchText(url, true); // 使用当前代理
  const jsonpPrefix = `${callback}(`;
  if (!responseText.startsWith(jsonpPrefix) || !responseText.endsWith(');')) {
    throw new Error(`unexpected jsonp response: ${responseText.slice(0, 80)}`);
  }
  const payload = JSON.parse(responseText.slice(jsonpPrefix.length, -2));
  if (!payload || payload.state !== 1 || !payload.data || !payload.data.token) {
    throw new Error(`login api failed: ${responseText.slice(0, 120)}`);
  }
  return {
    token: payload.data.token,
    time: String(payload.data.time || fakeLoginData.time),
    sign: String(payload.data.sign || fakeLoginData.sign)
  };
}

const fakeLoginData = {
  gid: "1003279",
  pid: "46",
  token: "",
  time: "1778137103",
  sign: "8f44cb6da674f966231cb60432ba5b07",
  appVer: "134",
  platCode: "37wan",
  IMEI: "DCEADE00-A9B3-42F2-B4EB-8C766C0DD7A4"
};
// 拦截扫码接口（自动登录，保留 mock 数据）
app.use('/pc/getCodeInfo', async (req, res) => {
  console.log('[Mock] getCodeInfo');
  const sessionId = String(req.query.id || '');
  const account = getAccountFromRequest(req);
  let dynamicData = {};
  try {
    dynamicData = await getDynamicTokenInfo(account);
    console.log(`[Mock] token fetched for account: ${account.uname}`);
  } catch (error) {
    console.error(`[Mock] token fetch failed, fallback to static token: ${error.message}`);
  }
  res.json({ state: 1, msg: "success", data: { ...fakeLoginData, ...dynamicData } });
});
app.use('/pc/getId', (req, res) => {
  const account = getAccountFromRequest(req);
  const id = `mock_id_${Date.now()}_${Math.random().toString(36).slice(2, 8)}`;
  console.log(`[Mock] getId => ${id} for account: ${account.uname}`);
  res.json({ state: 1, msg: "success", data: id });
});

/** login.php / 账号页「刷新 token」：无状态请求 h5sdk/login（持久化由账号页写 Supabase） */
app.post('/api/token/refresh', async (req, res) => {
  try {
    const uname = String(req.body.uname || '').trim();
    const upwd = String(req.body.upwd || '').trim();
    if (!uname || !upwd) {
      res.status(400).json({ ok: false, message: '缺少 uname 或 upwd' });
      return;
    }
    const loginData = await fetchH5sdkLoginData(uname, upwd);
    res.json({ ok: true, ...loginData });
  } catch (error) {
    console.error('[token/refresh]', error.message);
    res.status(500).json({ ok: false, message: error.message });
  }
});

/**
 * 快捷登录：仅根据账号的 uname、upwd、uinfo 生成 cookie 注入脚本，打开 37 公开门户。
 */
app.post('/api/quick-login/ensure', (req, res) => {
  try {
    const uname = String(req.body.uname || '').trim();
    const upwd = String(req.body.upwd || '').trim();
    const uinfo = String(req.body.uinfo || '').trim();
    if (!uname || !upwd) {
      res.status(400).json({ ok: false, message: '缺少 uname 或 upwd' });
      return;
    }
    const portalUrl = `${appConfig.quickLogin.portalOrigin}${appConfig.quickLogin.publicPath}?${new URLSearchParams({
      pid: appConfig.quickLogin.pid,
      gid: appConfig.quickLogin.gid
    }).toString()}`;

    let uinfoVal = uinfo;
    let historyVal = '';
    if (uinfoVal) {
      historyVal = JSON.stringify([{ uname, upwd }]);
    } else {
      const cookies = build37CookieValues({ uname, upwd, autoLogin: true });
      uinfoVal = cookies.uinfo;
      historyVal = cookies.history;
    }
    res.json({
      ok: true,
      portalUrl,
      uinfo: uinfoVal,
      history: historyVal
    });
  } catch (error) {
    console.error('[quick-login/ensure]', error.message);
    res.status(500).json({ ok: false, message: error.message });
  }
});

app.get('/api/quick-login/config', (req, res) => {
  const q = appConfig.quickLogin;
  const url = `${q.portalOrigin}${q.publicPath}?${new URLSearchParams({ pid: q.pid, gid: q.gid }).toString()}`;
  res.json({ ok: true, portalUrl: url, pid: q.pid, gid: q.gid });
});

/**
 * 快捷登录窗口 JSONP 拦截端点：统一处理 h5sdk/login 和 h5sdk/query_login
 * 路径格式：/api/h5sdk-proxy/login 或 /api/h5sdk-proxy/query_login
 * - login：从请求参数中提取 uname/upwd/sign 等，通过代理池 IP 真实调用 s-api.37.com.cn，
 *   拿到真实数据后原样返回 JSONP 响应给子窗口。
 * - query_login：返回伪造的已登录状态，阻止无限轮询。
 */
app.get('/api/h5sdk-proxy/:action', async (req, res) => {
  const action = req.params.action;
  const callbackName = String(req.query.callback || 'jsonp_callback_0');
  console.log('[h5sdk-proxy/' + action + '] callback=' + callbackName + ', uname=' + (req.query.uname || ''));

  res.setHeader('Content-Type', 'application/javascript; charset=utf-8');

  if (action === 'query_login') {
    // query_login：直接返回伪造的已登录状态，不请求真实服务器
    const fakeResp = { state: 1, data: { logined: true } };
    const respBody = callbackName + '(' + JSON.stringify(fakeResp) + ');';
    console.log('[h5sdk-proxy/query_login] 返回伪造已登录状态');
    return res.end(respBody);
  }

  if (action === 'login') {
    // login：从请求参数中提取所有参数，通过代理池 IP 真实调用 s-api.37.com.cn
    // 去掉 callback（我们自己用 callbackName 组装返回），保留其他所有参数
    const loginParams = new URLSearchParams(req.query);
    loginParams.delete('callback');
    const realUrl = `https://s-api.37.com.cn/h5sdk/login?${loginParams.toString()}`;
    console.log('[h5sdk-proxy/login] 通过代理池请求真实接口...');

    try {
      const responseText = await fetchText(realUrl, true); // useProxy=true，走代理池
      console.log('[h5sdk-proxy/login] 真实接口返回:', responseText.substring(0, 200));

      // 解析真实返回的 JSONP，替换 callback 名称后返回
      // 真实返回格式：jsonp_callback_xxxxx({...});
      const match = responseText.match(/^(\w+)\(([\s\S]+)\);?$/);
      if (match) {
        const respBody = callbackName + '(' + match[2] + ');';
        return res.end(respBody);
      }
      // 如果不是标准 JSONP 格式，直接返回原始响应
      return res.end(responseText);
    } catch (error) {
      console.error('[h5sdk-proxy/login] 代理请求失败:', error.message);
      // 失败时返回错误 JSONP 响应
      const errResp = { state: 0, msg: '代理请求失败: ' + error.message, data: {} };
      const respBody = callbackName + '(' + JSON.stringify(errResp) + ');';
      return res.end(respBody);
    }
  }

  res.status(404).send('unknown action: ' + action);
});

// h5sdk/login 代理（主窗口扫码页使用），处理签名并转发请求
app.use('/api/h5sdk/login', async (req, res) => {
  console.log('[h5sdk/login] request:', req.query.uname);
  const callback = `jsonp_callback_${Date.now()}`;
  const params = new URLSearchParams(req.query);
  params.set('callback', callback);
  // 移除 sign，让服务器自己验证（或我们需要生成正确的 sign）
  // 由于 API_KEY 未知，直接转发原参数
  const url = `https://s-api.37.com.cn/h5sdk/login?${params.toString()}`;
  console.log('[h5sdk/login] forwarding to:', url);
  try {
    const responseText = await fetchText(url, true); // 使用当前代理
    res.setHeader('Content-Type', 'application/javascript; charset=utf-8');
    res.end(responseText);
  } catch (error) {
    console.error('[h5sdk/login] failed:', error.message);
    res.status(500).json({ state: 0, msg: error.message });
  }
});

// 静态文件服务（只读资源；用户可写目录 userDataDir 供日志等）
app.use(express.static(staticRoot));
// 代理所有其他请求
app.use('/', createProxyMiddleware({
  target: TARGET,
  changeOrigin: true,
  secure: false,
  on: {
    proxyReq: (proxyReq, req, res) => {
      // 避免上游返回 gzip，便于我们在 login.php 场景下直接替换 HTML
      proxyReq.setHeader('accept-encoding', 'identity');
    },
    proxyRes: (proxyRes, req, res) => {
      if (req.path.includes('login.php')) {
        let body = [];
        proxyRes.on('data', (chunk) => body.push(chunk));
        proxyRes.on('end', () => {
          body = Buffer.concat(body).toString();

          // 只把 enter.js 指向本地代理，其他静态资源保持线上原始地址，最大化像素一致性
          body = body.replace(
            /https:\/\/dldl\.50pk\.com\/qq_res\/enter\.js(\?[^"']*)?/g,
            '/qq_res/enter.js$1'
          );
          body = body.replace(
            /(<script src=['"]\/qq_res\/enter\.js[^>]*><\/script>)/,
            `<script>window.__PC_QR_FIXED_TIME_MS=${fixedQrTimeMs};</script><script src="/dldl-login-inject.js"></script>\n    $1`
          );
          
          // 同步上游状态和基础响应头（修改 body 后移除长度/压缩相关头）
          res.statusCode = proxyRes.statusCode;
          Object.entries(proxyRes.headers || {}).forEach(([key, value]) => {
            const lowerKey = key.toLowerCase();
            if (lowerKey === 'content-length' || lowerKey === 'content-encoding') {
              return;
            }
            if (value !== undefined) {
              res.setHeader(key, value);
            }
          });
          res.setHeader('Content-Type', 'text/html; charset=utf-8');
          res.setHeader('Referrer-Policy', 'unsafe-url');
          // 让后续 /pc/* 请求不依赖 query/referrer，也能稳定拿到账号
          try {
            const qUname = String(req.query.uname || '').trim();
            const qUpwd = String(req.query.upwd || '').trim();
            if (qUname && qUpwd) {
              const cookieAttrs = 'Path=/; SameSite=Lax';
              res.append('Set-Cookie', `dldl_uname=${encodeURIComponent(qUname)}; ${cookieAttrs}`);
              res.append('Set-Cookie', `dldl_upwd=${encodeURIComponent(qUpwd)}; ${cookieAttrs}`);
            }
          } catch (_) {
            // ignore
          }
          res.end(body);
        });
        return;
      }
      if (req.path.includes('/qq_res/enter.js')) {
        let body = [];
        proxyRes.on('data', (chunk) => body.push(chunk));
        proxyRes.on('end', () => {
          body = Buffer.concat(body).toString();

          // 在“线上原版 enter.js”上仅替换扫码与回跳地址，保证行为在本地闭环
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/pc\/getId/g, '/pc/getId');
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/pc\/getCodeInfo/g, '/pc/getCodeInfo');
          body = body.replace(/https:\/\/dldl\.50pk\.com\/login\.php\?/g, '/login.php?');
          const accQsMin =
            '+(window.__DLDL_PROXY_UNAME?"&uname="+encodeURIComponent(window.__DLDL_PROXY_UNAME):"")+(window.__DLDL_PROXY_UPWD?"&upwd="+encodeURIComponent(window.__DLDL_PROXY_UPWD):"")';
          body = body.replace(
            /\/pc\/getId\?time="\+(\([^)]+\))\+"&sign="\+hex_md5\(e\.toString\(\)\+"pcjgv587!?"\)/g,
            `"/pc/getId?time="+$1+"&sign="+hex_md5(e.toString()+"pcjgv587!?")${accQsMin}`
          );
          body = body.replace(
            /\/pc\/getCodeInfo\?id="\+t\+\+"&time="\+(\([^)]+\))\+"&sign="\+hex_md5\(t\+e\.toString\(\)\+"pcjgv587!?"\)/g,
            `"/pc/getCodeInfo?id="+t+"&time="+$1+"&sign="+hex_md5(t+e.toString()+"pcjgv587!?")${accQsMin}`
          );
          const accQs =
            ' + (window.__DLDL_PROXY_UNAME ? "&uname=" + encodeURIComponent(window.__DLDL_PROXY_UNAME) : "") + (window.__DLDL_PROXY_UPWD ? "&upwd=" + encodeURIComponent(window.__DLDL_PROXY_UPWD) : "")';
          body = body.replace(
            /\/pc\/getId\?time=" \+ (\([^)]+\)) \+ "&sign=" \+ hex_md5\(e\.toString\(\) \+ "pcjgv587!?"\)/g,
            `"/pc/getId?time=" + $1 + "&sign=" + hex_md5(e.toString() + "pcjgv587!?")${accQs}`
          );
          body = body.replace(
            /\/pc\/getCodeInfo\?id=" \+ t \+ "&time=" \+ (\([^)]+\)) \+ "&sign=" \+ hex_md5\(t \+ e\.toString\(\) \+ "pcjgv587!?"\)/g,
            `"/pc/getCodeInfo?id=" + t + "&time=" + $1 + "&sign=" + hex_md5(t + e.toString() + "pcjgv587!?")${accQs}`
          );

          res.statusCode = proxyRes.statusCode;
          Object.entries(proxyRes.headers || {}).forEach(([key, value]) => {
            const lowerKey = key.toLowerCase();
            if (lowerKey === 'content-length' || lowerKey === 'content-encoding') {
              return;
            }
            if (value !== undefined) {
              res.setHeader(key, value);
            }
          });
          res.setHeader('Content-Type', 'application/javascript; charset=utf-8');
          res.end(body);
        });
        return;
      }
      proxyRes.pipe(res);
    }
  },
  selfHandleResponse: true
}));
async function startProxyServer() {
  ensureUserDataLayout();
  const server = await new Promise((resolve, reject) => {
    const s = app.listen(PORT, '127.0.0.1', () => resolve(s));
    s.on('error', reject);
  });
  ensureUserDataLayout();
  proxyReady = true;
  console.log(`✅ Proxy running at http://localhost:${PORT}`);
  console.log(`✅ Mock interceptors active`);
  console.log(`✅ Account uname: ${accountConfig.uname}`);
  console.log(`✅ Fixed QR time(ms): ${fixedQrTimeMs}`);
  console.log(`✅ Multi-account mode via login.php?uname=xxx&upwd=xxx`);
  console.log(`✅ Accounts storage: Supabase (account.html)`);
  console.log(`✅ Static root: ${staticRoot}`);
  console.log(`✅ User data dir: ${userDataDir}`);
  return server;
}

module.exports = { startProxyServer, PORT, userDataDir, staticRoot };

if (require.main === module) {
  startProxyServer().catch((error) => {
    console.error(`❌ Failed to start server: ${error.message}`);
    process.exit(1);
  });
}