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
  uname: process.env.DLDL_UNAME,
  upwd: process.env.DLDL_UPWD
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

function readAccountsFromFile() {
  try {
    const text = fs.readFileSync(accountsFilePath, 'utf8');
    const parsed = JSON.parse(text);
    // 严格新格式：{ area: string[], list: Record<string, Account[]> }
    if (parsed && typeof parsed === 'object' && !Array.isArray(parsed)) return parsed;
    throw new Error('account.json must be an object: { area, list }');
  } catch (error) {
    console.error(`[Accounts] read failed: ${error.message}`);
    return { area: [], list: {} };
  }
}

function validateAccountItem(acc) {
  if (!acc || typeof acc !== 'object') return { ok: false, message: 'account item invalid' };
  if (!String(acc.uname || '').trim()) return { ok: false, message: 'uname is required' };
  if (!String(acc.upwd || '').trim()) return { ok: false, message: 'upwd is required' };
  return { ok: true };
}

function validateAccounts(payload) {
  // 严格新格式：{ area: string[], list: Record<string, Account[]> }
  if (!payload || typeof payload !== 'object') return { ok: false, message: 'accounts payload invalid' };
  const area = payload.area;
  const list = payload.list;
  if (!Array.isArray(area)) return { ok: false, message: 'area must be an array' };
  if (!list || typeof list !== 'object') return { ok: false, message: 'list must be an object' };
  for (const a of area) {
    const key = String(a || '').trim();
    if (!key) return { ok: false, message: 'area item invalid' };
    const accounts = list[key];
    if (!Array.isArray(accounts)) return { ok: false, message: `list[${key}] must be an array` };
    for (const acc of accounts) {
      const v = validateAccountItem(acc);
      if (!v.ok) return v;
    }
  }
  // 允许 list 里多余的区服 key（前端会追加渲染）
  for (const k of Object.keys(list)) {
    const accounts = list[k];
    if (!Array.isArray(accounts)) return { ok: false, message: `list[${k}] must be an array` };
    for (const acc of accounts) {
      const v = validateAccountItem(acc);
      if (!v.ok) return v;
    }
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
  const accountsPayload = body.accounts ?? body;
  const validation = validateAccounts(accountsPayload);
  if (!validation.ok) {
    res.status(400).json({ ok: false, message: validation.message });
    return;
  }
  try {
    writeAccountsToFile(accountsPayload);
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
  token: "",
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
// h5sdk/login 代理，处理签名并转发请求
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
    const responseText = await fetchText(url);
    res.setHeader('Content-Type', 'application/javascript; charset=utf-8');
    res.end(responseText);
  } catch (error) {
    console.error('[h5sdk/login] failed:', error.message);
    res.status(500).json({ state: 0, msg: error.message });
  }
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
            `<script>(function(){\n  window.__PC_QR_FIXED_TIME_MS=${fixedQrTimeMs};\n  try{var p=new URLSearchParams(location.search||\"\");window.__DLDL_PROXY_UNAME=p.get(\"uname\")||\"\";window.__DLDL_PROXY_UPWD=p.get(\"upwd\")||\"\";}catch(_){window.__DLDL_PROXY_UNAME=\"\";window.__DLDL_PROXY_UPWD=\"\";}\n\n  function injectRefreshButton(){\n    try{\n      if(document.getElementById(\"dldl-refresh-token-btn\")) return;\n      var btn=document.createElement(\"button\");\n      btn.id=\"dldl-refresh-token-btn\";\n      btn.type=\"button\";\n      btn.textContent=\"刷新 token\";\n      btn.style.cssText=\"position:fixed;right:16px;top:16px;z-index:99999;background:#2f81f7;color:#fff;border:none;border-radius:8px;padding:10px 14px;font-size:14px;cursor:pointer;box-shadow:0 8px 24px rgba(0,0,0,.25);\";\n      btn.addEventListener(\"click\", async function(){\n        try{\n          btn.disabled=true;\n          btn.textContent=\"刷新中...\";\n          var uname=window.__DLDL_PROXY_UNAME||\"\";\n          var upwd=window.__DLDL_PROXY_UPWD||\"\";\n          if(!uname||!upwd) throw new Error(\"缺少账号信息\");\n          var resp=await fetch(\"/accounts\", {cache:\"no-store\"});\n          var json=await resp.json();\n          var area=Array.isArray(json.area)?json.area:[];\n          var list=(json.list&&typeof json.list===\"object\")?json.list:{};\n          var found=false;\n          for(var i=0;i<area.length;i++){\n            var key=String(area[i]||\"\");\n            var arr=list[key];\n            if(!Array.isArray(arr)) continue;\n            for(var j=0;j<arr.length;j++){\n              var acc=arr[j];\n              if(String(acc&&acc.uname||\"\")===uname && String(acc&&acc.upwd||\"\")===upwd){\n                var time=String(Math.floor(Date.now()/1000));\n                var signParams={uname:uname,upwd:upwd,autoLogin:\"true\",pid:\"46\",gid:\"1005176\",sversion:\"undefined\",version:\"1.0.4\",time:time,dev:\"9c71cbfa62ecfd4f5a1125d9c6c51367\",os:\"iOS\",over:\"18.5\"};\n                var API_KEY=\"Jp*4Y8vQOYck2*&Z\";\n                var sorted=Object.keys(signParams).sort();\n                var str=\"\";\n                for(var s=0;s<sorted.length;s++) str+=sorted[s]+\"=\"+signParams[sorted[s]];\n                var sign=hex_md5(str+API_KEY);\n                var url=\"https://s-api.37.com.cn/h5sdk/login?\"+new URLSearchParams(Object.assign({}, signParams, {sign:sign})).toString();\n                var payload=await new Promise(function(resolve,reject){\n                  var cb=\"__dldl_refresh_\"+Date.now()+\"_\"+Math.random().toString(36).slice(2);\n                  var u=new URL(url);\n                  u.searchParams.set(\"callback\", cb);\n                  var script=document.createElement(\"script\");\n                  var timer=setTimeout(function(){cleanup();reject(new Error(\"jsonp timeout\"));},15000);\n                  function cleanup(){ if(script&&script.parentNode) script.parentNode.removeChild(script); try{delete window[cb];}catch(_){window[cb]=undefined;} clearTimeout(timer); }\n                  window[cb]=function(data){ cleanup(); resolve(data); };\n                  script.onerror=function(){ cleanup(); reject(new Error(\"jsonp network error\")); };\n                  script.src=u.toString(); document.head.appendChild(script);\n                });\n                if(!payload||payload.state!==1||!payload.data||!payload.data.token) throw new Error((payload&&payload.msg)||\"login api failed\");\n                acc.token=String(payload.data.token);\n                acc.time=String(payload.data.time||time);\n                acc.sign=String(payload.data.sign||\"\");\n                found=true;\n                break;\n              }\n            }\n            if(found) break;\n          }\n          if(!found) throw new Error(\"未找到对应账号\");\n          var save=await fetch(\"/accounts\", {method:\"POST\", headers:{\"Content-Type\":\"application/json\"}, body:JSON.stringify({accounts:{area:area,list:list}})});\n          var saveJson=await save.json();\n          if(!save.ok || !saveJson.ok) throw new Error((saveJson&&saveJson.message)||\"保存失败\");\n          btn.textContent=\"刷新成功\";\n          setTimeout(function(){ btn.disabled=false; btn.textContent=\"刷新 token\"; }, 1200);\n        }catch(err){\n          console.error(err);\n          alert(\"刷新 token 失败：\" + (err&&err.message ? err.message : err));\n          btn.disabled=false;\n          btn.textContent=\"刷新 token\";\n        }\n      });\n      document.body.appendChild(btn);\n    }catch(_){ }\n  }\n  if(document.readyState===\"loading\") document.addEventListener(\"DOMContentLoaded\", injectRefreshButton); else injectRefreshButton();\n\n  // 兜底：不依赖 enter.js 的字符串替换是否命中，运行时强制给扫码请求补上 uname/upwd。\n  try{\n    var _open=XMLHttpRequest.prototype.open;\n    XMLHttpRequest.prototype.open=function(method,url){\n      try{\n        var uStr=String(url||\"\");\n        if(uStr){\n          var a=window.__DLDL_PROXY_UNAME||\"\";\n          var b=window.__DLDL_PROXY_UPWD||\"\";\n          if((uStr.indexOf(\"/pc/getId\")==0||uStr.indexOf(\"/pc/getCodeInfo\")==0) && (a||b)){\n            var u=new URL(uStr, location.origin);\n            if(a && !u.searchParams.has(\"uname\")) u.searchParams.set(\"uname\", a);\n            if(b && !u.searchParams.has(\"upwd\")) u.searchParams.set(\"upwd\", b);\n            url=u.pathname + (u.search||\"\");\n            arguments[1]=url;\n          }\n        }\n      }catch(_){ }\n      return _open.apply(this, arguments);\n    };\n  }catch(_){ }\n})();</script>\n    $1`
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
app.listen(PORT, () => {
  console.log(`✅ Proxy running at http://localhost:${PORT}`);
  console.log(`✅ Mock interceptors active`);
  console.log(`✅ Account uname: ${accountConfig.uname}`);
  console.log(`✅ Fixed QR time(ms): ${fixedQrTimeMs}`);
  console.log(`✅ Multi-account mode via login.php?uname=xxx&upwd=xxx`);
});