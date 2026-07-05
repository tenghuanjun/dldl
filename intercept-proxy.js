/**
 * 拦截代理 - 捕获 s-api-secure.37.com.cn/sdk/login 请求
 * 
 * 用法：
 *   1. node intercept-proxy.js
 *   2. 将 APP 的代理设置为 http://192.168.31.xxx:9099
 *   3. 观察控制台输出的请求详情
 * 
 * 两种拦截模式：
 *   模式A（推荐）：APP 配置 HTTP 代理 → 本地 9099 → 实时打印 → 转发到真实服务器
 *   模式B（反向代理）：直接把 APP 的请求目标改为 http://localhost:9099/sdk/login，自动打印并转发
 */

const http = require('http');
const https = require('https');
const net = require('net');
const url = require('url');
const crypto = require('crypto');

const LISTEN_PORT = 9099;
const TARGET_HOST = 's-api-secure.37.com.cn';
const TARGET_PATH = '/sdk/login';
const INTERCEPT_HOSTS = ['s-api-secure.37.com.cn'];
const AES_KEY = '0123456789012345'; // 从 bundle.js 解码出的加密密钥

// ==================== 解密工具 ====================

function decryptAES(encryptedBase64, key) {
  try {
    // hex 解码密钥
    const keyBuffer = Buffer.from(key, 'utf8');
    // base64url 还原为标准 base64
    let b64 = encryptedBase64.replace(/-/g, '+').replace(/_/g, '/');
    while (b64.length % 4) b64 += '=';
    const cipherBuffer = Buffer.from(b64, 'base64');

    const decipher = crypto.createDecipheriv('aes-128-ecb', keyBuffer, null);
    decipher.setAutoPadding(true);
    let decrypted = decipher.update(cipherBuffer);
    decrypted = Buffer.concat([decrypted, decipher.final()]);
    return decrypted.toString('utf8');
  } catch (e) {
    return `[解密失败] ${e.message}`;
  }
}

// ==================== 日志打印 ====================

function logIntercept(method, path, headers, body) {
  const timestamp = new Date().toISOString();
  console.log('\n' + '='.repeat(80));
  console.log(`[🔍 拦截到 sdk/login 请求] ${timestamp}`);
  console.log('='.repeat(80));
  console.log(`Method:  ${method}`);
  console.log(`Path:    ${path}`);
  console.log(`Host:    ${headers['host'] || TARGET_HOST}`);
  console.log('-'.repeat(40));
  console.log('📋 Headers:');
  for (const [k, v] of Object.entries(headers)) {
    console.log(`  ${k}: ${v}`);
  }
  console.log('-'.repeat(40));
  console.log('📦 Body (raw, first 500 chars):');
  console.log(body.substring(0, 500));
  if (body.length > 500) console.log(`  ... (total ${body.length} chars)`);

  // 尝试用 AES-128-ECB 解密
  if (body.length > 10) {
    console.log('-'.repeat(40));
    console.log('🔓 AES-128-ECB 解密结果:');
    const decrypted = decryptAES(body, AES_KEY);
    console.log(decrypted);
  }
  console.log('='.repeat(80) + '\n');
}

// ==================== HTTP 代理模式（正向代理） ====================

