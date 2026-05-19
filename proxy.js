const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const https = require('https');
const http = require('http');
const path = require('path');
const fs = require('fs');
const sqlite3 = require('sqlite3').verbose();
const { getInstance: getProxyPool } = require('./proxy-pool');
const app = express();
const PORT = Number(process.env.DLDL_PORT || process.env.PORT || 8080);
const TARGET = 'https://dldl.50pk.com';
const fixedQrTimeMs = String(process.env.DLDL_FIXED_TIME_MS || '1828368000000');
/** 静态资源目录（打包后只读，位于 asar 内） */
const staticRoot = process.env.DLDL_STATIC_ROOT || __dirname;
/** 用户可写目录：Electron 下为 app.getPath('userData')；直接 node proxy.js 时默认与脚本同目录 */
const userDataDir = process.env.DLDL_USER_DATA || __dirname;
const accountsFilePath = path.join(userDataDir, 'account.json');
const accountsDbPath = path.join(userDataDir, 'accounts.sqlite');
app.use(express.json({ limit: '1mb' }));

/** Tauri 轮询直到 200；listen 早于 SQLite 初始化，避免壳在 DB 慢/锁等待时误判端口超时 */
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

const IMPORT_DB_MAX_BYTES = 50 * 1024 * 1024;
const SQLITE_MAGIC = Buffer.from('SQLite format 3\0');

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
const qrSessionAccountMap = new Map();
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
let db = null;

async function closeDatabase() {
  if (!db) return;
  const instance = db;
  db = null;
  await new Promise((resolve, reject) => {
    instance.close((error) => {
      if (error) reject(error);
      else resolve();
    });
  });
}

function validateImportedAccountsDbFile(filePath) {
  return new Promise((resolve, reject) => {
    const testDb = new sqlite3.Database(filePath, sqlite3.OPEN_READONLY, (openError) => {
      if (openError) {
        reject(new Error(`无法作为 SQLite 打开：${openError.message}`));
        return;
      }
      testDb.all(
        `SELECT name FROM sqlite_master WHERE type='table' AND name IN ('areas','accounts')`,
        [],
        (err, rows) => {
          if (err) {
            testDb.close(() => reject(err));
            return;
          }
          const names = new Set((rows || []).map((row) => String(row && row.name)));
          if (!names.has('areas') || !names.has('accounts')) {
            testDb.close(() => reject(new Error('不是本工具使用的账号库（需包含 areas、accounts 表）。请确认文件由本页「导出数据库」生成或结构一致。')));
            return;
          }
          testDb.close((closeErr) => {
            if (closeErr) reject(closeErr);
            else resolve();
          });
        }
      );
    });
  });
}

function ensureUserDataLayout() {
  fs.mkdirSync(userDataDir, { recursive: true });
  function copyIfMissing(legacyPath, targetPath) {
    if (path.resolve(legacyPath) === path.resolve(targetPath)) return;
    if (fs.existsSync(targetPath)) return;
    if (!fs.existsSync(legacyPath)) return;
    try {
      fs.copyFileSync(legacyPath, targetPath);
      console.log(`[Accounts] 已从旧位置复制到用户数据目录: ${path.basename(targetPath)}`);
    } catch (error) {
      console.warn(`[Accounts] 跳过复制 ${legacyPath}: ${error.message}`);
    }
  }
  // 从「与 proxy.js 同目录」迁移（例如首次改用 Electron 而数据仍在项目根目录）
  copyIfMissing(path.join(__dirname, 'accounts.sqlite'), accountsDbPath);
  copyIfMissing(path.join(__dirname, 'account.json'), accountsFilePath);
}

