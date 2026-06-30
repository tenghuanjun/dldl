/**
 * 斗罗大陆 APP 端登录模拟
 * 
 * 通过反编译 APK 中的 SDK 插件 (sq_plugin_3.7.9.6.1) 分析得到：
 * 
 * 登录接口: POST http://s-api-secure.37.com.cn/sdk/login/
 * 
 * 密码加密: AES/ECB/PKCS5Padding
 *   - Key: APP_KEY 的前 16 位 (CR.wdPyFoanb6Thv)
 *   - 结果 Base64 编码
 * 
 * 签名算法 (SignV3):
 *   1. 所有参数按 key 字母排序
 *   2. 拼接 key=value
 *   3. 最后拼接 APP_KEY
 *   4. MD5 并转小写
 */

const crypto = require('crypto');

// ==================== 配置参数 ====================
const CONFIG = {
  APP_KEY: 'CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1',
  APP_HOST: '37.com.cn',
  // s-api-secure 有 IP 白名单限制，s-api 可用
  LOGIN_URL: 'http://s-api.37.com.cn/sdk/login/',
  PID: '1',
  GID: '1002997',
  REFER: '1_1002997_11327_1001',
  SCUT: '1',         // loginCode
  GWVERSION: '4.6.7',
  SVERSION: '3.7.9.6.1',
  FROM: 'android',
  HOST_SDK_VERSION: '3.7.9.6.1',
};

// AES Key 取 APP_KEY 前 16 位
CONFIG.AES_KEY = CONFIG.APP_KEY.substring(0, 16);
// dev = MD5(imei + mac + androidId + timestamp)，用固定值模拟
CONFIG.DEV = '9552cfd00bfed7dbd9f8133a0fc9b03e'; // 从 APK 字符串中提取的实际 dev 值

// ==================== 加密函数 ====================

/**
 * AES/ECB/PKCS5Padding 加密
 */
function aesEncrypt(plainText, key) {
  const cipher = crypto.createCipheriv('aes-128-ecb', Buffer.from(key, 'utf8'), null);
  cipher.setAutoPadding(true);
  let encrypted = cipher.update(plainText, 'utf8', 'base64');
  encrypted += cipher.final('base64');
  return encrypted;
}

/**
 * AES/ECB/PKCS5Padding 解密
 */
function aesDecrypt(base64Text, key) {
  const decipher = crypto.createDecipheriv('aes-128-ecb', Buffer.from(key, 'utf8'), null);
  decipher.setAutoPadding(true);
  let decrypted = decipher.update(base64Text, 'base64', 'utf8');
  decrypted += decipher.final('utf8');
  return decrypted;
}

/**
 * 加密密码 (APP端登录使用)
 */
function encryptPassword(password) {
  return aesEncrypt(password, CONFIG.AES_KEY);
}

/**
 * SignV3 签名算法
 * 1. 所有参数 key 字母排序
 * 2. 拼接 key=value
 * 3. 最后加 APP_KEY
 * 4. MD5 小写
 */
function signV3(params) {
  // 移除 sign 字段（如果有）
  const signParams = {};
  for (const key of Object.keys(params)) {
    if (key !== 'sign') {
      signParams[key] = params[key];
    }
  }
  
  // 按 key 排序
  const sortedKeys = Object.keys(signParams).sort();
  
  // 拼接
  let signStr = '';
  for (const key of sortedKeys) {
    signStr += key + '=' + signParams[key];
  }
  // 拼接 APP_KEY
  signStr += CONFIG.APP_KEY;
  
  console.log('[SignV3] 签名原串:', signStr);
  
  // MD5 小写
  return crypto.createHash('md5').update(signStr).digest('hex').toLowerCase();
}

// ==================== 登录请求 ====================

/**
 * 构建登录请求参数
 */
