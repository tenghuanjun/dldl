/**
 * 应用全局配置：URL、快捷登录门户、扫码页参数等。
 * 可通过环境变量覆盖（见各字段说明）。
 */
module.exports = {
  /** 扫码页 login.php（需 token，会走 h5sdk/login 或由本地缓存 token） */
  scanLogin: {
    gid: process.env.DLDL_SCAN_GID || '1003279',
    pid: process.env.DLDL_SCAN_PID || '46',
    host: process.env.DLDL_SCAN_HOST || 'https://dldl.50pk.com',
    path: '/login.php',
    platCode: '37wan',
    isPcLauncher: 'true'
  },
  /**
   * 快捷登录：37 公开门户，使用 UINFO cookie（直连 37.com.cn，不走本地代理）
   * https://37.com.cn/h5game/public/?pid=46&gid=1005176
   */
  quickLogin: {
    pid: process.env.DLDL_QUICK_PID || '46',
    gid: process.env.DLDL_QUICK_GID || '1005176',
    portalOrigin: process.env.DLDL_QUICK_ORIGIN || 'https://37.com.cn',
    publicPath: '/h5game/public/'
  },
  h5sdk: {
    apiKey: process.env.DLDL_H5SDK_API_KEY || 'Jp*4Y8vQOYck2*&Z',
    loginUrl: 'https://s-api.37.com.cn/h5sdk/login'
  }
};
