const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const https = require('https');
const http = require('http');
const path = require('path');
const fs = require('fs');
const { getInstance: getProxyPool } = require('./proxy-pool');
const { GHActionsRelay } = require('./gh-relay');
const crypto = require('crypto');
const CryptoJS = require('crypto-js');
const appConfig = require('./config');
const { build37CookieValues } = require('./dldl-uinfo');
const app = express();
const PORT = Number(process.env.DLDL_PORT || process.env.PORT || 8080);
const TARGET = 'https://dldl.50pk.com';
const fixedQrTimeMs = String(process.env.DLDL_FIXED_TIME_MS || '1828368000000');

/** Token 获取优先级: "relay"（GitHub Actions 中继, 默认）, "proxy-pool"（代理池）, "direct"（直连） */
const TOKEN_MODE = process.env.DLDL_TOKEN_MODE || 'relay';
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

// 初始化代理池（作为备选方案）
const proxyPool = getProxyPool({
  validateUrl: 'https://dldl.50pk.com',
  validateTimeout: 5000,
  maxProxies: 30,
  refreshInterval: 300000
});

// 初始化 GitHub Actions 中继（利用 Runner 的 Azure IP 轮换）
const ghRelay = new GHActionsRelay({
  gistId: process.env.DLDL_GIST_ID,
  gistToken: process.env.DLDL_GIST_TOKEN,
  rawUrl: process.env.DLDL_RAW_CACHE_URL,
  timeout: 5000
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

// GH-Relay 状态 API
app.get('/gh-relay/status', (req, res) => {
  res.json({ ok: true, tokenMode: TOKEN_MODE, ...ghRelay.getStatus() });
});

// 手动刷新 GH-Relay（读取最新 Gist 数据）
app.post('/gh-relay/refresh', async (req, res) => {
  try {
    const data = await ghRelay.getLatest();
    res.json({
      ok: true,
      runnerIP: data.runnerIP,
      updatedAt: data.updatedAt,
      accountCount: Object.keys(data.tokens || {}).length
    });
  } catch (error) {
    res.status(500).json({ ok: false, message: error.message });
  }
});

// 获取当前 IP 状态（供按钮展示）
app.post('/proxy-pool/switch', async (req, res) => {
  // 设置整体超时（防止初始化太久导致前端挂起）
  const switchTimeout = setTimeout(() => {
    if (!res.headersSent) {
      console.warn('[SwitchIP] 切换超时（45s），返回直连');
      res.json({ ok: true, source: 'direct', message: '切换超时，当前走直连' });
    }
  }, 45000);

  try {
    const useRelay = TOKEN_MODE !== 'proxy-pool' && TOKEN_MODE !== 'direct';

    if (useRelay && (ghRelay.gistId || ghRelay.rawUrl)) {
      try {
        const result = await ghRelay.switchIP();
        clearTimeout(switchTimeout);
        res.json({
          ok: true,
          source: 'gh-relay',
          switched: result.switched,
          ip: result.runnerIP,
          updatedAt: result.updatedAt,
          tokenCount: result.tokenCount
        });
        return;
      } catch (e) {
        console.warn('[SwitchIP] GH-Relay 失败:', e.message, '→ 回退代理池');
      }
    }

    try {
      const newProxy = await proxyPool.switchProxy();
      clearTimeout(switchTimeout);
      if (newProxy) {
        res.json({ ok: true, source: 'proxy-pool', currentProxy: newProxy, ...proxyPool.getStatus() });
      } else {
        res.json({ ok: true, source: 'direct', message: '无可用代理，当前走直连' });
      }
    } catch (error) {
      clearTimeout(switchTimeout);
      res.json({ ok: true, source: 'direct', message: '代理池切换失败，走直连' });
    }
  } catch (e) {
    clearTimeout(switchTimeout);
    if (!res.headersSent) {
      res.json({ ok: true, source: 'direct', message: '切换异常，走直连' });
    }
  }
});

// ==================== 按需 Token 请求（核心） ====================

/**
 * POST /gh-relay/request-token
 * Body: { uname, upwd }
 * 流程：写入 Gist pending 队列 → 等待 GitHub Actions 从新 IP 获取 → 返回 token
 * 超时：最多等 130 秒（两次 workflow 间隔 + 执行时间）
 */
app.post('/gh-relay/request-token', async (req, res) => {
  const uname = String(req.body.uname || '').trim();
  const upwd = String(req.body.upwd || '').trim();

  if (!uname || !upwd) {
    return res.status(400).json({ ok: false, message: '缺少 uname 或 upwd' });
  }

  if (!ghRelay.gistToken) {
    return res.status(400).json({ ok: false, message: 'GH-Relay 未配置（需要 DLDL_GIST_TOKEN 环境变量）' });
  }

  // 先检查是否已有新鲜缓存（5 分钟内）
  try {
    const cached = await ghRelay.getToken(uname);
    if (cached) {
      console.log('[GH-Relay] 缓存命中:', uname, 'IP:', cached.runnerIP);
      return res.json({ ok: true, source: 'cache', ...cached });
    }
  } catch (_) {}

  // 写入 pending 并等待
  console.log('[GH-Relay] 按需请求 token:', uname);
  try {
    const result = await ghRelay.requestToken(uname, upwd);
    res.json({ ok: true, source: 'fresh', ...result });
  } catch (e) {
    res.status(500).json({ ok: false, message: e.message });
  }
});

/**
 * GET /gh-relay/cached-token?uname=xxx
 * 仅读取缓存，不触发新请求（快速路径）
 */
app.get('/gh-relay/cached-token', async (req, res) => {
  const uname = String(req.query.uname || '').trim();
  if (!uname) return res.status(400).json({ ok: false, message: '缺少 uname' });

  try {
    const cached = await ghRelay.getToken(uname);
    if (cached) {
      return res.json({ ok: true, ...cached });
    }
    res.json({ ok: true, cached: false });
  } catch (e) {
    res.json({ ok: true, cached: false, error: e.message });
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
 * 优先使用 GitHub Actions relay（Azure IP 轮换），失败回退到代理池。
 * @param {string} uname
 * @param {string} upwd
 * @returns {Promise<{ token: string, time: string, sign: string }>}
 */
async function fetchH5sdkLoginData(uname, upwd, timeoutMs = 0) {
  // 优先级1: GitHub Actions Relay（利用 Azure IP 轮换）
  if (TOKEN_MODE !== 'proxy-pool' && TOKEN_MODE !== 'direct') {
    try {
      const relayToken = await ghRelay.getToken(uname);
      if (relayToken) {
        console.log(`[Token] GH-Relay 命中(h5sdk): ${uname}`);
        return {
          token: String(relayToken.token),
          time: String(relayToken.time || '4129596000'),
          sign: String(relayToken.sign || '')
        };
      }
    } catch (e) {
      console.warn(`[Token] GH-Relay 失败: ${e.message}，回退到代理池直连`);
    }
  }

  // 优先级2: 代理池直连（原逻辑）
  if (TOKEN_MODE !== 'direct') {
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
    const text = await fetchText(url, true, timeoutMs); // 走代理池，切换IP时生效
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

  throw new Error('所有 token 获取渠道均失败');
}

function fetchText(url, useProxy = false, timeoutMs = 0) {
  const doFetch = new Promise(async (resolve, reject) => {
    const urlObj = new URL(url);
    const isTargetHttps = urlObj.protocol === 'https:';
    let proxyUrl = null;
    const pool = getProxyPool();

    if (useProxy) {
      proxyUrl = await pool.getProxy();
    }

    // 如果走代理，通过代理发出请求
    if (proxyUrl) {
      console.log(`[Proxy] 使用代理: ${proxyUrl} -> ${urlObj.hostname}`);
      return fetchViaProxy(urlObj, proxyUrl, timeoutMs, resolve, reject);
    }

    // 直连（无代理或代理不可用）
    const effectiveTimeout = timeoutMs > 0 ? timeoutMs : 15000;
    const protocol = isTargetHttps ? https : http;
    const options = {
      hostname: urlObj.hostname,
      port: urlObj.port || (isTargetHttps ? 443 : 80),
      path: urlObj.pathname + urlObj.search,
      method: 'GET',
      timeout: effectiveTimeout,
      headers: { 'User-Agent': 'Mozilla/5.0' }
    };

    const req = protocol.request(options, (resp) => {
      let data = '';
      resp.on('data', (chunk) => { data += chunk.toString(); });
      resp.on('end', () => resolve(data));
    });

    req.on('error', reject);
    req.on('timeout', () => {
      req.destroy();
      reject(new Error('Request timeout'));
    });
    req.end();
  });

  // 用 Promise.race 兜底：无论 socket 超时是否生效，总时限必定触发
  if (timeoutMs > 0) {
    return Promise.race([
      doFetch,
      new Promise((_, reject) =>
        setTimeout(() => reject(new Error('Request timeout')), timeoutMs)
      )
    ]);
  }
  return doFetch;
}

/**
 * 通过 HTTP 代理发出请求。
 * - HTTPS 目标：先 CONNECT 建立隧道，再在隧道上发 HTTPS 请求
 * - HTTP 目标：直接向代理发 GET 请求，path 为完整 URL
 */
function fetchViaProxy(urlObj, proxyUrl, timeoutMs, resolve, reject) {
  const pool = getProxyPool();
  const proxyParsed = new URL(proxyUrl);
  const isTargetHttps = urlObj.protocol === 'https:';
  // CONNECT 超时取 timeoutMs 或默认 10s，实际请求超时取 timeoutMs 或默认 15s
  const connectTimeout = timeoutMs > 0 ? Math.min(timeoutMs, 10000) : 10000;
  const reqTimeout = timeoutMs > 0 ? timeoutMs : 15000;

  const doRequestOnSocket = (socket) => {
    const protocol = isTargetHttps ? https : http;
    const reqOptions = {
      hostname: urlObj.hostname,
      port: urlObj.port || (isTargetHttps ? 443 : 80),
      path: urlObj.pathname + urlObj.search,
      method: 'GET',
      timeout: reqTimeout,
      headers: {
        'User-Agent': 'Mozilla/5.0',
        'Host': urlObj.hostname
      },
      socket,
      agent: false,
      rejectUnauthorized: false,
    };

    const req = protocol.request(reqOptions, (resp) => {
      let data = '';
      resp.on('data', (chunk) => { data += chunk.toString(); });
      resp.on('end', () => resolve(data));
    });
    req.on('error', (err) => {
      console.error(`[Proxy] 代理请求失败: ${proxyUrl}, 错误: ${err.message}`);
      pool.removeProxy(proxyUrl); // 移除失效代理
      reject(err);
    });
    req.on('timeout', () => {
      console.error(`[Proxy] 代理请求超时: ${proxyUrl}`);
      pool.removeProxy(proxyUrl);
      req.destroy();
      reject(new Error('Proxy request timeout'));
    });
    req.end();
  };

  if (isTargetHttps) {
    // HTTPS 目标 → CONNECT 隧道
    const connectReq = http.request({
      hostname: proxyParsed.hostname,
      port: proxyParsed.port,
      method: 'CONNECT',
      path: `${urlObj.hostname}:${urlObj.port || 443}`,
      timeout: connectTimeout,
      headers: { 'Host': `${urlObj.hostname}:${urlObj.port || 443}` },
    });

    connectReq.on('connect', (res, socket) => {
      if (res.statusCode !== 200) {
        console.error(`[Proxy] CONNECT 隧道建立失败, 状态码: ${res.statusCode}, 代理: ${proxyUrl}`);
        pool.removeProxy(proxyUrl);
        reject(new Error(`CONNECT tunnel failed with ${res.statusCode}`));
        return;
      }
      doRequestOnSocket(socket);
    });

    connectReq.on('error', (err) => {
      console.error(`[Proxy] CONNECT 连接失败: ${proxyUrl}, 错误: ${err.message}`);
      pool.removeProxy(proxyUrl);
      reject(err);
    });
    connectReq.on('timeout', () => {
      console.error(`[Proxy] CONNECT 超时: ${proxyUrl}`);
      pool.removeProxy(proxyUrl);
      connectReq.destroy();
      reject(new Error('CONNECT timeout'));
    });
    connectReq.end();
  } else {
    // HTTP 目标 → 直接转发（完整 URL 作为 path）
    const req = http.request({
      hostname: proxyParsed.hostname,
      port: proxyParsed.port,
      path: urlObj.href,
      method: 'GET',
      timeout: reqTimeout,
      headers: {
        'User-Agent': 'Mozilla/5.0',
        'Host': urlObj.hostname
      },
    }, (resp) => {
      let data = '';
      resp.on('data', (chunk) => { data += chunk.toString(); });
      resp.on('end', () => resolve(data));
    });

    req.on('error', (err) => {
      console.error(`[Proxy] HTTP 代理请求失败: ${proxyUrl}, 错误: ${err.message}`);
      pool.removeProxy(proxyUrl);
      reject(err);
    });
    req.on('timeout', () => {
      console.error(`[Proxy] HTTP 代理请求超时: ${proxyUrl}`);
      pool.removeProxy(proxyUrl);
      req.destroy();
      reject(new Error('Proxy request timeout'));
    });
    req.end();
  }
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
  // 优先级1: GitHub Actions Relay（利用 Azure IP 轮换）
  if (TOKEN_MODE !== 'proxy-pool' && TOKEN_MODE !== 'direct') {
    try {
      const relayToken = await ghRelay.getToken(account.uname);
      if (relayToken) {
        console.log(`[Token] GH-Relay 命中: ${account.uname}`);
        return {
          token: relayToken.token,
          time: String(relayToken.time || fakeLoginData.time),
          sign: String(relayToken.sign || fakeLoginData.sign)
        };
      }
    } catch (e) {
      console.warn(`[Token] GH-Relay 失败: ${e.message}，回退到代理池`);
    }
  }

  // 优先级2: 代理池直连（原来的逻辑）
  if (TOKEN_MODE !== 'direct') {
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

  throw new Error('所有 token 获取渠道均失败');
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

/** Supabase REST 配置（与 account.html 共用同一数据库） */
const SUPABASE_URL = 'https://xywlbjsyhpyyxboznmct.supabase.co';
const SUPABASE_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh5d2xianN5aHB5eXhib3pubWN0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkwODgwNzIsImV4cCI6MjA5NDY2NDA3Mn0.Q0KzoMgwNInH4gi30DEK_d1NbZCwl5yFjnTjubm_gYs';

/**
 * 通过 dldl_account_id 从 Supabase 解析 uname/upwd
 */
async function resolveAccountById(accountId) {
  const url = `${SUPABASE_URL}/rest/v1/accounts?id=eq.${encodeURIComponent(accountId)}&select=uname,upwd`;
  return new Promise((resolve, reject) => {
    const req = https.request(url, {
      method: 'GET',
      timeout: 5000,
      headers: {
        'apikey': SUPABASE_KEY,
        'Authorization': `Bearer ${SUPABASE_KEY}`,
      },
    }, (resp) => {
      let data = '';
      resp.on('data', c => data += c);
      resp.on('end', () => {
        try {
          const rows = JSON.parse(data);
          if (Array.isArray(rows) && rows.length > 0) {
            resolve({ uname: String(rows[0].uname || ''), upwd: String(rows[0].upwd || '') });
          } else {
            resolve(null);
          }
        } catch (e) {
          reject(e);
        }
      });
    });
    req.on('error', reject);
    req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    req.end();
  });
}

/** 根据 dldl_account_id 查询账号凭据（login.php 注入脚本调用） */
app.post('/api/account/resolve', async (req, res) => {
  try {
    const accountId = String(req.body.dldl_account_id || '').trim();
    if (!accountId) {
      res.status(400).json({ ok: false, message: '缺少 dldl_account_id' });
      return;
    }
    const info = await resolveAccountById(accountId);
    if (!info) {
      res.status(404).json({ ok: false, message: '账号未找到' });
      return;
    }
    res.json({ ok: true, ...info });
  } catch (e) {
    console.error('[api/account/resolve] 失败:', e.message);
    res.status(500).json({ ok: false, message: e.message });
  }
});

/** login.php / 账号页「刷新 token」：无状态请求 h5sdk/login（持久化由账号页写 Supabase），3 秒超时 */
app.post('/api/token/refresh', async (req, res) => {
  try {
    const uname = String(req.body.uname || '').trim();
    const upwd = String(req.body.upwd || '').trim();
    if (!uname || !upwd) {
      res.status(400).json({ ok: false, message: '缺少 uname 或 upwd' });
      return;
    }
    const loginData = await fetchH5sdkLoginData(uname, upwd, 3000);
    res.json({ ok: true, ...loginData });
  } catch (error) {
    console.error('[token/refresh]', error.message);
    const code = error.message.includes('timeout') || error.message.includes('超时') ? 408 : 500;
    res.status(code).json({ ok: false, message: error.message });
  }
});

// ==================== APP 端登录（反编译 APK SDK 实现） ====================

const APP_LOGIN_CONFIG = {
  APP_KEY: 'CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1',
  LOGIN_URL: 'http://s-api.37.com.cn/sdk/login/',
  QRCODE_SCAN_URL: 'http://s-api.37.com.cn/go/sdk/account/qrcode/scan',
  QRCODE_CONFIRM_URL: 'http://s-api.37.com.cn/go/sdk/account/qrcode/confirm',
  QRCODE_CANCEL_URL: 'http://s-api.37.com.cn/go/sdk/account/qrcode/cancel',
  // 游戏上下文 gid/pid（SDK 登录用的，与具体游戏无关，用默认值即可）
  PID: '1',
  GID: '1002997',
  REFER: '1_1002997_11327_1001',
  SCUT: '1',
  GWVERSION: '4.6.7',
  SVERSION: '3.7.9.6.1',
  FROM: 'android',
  HOST_SDK_VERSION: '3.7.9.6.1',
  DEV: '9552cfd00bfed7dbd9f8133a0fc9b03e',
};
APP_LOGIN_CONFIG.AES_KEY = APP_LOGIN_CONFIG.APP_KEY.substring(0, 16);

function appAesEncrypt(plainText, key) {
  const cipher = crypto.createCipheriv('aes-128-ecb', Buffer.from(key, 'utf8'), null);
  cipher.setAutoPadding(true);
  let encrypted = cipher.update(plainText, 'utf8', 'base64');
  encrypted += cipher.final('base64');
  return encrypted;
}

function appSignV3(params, appKey) {
  const sortedKeys = Object.keys(params).filter(k => k !== 'sign').sort();
  let signStr = '';
  for (const key of sortedKeys) {
    signStr += key + '=' + params[key];
  }
  signStr += appKey;
  return crypto.createHash('md5').update(signStr).digest('hex').toLowerCase();
}

/**
 * 构建 CommonParamsV1 公共参数（反编译自 com.sqwan.common.request.CommonParamsV1）
 * 用于 qrcode/scan, qrcode/confirm 等 SDK API 请求
 */
function buildCommonParamsV1() {
  const timestamp = String(Math.floor(Date.now() / 1000));
  return {
    gid: APP_LOGIN_CONFIG.GID,
    pid: APP_LOGIN_CONFIG.PID,
    refer: APP_LOGIN_CONFIG.REFER,
    version: '1.0.0',
    time: timestamp,
    dev: APP_LOGIN_CONFIG.DEV,
    oaid: '',
    sversion: APP_LOGIN_CONFIG.SVERSION,
    gwversion: APP_LOGIN_CONFIG.GWVERSION,
    is_root: '0',
    is_simulator: '0',
  };
}

/**
 * HTTP POST 请求（用于 qrcode/scan, qrcode/confirm 等 SDK 接口）
 * 这些接口在 s-api.37.com.cn 上，需要 form-urlencoded 格式
 */
function sdkHttpPost(url, params) {
  const formBody = Object.keys(params)
    .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(String(params[k])))
    .join('&');

  return new Promise((resolve, reject) => {
    const urlObj = new URL(url);
    const opts = {
      hostname: urlObj.hostname,
      port: urlObj.port || 80,
      path: urlObj.pathname,
      method: 'POST',
      timeout: 10000,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'Content-Length': Buffer.byteLength(formBody),
        'User-Agent': 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)',
      },
    };
    const req = http.request(opts, (resp) => {
      let data = '';
      resp.on('data', (chunk) => { data += chunk.toString(); });
      resp.on('end', () => resolve({ status: resp.statusCode, body: data }));
    });
    req.on('error', reject);
    req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    req.write(formBody);
    req.end();
  });
}

/**
 * 模拟 APP 扫码：通知服务端「用户XX扫了这个二维码」
 * POST http://s-api.37.com.cn/go/sdk/account/qrcode/scan
 * 参数: os, code(sessionId), token(APP登录token), login_type, + CommonParamsV1 + SignV3
 */
async function callQrcodeScan(token, sessionId, loginType) {
  const params = {
    os: 'android',
    code: sessionId,
    token: token,
    login_type: loginType || 'common',
    ...buildCommonParamsV1(),
  };
  params.sign = appSignV3(params, APP_LOGIN_CONFIG.APP_KEY);

  console.log('[qrcode/scan] 请求: code=' + (sessionId || '').slice(0, 20) + '... token=' + (token || '').slice(0, 16) + '...');
  const { status, body } = await sdkHttpPost(APP_LOGIN_CONFIG.QRCODE_SCAN_URL, params);
  console.log('[qrcode/scan] HTTP ' + status + ' resp: ' + (body || '').slice(0, 300));

  const json = safeJsonParse(body);
  if (!json || json.state !== 1) {
    throw new Error('qrcode/scan 失败(state=' + (json && json.state) + '): ' + (body || '').slice(0, 200));
  }
  console.log('[qrcode/scan] ✅ 扫码通知成功');
  return json;
}

/**
 * 模拟 APP 确认授权：通知服务端「用户确认登录」
 * POST http://s-api.37.com.cn/go/sdk/account/qrcode/confirm
 * 参数: os, token, code(sessionId), + CommonParamsV1 + SignV3
 */
async function callQrcodeConfirm(token, sessionId) {
  const params = {
    os: 'android',
    token: token,
    code: sessionId,
    ...buildCommonParamsV1(),
  };
  params.sign = appSignV3(params, APP_LOGIN_CONFIG.APP_KEY);

  console.log('[qrcode/confirm] 请求: code=' + (sessionId || '').slice(0, 20) + '...');
  const { status, body } = await sdkHttpPost(APP_LOGIN_CONFIG.QRCODE_CONFIRM_URL, params);
  console.log('[qrcode/confirm] HTTP ' + status + ' resp: ' + (body || '').slice(0, 300));

  const json = safeJsonParse(body);
  if (!json || json.state !== 1) {
    throw new Error('qrcode/confirm 失败(state=' + (json && json.state) + '): ' + (body || '').slice(0, 200));
  }
  console.log('[qrcode/confirm] ✅ 确认授权成功');
  return json;
}

// ==================== 真实 37wan PC 扫码流程模拟 ====================

const PC_HOST = 'app.xxh5.z7xz.com';
const PC_SIGN_KEY = 'pcjgv587!?';

/** /pc/getId: sign = MD5(time + "pcjgv587!?") */
function pcGetIdSign(time) {
  return crypto.createHash('md5').update(String(time) + PC_SIGN_KEY).digest('hex');
}

/** /pc/getCodeInfo: sign = MD5(sessionId + time + "pcjgv587!?") */
function pcGetCodeInfoSign(sessionId, time) {
  return crypto.createHash('md5').update(String(sessionId) + String(time) + PC_SIGN_KEY).digest('hex');
}

/** 安全 JSON 解析 */
function safeJsonParse(text) {
  try { return JSON.parse(text); } catch (_) { return null; }
}

/** HTTPS 请求（GET 或 POST，返回 JSON） */
function pcHttpsRequest(method, path, body) {
  return new Promise((resolve, reject) => {
    const headers = {
      'User-Agent': '37MobileGame/4.6.7 (Android)',
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    };
    if (body) headers['Content-Length'] = Buffer.byteLength(body);
    const opts = { hostname: PC_HOST, path, method, timeout: 8000, headers };
    const req = https.request(opts, (resp) => {
      let data = '';
      resp.on('data', c => data += c);
      resp.on('end', () => resolve({ status: resp.statusCode, body: data }));
    });
    req.on('error', reject);
    req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    if (body) req.write(body);
    req.end();
  });
}

/**
 * 调用真实 37wan /pc/getId 获取会话 ID
 */
async function callRealPcGetId() {
  const time = Date.now();
  const sign = pcGetIdSign(time);
  const { status, body } = await pcHttpsRequest('GET', `/pc/getId?time=${time}&sign=${sign}`);
  if (status !== 200) throw new Error(`pc/getId HTTP ${status}`);
  const json = safeJsonParse(body);
  if (!json || json.state !== 1 || !json.data) {
    throw new Error('pc/getId 无有效 sessionId: ' + (body || '').slice(0, 120));
  }
  return String(json.data);
}

/**
 * 向 PC 扫码会话写入游戏入口参数（替代扫码授权流程）
 * 
 * 原理：enter.js 第 983 行 sendPostCodeInfo 通过 POST /pc/postCodeInfo
 * 将游戏入口参数写入 session，然后 PC 端通过 /pc/getCodeInfo 取回。
 * 
 * 这里直接绕过真实手机扫码（qrcode/scan + qrcode/confirm 在 s-api.37.com.cn 上无法生效），
 * 改用 app.xxh5.z7xz.com 本服的 /pc/postCodeInfo 写入参数。
 * 
 * @param {string} sessionId - 来自 /pc/getId 的会话 ID
 * @param {object} gameParams - { gid, pid, token, time, sign, appVer, platCode, IMEI }
 */
async function postGameEntryParams(sessionId, gameParams) {
  const dataJson = JSON.stringify(gameParams);
  const now = Date.now();
  const sign = crypto.createHash('md5')
    .update(sessionId + String(now) + 'pcjgv587!?')
    .digest('hex');

  const formBody =
    'id=' + encodeURIComponent(sessionId) +
    '&data=' + encodeURIComponent(dataJson) +
    '&time=' + now +
    '&sign=' + sign;

  console.log('[pc-postCodeInfo] 写入游戏入口参数: gid=' + gameParams.gid + ', pid=' + gameParams.pid);

  return new Promise((resolve, reject) => {
    const req = https.request({
      hostname: PC_HOST,
      path: '/pc/postCodeInfo',
      method: 'POST',
      timeout: 8000,
      headers: {
        'User-Agent': '37MobileGame/4.6.7 (Android)',
        'Content-Type': 'application/x-www-form-urlencoded',
        'Content-Length': Buffer.byteLength(formBody),
      },
    }, (resp) => {
      let data = '';
      resp.on('data', c => data += c);
      resp.on('end', () => {
        const json = safeJsonParse(data);
        if (!json || json.state !== 1) {
          reject(new Error('pc/postCodeInfo 失败: ' + (data || '').slice(0, 200)));
          return;
        }
        console.log('[pc-postCodeInfo] ✅ 参数写入成功');
        resolve(true);
      });
    });
    req.on('error', reject);
    req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    req.write(formBody);
    req.end();
  });
}

/**
 * 调用真实 37wan /pc/getCodeInfo 获取游戏入口参数
 */
async function callRealPcGetCodeInfo(sessionId) {
  const time = Date.now();
  const sign = pcGetCodeInfoSign(sessionId, time);
  const encodedId = encodeURIComponent(sessionId);
  const { status, body } = await pcHttpsRequest('GET', `/pc/getCodeInfo?id=${encodedId}&time=${time}&sign=${sign}`);
  if (status !== 200) throw new Error(`pc/getCodeInfo HTTP ${status}`);
  const json = safeJsonParse(body);
  if (!json || json.state !== 1 || !json.data) {
    throw new Error('pc/getCodeInfo 无游戏入口参数: ' + (body || '').slice(0, 120));
  }
  return json.data; // { gid, pid, token, time, sign, appVer, platCode, IMEI }
}

/**
 * 获取游戏入口参数（通过 APP SDK 侧服务器，不跨域）
 * 
 * 使用 app.xxh5.z7xz.com 的 /pc/postCodeInfo 写入参数，绕过 s-api.37.com.cn 的跨服限制。
 * 完整链路:
 *   1. SDK 登录 → 获取 APP token（已完成）
 *   2. GET  /pc/getId          → 获取 sessionId
 *   3. POST /pc/postCodeInfo   → 写入游戏入口参数（跳过手机扫码）
 *   4. GET  /pc/getCodeInfo    → 取回游戏入口参数
 * 
 * @param {string} sdkToken - SDK 登录获得的 token
 * @param {string} loginType - 登录类型 (phone/wx/common)
 * @param {string} uname - 用户名
 * @param {string} upwd - 密码
 * @returns 游戏入口参数 { gid, pid, token, time, sign, appVer, platCode, IMEI }
 */
async function realPcScanFlow(sdkToken, loginType, uname, upwd) {
  // 1. 获取真实会话 ID
  const sessionId = await callRealPcGetId();
  console.log(`[pc-flow] Step 1/3: 获取 sessionId: ${sessionId.slice(0, 10)}...`);

  // 2. 获取游戏入口 token/sign（用 h5sdk/login 获取真实的游戏会话凭证）
  // 注意：这里用 h5sdk 只是获取游戏入口的 token/sign，不是做用户登录
  // SDK 登录（用户认证）已经在前面通过 sdk/login 完成了
  let h5sdkInfo;
  try {
    h5sdkInfo = await fetchH5sdkLoginData(uname, upwd, 8000);
    console.log('[pc-flow] Step 2/3: h5sdk 获取游戏入口 token 成功');
  } catch (e) {
    throw new Error('获取游戏入口凭证失败（h5sdk）: ' + e.message);
  }

  // 3. 构造游戏入口参数并通过 pc/postCodeInfo 写入 session
  const gameParams = {
    gid: '1003279',
    pid: '46',
    token: h5sdkInfo.token,
    time: h5sdkInfo.time,
    sign: h5sdkInfo.sign,
    appVer: '134',
    platCode: '37wan',
    IMEI: 'DCEADE00-A9B3-42F2-B4EB-8C766C0DD7A4',
  };

  await postGameEntryParams(sessionId, gameParams);
  console.log('[pc-flow] Step 2/3 ✅: 游戏入口参数已写入 session');

  // 4. 从 session 取回游戏入口参数
  const params = await callRealPcGetCodeInfo(sessionId);
  console.log(`[pc-flow] Step 3/3 ✅ 获取游戏入口参数: gid=${params.gid}, pid=${params.pid}, token=${(params.token || '').slice(0, 20)}...`);
  return params;
}

app.post('/api/app-login', async (req, res) => {
  try {
    const uname = String(req.body.uname || '').trim();
    const upwd = String(req.body.upwd || '').trim();
    if (!uname || !upwd) {
      return res.status(400).json({ ok: false, message: '缺少 uname 或 upwd' });
    }

    const encryptedPwd = appAesEncrypt(upwd, APP_LOGIN_CONFIG.AES_KEY);
    const timestamp = String(Math.floor(Date.now() / 1000));

    const params = {
      uname,
      upwd: encryptedPwd,
      signType: 'all',
      display_name: '斗罗大陆',
      trans_info: '',
      pid: APP_LOGIN_CONFIG.PID,
      gid: APP_LOGIN_CONFIG.GID,
      refer: APP_LOGIN_CONFIG.REFER,
      dev: APP_LOGIN_CONFIG.DEV,
      sversion: APP_LOGIN_CONFIG.SVERSION,
      version: '1.0.0',
      gwversion: APP_LOGIN_CONFIG.GWVERSION,
      time: timestamp,
      scut: APP_LOGIN_CONFIG.SCUT,
      oaid: '',
      from: APP_LOGIN_CONFIG.FROM,
      host_sdk_version: APP_LOGIN_CONFIG.HOST_SDK_VERSION,
      is_root: '0',
      is_simulator: '0',
    };
    params.sign = appSignV3(params, APP_LOGIN_CONFIG.APP_KEY);

    const formBody = Object.keys(params)
      .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
      .join('&');

    console.log('[app-login] 请求登录:', uname);

    const body = await new Promise((resolve, reject) => {
      const urlObj = new URL(APP_LOGIN_CONFIG.LOGIN_URL);
      const reqOpts = {
        hostname: urlObj.hostname,
        port: urlObj.port || 80,
        path: urlObj.pathname,
        method: 'POST',
        timeout: 15000,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
          'Content-Length': Buffer.byteLength(formBody),
          'User-Agent': 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)',
        },
      };
      const httpReq = http.request(reqOpts, (resp) => {
        let data = '';
        resp.on('data', (chunk) => { data += chunk.toString(); });
        resp.on('end', () => resolve(data));
      });
      httpReq.on('error', reject);
      httpReq.on('timeout', () => { httpReq.destroy(); reject(new Error('Request timeout')); });
      httpReq.write(formBody);
      httpReq.end();
    });

    console.log('[app-login] SDK响应:\n', JSON.stringify(JSON.parse(body), null, 2));
    const json = JSON.parse(body);
    const data = json.data || json;

    // ===== 获取游戏入口参数（使用 APP SDK 的 PC 扫码授权流程） =====
    // 完整链路: SDK登录(/sdk/login) → /pc/getId → qrcode/scan → qrcode/confirm → /pc/getCodeInfo
    const sdkToken = data.token;
    // 判断登录类型：SDK响应中的 login_type (2=手机号, 3=微信, 其他=普通)
    const rawLt = String(data.login_type || '');
    let loginType = rawLt === '2' ? 'phone' : rawLt === '3' ? 'wx' : 'common';
    if (!rawLt) {
      const un = String(data.uname || data.login_account || '');
      if (/^1[3-9]\d{9}$/.test(un)) loginType = 'phone';
      else loginType = 'common';
    }
    console.log(`[app-login] loginType 判定: raw="${rawLt}" → final="${loginType}"`);

    if (!sdkToken) {
      throw new Error('SDK 登录未返回 token，无法继续获取游戏入口参数');
    }

    console.log('[app-login] 使用 APP SDK PC 扫码流程获取游戏入口参数...');
    const gameEntryParams = await realPcScanFlow(sdkToken, loginType, uname, upwd);
    console.log('[app-login] ✅ APP SDK 扫码流程成功，获取到游戏入口参数');

    res.json({
      ok: json.state === 1,
      state: json.state,
      msg: json.msg,
      // SDK 登录透传字段（仅作身份认证用）
      uid: data.uid,
      uname: data.uname,
      appToken: data.token,
      refresh_token: data.refresh_token,
      login_account: data.login_account,
      is_open: data.is_open,
      // 游戏入口参数（来自真实 37wan /pc/getCodeInfo 或 h5sdk 回退）
      token: gameEntryParams.token,
      sign: gameEntryParams.sign,
      entryTime: gameEntryParams.time,
      appVer: gameEntryParams.appVer,
      platCode: gameEntryParams.platCode,
      IMEI: gameEntryParams.IMEI,
      entryGid: gameEntryParams.gid,
      entryPid: gameEntryParams.pid,
    });
  } catch (error) {
    console.error('[app-login] 失败:', error.message);
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

// app.xxh5.z7xz.com 上的接口代理：enter.js 原版会直连外部，
// 页面从本地 localhost 加载后跨域请求可能因 CORS / 混合内容策略异常返回，
// 这里单独代理到正确上游，确保与扫码页流程行为一致
const appXxh5Proxy = createProxyMiddleware({
  target: 'http://app.xxh5.z7xz.com',
  changeOrigin: true,
  secure: false,
  on: {
    proxyReq: (proxyReq, req, res) => {
      proxyReq.setHeader('accept-encoding', 'identity');
      console.log('[proxy] -> app.xxh5', req.originalUrl);
    },
    proxyRes: (proxyRes, req, res) => {
      let body = [];
      proxyRes.on('data', (chunk) => body.push(chunk));
      proxyRes.on('end', () => {
        body = Buffer.concat(body).toString();
        try {
          console.log('[proxy] <- app.xxh5\n', JSON.stringify(JSON.parse(body), null, 2));
        } catch (_) {
          console.log('[proxy] <- app.xxh5', body);
        }
        res.statusCode = proxyRes.statusCode;
        Object.entries(proxyRes.headers || {}).forEach(([key, value]) => {
          if (key.toLowerCase() !== 'content-length' && value !== undefined) {
            res.setHeader(key, value);
          }
        });
        res.end(body);
      });
    },
  },
  selfHandleResponse: true,
});
app.use('/importServer', appXxh5Proxy);
app.use('/serverselect', appXxh5Proxy);
app.use('/query', appXxh5Proxy);

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
        proxyRes.on('end', async () => {
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
          // 兼容 dldl_account_id：从 Supabase 解析凭据，避免 URL 明文携带 uname/upwd
          try {
            let qUname = String(req.query.uname || '').trim();
            let qUpwd = String(req.query.upwd || '').trim();
            if ((!qUname || !qUpwd) && req.query.dldl_account_id) {
              const info = await resolveAccountById(String(req.query.dldl_account_id).trim());
              if (info) { qUname = info.uname; qUpwd = info.upwd; }
            }
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

          // 在"线上原版 enter.js"上仅替换扫码与回跳地址，保证行为在本地闭环
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/pc\/getId/g, '/pc/getId');
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/pc\/getCodeInfo/g, '/pc/getCodeInfo');
          body = body.replace(/https:\/\/dldl\.50pk\.com\/login\.php\?/g, '/login.php?');
          // importServer / loginserverdata / serverByUid 都在 app.xxh5.z7xz.com，走本地代理避免跨域
          body = body.replace(/http:\/\/app\.xxh5\.z7xz\.com\/importServer/g, '/importServer');
          body = body.replace(/http:\/\/app\.xxh5\.z7xz\.com\/serverselect\/loginserverdata/g, '/serverselect/loginserverdata');
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/query\/serverByUid/g, '/query/serverByUid');
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
  console.log(`✅ Token mode: ${TOKEN_MODE} (relay=GitHub Actions Azure IP轮换 | proxy-pool=免费代理池 | direct=直连)`);
  if (TOKEN_MODE !== 'proxy-pool' && TOKEN_MODE !== 'direct') {
    console.log(`✅ GH-Relay: Gist=${ghRelay.gistId ? '已配置' : '未配置'} | RawURL=${ghRelay.rawUrl ? '已配置' : '未配置'}`);
  }
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