function buildLoginParams(username, password) {
  const encryptedPwd = encryptPassword(password);
  const timestamp = String(Math.floor(Date.now() / 1000));
  
  const params = {
    uname: username,
    upwd: encryptedPwd,
    signType: 'all',              // SqConstants.PURCHASE_DETAIL_ALL
    display_name: '斗罗大陆',       // AppUtils.getAppName()
    trans_info: '',               // SqRequest.getTransInfo()
    // CommonParamsV2 添加的公共参数
    pid: CONFIG.PID,
    gid: CONFIG.GID,
    refer: CONFIG.REFER,
    dev: CONFIG.DEV,
    sversion: CONFIG.SVERSION,
    version: '1.0.0',            // AppUtils.getVersionName()，用个默认值
    gwversion: CONFIG.GWVERSION,
    time: timestamp,
    scut: CONFIG.SCUT,
    oaid: '',                     // DeviceUtils.getOaid()
    from: CONFIG.FROM,
    host_sdk_version: CONFIG.HOST_SDK_VERSION,
    is_root: '0',                 // RootLogic.getValue()
    is_simulator: '0',            // SimulatorLogic.getValue()
  };
  
  // 计算签名
  params.sign = signV3(params);
  
  console.log('[登录参数]:', JSON.stringify(params, null, 2));
  
  return params;
}

/**
 * 发送登录请求
 */
async function doLogin(username, password) {
  const params = buildLoginParams(username, password);
  
  // 构建 form-urlencoded body
  const formBody = Object.keys(params)
    .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key]))
    .join('&');
  
  console.log('\n[请求] POST', CONFIG.LOGIN_URL);
  console.log('[请求体]:', formBody.substring(0, 200) + '...');
  
  try {
    const response = await fetch(CONFIG.LOGIN_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'User-Agent': 'Dalvik/2.1.0 (Linux; U; Android 10; Android SDK built for x86_64 Build/QT)',
      },
      body: formBody,
    });
    
    const responseText = await response.text();
    console.log('\n[响应] HTTP', response.status);
    console.log('[响应体]:', responseText);
    
    // 尝试解析 JSON
    try {
      const json = JSON.parse(responseText);
      
      // 响应格式: { state, msg, data: { token, uid, uname, ... } }
      const data = json.data || json;
      
      console.log('\n[解析结果]:');
      console.log('  state:', json.state);
      console.log('  msg:', json.msg);
      console.log('  uid:', data.uid);
      console.log('  uname:', data.uname);
      console.log('  token:', data.token ? data.token.substring(0, 50) + '...' : null);
      console.log('  refresh_token:', data.refresh_token ? data.refresh_token.substring(0, 50) + '...' : null);
      console.log('  login_account:', data.login_account);
      console.log('  action_type:', data.action_type);
      
      return json;
    } catch (e) {
      console.log('[响应不是JSON，尝试解密...]');
      try {
        const decrypted = aesDecrypt(responseText, CONFIG.AES_KEY);
        console.log('[解密结果]:', decrypted);
        return JSON.parse(decrypted);
      } catch (e2) {
        console.log('[解密失败]:', e2.message);
        return responseText;
      }
    }
  } catch (error) {
    console.error('[请求失败]:', error.message);
    throw error;
  }
}

// ==================== 命令行入口 ====================

async function main() {
  const args = process.argv.slice(2);
  
  if (args.length < 2) {
    console.log('用法: node app_login.js <账号> <密码>');
    console.log('示例: node app_login.js testuser123 mypassword');
    process.exit(1);
  }
  
  const username = args[0];
  const password = args[1];
  
  console.log('========================================');
  console.log('  斗罗大陆 APP 端登录模拟');
  console.log('========================================');
  console.log('账号:', username);
  console.log('密码:', password.replace(/./g, '*'));
  console.log('加密后密码:', encryptPassword(password));
  console.log('AES Key:', CONFIG.AES_KEY);
  console.log('========================================\n');
  
  try {
    const result = await doLogin(username, password);
    
    console.log('\n========================================');
    console.log('  登录完成');
    console.log('========================================');
    
    // 输出关键字段供后续使用
    const data = result && result.data ? result.data : result;
    if (data && data.token) {
      console.log('\n关键字段:');
      console.log('  token:', data.token);
      console.log('  uid:', data.uid);
      console.log('  uname:', data.uname);
      console.log('  refresh_token:', data.refresh_token);
    }
  } catch (error) {
    console.error('\n登录失败:', error.message);
    process.exit(1);
  }
}

main();
