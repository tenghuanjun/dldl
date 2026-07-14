/**
 * DLDL-Proxy Electron 主进程入口
 * - 启动本地 Express 代理服务
 * - 创建主窗口加载 account.html
 * - 处理快捷登录 IPC：打开 37 门户并注入 UINFO/HISTORY cookie
 * - 管理子窗口（扫码页），共享 session 以支持多窗口 cookie 隔离
 */
// Node.js 侧忽略 SSL 证书验证（必须在网络/HTTPS 模块加载前设置，否则直连 HTTPS 仍可能报握手错误）
process.env.NODE_TLS_REJECT_UNAUTHORIZED = '0';

const path = require('path');
const fs = require('fs');
const { app, BrowserWindow, Tray, Menu, nativeImage, ipcMain, session, dialog, screen } = require('electron');

// 构建产物优先：打包后存在 .min.js 则加载混淆版 preload，否则回退源码（开发态）
function pickPreload(name) {
  const min = path.join(__dirname, name.replace(/\.js$/, '.min.js'));
  return fs.existsSync(min) ? min : path.join(__dirname, name);
}

// 终端 UTF-8 编码（解决 Windows 下中文乱码）
if (process.platform === 'win32') {
  try {
    const { execSync } = require('child_process');
    execSync('chcp 65001', { stdio: 'ignore' });
  } catch (_) {}
}


// ========== APP 登录「独立进程」子窗口模式（对齐《海神服多开逻辑分析》的 CreateProcess 多开模型） ==========
// 每个 APP 登录窗口 = 一个独立 Electron 实例（独立渲染进程 + 独立 GPU 进程 + 独立 userData），
// 这是根治「多窗口共享 GPU 进程 → 卡顿」的唯一办法。
// 必须在 app.commandLine.appendSwitch / requestSingleInstanceLock 之前分流，否则子进程抢不到单实例锁会自杀。
const IS_APP_LOGIN_CHILD = process.argv.includes('--app-login-child');
if (IS_APP_LOGIN_CHILD) {
  runAppLoginChildMode();
  return; // CommonJS 顶层 return 合法，终止后续执行
}

// ========== 网络 & SSL ==========
// 忽略 Chromium 渲染进程的 SSL 证书错误（避免游戏服务器自签证书导致握手失败刷屏）
app.commandLine.appendSwitch('ignore-certificate-errors');
// 禁用本地 HTTP 缓存：避免修改 account.html 后需手动硬刷新才生效（本地管理面板无缓存需求）
app.commandLine.appendSwitch('disable-http-cache');
// 测试用：开启远程调试端口，供 Playwright/CDP 自动驱动桌面端（正式发布前请删除此行）
app.commandLine.appendSwitch('remote-debugging-port', '9222');
// Node.js 侧忽略 SSL 证书验证见文件顶部（需在网络模块加载前设置）

// ========== GPU 加速 & 渲染优化 ==========
// 禁用 GPU 沙箱（在某些 Windows 系统上可提升渲染性能）
app.commandLine.appendSwitch('disable-gpu-sandbox');
// 启用 2D Canvas 硬件加速
app.commandLine.appendSwitch('enable-accelerated-2d-canvas');
// 启用 GPU 光栅化（将页面内容交由 GPU 渲染）
app.commandLine.appendSwitch('enable-gpu-rasterization');
// 启用零拷贝（减少 CPU-GPU 之间的内存拷贝）
app.commandLine.appendSwitch('enable-zero-copy');
// 使用 ANGLE 的 D3D11 后端（Windows 下性能更好）
app.commandLine.appendSwitch('use-angle', 'd3d11');
// 注意：不要加 disable-frame-rate-limit。多开时若去掉 60fps 上限，聚焦窗口会无节制占满 GPU。
// 忽略 GPU 黑名单（强制启用硬件加速，即使驱动在 Chromium 黑名单中）
app.commandLine.appendSwitch('ignore-gpu-blacklist');
// 启用 QUIC 协议优化网络
app.commandLine.appendSwitch('enable-quic');
// 增加渲染进程的共享内存大小（默认 8MB → 128MB，减少内存映射失败）
app.commandLine.appendSwitch('js-flags', '--max-old-space-size=4096');

// ========== 单实例锁 ==========

