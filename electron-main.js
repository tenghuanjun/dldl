/**
 * DLDL-Proxy Electron 主进程入口
 * - 启动本地 Express 代理服务
 * - 创建主窗口加载 account.html
 * - 处理快捷登录 IPC：打开 37 门户并注入 UINFO/HISTORY cookie
 * - 管理子窗口（扫码页），共享 session 以支持多窗口 cookie 隔离
 */
const path = require('path');
const { app, BrowserWindow, ipcMain, session, dialog } = require('electron');

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
      minWidth: 360,
      minHeight: 600,
      show: false,
      autoHideMenuBar: true,
      backgroundColor: '#1a1a2e',
      title: '快捷登录',
      webPreferences: {
        nodeIntegration: false,
        contextIsolation: true,
        sandbox: true,
        session: quickSession
      }
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
      webPreferences: {
        nodeIntegration: false,
        contextIsolation: true,
        preload: path.join(__dirname, 'preload.js')
      }
    });

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
          webPreferences: {
            nodeIntegration: false,
            contextIsolation: true,
            sandbox: true,
            spellcheck: false,
            session: mainWindow.webContents.session
          }
        }
      };
    });

    // 子窗口（扫码页）延迟显示，避免白屏
    mainWindow.webContents.on('did-create-window', (childWindow) => {
      if (!childWindow || childWindow.isDestroyed()) return;
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

    mainWindow.on('closed', () => {
      mainWindow = null;
    });

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
    if (!httpServer) return;
    event.preventDefault();
    closeHttpServer().then(() => {
      // 刷新所有 session 的 cookie 存储
      session.defaultSession.cookies.flushStore().catch(() => {});
      app.exit(0);
    });
  });

  app.on('window-all-closed', () => {
    app.quit();
  });
}