function normalizeAccountsPayload(payload) {
  const source = payload && typeof payload === 'object' ? payload : {};
  const sourceArea = Array.isArray(source.area) ? source.area : [];
  const sourceList = source.list && typeof source.list === 'object' ? source.list : {};

  const area = [];
  const areaSeen = new Set();
  for (const rawArea of sourceArea) {
    const areaName = String(rawArea || '').trim();
    if (!areaName || areaSeen.has(areaName)) continue;
    areaSeen.add(areaName);
    area.push(areaName);
  }
  for (const key of Object.keys(sourceList)) {
    const areaName = String(key || '').trim();
    if (!areaName || areaSeen.has(areaName)) continue;
    areaSeen.add(areaName);
    area.push(areaName);
  }

  const list = {};
  const dropped = [];
  for (const areaName of area) {
    const rows = Array.isArray(sourceList[areaName]) ? sourceList[areaName] : [];
    const areaRows = [];
    const accountSeen = new Set();
    for (const item of rows) {
      const uname = String(item && item.uname ? item.uname : '').trim();
      const upwd = String(item && item.upwd ? item.upwd : '').trim();
      if (!uname || !upwd) {
        dropped.push({ areaName, reason: 'uname/upwd missing' });
        continue;
      }
      const dedupeKey = `${areaName}::${uname}`;
      if (accountSeen.has(dedupeKey)) {
        dropped.push({ areaName, reason: `duplicate uname in area: ${uname}` });
        continue;
      }
      accountSeen.add(dedupeKey);
      areaRows.push({
        uname,
        upwd,
        name: String(item && item.name ? item.name : '').trim(),
        token: String(item && item.token ? item.token : '').trim(),
        time: String(item && item.time ? item.time : '').trim(),
        sign: String(item && item.sign ? item.sign : '').trim()
      });
    }
    list[areaName] = areaRows;
  }

  return { area, list, dropped };
}

function openDatabase() {
  return new Promise((resolve, reject) => {
    const instance = new sqlite3.Database(accountsDbPath, (error) => {
      if (error) {
        reject(error);
        return;
      }
      resolve(instance);
    });
  });
}

function dbRun(sql, params = []) {
  return new Promise((resolve, reject) => {
    db.run(sql, params, function onRun(error) {
      if (error) {
        reject(error);
        return;
      }
      resolve(this);
    });
  });
}

function dbGet(sql, params = []) {
  return new Promise((resolve, reject) => {
    db.get(sql, params, (error, row) => {
      if (error) {
        reject(error);
        return;
      }
      resolve(row);
    });
  });
}

function dbAll(sql, params = []) {
  return new Promise((resolve, reject) => {
    db.all(sql, params, (error, rows) => {
      if (error) {
        reject(error);
        return;
      }
      resolve(rows);
    });
  });
}