const gotTheLock = app.requestSingleInstanceLock();
if (!gotTheLock) {
  app.quit();
} else {
  process.env.DLDL_USER_DATA = app.getPath('userData');
  process.env.DLDL_STATIC_ROOT = __dirname;

  // ========== 加载代理模块 ==========

  let startProxyServer;
  let PORT;
  try {
    ({ startProxyServer, PORT } = require('./proxy'));
    // 设置环境变量，供 quick-login-preload.js 在渲染进程中读取
    process.env.DLDL_PORT = String(PORT);
  } catch (error) {
    const message = error && error.message ? error.message : String(error);
    console.error('加载代理模块失败:', error);
    dialog.showErrorBox(
      'DLDL-Proxy',
      `无法加载本地服务。\n\n${message}`
    );
    app.quit();
    process.exit(1);
  }

  let mainWindow = null;
  let tray = null;
  app.isQuiting = false;
  let httpServer = null;
  let portalCookieReady = false;

  // ========== 快捷登录专用 session ==========
  // 每个快捷登录窗口使用独立的 partition，实现 cookie 隔离
  // 同时共享主 session 的磁盘缓存（persist 到同一 userData）

  /**
   * 确保 37.com.cn 的 cookie jar 已初始化
   * 在应用启动时预创建 session 以加速首次快捷登录
   */
  function ensurePortalSession() {
    if (portalCookieReady) return;
    // 预创建 default session 的 cookie 存储，确保 setCookie 可用
    const defaultSession = session.defaultSession;
    // 触发 cookie store 初始化
    defaultSession.cookies.get({ url: 'https://37.com.cn' }).catch(() => {});
    portalCookieReady = true;
  }

  /**
   * 为快捷登录创建独立窗口，注入 UINFO/HISTORY cookie
   * @param {string} url - 37 门户地址
   * @param {string} uinfo - UINFO cookie 值
   * @param {string} history - HISTORY cookie 值
   */
  async function openQuickLoginWindow(url, uinfo, history) {
    ensurePortalSession();

    const defaultSession = session.defaultSession;

    // 先设置 cookie（在创建窗口之前，确保页面加载时 cookie 已就绪）
    const cookieOpts = {
      url: 'https://37.com.cn',
      domain: '.37.com.cn',
      path: '/',
      secure: true,
      httpOnly: false,
      sameSite: 'lax',
      expirationDate: 99999999999
    };

    try {
      await defaultSession.cookies.set({
        ...cookieOpts,
        name: 'UINFO',
        value: uinfo
      });
      if (history && history.trim()) {
        await defaultSession.cookies.set({
          ...cookieOpts,
          name: 'HISTORY',
          value: history
        });
      }
    } catch (e) {
      console.error('设置 cookie 失败:', e);
    }

    // 创建新窗口，使用独立 partition 实现 cookie 隔离
    // 但 37.com.cn 的 cookie 通过 defaultSession 注入
    const partition = `quick_${Date.now()}`;
    const quickSession = session.fromPartition(partition, { cache: true });

    // 将 defaultSession 中已设置的 37.com.cn cookie 复制到新 session
    try {
      const cookies = await defaultSession.cookies.get({ url: 'https://37.com.cn' });
      for (const ck of cookies) {
        await quickSession.cookies.set({
          url: 'https://37.com.cn',
          name: ck.name,
          value: ck.value,
          domain: ck.domain,
          path: ck.path,
          secure: ck.secure,
          httpOnly: ck.httpOnly,
          sameSite: ck.sameSite,
          expirationDate: ck.expirationDate
        });
      }
    } catch (e) {
      console.error('复制 cookie 到子 session 失败:', e);
    }

    const childWindow = new BrowserWindow({
      width: 420,
      height: 720,
      minWidth: 100,
      minHeight: 100,
      show: false,
      autoHideMenuBar: true,
      backgroundColor: '#1a1a2e',
      title: '快捷登录',
      // GPU/渲染优化
      backgroundThrottling: false,
      webPreferences: {
        nodeIntegration: false,
        contextIsolation: true,
        sandbox: false,
        preload: pickPreload('quick-login-preload.js'),
        session: quickSession,
        // 渲染加速
        backgroundThrottling: false,
        enablePreferredSizeMode: true
      }
    });

    // 快捷登录窗口不需要高帧率，限制 30fps 节省资源
    childWindow.webContents.setFrameRate(30);

    // 拦截子窗口中所有发往 s-api.37.com.cn 的 JSONP 请求，
    // redirect 到本地 Express 代理。Express 端点 /api/h5sdk-proxy/:action 会：
    // - 对 login：用代理池 IP 真实调用 s-api.37.com.cn，拿到数据后返回
    // - 对 query_login：返回伪造的已登录状态（logined: true）
    const localProxyBase = `http://127.0.0.1:${PORT}`;

    quickSession.webRequest.onBeforeRequest({ urls: ['*://s-api.37.com.cn/h5sdk/login*'] }, (details, callback) => {
      console.log('[quick-login] 拦截 h5sdk/login，redirect 到本地代理');
      const urlObj = new URL(details.url);
      const redirectUrl = localProxyBase + '/api/h5sdk-proxy/login' + urlObj.search;
      console.log('[quick-login] redirect to:', redirectUrl.substring(0, 120) + '...');
      callback({ redirectURL: redirectUrl });
    });

    quickSession.webRequest.onBeforeRequest({ urls: ['*://s-api.37.com.cn/h5sdk/query_login*'] }, (details, callback) => {
      console.log('[quick-login] 拦截 query_login，redirect 到本地代理');
      const urlObj = new URL(details.url);
      const redirectUrl = localProxyBase + '/api/h5sdk-proxy/query_login' + urlObj.search;
      callback({ redirectURL: redirectUrl });
    });

    // 页面加载完成后再显示，避免白屏
    childWindow.once('ready-to-show', () => {
      if (!childWindow.isDestroyed()) {
        childWindow.show();
        childWindow.focus();
      }
    });

    childWindow.on('closed', () => {
      // 清理 session
      try {
        quickSession.cookies.flushStore();
      } catch (_) {}
    });

    try {
      await childWindow.loadURL(url);
      // 兜底：如果 ready-to-show 没触发，手动 show
      if (!childWindow.isDestroyed() && !childWindow.isVisible()) {
        childWindow.show();
        childWindow.focus();
      }
    } catch (err) {
      console.error('快捷登录窗口加载失败:', err);
      if (!childWindow.isDestroyed()) {
        childWindow.close();
      }
      throw err;
    }

    return childWindow;
  }

  // ========== IPC 处理 ==========

  /**
   * handle 模式：前端 invoke 调用，返回结果
   */
  ipcMain.handle('open-quick-login', async (_event, payload) => {
    const { url, uinfo, history } = payload || {};
    if (!url || !uinfo) {
      return { ok: false, message: '缺少 url 或 uinfo' };
    }
    if (!/^https?:\/\//i.test(url)) {
      return { ok: false, message: 'URL 必须以 http 或 https 开头' };
    }
    try {
      await openQuickLoginWindow(url, uinfo, history || '');
      return { ok: true };
    } catch (e) {
      return { ok: false, message: e?.message || String(e) };
    }
  });

  /**
   * on 模式：前端 event.emit 风格调用，无需等待返回
   */
  ipcMain.on('dldl-open-quick-login', (_event, payload) => {
    const { url, uinfo, history } = payload || {};
    if (!url || !uinfo) return;
    if (!/^https?:\/\//i.test(url)) return;
    openQuickLoginWindow(url, uinfo, history || '').catch(err => {
      console.error('快捷登录窗口打开失败:', err);
    });
  });


  // ========== APP 登录多开管理（参考《海神服多开逻辑分析》的 CreateProcess 多开模型） ==========
  // 核心思想：每个 APP 登录窗口 = 一个独立的 Electron 进程（独立渲染进程 + 独立 GPU 进程 + 独立 userData），
  // 由主进程（主控）统一 spawn 拉起、平铺排版、登记、关闭回收。对应海神服「主控用 CreateProcess 拉起 N 个独立浏览器 exe」，
  // 故障域 + 资源域双重隔离：单个登录窗口崩溃/卡满 GPU 不影响主窗口与其他窗口。
  const { spawn } = require('child_process');
  const appLoginWindows = new Map(); // id -> ChildProcess（独立 Electron 子进程）

  const APP_WIN_W = 360;
  const APP_WIN_H = 600;
  const APP_WIN_GAP = 3;

  /**
   * 打开一个独立的 APP 登录窗口（独立 Electron 进程 = 独立 GPU 进程）
   * @param {string} url - 已构建好的登录地址（含 token/sign）
   * @param {string} title - 窗口标题（一般传账号名）
   * @returns {{ok: boolean, id?: string, message?: string}}
   */
  function openAppLoginWindow(url, title) {
    if (!url || !/^https?:\/\//i.test(url)) {
      return { ok: false, message: 'URL 必须以 http 或 https 开头' };
    }
    const id = `app_${Date.now()}_${Math.random().toString(36).slice(2)}`;

    // 平铺排版：按已开窗口数量计算网格坐标（对应文档 MoveWindow/SetWindowPos 平铺，避免全部叠在屏幕中央）
    let x = 0, y = 0;
    try {
      const { width: sw, height: sh } = screen.getPrimaryDisplay().workAreaSize;
      const cols = Math.max(1, Math.floor((sw + APP_WIN_GAP) / (APP_WIN_W + APP_WIN_GAP)));
      const idx = appLoginWindows.size;
      const col = idx % cols;
      const row = Math.floor(idx / cols);
      x = col * (APP_WIN_W + APP_WIN_GAP);
      y = Math.min(row * (APP_WIN_H + APP_WIN_GAP), Math.max(0, sh - APP_WIN_H));
    } catch (_) {
      // 取不到屏幕信息则放在默认位置
    }

    // 关键：每个 APP 登录窗口 = 一个独立的 Electron 子进程（独立 GPU 进程），
    // 对应海神服 CreateProcess 拉起独立浏览器 exe。子进程复用主入口文件，靠 --app-login-child 分流；
    // 其余参数走环境变量（避免命令行转义/特殊字符问题）。
    const childEntry = app.getAppPath(); // 开发=项目目录，打包=app.asar；Electron 壳据此加载正确入口
    const env = {
      ...process.env,
      APP_LOGIN_ID: id,
      APP_LOGIN_URL: url,
      APP_LOGIN_TITLE: title || 'APP 登录',
      APP_LOGIN_X: String(x),
      APP_LOGIN_Y: String(y),
      APP_LOGIN_W: String(APP_WIN_W),
      APP_LOGIN_H: String(APP_WIN_H),
    };
    let child;
    try {
      child = spawn(process.execPath, [childEntry, '--app-login-child'], {
        env,
        stdio: ['ignore', 'inherit', 'inherit'],
      });
    } catch (e) {
      return { ok: false, message: `启动独立进程失败：${e?.message || e}` };
    }
    child.on('error', (e) => { console.error('[app-login] 子进程错误:', e); appLoginWindows.delete(id); });
    child.on('exit', () => { appLoginWindows.delete(id); });

    // 登记到注册表（对应文档 FindWindowEx 持有 HWND）
    appLoginWindows.set(id, child);

    return { ok: true, id };
  }

  /** 关闭所有 APP 登录窗口（对应文档批量 TerminateProcess 回收） */
  function closeAllAppLoginWindows() {
    for (const child of appLoginWindows.values()) {
      try { child.kill('SIGTERM'); } catch (_) {}
    }
    appLoginWindows.clear();
  }

  ipcMain.handle('open-app-login', (_event, payload) => {
    const { url, title } = payload || {};
    return openAppLoginWindow(url, title);
  });

  ipcMain.on('dldl-open-app-login', (_event, payload) => {
    const { url, title } = payload || {};
    openAppLoginWindow(url, title);
  });

  ipcMain.handle('close-all-app-logins', () => {
    closeAllAppLoginWindows();
    return { ok: true };
  });

  // ========== 批量启动任意 exe（桌面端：浏览选程序 + 启动数量） ==========
  // 用户只想要：浏览选一个 exe → 填数量 N → 点「批量启动」→ 拉起 N 个独立进程。
  // 直接 spawn 外部 exe（detached + unref），不纳入 dldl 自身多开进程管理。

  /** 系统文件选择框，只允许选 .exe，返回绝对路径 */
  ipcMain.handle('pick-exe', async () => {
    try {
      const result = await dialog.showOpenDialog({
        title: '选择要启动的程序',
        properties: ['openFile'],
        filters: [{ name: '可执行程序', extensions: ['exe'] }]
      });
      if (result.canceled || !result.filePaths || !result.filePaths.length) {
        return { ok: false, canceled: true };
      }
      return { ok: true, path: result.filePaths[0] };
    } catch (e) {
      return { ok: false, message: e && e.message ? e.message : String(e) };
    }
  });

  // 批量启动的原生客户端进程 PID 集合（供窗口同步枚举时识别为可同步目标）
  const nativeClientPids = new Set();

  /** 按数量批量启动指定 exe */
  ipcMain.handle('batch-launch-exe', async (_e, payload) => {
    const { exePath, count } = payload || {};
    if (!exePath || typeof exePath !== 'string') {
      return { ok: false, message: '请先选择要启动的程序' };
    }
    if (!fs.existsSync(exePath)) {
      return { ok: false, message: '程序路径不存在：' + exePath };
    }
    const n = Math.max(1, Math.min(200, parseInt(count, 10) || 1));
    let launched = 0;
    const errors = [];
    for (let i = 0; i < n; i++) {
      try {
        // detached + unref：启动的进程独立存活，不受 dldl 主进程退出影响
        const child = spawn(exePath, [], { detached: true, stdio: 'ignore' });
        if (child.pid) {
          const pid = child.pid;
          nativeClientPids.add(pid);
          child.on('exit', () => nativeClientPids.delete(pid));
        }
        child.unref();
        launched++;
      } catch (e) {
        errors.push(e && e.message ? e.message : String(e));
        if (errors.length >= 5) break;
      }
    }
    return { ok: true, launched, total: n, errors };
  });

  // ========== 批量启动程序 + 同步方式 本地持久化 ==========
  // 用户每次重启都要重新选 exe / 填数量 / 选同步方式，体验差；
  // 把配置落盘到 userData 下的 JSON，打开同步弹窗时自动回填。
  const exeLaunchConfigPath = path.join(app.getPath('userData'), 'exe-launch-config.json');
  ipcMain.handle('get-exe-launch-config', () => {
    try {
      if (fs.existsSync(exeLaunchConfigPath)) {
        return { ok: true, config: JSON.parse(fs.readFileSync(exeLaunchConfigPath, 'utf8')) };
      }
    } catch (_) { /* 损坏则忽略，回退默认 */ }
    return { ok: true, config: {} };
  });
  ipcMain.handle('save-exe-launch-config', (_e, cfg) => {
    try {
      fs.writeFileSync(exeLaunchConfigPath, JSON.stringify(cfg || {}, null, 2));
      return { ok: true };
    } catch (e) {
      return { ok: false, message: e && e.message ? e.message : String(e) };
    }
  });


  // ========== 窗口同步引擎（整合自 tongbuqi/window_sync.py，Python headless 引擎） ==========
  // 每个游戏窗口是独立 Electron 子进程（Chrome_WidgetWin_1 / CEF），主进程已持有其 PID，
  // 故把 PID 列表传给 Python 引擎即可精准识别「多开的斗罗窗口」。
  // 引擎通过 stdin/stdout 的行分隔 JSON 与主进程通信，同步核心逻辑（低级鼠标钩子捕获主控 →
  // PostMessage 投递到各窗口渲染窗口）与原同步器完全一致。
  const PY_CANDIDATES = ['python', 'py', 'python3'];
  let syncProc = null;
  let syncStdoutBuf = '';
  const syncQueue = []; // {expect, resolve, reject, timer}

  function onSyncLine(obj) {
    if (!obj || typeof obj !== 'object') return;
    if (obj.type === 'ready') return;
    const head = syncQueue[0];
    if (head && (obj.type === head.expect || obj.type === 'error')) {
      syncQueue.shift();
      clearTimeout(head.timer);
      if (obj.type === 'error') head.reject(new Error(obj.msg || '同步引擎错误'));
      else head.resolve(obj);
      return;
    }
    // 无人等待的消息（如 F10 强制停止的 status）→ 转发给渲染进程
    if (obj.type === 'status' && mainWindow && !mainWindow.isDestroyed()) {
      mainWindow.webContents.send('sync-event', obj);
    }
  }

  /** 计算自包含 exe 路径 (PyInstaller 打包产物, 免装 Python)；不存在返回 null */
  function syncExePath() {
    const exe = app.isPackaged
      ? path.join(process.resourcesPath, 'sync_engine_dist', 'sync_engine.exe')
      : path.join(__dirname, 'sync_engine_dist', 'sync_engine.exe');
    return fs.existsSync(exe) ? exe : null;
  }

  /** 计算 .py 脚本路径 (开发态/未打包时回退)；不存在返回 null */
  function syncPyPath() {
    const script = app.isPackaged
      ? path.join(process.resourcesPath, 'sync_engine.py')
      : path.join(__dirname, 'sync_engine.py');
    return fs.existsSync(script) ? script : null;
  }

  function spawnSyncEngine() {
    const exe = syncExePath();
    if (exe) {
      // 优先用自包含 exe（打包目标机免装 Python）
      try {
        const p = spawn(exe, [], {
          cwd: path.dirname(exe),
          env: { ...process.env, PYTHONIOENCODING: 'utf-8' },
          stdio: ['pipe', 'pipe', 'pipe'],
        });
        attachSyncProc(p, exe);
        return p;
      } catch (e) {
        console.error('[sync] 启动 exe 失败, 回退 python:', e);
      }
    }
    // 回退: 用 python 直接跑 .py（需本机装 Python + pywin32）
    const script = syncPyPath();
    if (!script) {
      console.error('[sync] 既找不到引擎 exe 也找不到 sync_engine.py');
      syncProc = null;
      return null;
    }
    const tryIdx = (idx) => {
      if (idx >= PY_CANDIDATES.length) {
        console.error('[sync] 未找到可用的 Python 运行时');
        syncProc = null;
        return;
      }
      const bin = PY_CANDIDATES[idx];
      const args = bin === 'py' ? ['-3', script] : [script];
      let p;
      try {
        p = spawn(bin, args, {
          cwd: path.dirname(script),
          env: { ...process.env, PYTHONIOENCODING: 'utf-8' },
          stdio: ['pipe', 'pipe', 'pipe'],
        });
      } catch (e) {
        return tryIdx(idx + 1);
      }
      attachSyncProc(p, `${bin} ${script}`);
    };
    tryIdx(0);
    return syncProc;
  }

  function attachSyncProc(p, label) {
    syncProc = p;
    let started = false;
    p.on('error', (e) => {
      if (!started) {
        console.error('[sync] 启动失败:', label, e);
        if (p === syncProc) syncProc = null;
      } else {
        console.error('[sync] 引擎错误:', e);
        if (p === syncProc) syncProc = null;
      }
    });
    p.stdout.on('data', (d) => {
      started = true;
      syncStdoutBuf += d.toString('utf8');
      let nl;
      while ((nl = syncStdoutBuf.indexOf('\n')) >= 0) {
        const line = syncStdoutBuf.slice(0, nl).trim();
        syncStdoutBuf = syncStdoutBuf.slice(nl + 1);
        if (line) { try { onSyncLine(JSON.parse(line)); } catch (_) {} }
      }
    });
    p.stderr.on('data', (d) => { console.error('[sync-py]', d.toString('utf8').trim()); });
    p.on('exit', (code) => { console.log('[sync] 引擎退出:', code); if (p === syncProc) syncProc = null; });
  }

  function ensureSyncProc() {
    if (syncProc && !syncProc.killed) return syncProc;
    return spawnSyncEngine();
  }

  /**
   * 向 Python 引擎发送一条指令。
   * @param {Object} cmd - 指令对象
   * @param {string} [expect] - 期望的回复 type；省略则 fire-and-forget
   * @param {number} [timeoutMs]
   */
  function sendSync(cmd, expect, timeoutMs = 6000) {
    return new Promise((resolve, reject) => {
      const proc = ensureSyncProc();
      if (!proc) return reject(new Error('无法启动同步引擎（需要本机安装 Python 及 pywin32）'));
      const write = () => {
        try { proc.stdin.write(JSON.stringify(cmd) + '\n'); }
        catch (e) { reject(new Error('写入同步引擎失败：' + (e && e.message || e))); return false; }
        return true;
      };
      if (!expect) { if (write()) resolve({ ok: true }); return; }
      const entry = { expect, resolve, reject };
      entry.timer = setTimeout(() => {
        const i = syncQueue.indexOf(entry);
        if (i >= 0) syncQueue.splice(i, 1);
        reject(new Error('同步引擎响应超时'));
      }, timeoutMs);
      syncQueue.push(entry);
      if (!write()) { clearTimeout(entry.timer); const i = syncQueue.indexOf(entry); if (i >= 0) syncQueue.splice(i, 1); }
    });
  }

  function stopSyncEngine() {
    if (!syncProc) return;
    try { syncProc.stdin.write(JSON.stringify({ cmd: 'quit' }) + '\n'); } catch (_) {}
    try { syncProc.kill(); } catch (_) {}
    syncProc = null;
  }

  // 枚举多开的斗罗窗口（按 appLoginWindows 里的子进程 PID 精准识别）
  ipcMain.handle('sync-enumerate', async () => {
    const appPids = [...appLoginWindows.values()].map((c) => c && c.pid).filter(Boolean);
    const nativePids = [...nativeClientPids];
    // pids = APP 登录多开窗口 + 批量启动的原生客户端，均视为可同步目标
    const pids = [...appPids, ...nativePids];
    try {
      const obj = await sendSync({ cmd: 'enumerate', pids, native_pids: nativePids }, 'windows');
      return { ok: true, windows: obj.list || [], pids };
    } catch (e) {
      return { ok: false, message: e && e.message || String(e) };
    }
  });

  // 主控窗口红框高亮
  ipcMain.handle('sync-highlight', async (_e, payload) => {
    const { hwnd, times } = payload || {};
    try { sendSync({ cmd: 'highlight', hwnd, times: times || 3 }); return { ok: true }; }
    catch (e) { return { ok: false, message: e && e.message || String(e) }; }
  });

  // 开始同步：master=主控 hwnd，targets=同步窗口 hwnd 数组
  ipcMain.handle('sync-start', async (_e, payload) => {
    const { master, targets, mode } = payload || {};
    try {
      const s = await sendSync({ cmd: 'start', master, targets, mode: mode || 'web' }, 'status');
      return { ok: true, status: s };
    } catch (e) {
      return { ok: false, message: e && e.message || String(e) };
    }
  });

  ipcMain.handle('sync-stop', async () => {
    try { const s = await sendSync({ cmd: 'stop' }, 'status'); return { ok: true, status: s }; }
    catch (e) { return { ok: false, message: e && e.message || String(e) }; }
  });

  ipcMain.handle('sync-status', async () => {
    try { const s = await sendSync({ cmd: 'status' }, 'status'); return { ok: true, status: s }; }
    catch (e) { return { ok: false, message: e && e.message || String(e) }; }
  });

  // 窗口管理：关闭 / 宽高 / 自动排列 / 隐藏 / 显示（仅作用于多开游戏窗口，不碰账号列表）
  // hwnds 由渲染进程从已枚举的 syncWindows 传入（均来自 appLoginWindows 子进程 PID），
  // 账号列表主窗口不在其中，故不会误关。
  ipcMain.handle('sync-window-op', async (_e, payload) => {
    const { action, hwnds } = payload || {};
    // Python 引擎只认一个 cmd: 'window-op'，内部再按 action 字段分发
    if (!['close', 'set_size', 'arrange', 'hide', 'show'].includes(action)) {
      return { ok: false, message: '未知窗口操作: ' + action };
    }
    // 透传额外参数（w/h/cols/gap/winW/winH/startX/startY）
    const extra = {};
    for (const k of ['w', 'h', 'cols', 'gap', 'winW', 'winH', 'startX', 'startY']) {
      if (payload[k] !== undefined) extra[k] = payload[k];
    }
    // 关闭原生客户端需 TerminateProcess 而非 WM_CLOSE，告知引擎哪些 PID 是批量启动的原生进程
    if (action === 'close') {
      extra.native_pids = [...nativeClientPids];
    }
    try {
      const obj = await sendSync({ cmd: 'window-op', action, hwnds: hwnds || [], ...extra }, 'window-op');
      return { ok: true, count: obj.count || 0, failed: obj.failed || [], action };
    } catch (e) {
      return { ok: false, message: e && e.message || String(e) };
    }
  });

  // 批量复制通行证码：按百分比坐标逐个激活原生客户端窗口 → 双击 → Ctrl+C → 读剪贴板
  ipcMain.handle('batch-copy-passcodes', async (_e, payload) => {
    const { hwnds, px, py } = payload || {};
    if (!hwnds || !hwnds.length) {
      return { ok: false, message: '没有可操作的窗口，请先批量启动并刷新窗口' };
    }
    try {
      // 逐窗模式每次只传 1 个 hwnd，每窗留 8 秒，最低 15 秒
      const timeout = Math.max(15000, (hwnds.length || 1) * 8000);
      const obj = await sendSync({
        cmd: 'batch-copy',
        hwnds,
        px: px || 0,
        py: py || 0,
      }, 'batch-copy-result', timeout);
      return { ok: true, codes: obj.codes || [] };
    } catch (e) {
      return { ok: false, message: e && e.message || String(e) };
    }
  });


  // ========== 主窗口创建 ==========

  async function createMainWindow() {
    mainWindow = new BrowserWindow({
      width: 1280,
      height: 800,
      minWidth: 800,
      minHeight: 600,
      show: false,
      autoHideMenuBar: true,
      backgroundColor: '#1a1a2e',
      title: 'DLDL-Proxy',
      // GPU/渲染优化
      backgroundThrottling: false,
      webPreferences: {
        nodeIntegration: false,
        contextIsolation: true,
        preload: pickPreload('preload.js'),
        // 渲染加速
        backgroundThrottling: false,
        enablePreferredSizeMode: true
      }
    });

    // 主窗口管理界面，60fps 保证流畅
    mainWindow.webContents.setFrameRate(60);

    // 扫码页等通过 window.open 打开的窗口：统一选项，共享 session
    mainWindow.webContents.setWindowOpenHandler((details) => {
      const raw = details.url || '';
      if (!/^(https?:|about:)/i.test(raw)) {
        return { action: 'deny' };
      }
      return {
        action: 'allow',
        overrideBrowserWindowOptions: {
          show: false,
          autoHideMenuBar: true,
          backgroundColor: '#000000',
          // GPU/渲染优化：子窗口（扫码页）也不降频
          backgroundThrottling: false,
          webPreferences: {
            nodeIntegration: false,
            contextIsolation: true,
            sandbox: true,
            spellcheck: false,
            session: mainWindow.webContents.session,
            // 渲染加速
            backgroundThrottling: false,
            enablePreferredSizeMode: true
          }
        }
      };
    });

    // 子窗口（扫码页）延迟显示，避免白屏
    mainWindow.webContents.on('did-create-window', (childWindow) => {
      if (!childWindow || childWindow.isDestroyed()) return;
      // 游戏扫码页需要流畅帧率，60fps
      childWindow.webContents.setFrameRate(60);
      let shown = false;
      const showOnce = () => {
        if (shown || childWindow.isDestroyed()) return;
        shown = true;
        childWindow.show();
      };
      childWindow.once('ready-to-show', showOnce);
      // 兜底：如果 3 秒内没 ready-to-show，也显示
      setTimeout(showOnce, 3000);
    });

    // 主窗口显示时机
    mainWindow.once('ready-to-show', () => {
      if (mainWindow && !mainWindow.isDestroyed()) {
        mainWindow.show();
        mainWindow.focus();
      }
    });

    // 关闭按钮 → 最小化到托盘（不真正退出，保留多开窗口）
    mainWindow.on('close', (e) => {
      if (!app.isQuiting) {
        e.preventDefault();
        mainWindow.hide();
        return;
      }
      mainWindow = null;
    });

    // ===== 系统托盘：最小化到托盘后双击/右键可恢复；点「退出」才真退出并回收多开 =====
    if (!tray) {
      try {
        const iconFile = path.join(__dirname, 'logo.png');
        const img = fs.existsSync(iconFile)
          ? nativeImage.createFromPath(iconFile)
          : nativeImage.createFromDataURL('iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==');
        tray = new Tray(img);
        tray.setToolTip('DLDL-Proxy');
        const trayMenu = Menu.buildFromTemplate([
          { label: '显示主窗口', click: () => { if (mainWindow) { mainWindow.show(); mainWindow.focus(); } } },
          { type: 'separator' },
          { label: '退出（关闭所有多开窗口）', click: () => { app.isQuiting = true; app.quit(); } }
        ]);
        tray.setContextMenu(trayMenu);
        tray.on('click', () => { if (mainWindow) { mainWindow.show(); mainWindow.focus(); } });
      } catch (e) {
        console.error('[tray] 创建托盘失败:', e);
      }
    }

    const url = `http://127.0.0.1:${PORT}/account.html`;
    try {
      await mainWindow.loadURL(url);
      // 兜底显示
      if (mainWindow && !mainWindow.isDestroyed() && !mainWindow.isVisible()) {
        mainWindow.show();
        mainWindow.focus();
      }
    } catch (err) {
      console.error('加载页面失败:', err);
      dialog.showErrorBox(
        'DLDL-Proxy',
        `无法加载页面：${err && err.message ? err.message : String(err)}`
      );
    }
  }

  // ========== HTTP Server 管理 ==========

  function closeHttpServer() {
    if (!httpServer) return Promise.resolve();
    const server = httpServer;
    httpServer = null;
    return new Promise((resolve) => {
      server.close(() => resolve());
    });
  }

  // ========== 应用生命周期 ==========

  app.on('second-instance', () => {
    if (mainWindow) {
      if (mainWindow.isMinimized()) mainWindow.restore();
      mainWindow.show();
      mainWindow.focus();
    }
  });

  app.whenReady().then(async () => {
    try {
      httpServer = await startProxyServer();
    } catch (error) {
      const message = error && error.message ? error.message : String(error);
      console.error('代理服务启动失败:', error);
      dialog.showErrorBox(
        'DLDL-Proxy',
        `本地代理未启动（常见原因：端口 ${PORT} 已被占用）。\n\n${message}`
      );
      app.quit();
      return;
    }
    ensurePortalSession();
    await createMainWindow();

    app.on('activate', () => {
      if (BrowserWindow.getAllWindows().length === 0) {
        createMainWindow();
      }
    });
  });

  app.on('before-quit', (event) => {
    // 退出前回收所有独立的 APP 登录子进程，避免遗留僵尸进程占满 GPU/内存
    closeAllAppLoginWindows();
    // 回收 Python 同步引擎
    stopSyncEngine();
    if (!httpServer) return;
    event.preventDefault();
    closeHttpServer().then(() => {
      // 刷新所有 session 的 cookie 存储
      session.defaultSession.cookies.flushStore().catch(() => {});
      app.exit(0);
    });
  });

  app.on('window-all-closed', () => {
    if (!app.isQuiting) app.quit();
  });
}

// ========== APP 登录子进程模式（独立 Electron 实例，每个窗口一个） ==========
// 由主进程 spawn 独立 Electron 实例并以 --app-login-child 启动。本函数在该实例中执行，
// 只负责加载一个游戏窗口，自带独立 GPU 进程 / 渲染进程 / userData / session，
// 对应海神服「每窗口一个独立浏览器 exe」。窗口关闭即退出本实例（等价 TerminateProcess 回收自身）。
function runAppLoginChildMode() {
  const os = require('os');
  const id = process.env.APP_LOGIN_ID || `child_${Date.now()}`;
  // 每个子进程使用独立的 userData 目录，避免多个 Chromium 实例占用同一 userData 的单例锁冲突
  try {
    app.setPath('userData', path.join(os.tmpdir(), 'dldl_app_login', id));
  } catch (_) {}

  const url = process.env.APP_LOGIN_URL;
  const title = process.env.APP_LOGIN_TITLE || 'APP 登录';
  const w = Number(process.env.APP_LOGIN_W) || 360;
  const h = Number(process.env.APP_LOGIN_H) || 660;
  const x = process.env.APP_LOGIN_X !== undefined ? Number(process.env.APP_LOGIN_X) : undefined;
  const y = process.env.APP_LOGIN_Y !== undefined ? Number(process.env.APP_LOGIN_Y) : undefined;

  app.whenReady().then(() => {
    const win = new BrowserWindow({
      width: w,
      height: h,
      x, y,
      minWidth: 320,
      minHeight: 480,
      show: false,
      autoHideMenuBar: true,
      backgroundColor: '#000000',
      title,
      backgroundThrottling: true,
      webPreferences: {
        nodeIntegration: false,
        contextIsolation: true,
        sandbox: true,
        spellcheck: false,
        backgroundThrottling: true
      }
    });

    // 焦点感知帧率：聚焦窗口满帧，失焦降到 5fps（独立 GPU 后仍是进一步省资源）
    try { win.webContents.setFrameRate(60); } catch (_) {}
    win.on('focus', () => { try { win.webContents.setFrameRate(60); } catch (_) {} });
    win.on('blur', () => { try { win.webContents.setFrameRate(5); } catch (_) {} });

    win.once('ready-to-show', () => { if (!win.isDestroyed()) win.show(); });
    if (url) {
      win.loadURL(url).catch(err => {
        console.error('[app-login-child] 加载失败:', err);
        if (!win.isDestroyed()) win.close();
      });
    }
    // 子窗口关闭 → 退出本子进程（对应海神服 TerminateProcess 回收自身）
    win.on('closed', () => { app.quit(); });
  });

  app.on('window-all-closed', () => { app.quit(); });
}
