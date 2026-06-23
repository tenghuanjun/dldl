const https = require('https');
const http = require('http');

class ProxyPool {
  constructor(options = {}) {
    this.proxies = [];
    this.currentIndex = 0;
    this.currentProxy = null; // 当前正在使用的代理
    this.lastUsedProxy = null; // 上一次使用的代理（用于切换时排除）
    this.validateUrl = options.validateUrl || 'https://dldl.50pk.com';
    this.validateTimeout = options.validateTimeout || 5000;
    this.maxProxies = options.maxProxies || 50;
    this.refreshInterval = options.refreshInterval || 300000; // 5分钟刷新一次
    this.lastRefresh = 0;
    this.initialized = false;
  }

  // 从免费代理源获取代理列表
  async fetchProxies() {
    const sources = [
      this.fetchFromFreeProxyList,
      this.fetchFromProxyScrape,
    ];

    const results = await Promise.allSettled(
      sources.map(fn => fn.call(this))
    );

    const allProxies = [];
    for (const result of results) {
      if (result.status === 'fulfilled' && Array.isArray(result.value)) {
        allProxies.push(...result.value);
      }
    }

    return [...new Set(allProxies)];
  }

  // 从 free-proxy-list.net 获取
  async fetchFromFreeProxyList() {
    return new Promise((resolve, reject) => {
      const url = 'https://free-proxy-list.net/';
      https.get(url, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = [];
            const rows = data.match(/<tr>[\s\S]*?<\/tr>/g) || [];
            for (const row of rows.slice(1, 21)) { // 取前20个
              const cols = row.match(/<td>(.*?)<\/td>/g) || [];
              if (cols.length >= 7) {
                const ip = cols[0].replace(/<\/?td>/g, '').trim();
                const port = cols[1].replace(/<\/?td>/g, '').trim();
                const https = cols[6].replace(/<\/?td>/g, '').trim();
                if (ip && port && https === 'yes') {
                  proxies.push(`http://${ip}:${port}`);
                }
              }
            }
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      }).on('error', reject);
    });
  }

  // 从 proxyscrape 获取
  async fetchFromProxyScrape() {
    return new Promise((resolve, reject) => {
      const url = 'https://api.proxyscrape.com/v2/?request=displayproxies&protocol=http&timeout=5000&country=all&ssl=all&anonymity=all';
      https.get(url, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = data.split('\n')
              .map(line => line.trim())
              .filter(line => line && line.includes(':'))
              .map(line => `http://${line}`);
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      }).on('error', reject);
    });
  }

  // 验证代理是否可用（支持 HTTP 和 HTTPS 目标）
  async validateProxy(proxy) {
    return new Promise((resolve) => {
      const targetUrl = new URL(this.validateUrl);
      const proxyUrl = new URL(proxy);
      const isTargetHttps = targetUrl.protocol === 'https:';

      if (isTargetHttps) {
        // HTTPS 目标：通过 CONNECT 隧道验证代理
        const connectReq = http.request({
          hostname: proxyUrl.hostname,
          port: proxyUrl.port,
          method: 'CONNECT',
          path: `${targetUrl.hostname}:${targetUrl.port || 443}`,
          timeout: this.validateTimeout,
          headers: { 'Host': `${targetUrl.hostname}:${targetUrl.port || 443}` }
        });

        connectReq.on('connect', (res, socket) => {
          const req = https.request({
            hostname: targetUrl.hostname,
            port: targetUrl.port || 443,
            path: targetUrl.pathname + targetUrl.search,
            method: 'GET',
            timeout: this.validateTimeout,
            headers: { 'User-Agent': 'Mozilla/5.0' },
            socket,
            agent: false,
            rejectUnauthorized: false,
          }, (res) => {
            let data = '';
            res.on('data', chunk => data += chunk);
            res.on('end', () => resolve(res.statusCode >= 200 && res.statusCode < 400));
          });
          req.on('error', () => resolve(false));
          req.on('timeout', () => { req.destroy(); resolve(false); });
          req.end();
        });

        connectReq.on('error', () => resolve(false));
        connectReq.on('timeout', () => { connectReq.destroy(); resolve(false); });
        connectReq.end();
      } else {
        // HTTP 目标：通过代理转发（完整 URL 作为 path）
        const req = http.request({
          hostname: proxyUrl.hostname,
          port: proxyUrl.port,
          path: this.validateUrl,
          method: 'GET',
          timeout: this.validateTimeout,
          headers: {
            'User-Agent': 'Mozilla/5.0',
            'Host': targetUrl.hostname
          }
        }, (res) => {
          let data = '';
          res.on('data', chunk => data += chunk);
          res.on('end', () => resolve(res.statusCode >= 200 && res.statusCode < 400));
        });

        req.on('error', () => resolve(false));
        req.on('timeout', () => {
          req.destroy();
          resolve(false);
        });
        req.end();
      }
    });
  }

  // 批量验证代理
  async validateProxies(proxyList) {
    const concurrency = 10;
    const validProxies = [];
    
    for (let i = 0; i < proxyList.length; i += concurrency) {
      const batch = proxyList.slice(i, i + concurrency);
      const results = await Promise.allSettled(
        batch.map(async (proxy) => {
          const isValid = await this.validateProxy(proxy);
          return isValid ? proxy : null;
        })
      );
      
      for (const result of results) {
        if (result.status === 'fulfilled' && result.value) {
          validProxies.push(result.value);
        }
      }
    }
    
    return validProxies;
  }

  // 初始化代理池
  async init() {
    console.log('[ProxyPool] 正在获取代理列表...');
    const rawProxies = await this.fetchProxies();
    console.log(`[ProxyPool] 获取到 ${rawProxies.length} 个代理，开始验证...`);
    
    const validProxies = await this.validateProxies(rawProxies.slice(0, this.maxProxies));
    this.proxies = validProxies;
    this.initialized = true;
    this.lastRefresh = Date.now();
    
    console.log(`[ProxyPool] 验证完成，可用代理: ${this.proxies.length} 个`);
  }

  // 获取当前使用的代理（不阻塞：池未就绪则后台初始化，本次直连）
  async getProxy() {
    // 未初始化：触发后台初始化，本次不等待直接走直连
    if (!this.initialized) {
      this.init().catch(err => console.error('[ProxyPool] 初始化失败:', err.message));
      return null;
    }

    // 池为空：触发后台刷新
    if (this.proxies.length === 0) {
      if (Date.now() - this.lastRefresh > 30000) {
        this.init().catch(err => console.error('[ProxyPool] 刷新失败:', err.message));
      }
      return null;
    }

    // 检查是否需要定期刷新（后台进行）
    if (Date.now() - this.lastRefresh > this.refreshInterval) {
      this.init().catch(err => console.error('[ProxyPool] 刷新失败:', err.message));
    }

    // 如果没有当前代理，设置一个
    if (!this.currentProxy) {
      this.currentProxy = this.proxies[this.currentIndex];
      this.currentIndex = (this.currentIndex + 1) % this.proxies.length;
    }

    return this.currentProxy;
  }

  // 切换到一个与当前不同的代理
  async switchProxy() {
    if (!this.initialized || this.proxies.length === 0) {
      await this.init();
    }

    if (this.proxies.length === 0) {
      return null;
    }

    // 如果只有1个代理，无法切换不同IP
    if (this.proxies.length === 1) {
      console.warn('[ProxyPool] 只有1个可用代理，无法切换不同IP');
      this.currentProxy = this.proxies[0];
      return this.currentProxy;
    }

    // 记录上一次使用的代理
    this.lastUsedProxy = this.currentProxy;

    // 尝试找到一个与当前不同的代理
    let newProxy = null;
    let attempts = 0;
    const maxAttempts = this.proxies.length * 2; // 防止无限循环

    while (attempts < maxAttempts) {
      newProxy = this.proxies[this.currentIndex];
      this.currentIndex = (this.currentIndex + 1) % this.proxies.length;
      
      // 如果与当前不同，就使用这个
      if (newProxy !== this.lastUsedProxy) {
        break;
      }
      attempts++;
    }

    this.currentProxy = newProxy;
    console.log(`[ProxyPool] 切换IP: ${this.lastUsedProxy} -> ${this.currentProxy}`);
    return this.currentProxy;
  }

  // 获取当前代理（不触发初始化）
  getCurrentProxy() {
    return this.currentProxy;
  }

  // 获取上次使用的代理
  getLastUsedProxy() {
    return this.lastUsedProxy;
  }

  // 创建用于 HTTP 代理转发的配置（HTTP 目标走完整 URL 转发）
  getProxyConfig(proxyUrl) {
    if (!proxyUrl) return null;
    const proxy = new URL(proxyUrl);
    return {
      host: proxy.hostname,
      port: parseInt(proxy.port, 10),
    };
  }

  // 创建 HTTPS Agent（通过 CONNECT 隧道支持 HTTPS 目标走 HTTP 代理）
  createHttpsAgent(proxyUrl) {
    if (!proxyUrl) return null;
    const proxy = new URL(proxyUrl);
    const self = this;

    return new http.Agent({
      keepAlive: true,
      createConnection: (opts, cb) => {
        const targetHost = opts.hostname || opts.host;
        const targetPort = opts.port || 443;
        const connectReq = http.request({
          host: proxy.hostname,
          port: proxy.port,
          method: 'CONNECT',
          path: `${targetHost}:${targetPort}`,
          timeout: self.validateTimeout,
          headers: { 'Host': `${targetHost}:${targetPort}` },
        });

        connectReq.on('connect', (res, socket) => {
          cb(null, socket);
        });
        connectReq.on('error', (err) => cb(err));
        connectReq.end();
      }
    });
  }

  // 标记代理失效并从池中移除
  removeProxy(proxyUrl) {
    const idx = this.proxies.indexOf(proxyUrl);
    if (idx !== -1) {
      this.proxies.splice(idx, 1);
      console.log(`[ProxyPool] 移除失效代理: ${proxyUrl}，剩余: ${this.proxies.length}`);
    }
    if (this.currentProxy === proxyUrl) {
      this.currentProxy = this.proxies.length > 0 ? this.proxies[0] : null;
      this.currentIndex = 0;
    }
  }

  // 获取代理池状态
  getStatus() {
    return {
      initialized: this.initialized,
      total: this.proxies.length,
      currentIndex: this.currentIndex,
      currentProxy: this.currentProxy,
      lastUsedProxy: this.lastUsedProxy,
      lastRefresh: this.lastRefresh
    };
  }
}

// 单例模式
let instance = null;

module.exports = {
  ProxyPool,
  getInstance: (options) => {
    if (!instance) {
      instance = new ProxyPool(options);
    }
    return instance;
  }
};