async function initDatabase() {
  await closeDatabase();
  db = await openDatabase();
  await dbRun('PRAGMA busy_timeout = 15000');
  await dbRun('PRAGMA foreign_keys = ON');
  await dbRun(`
    CREATE TABLE IF NOT EXISTS areas (
      name TEXT PRIMARY KEY,
      sort_order INTEGER NOT NULL
    )
  `);
  await dbRun(`
    CREATE TABLE IF NOT EXISTS accounts (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      area_name TEXT NOT NULL,
      uname TEXT NOT NULL,
      upwd TEXT NOT NULL,
      name TEXT,
      token TEXT,
      time TEXT,
      sign TEXT,
      UNIQUE(area_name, uname),
      FOREIGN KEY (area_name) REFERENCES areas(name) ON DELETE CASCADE
    )
  `);
  const tableInfo = await dbAll("PRAGMA table_info('accounts')");
  const hasLegacyUniqueUname =
    Array.isArray(tableInfo)
    && tableInfo.some((column) => String(column && column.name) === 'uname')
    && tableInfo.some((column) => String(column && column.name) === 'uname' && Number(column.pk) === 0);
  const createSqlRow = await dbGet("SELECT sql FROM sqlite_master WHERE type='table' AND name='accounts'");
  const createSql = String((createSqlRow && createSqlRow.sql) || '').toUpperCase();
  if (hasLegacyUniqueUname && createSql.includes('UNAME TEXT NOT NULL UNIQUE')) {
    await dbRun('BEGIN IMMEDIATE TRANSACTION');
    try {
      await dbRun(`
        CREATE TABLE IF NOT EXISTS accounts_new (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          area_name TEXT NOT NULL,
          uname TEXT NOT NULL,
          upwd TEXT NOT NULL,
          name TEXT,
          token TEXT,
          time TEXT,
          sign TEXT,
          UNIQUE(area_name, uname),
          FOREIGN KEY (area_name) REFERENCES areas(name) ON DELETE CASCADE
        )
      `);
      await dbRun(`
        INSERT OR IGNORE INTO accounts_new(area_name, uname, upwd, name, token, time, sign)
        SELECT area_name, uname, upwd, name, token, time, sign FROM accounts
      `);
      await dbRun('DROP TABLE accounts');
      await dbRun('ALTER TABLE accounts_new RENAME TO accounts');
      await dbRun('COMMIT');
      console.log('[Accounts] upgraded SQLite schema to UNIQUE(area_name, uname)');
    } catch (error) {
      await dbRun('ROLLBACK');
      throw error;
    }
  }
  await dbRun('CREATE INDEX IF NOT EXISTS idx_accounts_area_name ON accounts(area_name)');
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

function readAccountsFromJsonFile() {
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

async function readAccountsFromDb() {
  const rows = await dbAll(`
    SELECT
      a.name AS area_name,
      ac.uname AS uname,
      ac.upwd AS upwd,
      ac.name AS display_name,
      ac.token AS token,
      ac.time AS time,
      ac.sign AS sign
    FROM areas a
    LEFT JOIN accounts ac ON ac.area_name = a.name
    ORDER BY a.sort_order ASC, ac.id ASC
  `);
  const area = [];
  const list = {};
  for (const row of rows) {
    const areaName = String(row.area_name || '');
    if (!list[areaName]) {
      area.push(areaName);
      list[areaName] = [];
    }
    if (!row.uname) continue;
    list[areaName].push({
      uname: String(row.uname || ''),
      upwd: String(row.upwd || ''),
      name: String(row.display_name || ''),
      token: String(row.token || ''),
      time: String(row.time || ''),
      sign: String(row.sign || '')
    });
  }
  return { area, list };
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

async function writeAccountsToDb(accounts) {
  const areaOrder = [];
  const areaSeen = new Set();
  for (const areaName of accounts.area || []) {
    const key = String(areaName || '').trim();
    if (!key || areaSeen.has(key)) continue;
    areaSeen.add(key);
    areaOrder.push(key);
  }
  for (const key of Object.keys(accounts.list || {})) {
    const name = String(key || '').trim();
    if (!name || areaSeen.has(name)) continue;
    areaSeen.add(name);
    areaOrder.push(name);
  }

  await dbRun('BEGIN IMMEDIATE TRANSACTION');
  try {
    await dbRun('DELETE FROM accounts');
    await dbRun('DELETE FROM areas');
    for (let idx = 0; idx < areaOrder.length; idx += 1) {
      const areaName = areaOrder[idx];
      await dbRun('INSERT INTO areas(name, sort_order) VALUES(?, ?)', [areaName, idx]);
      const rows = Array.isArray(accounts.list[areaName]) ? accounts.list[areaName] : [];
      for (const acc of rows) {
        await dbRun(
          `INSERT INTO accounts(area_name, uname, upwd, name, token, time, sign)
           VALUES(?, ?, ?, ?, ?, ?, ?)`,
          [
            areaName,
            String(acc.uname || ''),
            String(acc.upwd || ''),
            String(acc.name || ''),
            String(acc.token || ''),
            String(acc.time || ''),
            String(acc.sign || '')
          ]
        );
      }
    }
    await dbRun('COMMIT');
  } catch (error) {
    await dbRun('ROLLBACK');
    throw error;
  }
}

async function migrateJsonToDbIfNeeded() {
  const result = await dbGet('SELECT COUNT(1) AS count FROM accounts');
  if (Number(result && result.count) > 0) return;
  if (!fs.existsSync(accountsFilePath)) return;
  const jsonPayload = readAccountsFromJsonFile();
  const normalizedPayload = normalizeAccountsPayload(jsonPayload);
  const validation = validateAccounts(normalizedPayload);
  if (!validation.ok) {
    console.warn(`[Accounts] skip JSON migration: ${validation.message}`);
    return;
  }
  await writeAccountsToDb(normalizedPayload);
  if (normalizedPayload.dropped.length > 0) {
    console.warn(`[Accounts] migration dropped invalid rows: ${normalizedPayload.dropped.length}`);
  }
  console.log(`[Accounts] migrated JSON data into SQLite: ${accountsDbPath}`);
}

// 供 account.html 读取/写入账号
app.get('/accounts', async (req, res) => {
  try {
    const payload = await readAccountsFromDb();
    res.json(payload);
  } catch (error) {
    console.error(`[Accounts] query failed: ${error.message}`);
    res.status(500).json({ ok: false, message: error.message });
  }
});

app.post('/accounts', async (req, res) => {
  const body = req.body || {};
  const accountsPayload = body.accounts ?? body;
  const normalizedPayload = normalizeAccountsPayload(accountsPayload);
  const validation = validateAccounts(normalizedPayload);
  if (!validation.ok) {
    res.status(400).json({ ok: false, message: validation.message });
    return;
  }
  try {
    await writeAccountsToDb(normalizedPayload);
    res.json({ ok: true });
  } catch (error) {
    console.error(`[Accounts] write failed: ${error.message}`);
    res.status(500).json({ ok: false, message: error.message });
  }
});

/** 下载当前账号 SQLite（换机备份用） */
app.get('/accounts/database/export', async (req, res) => {
  let reopened = false;
  async function reopenOnce() {
    if (reopened) return;
    reopened = true;
    try {
      await initDatabase();
    } catch (error) {
      console.error(`[Accounts] export 后重新打开数据库失败: ${error.message}`);
    }
  }
  try {
    await closeDatabase();
    if (!fs.existsSync(accountsDbPath)) {
      await initDatabase();
      await closeDatabase();
    }
    const stamp = new Date().toISOString().replace(/[:.]/g, '-').slice(0, 19);
    const filename = `dldl-accounts-${stamp}.sqlite`;
    res.setHeader('Content-Type', 'application/octet-stream');
    res.setHeader('Content-Disposition', `attachment; filename="${filename}"`);
    const stream = fs.createReadStream(accountsDbPath);
    stream.on('error', async (streamError) => {
      console.error(`[Accounts] export stream: ${streamError.message}`);
      await reopenOnce();
      if (!res.headersSent) {
        res.status(500).json({ ok: false, message: streamError.message });
      } else {
        res.destroy();
      }
    });
    res.on('finish', reopenOnce);
    res.on('close', reopenOnce);
    stream.pipe(res);
  } catch (error) {
    console.error(`[Accounts] export failed: ${error.message}`);
    await reopenOnce();
    if (!res.headersSent) {
      res.status(500).json({ ok: false, message: error.message });
    }
  }
});

/** 用上传的 .sqlite 整体替换当前账号库（换机恢复用） */
app.post(
  '/accounts/database/import',
  express.raw({ type: 'application/octet-stream', limit: IMPORT_DB_MAX_BYTES }),
  async (req, res) => {
    const body = req.body;
    if (!Buffer.isBuffer(body) || body.length < 100) {
      res.status(400).json({ ok: false, message: '请上传有效的 SQLite 文件（application/octet-stream）' });
      return;
    }
    if (!body.subarray(0, SQLITE_MAGIC.length).equals(SQLITE_MAGIC)) {
      res.status(400).json({ ok: false, message: '文件头不是 SQLite 数据库' });
      return;
    }
    const tempPath = path.join(userDataDir, `accounts.import.${Date.now()}.sqlite`);
    const backupPath = path.join(userDataDir, `accounts.before-import.${Date.now()}.sqlite`);
    let hadExisting = false;
    try {
      fs.mkdirSync(userDataDir, { recursive: true });
      fs.writeFileSync(tempPath, body);
      await validateImportedAccountsDbFile(tempPath);
      await closeDatabase();
      if (fs.existsSync(accountsDbPath)) {
        hadExisting = true;
        fs.copyFileSync(accountsDbPath, backupPath);
        fs.unlinkSync(accountsDbPath);
      }
      fs.renameSync(tempPath, accountsDbPath);
      await initDatabase();
      console.log(`[Accounts] 已从上传文件导入数据库: ${accountsDbPath}${hadExisting ? `（已备份旧库到 ${path.basename(backupPath)}）` : ''}`);
      res.json({ ok: true, backup: hadExisting ? path.basename(backupPath) : null });
    } catch (error) {
      console.error(`[Accounts] import failed: ${error.message}`);
      try {
        if (fs.existsSync(tempPath)) fs.unlinkSync(tempPath);
      } catch (_) {
        // ignore
      }
      try {
        if (hadExisting && fs.existsSync(backupPath) && !fs.existsSync(accountsDbPath)) {
          fs.copyFileSync(backupPath, accountsDbPath);
        }
      } catch (restoreError) {
        console.error(`[Accounts] import 回滚失败: ${restoreError.message}`);
      }
      try {
        await initDatabase();
      } catch (reopenError) {
        console.error(`[Accounts] import 失败后 initDatabase: ${reopenError.message}`);
      }
      res.status(400).json({ ok: false, message: error.message || String(error) });
    }
  }
);

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
    const responseText = await fetchText(url, true); // 使用当前代理
    res.setHeader('Content-Type', 'application/javascript; charset=utf-8');
    res.end(responseText);
  } catch (error) {
    console.error('[h5sdk/login] failed:', error.message);
    res.status(500).json({ state: 0, msg: error.message });
  }
});

