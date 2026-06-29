/**
 * GitHub Actions Runner 脚本 — 从 Azure IP 获取 token 并写入 Gist
 * 被 .github/workflows/token-fetcher.yml 调用
 */
const https = require('https');
const crypto = require('crypto');

function md5(str) {
  return crypto.createHash('md5').update(str).digest('hex');
}

function h5sdkSign(params, apiKey) {
  const sorted = Object.keys(params).sort();
  let str = '';
  for (const k of sorted) str += k + '=' + params[k];
  return md5(str + apiKey);
}

function httpsGet(url, timeout = 10000) {
  return new Promise((resolve, reject) => {
    const req = https.get(url, {
      headers: { 'User-Agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 18_5 like Mac OS X) AppleWebKit/605.1.15' },
      timeout
    }, (res) => {
      let data = '';
      res.on('data', chunk => data += chunk);
      res.on('end', () => resolve({ status: res.statusCode, body: data }));
    });
    req.on('error', reject);
    req.on('timeout', () => { req.destroy(); reject(new Error('Timeout')); });
  });
}

async function readGist() {
  console.log('[DEBUG] readGist: Gist ID =', process.env.GH_GIST_ID);
  console.log('[DEBUG] readGist: Token prefix =', (process.env.GH_GIST_TOKEN || '').substring(0, 10) + '...');
  return new Promise((resolve) => {
    const req = https.get('https://api.github.com/gists/' + process.env.GH_GIST_ID, {
      headers: {
        'Authorization': 'Bearer ' + process.env.GH_GIST_TOKEN,
        'Accept': 'application/vnd.github.v3+json',
        'User-Agent': 'DLDL-TokenFetcher'
      }
    }, (res) => {
      let data = '';
      res.on('data', c => data += c);
      res.on('end', () => {
        console.log('[DEBUG] readGist: HTTP status =', res.statusCode);
        if (res.statusCode !== 200) {
          console.error('[DEBUG] readGist: 非 200 响应, body =', data.substring(0, 300));
          resolve({});
          return;
        }
        try {
          const gist = JSON.parse(data);
          const file = gist.files && gist.files['dldl-tokens.json'];
          const content = file && file.content ? JSON.parse(file.content) : {};
          console.log('[DEBUG] readGist: 读取到 keys =', Object.keys(content));
          resolve(content);
        } catch (e) {
          console.error('[DEBUG] readGist: JSON 解析失败', e.message);
          resolve({});
        }
      });
    });
    req.on('error', (e) => {
      console.error('[DEBUG] readGist: 请求错误', e.message);
      resolve({});
    });
  });
}

async function updateGist(data) {
  const json = JSON.stringify(data);
  return new Promise((resolve, reject) => {
    const req = https.request({
      hostname: 'api.github.com',
      path: '/gists/' + process.env.GH_GIST_ID,
      method: 'PATCH',
      headers: {
        'Authorization': 'Bearer ' + process.env.GH_GIST_TOKEN,
        'Accept': 'application/vnd.github.v3+json',
        'Content-Type': 'application/json',
        'User-Agent': 'DLDL-TokenFetcher',
        'Content-Length': Buffer.byteLength(json)
      }
    }, (res) => {
      let body = '';
      res.on('data', c => body += c);
      res.on('end', () => {
        if (res.statusCode === 200) {
          console.log('✅ Gist 更新成功');
          resolve(true);
        } else {
          console.error('❌ Gist 更新失败 HTTP', res.statusCode, body.substring(0, 200));
          reject(new Error('Gist update failed: ' + res.statusCode));
        }
      });
    });
    req.on('error', reject);
    req.write(json);
    req.end();
  });
}

