const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const path = require('path');
const app = express();
const PORT = 8080;
const TARGET = 'https://dldl.50pk.com';
const fakeLoginData = {
  gid: "1005176",
  pid: "46",
  token: "BASE64MGU3OHhGZzBJdzNVWGI2eno0T1dwbVgwK2pRSmg1Ymc3eDAzaHhrSXQvV0s3Vlh2STRVbFA4V0RlWDA2emc1by8vN2MzalNXSlk0aHMvazNuSWZOaUJVMG5LMUNONnVDMGpTQVBiSUk4dU1xd3BaeFNzYXRjQ1VpSVhmWUFKL1RzaVNKaktjKzQyRWc1NEltVGd0UlMrSG1wcHVUWjc2ZXhidTVpWnJjVmVqc09aelNiNHpJcjVCUkxKWnBGQQ==",
  time: "1828454400",
  sign: "69f471146c0fa02ce1ba66f013f2b880",
  appVer: "134",
  platCode: "37wan",
  IMEI: "DCEADE00-A9B3-42F2-B4EB-8C766C0DD7A4"
};
// 拦截扫码接口
app.use('/pc/getCodeInfo', (req, res) => {
  console.log('[Mock] getCodeInfo');
  res.json({ state: 1, msg: "success", data: fakeLoginData });
});
app.use('/pc/getId', (req, res) => {
  console.log('[Mock] getId');
  res.json({ state: 1, msg: "success", data: "mock_id_" + Date.now() });
});
// 静态文件服务
app.use(express.static(__dirname));
// 代理所有其他请求
app.use('/', createProxyMiddleware({
  target: TARGET,
  changeOrigin: true,
  secure: false,
  on: {
    proxyReq: (proxyReq, req, res) => {
      // 避免上游返回 gzip，便于我们在 login.php 场景下直接替换 HTML
      proxyReq.setHeader('accept-encoding', 'identity');
    },
    proxyRes: (proxyRes, req, res) => {
      if (req.path.includes('login.php')) {
        let body = [];
        proxyRes.on('data', (chunk) => body.push(chunk));
        proxyRes.on('end', () => {
          body = Buffer.concat(body).toString();

          // 只把 enter.js 指向本地代理，其他静态资源保持线上原始地址，最大化像素一致性
          body = body.replace(
            /https:\/\/dldl\.50pk\.com\/qq_res\/enter\.js(\?[^"']*)?/g,
            '/qq_res/enter.js$1'
          );
          
          // 同步上游状态和基础响应头（修改 body 后移除长度/压缩相关头）
          res.statusCode = proxyRes.statusCode;
          Object.entries(proxyRes.headers || {}).forEach(([key, value]) => {
            const lowerKey = key.toLowerCase();
            if (lowerKey === 'content-length' || lowerKey === 'content-encoding') {
              return;
            }
            if (value !== undefined) {
              res.setHeader(key, value);
            }
          });
          res.setHeader('Content-Type', 'text/html; charset=utf-8');
          res.end(body);
        });
        return;
      }
      if (req.path.includes('/qq_res/enter.js')) {
        let body = [];
        proxyRes.on('data', (chunk) => body.push(chunk));
        proxyRes.on('end', () => {
          body = Buffer.concat(body).toString();

          // 在“线上原版 enter.js”上仅替换扫码与回跳地址，保证行为在本地闭环
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/pc\/getId/g, '/pc/getId');
          body = body.replace(/https:\/\/app\.xxh5\.z7xz\.com\/pc\/getCodeInfo/g, '/pc/getCodeInfo');
          body = body.replace(/https:\/\/dldl\.50pk\.com\/login\.php\?/g, '/login.php?');

          res.statusCode = proxyRes.statusCode;
          Object.entries(proxyRes.headers || {}).forEach(([key, value]) => {
            const lowerKey = key.toLowerCase();
            if (lowerKey === 'content-length' || lowerKey === 'content-encoding') {
              return;
            }
            if (value !== undefined) {
              res.setHeader(key, value);
            }
          });
          res.setHeader('Content-Type', 'application/javascript; charset=utf-8');
          res.end(body);
        });
        return;
      }
      proxyRes.pipe(res);
    }
  },
  selfHandleResponse: true
}));
app.listen(PORT, () => {
  console.log(`✅ Proxy running at http://localhost:${PORT}`);
  console.log(`✅ Mock interceptors active`);
});