// 魂兽森林自动化脚本
app.get('/forest-fight-auto.js', (req, res) => {
  const scriptPath = path.join(staticRoot, 'forest-fight-auto.js');
  if (fs.existsSync(scriptPath)) {
    res.setHeader('Content-Type', 'application/javascript; charset=utf-8');
    res.sendFile(scriptPath);
  } else {
    res.status(404).send('// 脚本不存在');
  }
});

// 静态文件服务（只读资源；数据库与 account.json 在用户目录 userDataDir）
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
            `<script>(function(){\n  window.__PC_QR_FIXED_TIME_MS=${fixedQrTimeMs};\n  try{var p=new URLSearchParams(location.search||\"\");window.__DLDL_PROXY_UNAME=p.get(\"uname\")||\"\";window.__DLDL_PROXY_UPWD=p.get(\"upwd\")||\"\";}catch(_){window.__DLDL_PROXY_UNAME=\"\";window.__DLDL_PROXY_UPWD=\"\";}\n\n  function injectRefreshButton(){\n    try{\n      if(document.getElementById(\"dldl-refresh-token-btn\")) return;\n      var btn=document.createElement(\"button\");\n      btn.id=\"dldl-refresh-token-btn\";\n      btn.type=\"button\";\n      btn.textContent=\"刷新 token\";\n      btn.style.cssText=\"position:fixed;right:16px;top:16px;z-index:99999;background:#2f81f7;color:#fff;border:none;border-radius:8px;padding:10px 14px;font-size:14px;cursor:pointer;box-shadow:0 8px 24px rgba(0,0,0,.25);\";\n      btn.addEventListener(\"click\", async function(){\n        try{\n          btn.disabled=true;\n          btn.textContent=\"刷新中...\";\n          var uname=window.__DLDL_PROXY_UNAME||\"\";\n          var upwd=window.__DLDL_PROXY_UPWD||\"\";\n          if(!uname||!upwd) throw new Error(\"缺少账号信息\");\n          var resp=await fetch(\"/accounts\", {cache:\"no-store\"});\n          var json=await resp.json();\n          var area=Array.isArray(json.area)?json.area:[];\n          var list=(json.list&&typeof json.list===\"object\")?json.list:{};\n          var found=false;\n          for(var i=0;i<area.length;i++){\n            var key=String(area[i]||\"\");\n            var arr=list[key];\n            if(!Array.isArray(arr)) continue;\n            for(var j=0;j<arr.length;j++){\n              var acc=arr[j];\n              if(String(acc&&acc.uname||\"\")===uname && String(acc&&acc.upwd||\"\")===upwd){\n                var time=String(Math.floor(Date.now()/1000));\n                var signParams={uname:uname,upwd:upwd,autoLogin:\"true\",pid:\"46\",gid:\"1003279\",sversion:\"undefined\",version:\"1.0.4\",time:time,dev:\"9c71cbfa62ecfd4f5a1125d9c6c51367\",os:\"iOS\",over:\"18.5\"};\n                var API_KEY=\"Jp*4Y8vQOYck2*&Z\";\n                var sorted=Object.keys(signParams).sort();\n                var str=\"\";\n                for(var s=0;s<sorted.length;s++) str+=sorted[s]+\"=\"+signParams[sorted[s]];\n                var sign=hex_md5(str+API_KEY);\n                var url=\"https://s-api.37.com.cn/h5sdk/login?\"+new URLSearchParams(Object.assign({}, signParams, {sign:sign})).toString();\n                var payload=await new Promise(function(resolve,reject){\n                  var cb=\"__dldl_refresh_\"+Date.now()+\"_\"+Math.random().toString(36).slice(2);\n                  var u=new URL(url);\n                  u.searchParams.set(\"callback\", cb);\n                  var script=document.createElement(\"script\");\n                  var timer=setTimeout(function(){cleanup();reject(new Error(\"jsonp timeout\"));},15000);\n                  function cleanup(){ if(script&&script.parentNode) script.parentNode.removeChild(script); try{delete window[cb];}catch(_){window[cb]=undefined;} clearTimeout(timer); }\n                  window[cb]=function(data){ cleanup(); resolve(data); };\n                  script.onerror=function(){ cleanup(); reject(new Error(\"jsonp network error\")); };\n                  script.src=u.toString(); document.head.appendChild(script);\n                });\n                if(!payload||payload.state!==1||!payload.data||!payload.data.token) throw new Error((payload&&payload.msg)||\"login api failed\");\n                acc.token=String(payload.data.token);\n                acc.time=String(payload.data.time||time);\n                acc.sign=String(payload.data.sign||\"\");\n                found=true;\n                break;\n              }\n            }\n            if(found) break;\n          }\n          if(!found) throw new Error(\"未找到对应账号\");\n          var save=await fetch(\"/accounts\", {method:\"POST\", headers:{\"Content-Type\":\"application/json\"}, body:JSON.stringify({accounts:{area:area,list:list}})});\n          var saveJson=await save.json();\n          if(!save.ok || !saveJson.ok) throw new Error((saveJson&&saveJson.message)||\"保存失败\");\n          btn.textContent=\"刷新成功\";\n          setTimeout(function(){ btn.disabled=false; btn.textContent=\"刷新 token\"; }, 1200);\n        }catch(err){\n          console.error(err);\n          alert(\"刷新 token 失败：\" + (err&&err.message ? err.message : err));\n          btn.disabled=false;\n          btn.textContent=\"刷新 token\";\n        }\n      });\n      document.body.appendChild(btn);\n    }catch(_){ }\n  }\n  if(document.readyState===\"loading\") document.addEventListener(\"DOMContentLoaded\", injectRefreshButton); else injectRefreshButton();\n\n  // 兜底：不依赖 enter.js 的字符串替换是否命中，运行时强制给扫码请求补上 uname/upwd。\n  try{\n    var _open=XMLHttpRequest.prototype.open;\n    XMLHttpRequest.prototype.open=function(method,url){\n      try{\n        var uStr=String(url||\"\");\n        if(uStr){\n          var a=window.__DLDL_PROXY_UNAME||\"\";\n          var b=window.__DLDL_PROXY_UPWD||\"\";\n          if((uStr.indexOf(\"/pc/getId\")==0||uStr.indexOf(\"/pc/getCodeInfo\")==0) && (a||b)){\n            var u=new URL(uStr, location.origin);\n            if(a && !u.searchParams.has(\"uname\")) u.searchParams.set(\"uname\", a);\n            if(b && !u.searchParams.has(\"upwd\")) u.searchParams.set(\"upwd\", b);\n            url=u.pathname + (u.search||\"\");\n            arguments[1]=url;\n          }\n        }\n      }catch(_){ }\n      return _open.apply(this, arguments);\n    };\n  }catch(_){ }\n})();</script>\n    $1`
          );
          
          // 注入魂兽森林自动化脚本（如果 URL 包含 dldl_forest=1）
          try {
            const urlParams = new URL(req.url, 'http://localhost').searchParams;
            if (urlParams.get('dldl_forest') === '1') {
              body = body.replace(
                '</head>',
                `<script src="/forest-fight-auto.js"></script></head>`
              );
              console.log('[ForestFight] 已注入自动化脚本');
            }
          } catch (_) {}

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
  try {
    await initDatabase();
    await migrateJsonToDbIfNeeded();
  } catch (error) {
    try {
      server.close();
    } catch (_) {
      // ignore
    }
    throw error;
  }
  proxyReady = true;
  console.log(`✅ Proxy running at http://localhost:${PORT}`);
  console.log(`✅ Mock interceptors active`);
  console.log(`✅ Account uname: ${accountConfig.uname}`);
  console.log(`✅ Fixed QR time(ms): ${fixedQrTimeMs}`);
  console.log(`✅ Multi-account mode via login.php?uname=xxx&upwd=xxx`);
  console.log(`✅ Accounts storage: SQLite (${accountsDbPath})`);
  console.log(`✅ Static root: ${staticRoot}`);
  console.log(`✅ User data dir: ${userDataDir}`);
  return server;
}

module.exports = { startProxyServer, PORT, accountsDbPath, userDataDir, staticRoot };

if (require.main === module) {
  startProxyServer().catch((error) => {
    console.error(`❌ Failed to start server: ${error.message}`);
    process.exit(1);
  });
}