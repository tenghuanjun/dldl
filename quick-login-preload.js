/**
 * 快捷登录窗口 preload 脚本
 * 在页面加载前 hook HTMLScriptElement.prototype.src，
 * 将所有发往 s-api.37.com.cn 的 JSONP 请求重写到本地 Express 代理。
 * 这比 webRequest.onBeforeRequest redirect 更可靠，因为它不经过网络层，
 * 不受 Chromium 混合内容策略限制。
 */
(function () {
  const { contextBridge } = require('electron');

  // 从环境变量或 process.argv 获取端口号
  // preload 脚本运行在渲染进程中，可以通过 IPC 获取配置
  // 这里用 contextBridge 暴露一个方法给前端，但前端不需要调用

  // 使用 process.env 传递的端口
  const PORT = process.env.DLDL_PORT || '8080';
  const localBase = 'http://127.0.0.1:' + PORT + '/api/h5sdk-proxy';
  const apiPattern = /^(https?:)?\/\/s-api\.37\.com\.cn\/h5sdk\/(login|query_login)\/?/;

  // Hook HTMLScriptElement.prototype.src setter
  const origSrcDesc = Object.getOwnPropertyDescriptor(HTMLScriptElement.prototype, 'src');
  if (origSrcDesc && origSrcDesc.set) {
    const origSet = origSrcDesc.set;
    Object.defineProperty(HTMLScriptElement.prototype, 'src', {
      get: origSrcDesc.get,
      set: function (value) {
        if (typeof value === 'string' && apiPattern.test(value)) {
          const newValue = value.replace(apiPattern, localBase + '/$2');
          console.log('[quick-login/preload] hook src:', value.substring(0, 80), '->', newValue);
          origSet.call(this, newValue);
        } else {
          origSet.call(this, value);
        }
      },
      configurable: true,
      enumerable: true
    });
    console.log('[quick-login/preload] 已 hook HTMLScriptElement.prototype.src');
  }

  // 暴露一个标记，方便调试
  contextBridge.exposeInMainWorld('__DLDL_QUICK_LOGIN_PRELOAD__', true);
})();
