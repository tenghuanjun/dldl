const { app, BrowserWindow, dialog } = require('electron');

const gotTheLock = app.requestSingleInstanceLock();

if (!gotTheLock) {
  app.quit();
} else {
  process.env.DLDL_USER_DATA = app.getPath('userData');
  process.env.DLDL_STATIC_ROOT = __dirname;

  const { startProxyServer, PORT } = require('./proxy');

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
    const url = `http://127.0.0.1:${PORT}/account.html`;
    try {
      await mainWindow.loadURL(url);
    } catch (err) {
      console.error('加载页面失败:', err);
      dialog.showErrorBox('DLDL-Proxy', `无法加载页面：${err && err.message ? err.message : String(err)}`);
    }
    mainWindow.once('ready-to-show', () => {
      mainWindow.show();
    });
    mainWindow.on('closed', () => {
      mainWindow = null;
    });
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
