// Cloudflare Worker 代码
// 部署到 Cloudflare Workers 后，将 Worker URL 填入 account.html 的 PROXY_WORKER_URL 配置

export default {
  async fetch(request, env) {
    // 处理 CORS 预检请求
    if (request.method === 'OPTIONS') {
      return new Response(null, {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
          'Access-Control-Allow-Headers': 'Content-Type',
        },
      });
    }

    try {
      const url = new URL(request.url);
      
      // 根路径测试
      if (url.pathname === '/') {
        return new Response(JSON.stringify({ message: 'Worker is running', timestamp: Date.now() }), {
          headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
          },
        });
      }
      
      // 测试端点
      if (url.pathname === '/test') {
        return new Response(JSON.stringify({ status: 'ok', timestamp: Date.now() }), {
          headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
          },
        });
      }
      
      // 获取当前出口IP的接口
      if (url.pathname === '/get-ip') {
        try {
          const ipResponse = await fetch('https://api.ipify.org?format=json');
          if (!ipResponse.ok) {
            throw new Error(`ipify.org returned ${ipResponse.status}`);
          }
          const ipData = await ipResponse.json();
          return new Response(JSON.stringify({ ip: ipData.ip }), {
            headers: {
              'Content-Type': 'application/json',
              'Access-Control-Allow-Origin': '*',
            },
          });
        } catch (error) {
          return new Response(JSON.stringify({ error: 'Failed to get IP', details: error.message }), {
            status: 500,
            headers: {
              'Content-Type': 'application/json',
              'Access-Control-Allow-Origin': '*',
            },
          });
        }
      }
      
      // 构建目标 URL
      const targetUrl = `https://s-api.37.com.cn${url.pathname}${url.search}`;
      console.log(`Proxying request to: ${targetUrl}`);
      
      // 转发请求到目标 API
      const response = await fetch(targetUrl, {
        method: request.method,
        headers: {
          'User-Agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 18_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.5 Mobile/15E148 Safari/604.1',
          'Accept': '*/*',
          'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8',
        },
      });
      
      console.log(`Response status: ${response.status}, ok: ${response.ok}`);

      // 获取响应内容
      const body = await response.text();
      console.log(`Response body length: ${body.length}`);

      // 返回响应，添加 CORS 头
      return new Response(body, {
        status: response.status,
        statusText: response.statusText,
        headers: {
          'Content-Type': response.headers.get('Content-Type') || 'application/javascript; charset=utf-8',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
          'Access-Control-Allow-Headers': 'Content-Type',
        },
      });
    } catch (error) {
      return new Response(JSON.stringify({ error: error.message }), {
        status: 500,
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
        },
      });
    }
  },
};
