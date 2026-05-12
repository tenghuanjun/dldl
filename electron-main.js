const path = require('path');
const fs = require('fs');
const Module = require('module');
const { app, BrowserWindow, dialog } = require('electron');

/**
 * sqlite3 等原生 .node 会放在 app.asar.unpacked 下，但 asar 里仍可能保留一份副本；
 * Node 若先解析到 asar 内的 sqlite3，加载 native 会失败（应用双击无反应或秒退）。
 * 将 unpacked 的 node_modules 插到搜索路径最前，确保优先加载磁盘上的原生模块。
 */
function preferUnpackedNodeModules() {
  if (!app.isPackaged) return;
  const unpackedRoot = path.join(process.resourcesPath, 'app.asar.unpacked', 'node_modules');
  try {
    if (fs.existsSync(unpackedRoot)) {
      Module.globalPaths.unshift(unpackedRoot);
    }
  } catch (_) {
    // ignore
  }
}

const gotTheLock = app.requestSingleInstanceLock();

if (!gotTheLock) {
  app.quit();
} else {
  process.env.DLDL_USER_DATA = app.getPath('userData');
  process.env.DLDL_STATIC_ROOT = __dirname;

  preferUnpackedNodeModules();

  let startProxyServer;
  let PORT;
  try {
    ({ startProxyServer, PORT } = require('./proxy'));
  } catch (error) {
    const message = error && error.message ? error.message : String(error);
    console.error('加载代理模块失败:', error);
    dialog.showErrorBox(
      'DLDL-Proxy',
      `无法加载本地服务（常见于原生模块 sqlite3 未从 asar.unpacked 加载，或安装包 CPU 架构与电脑不一致）。\n\n${message}`
    );
    app.quit();
    process.exit(1);
  }

  let mainWindow = null;
  let httpServer = null;

  app.on('second-instance', () => {
    if (mainWindow) {
      if (mainWindow.isMinimized()) mainWindow.restore();
      mainWindow.focus();
    }
  });

  async function createWindow() {
    mainWindow = new BrowserWindow({
      width: 1280,
      height: 800,
      show: false,
      webPreferences: {
        nodeIntegration: false,
        contextIsolation: true
      }
    });

    // 扫码页等通过 window.open 打开的窗口：统一选项，减轻多窗口时的白屏/卡顿，并与主窗口共享 session 以复用磁盘缓存
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

    mainWindow.webContents.on('did-create-window', (childWindow) => {
      if (!childWindow || childWindow.isDestroyed()) return;
      let shown = false;
      const showOnce = () => {
        if (shown || childWindow.isDestroyed()) return;
        shown = true;
        childWindow.show();
      };
      childWindow.once('ready-to-show', showOnce);
      childWindow.webContents.once('did-finish-load', showOnce);
    });

    // 必须在 loadURL 之前监听：否则本地页加载很快时 ready-to-show 已触发，会永远不调 show()
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
      if (mainWindow && !mainWindow.isDestroyed() && !mainWindow.isVisible()) {
        mainWindow.show();
        mainWindow.focus();
      }
    } catch (err) {
      console.error('加载页面失败:', err);
      dialog.showErrorBox('DLDL-Proxy', `无法加载页面：${err && err.message ? err.message : String(err)}`);
    }
  }

  function closeHttpServer() {
    if (!httpServer) return Promise.resolve();
    const server = httpServer;
    httpServer = null;
    return new Promise((resolve) => {
      server.close(() => resolve());
    });
  }

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
    await createWindow();
    app.on('activate', () => {
      if (BrowserWindow.getAllWindows().length === 0) {
        createWindow();
      }
    });
  });

  app.on('before-quit', (event) => {
    if (!httpServer) return;
    event.preventDefault();
    closeHttpServer().then(() => app.exit(0));
  });

  app.on('window-all-closed', () => {
    app.quit();
  });
}
