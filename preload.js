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

  // ========== 窗口同步（整合自 tongbuqi 同步器，Python headless 引擎） ==========
  /**
   * 枚举多开的斗罗窗口（按子进程 PID 精准识别）
   * @returns {Promise<{ok:boolean, windows?:Array, message?:string}>}
   */
  syncEnumerate: () => ipcRenderer.invoke('sync-enumerate'),
  /**
   * 主控窗口红框高亮
   * @param {number} hwnd
   */
  syncHighlight: (hwnd) => ipcRenderer.invoke('sync-highlight', { hwnd }),
  /**
   * 开始同步
   * @param {{master:number, targets:number[], mode?:string}} payload
   */
  syncStart: (payload) => ipcRenderer.invoke('sync-start', payload),
  /** 停止同步 */
  syncStop: () => ipcRenderer.invoke('sync-stop'),
  /** 查询同步状态 */
  syncStatus: () => ipcRenderer.invoke('sync-status'),
  /**
   * 窗口管理：关闭/宽高/排列/隐藏/显示
   * @param {{action:'close'|'set_size'|'arrange'|'hide'|'show', hwnds:number[], w?:number, h?:number, cols?:number, gap?:number, winW?:number, winH?:number, startX?:number, startY?:number}} payload
   */
  syncWindowOp: (payload) => ipcRenderer.invoke('sync-window-op', payload),
  /**
   * 监听引擎主动上报的事件（如 F10 强制停止）
   * @param {(payload:any)=>void} handler
   */
  onSyncEvent: (handler) => {
    if (typeof handler !== 'function') return;
    ipcRenderer.on('sync-event', (_e, payload) => handler(payload));
  },

});
