/**
 * DLDL-Proxy preload 脚本
 * 通过 contextBridge 安全地暴露 Electron IPC API 给渲染进程（account.html）
 * 替代 Tauri 的 window.__TAURI__ 桥接
 */
const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('__ELECTRON__', {
  /**
   * 快捷登录：打开 37 门户窗口并注入 UINFO/HISTORY cookie
   * @param {Object} payload - { url, uname, upwd, uinfo, history }
   * @returns {Promise<{ok: boolean, message?: string}>}
   */
  openQuickLogin: (payload) => {
    return ipcRenderer.invoke('open-quick-login', payload);
  },

  /**
   * 快捷登录（fire-and-forget 模式，不等待返回）
   * @param {Object} payload - { url, uname, upwd, uinfo, history }
   */
  emitQuickLogin: (payload) => {
    ipcRenderer.send('dldl-open-quick-login', payload);
  },

  /**
   * APP 登录（多开）：由主进程创建独立渲染进程窗口（进程级隔离 + 平铺排版 + 回收）
   * @param {Object} payload - { url, title }
   * @returns {Promise<{ok: boolean, id?: string, message?: string}>}
   */
  openAppLogin: (payload) => {
    return ipcRenderer.invoke('open-app-login', payload);
  },

  /**
   * APP 登录（fire-and-forget 模式，不等待返回，适合批量启动）
   * @param {Object} payload - { url, title }
   */
  emitAppLogin: (payload) => {
    ipcRenderer.send('dldl-open-app-login', payload);
  },

  /**
   * 关闭所有 APP 登录窗口（回收）
   * @returns {Promise<{ok: boolean}>}
   */
  closeAllAppLogins: () => {
    return ipcRenderer.invoke('close-all-app-logins');
  },

});
