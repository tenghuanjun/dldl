const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const https = require('https');
const path = require('path');
const fs = require('fs');
const app = express();
const PORT = 8080;
const TARGET = 'https://dldl.50pk.com';
const fixedQrTimeMs = String(process.env.DLDL_FIXED_TIME_MS || '1828368000000');
const accountsFilePath = path.join(__dirname, 'account.json');
app.use(express.json({ limit: '1mb' }));

const accountConfig = {
  uname: process.env.DLDL_UNAME || 'laogao666666',
  upwd: process.env.DLDL_UPWD || '1234561239'
};
const qrSessionAccountMap = new Map();
const tokenApiBaseParams = {
  autoLogin: 'true',
  pid: '46',
  gid: '1005176',
  sversion: 'undefined',
  version: '1.0.4',
  time: '1778137876',
  dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
  os: 'iOS',
  over: '18.5',
  sign: '865145b213b92f565e24b8022ad18ace'
};

function fetchText(url) {
  return new Promise((resolve, reject) => {
    https.get(url, (resp) => {
      let data = '';
      resp.on('data', (chunk) => {
        data += chunk.toString();
      });
      resp.on('end', () => resolve(data));
    }).on('error', reject);
  });
}

function getAccountFromRequest(req) {
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
    uname: req.query.uname || refererUname || accountConfig.uname,
    upwd: req.query.upwd || refererUpwd || accountConfig.upwd
  };
}

function readAccountsFromFile() {
  try {
    const text = fs.readFileSync(accountsFilePath, 'utf8');
    const parsed = JSON.parse(text);
    return Array.isArray(parsed) ? parsed : [];
  } catch (error) {
    console.error(`[Accounts] read failed: ${error.message}`);
    return [];
  }
}

function validateAccounts(accounts) {
  if (!Array.isArray(accounts)) return { ok: false, message: 'accounts must be an array' };
  for (const acc of accounts) {
    if (!acc || typeof acc !== 'object') return { ok: false, message: 'account item invalid' };
    if (!String(acc.uname || '').trim()) return { ok: false, message: 'uname is required' };
    if (!String(acc.upwd || '').trim()) return { ok: false, message: 'upwd is required' };
  }
  return { ok: true };
}

function writeAccountsToFile(accounts) {
  const tmpPath = `${accountsFilePath}.tmp`;
  fs.writeFileSync(tmpPath, JSON.stringify(accounts, null, 2), 'utf8');
  fs.renameSync(tmpPath, accountsFilePath);
}

// 供 account.html 读取/写入账号
app.get('/accounts', (req, res) => {
  res.json(readAccountsFromFile());
});

app.post('/accounts', (req, res) => {
  const body = req.body || {};
  const accounts = Array.isArray(body) ? body : body.accounts;
  const validation = validateAccounts(accounts);
  if (!validation.ok) {
    res.status(400).json({ ok: false, message: validation.message });
    return;
  }
  try {
    writeAccountsToFile(accounts);
    res.json({ ok: true });
  } catch (error) {
    console.error(`[Accounts] write failed: ${error.message}`);
    res.status(500).json({ ok: false, message: error.message });
  }
});

async function getDynamicTokenInfo(account) {
  const callback = `jsonp_callback_${Date.now()}`;
  const params = new URLSearchParams({
    ...tokenApiBaseParams,
    uname: account.uname,
    upwd: account.upwd,
    callback
  });
  const url = `https://s-api.37.com.cn/h5sdk/login?${params.toString()}`;
  const responseText = await fetchText(url);
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
  gid: "1005176",
  pid: "46",
  token: "BASE64N2MxYkNHQS8wQkNTaVdQVjYrd0o5VWFKS3NuaUoycERXeVFpekVhWjhZZ2RoNGIrZEg1dm5xTUN0MGIybVBRTVRnNkgwNm5pYTdxenR3RmlzVFVyYkNFZDl2RXlLbkV3SUtXS3k0S2pNNnZRN2FoTElEQ2dtcHZHRE5YZVFjUS9vTGlISTF0cThpVjNMY0N0b05PaXU4b2lRZk4zWXlDeFg2U05oejE1a3NEbTMrdjRFN0NtdnZ2aFc4QVE3VTlwYm5GMkdn",
  time: "1778137103",
  sign: "8f44cb6da674f966231cb60432ba5b07",
  appVer: "134",
  platCode: "37wan",
  IMEI: "DCEADE00-A9B3-42F2-B4EB-8C766C0DD7A4"
};
// 拦截扫码接口
app.use('/pc/getCodeInfo', async (req, res) => {
  console.log('[Mock] getCodeInfo');
  const sessionId = String(req.query.id || '');
  const account = qrSessionAccountMap.get(sessionId) || getAccountFromRequest(req);
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
  qrSessionAccountMap.set(id, account);
  console.log(`[Mock] getId => ${id} for account: ${account.uname}`);
  res.json({ state: 1, msg: "success", data: id });
});
// 静态文件服务
app.use(express.static(__dirname));
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
            `<script>window.__PC_QR_FIXED_TIME_MS=${fixedQrTimeMs};</script>\n    $1`
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
app.listen(PORT, () => {
  console.log(`✅ Proxy running at http://localhost:${PORT}`);
  console.log(`✅ Mock interceptors active`);
  console.log(`✅ Account uname: ${accountConfig.uname}`);
  console.log(`✅ Fixed QR time(ms): ${fixedQrTimeMs}`);
  console.log(`✅ Multi-account mode via login.php?uname=xxx&upwd=xxx`);
});