const proxyServer = http.createServer((clientReq, clientRes) => {
  const reqUrl = url.parse(clientReq.url);
  const isInterceptTarget =
    reqUrl.hostname === TARGET_HOST && reqUrl.pathname === TARGET_PATH;

  // 收集请求体
  let bodyChunks = [];
  clientReq.on('data', chunk => bodyChunks.push(chunk));
  clientReq.on('end', () => {
    const body = Buffer.concat(bodyChunks).toString('utf8');

    if (isInterceptTarget) {
      logIntercept(clientReq.method, reqUrl.path, clientReq.headers, body);
    }

    // 转发到真实服务器
    const options = {
      hostname: reqUrl.hostname,
      port: reqUrl.port || 80,
      path: reqUrl.path,
      method: clientReq.method,
      headers: { ...clientReq.headers },
    };
    // 移除代理特有的 header
    delete options.headers['proxy-connection'];
    delete options.headers['proxy-authorization'];

    const proxyReq = http.request(options, (proxyRes) => {
      // 收集响应体
      let resChunks = [];
      proxyRes.on('data', chunk => resChunks.push(chunk));
      proxyRes.on('end', () => {
        const resBody = Buffer.concat(resChunks).toString('utf8');

        if (isInterceptTarget) {
          console.log('📤 响应:');
          console.log(`  Status: ${proxyRes.statusCode}`);
          console.log(`  Body (first 300 chars): ${resBody.substring(0, 300)}`);
          console.log('='.repeat(80) + '\n');
        }

        clientRes.writeHead(proxyRes.statusCode, proxyRes.headers);
        clientRes.end(Buffer.concat(resChunks));
      });
    });

    proxyReq.on('error', (err) => {
      console.error(`[代理错误] ${err.message}`);
      clientRes.writeHead(502);
      clientRes.end('Proxy Error: ' + err.message);
    });

    proxyReq.end(Buffer.concat(bodyChunks));
  });
});

// ==================== CONNECT 隧道（HTTPS） ====================

proxyServer.on('connect', (req, clientSocket, head) => {
  const [hostname, port] = req.url.split(':');
  const targetPort = parseInt(port) || 443;
  const isInterceptTarget = hostname === TARGET_HOST;

  if (isInterceptTarget) {
    console.log(`[🔒 CONNECT] ${hostname}:${targetPort} — HTTPS 隧道，无法查看明文`);
    console.log(`  提示：HTTPS 加密流量需配置 mitmproxy 或安装证书才能解密`);
    console.log('='.repeat(80) + '\n');
  }

  const serverSocket = net.connect(targetPort, hostname, () => {
    clientSocket.write('HTTP/1.1 200 Connection Established\r\n\r\n');
    serverSocket.write(head);
    serverSocket.pipe(clientSocket);
    clientSocket.pipe(serverSocket);
  });

  serverSocket.on('error', (err) => {
    console.error(`[CONNECT 错误] ${hostname}:${targetPort} - ${err.message}`);
    clientSocket.end();
  });

  clientSocket.on('error', (err) => {
    serverSocket.end();
  });
});

proxyServer.listen(LISTEN_PORT, '0.0.0.0', () => {
  const os = require('os');
  const interfaces = os.networkInterfaces();
  console.log('\n' + '='.repeat(60));
  console.log('  🔍 sdk/login 拦截代理已启动');
  console.log('='.repeat(60));
  console.log(`  监听地址: http://0.0.0.0:${LISTEN_PORT}`);
  console.log(`  目标接口: http://${TARGET_HOST}${TARGET_PATH}`);
  console.log(`  解密密钥: ${AES_KEY}`);
  console.log('-' + '-'.repeat(42));

  // 打印本机 IP
  for (const [name, addrs] of Object.entries(interfaces)) {
    for (const addr of addrs) {
      if (addr.family === 'IPv4' && !addr.internal) {
        console.log(`  本机 IP:  http://${addr.address}:${LISTEN_PORT}`);
      }
    }
  }
  console.log('='.repeat(60));
  console.log('');
  console.log('📱 APP 端配置方法：');
  console.log(`  1. 将 APP 的 HTTP 代理设置为上述「本机 IP:${LISTEN_PORT}」`);
  console.log('  2. 触发登录 → 控制台将打印加密参数 + 解密结果');
  console.log('');
  console.log('🌐 浏览器测试方法（直接替换请求 URL）：');
  console.log(`  curl -X POST "http://localhost:${LISTEN_PORT}/sdk/login" -d "你的加密参数"`);
  console.log('');
  console.log('⚠️  注意：');
  console.log('  - 如果 APP 用 HTTPS，本代理只能看到 CONNECT 隧道，无法解密内容');
  console.log('  - 要解密 HTTPS，需要 mitmproxy（pip3 install mitmproxy）');
  console.log('  - 也可以把 APP 的请求改为 HTTP（如果 APP 允许）');
  console.log('');
  console.log('🟢 等待请求...\n');
});