(async () => {
  // ======== 启动诊断 ========
  console.log('========== 启动诊断 ==========');
  console.log('GITHUB_EVENT_NAME:', process.env.GITHUB_EVENT_NAME || '(空)');
  console.log('GH_GIST_TOKEN 已配置:', !!process.env.GH_GIST_TOKEN);
  console.log('GH_GIST_ID 已配置:', !!process.env.GH_GIST_ID);
  if (process.env.GH_GIST_TOKEN) {
    console.log('GH_GIST_TOKEN 前缀:', process.env.GH_GIST_TOKEN.substring(0, 6) + '...');
  }
  console.log('INPUT_UNAME:', process.env.INPUT_UNAME || '(空)');
  console.log('INPUT_UPWD 已配置:', !!process.env.INPUT_UPWD);
  console.log('INPUT_REQUEST_ID:', process.env.INPUT_REQUEST_ID || '(空)');
  console.log('================================');

  // 获取 Runner IP
  let runnerIP = 'unknown';
  try {
    const ipRes = await httpsGet('https://api.ipify.org');
    runnerIP = ipRes.body.trim();
    console.log('当前 Runner 出口 IP:', runnerIP);
  } catch (e) {
    console.warn('获取 IP 失败:', e.message);
  }

  const eventName = process.env.GITHUB_EVENT_NAME || '';
  const uname = process.env.INPUT_UNAME;
  const upwd = process.env.INPUT_UPWD;
  const requestId = process.env.INPUT_REQUEST_ID || '';

  // 非 workflow_dispatch 时仅记录 IP（保活）
  if (eventName !== 'workflow_dispatch' || !uname || !upwd) {
    console.log('[保活模式] 触发类型:', eventName, '| IP:', runnerIP);

    if (!process.env.GH_GIST_TOKEN || !process.env.GH_GIST_ID) {
      console.log('❌ 保活跳过：GH_GIST_TOKEN 或 GH_GIST_ID 未配置');
      return;
    }

    try {
      console.log('[保活] 读取 Gist...');
      const existing = await readGist();
      console.log('[保活] existing keys:', Object.keys(existing));
      console.log('[保活] 写入 Gist...');
      await updateGist({
        tokens: existing.tokens || {},
        pending: existing.pending || {},
        runnerIP,
        updatedAt: new Date().toISOString()
      });
      console.log('[保活] ✅ 完成');
    } catch (e) {
      console.error('❌ 保活 Gist 操作失败:', e.message);
      // 不抛异常，让 workflow 正常结束
    }
    return;
  }

  // 按需调用 h5sdk/login
  console.log('[按需] 为账号', uname, '获取 token...');

  const time = String(Math.floor(Date.now() / 1000));
  const baseParams = {
    uname, upwd,
    autoLogin: 'true',
    pid: '46',
    gid: '1003279',
    sversion: 'undefined',
    version: '1.0.4',
    time,
    dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
    os: 'iOS',
    over: '18.5'
  };
  const sign = h5sdkSign(baseParams, 'Jp*4Y8vQOYck2*&Z');
  const params = new URLSearchParams({ ...baseParams, sign });
  const url = 'https://s-api.37.com.cn/h5sdk/login?' + params.toString();

  let tokenResult;
  try {
    const { status, body } = await httpsGet(url, 10000);
    console.log('HTTP', status, 'body:', body.substring(0, 120));
    const match = body.match(/^(\w+)\(([\s\S]+)\);?$/);
    const jsonText = match ? match[2] : body;
    const payload = JSON.parse(jsonText);
    tokenResult = {
      ok: payload && payload.state === 1,
      token: payload?.data?.token || null,
      time: payload?.data?.time || time,
      sign: payload?.data?.sign || sign,
      msg: payload?.msg || ''
    };
  } catch (e) {
    tokenResult = { ok: false, token: null, time, sign, msg: e.message };
  }

  const entry = {
    uname,
    ...tokenResult,
    fetchedAt: new Date().toISOString(),
    runnerIP,
    requestId
  };

  console.log(tokenResult.ok ? '✅' : '❌', uname,
    tokenResult.ok ? 'token:' + (tokenResult.token || '').substring(0, 16) + '...' : tokenResult.msg);

  if (!process.env.GH_GIST_TOKEN || !process.env.GH_GIST_ID) {
    console.log('⚠️ 未配置 GH_GIST_TOKEN / GH_GIST_ID，仅输出结果');
    console.log(JSON.stringify(entry, null, 2));
    return;
  }

  // 合并：保留已有 tokens，覆盖/新增当前账号
  try {
    console.log('[按需] 读取 Gist...');
    const existing = await readGist();
    const tokens = existing.tokens || {};
    tokens[uname] = entry;
    console.log('[按需] 写入 Gist (', Object.keys(tokens).length, '个账号)...');
    await updateGist({
      tokens,
      runnerIP,
      updatedAt: new Date().toISOString(),
      lastRequestId: requestId
    });
    console.log('[按需] ✅ 完成');
  } catch (e) {
    console.error('❌ 按需 Gist 写入失败:', e.message);
    // 也打印 entry 供备用
    console.log(JSON.stringify(entry, null, 2));
  }
})().catch(e => {
  console.error('💥 未捕获异常:', e.message, e.stack);
  process.exit(0); // 不让异常导致 workflow 标红
});
