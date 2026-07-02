const https = require('https');
const http = require('http');

class ProxyPool {
  constructor(options = {}) {
    this.proxies = [];
    this.currentIndex = 0;
    this.currentProxy = null; // 当前正在使用的代理
    this.lastUsedProxy = null; // 上一次使用的代理（用于切换时排除）
    this.validateUrl = options.validateUrl || 'https://dldl.50pk.com';
    this.validateTimeout = options.validateTimeout || 8000;
    this.maxProxies = options.maxProxies || 60;
    this.refreshInterval = options.refreshInterval || 300000; // 5分钟刷新一次
    this.lastRefresh = 0;
    this.initialized = false;
    this._initPromise = null; // 防止并发初始化
  }

  // 从免费代理源获取代理列表（多个源并行抓取）
  async fetchProxies() {
    const sources = [
      this.fetchFromFreeProxyList,
      this.fetchFromSslProxies,
      this.fetchFromProxyScrapeHttps,
      this.fetchFromProxyScrapeHttp,
      this.fetchFromProxyListDownload,
    ];

    const results = await Promise.allSettled(
      sources.map(fn => fn.call(this))
    );

    const allProxies = [];
    for (const result of results) {
      if (result.status === 'fulfilled' && Array.isArray(result.value)) {
        console.log(`[ProxyPool] 源 ${result.value._source || 'unknown'} 返回 ${result.value.length} 个代理`);
        allProxies.push(...result.value);
      } else if (result.status === 'rejected') {
        console.warn(`[ProxyPool] 代理源获取失败: ${result.reason?.message}`);
      }
    }

    const unique = [...new Set(allProxies)];
    console.log(`[ProxyPool] 去重后共 ${unique.length} 个代理`);
    return unique;
  }

  // 从 free-proxy-list.net 获取（仅取 HTTPS 支持）
  async fetchFromFreeProxyList() {
    return new Promise((resolve, reject) => {
      const url = 'https://free-proxy-list.net/';
      const req = https.get(url, { timeout: 10000, headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)' } }, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = [];
            // 取所有行（不只是前20个）
            const rows = data.match(/<tr>[\s\S]*?<\/tr>/g) || [];
            for (const row of rows.slice(1, 51)) {
              const cols = row.match(/<td>(.*?)<\/td>/g) || [];
              if (cols.length >= 7) {
                const ip = cols[0].replace(/<\/?td>/g, '').trim();
                const port = cols[1].replace(/<\/?td>/g, '').trim();
                const httpsSupport = cols[6].replace(/<\/?td>/g, '').trim();
                // 只要 IP 和端口有效就加入（https列标记只是参考，实际通过验证来确认）
                if (ip && port && /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(ip)) {
                  proxies.push(`http://${ip}:${port}`);
                }
              }
            }
            proxies._source = 'free-proxy-list';
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    });
  }

  // 从 sslproxies.org 获取（专门收集 HTTPS 代理）
  async fetchFromSslProxies() {
    return new Promise((resolve, reject) => {
      const url = 'https://www.sslproxies.org/';
      const req = https.get(url, { timeout: 10000, headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)' } }, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = [];
            const rows = data.match(/<tr>[\s\S]*?<\/tr>/g) || [];
            for (const row of rows.slice(1, 51)) {
              const cols = row.match(/<td>(.*?)<\/td>/g) || [];
              if (cols.length >= 2) {
                const ip = cols[0].replace(/<\/?td>/g, '').trim();
                const port = cols[1].replace(/<\/?td>/g, '').trim();
                if (ip && port && /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(ip)) {
                  proxies.push(`http://${ip}:${port}`);
                }
              }
            }
            proxies._source = 'sslproxies';
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    });
  }

  // 从 proxyscrape v2 获取 HTTPS 代理
  async fetchFromProxyScrapeHttps() {
    return new Promise((resolve, reject) => {
      const url = 'https://api.proxyscrape.com/v2/?request=displayproxies&protocol=https&timeout=10000&country=all&ssl=all&anonymity=all';
      const req = https.get(url, { timeout: 10000, headers: { 'User-Agent': 'Mozilla/5.0' } }, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = data.split('\n')
              .map(line => line.trim())
              .filter(line => line && line.includes(':'))
              .map(line => `http://${line}`);
            proxies._source = 'proxyscrape-https';
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    });
  }

  // 从 proxyscrape v2 获取 HTTP 代理（备用，部分 HTTP 代理也支持 CONNECT）
  async fetchFromProxyScrapeHttp() {
    return new Promise((resolve, reject) => {
      const url = 'https://api.proxyscrape.com/v2/?request=displayproxies&protocol=http&timeout=10000&country=all&ssl=all&anonymity=all';
      const req = https.get(url, { timeout: 10000, headers: { 'User-Agent': 'Mozilla/5.0' } }, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = data.split('\n')
              .map(line => line.trim())
              .filter(line => line && line.includes(':'))
              .map(line => `http://${line}`);
            proxies._source = 'proxyscrape-http';
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    });
  }

  // 从 proxy-list.download 获取 HTTPS 代理
  async fetchFromProxyListDownload() {
    return new Promise((resolve, reject) => {
      const url = 'https://www.proxy-list.download/api/v1/get?type=https';
      const req = https.get(url, { timeout: 10000, headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)' } }, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
          try {
            const proxies = data.split('\n')
              .map(line => line.trim())
              .filter(line => line && line.includes(':'))
              .map(line => `http://${line}`);
            proxies._source = 'proxy-list-download';
            resolve(proxies);
          } catch (e) {
            reject(e);
          }
        });
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    });
  }

  // 验证代理是否可用（HTTPS CONNECT 隧道验证，这是我们实际使用的场景）
  async validateProxy(proxy) {
    return new Promise((resolve) => {
      const targetUrl = new URL(this.validateUrl);
      const proxyUrl = new URL(proxy);
      const targetHost = targetUrl.hostname;
      const targetPort = targetUrl.port || 443;

      // 建立 CONNECT 隧道
      const connectReq = http.request({
        hostname: proxyUrl.hostname,
        port: proxyUrl.port,
        method: 'CONNECT',
        path: `${targetHost}:${targetPort}`,
        timeout: this.validateTimeout,
        headers: {
          'Host': `${targetHost}:${targetPort}`,
          'User-Agent': 'Mozilla/5.0',
          'Proxy-Connection': 'Keep-Alive',
        }
      });

      let settled = false;
      const done = (valid) => {
        if (settled) return;
        settled = true;
        resolve(valid);
      };

      connectReq.on('connect', (res, socket) => {
        if (res.statusCode !== 200) {
          done(false);
          return;
        }
        // CONNECT 隧道建立成功，发送 HTTPS 请求验证
        const req = https.request({
          hostname: targetHost,
          port: targetPort,
          path: targetUrl.pathname + targetUrl.search,
          method: 'GET',
          timeout: this.validateTimeout,
          headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)' },
          socket,
          agent: false,
          rejectUnauthorized: false,
        }, (resp) => {
          // 收到响应即认为代理可用（不要求完整读取 body）
          resp.resume();
          done(resp.statusCode >= 200 && resp.statusCode < 400);
        });
        req.on('error', () => done(false));
        req.on('timeout', () => { req.destroy(); done(false); });
        req.end();
      });

      connectReq.on('error', () => done(false));
      connectReq.on('timeout', () => {
        connectReq.destroy();
        done(false);
      });
      connectReq.end();
    });
  }

  // 批量验证代理
  async validateProxies(proxyList) {
    const concurrency = 20; // 提高并发数以加快验证
    const validProxies = [];
    let checked = 0;
    const total = proxyList.length;
    
    for (let i = 0; i < proxyList.length; i += concurrency) {
      const batch = proxyList.slice(i, i + concurrency);
      const results = await Promise.allSettled(
        batch.map(async (proxy) => {
          const isValid = await this.validateProxy(proxy);
          checked++;
          if (checked % 20 === 0 || checked === total) {
            console.log(`[ProxyPool] 验证进度: ${checked}/${total}, 已通过: ${validProxies.length}`);
          }
          return isValid ? proxy : null;
        })
      );
      
      for (const result of results) {
        if (result.status === 'fulfilled' && result.value) {
          validProxies.push(result.value);
          if (validProxies.length >= 20) break; // 有 20 个可用就够了
        }
      }
      if (validProxies.length >= 20) break;
    }
    
    return validProxies;
  }

  // 初始化代理池（稳定等待完成）
  async init() {
    // 防止并发初始化
    if (this._initPromise) {
      console.log('[ProxyPool] 已有初始化进行中，等待完成...');
      return this._initPromise;
    }

    this._initPromise = (async () => {
      try {
        console.log('[ProxyPool] 正在获取代理列表...');
        const rawProxies = await this.fetchProxies();
        console.log(`[ProxyPool] 获取到 ${rawProxies.length} 个代理，开始验证 (并发=${20}, 超时=${this.validateTimeout}ms)...`);
        
        const validProxies = await this.validateProxies(rawProxies.slice(0, this.maxProxies));
        this.proxies = validProxies;
        this.initialized = true;
        this.lastRefresh = Date.now();
        
        console.log(`[ProxyPool] ✅ 验证完成，可用代理: ${this.proxies.length} 个`);
        if (this.proxies.length > 0) {
          console.log(`[ProxyPool] 示例: ${this.proxies.slice(0, 3).join(', ')}`);
        }
      } finally {
        this._initPromise = null;
      }
    })();

    return this._initPromise;
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
      console.log('[ProxyPool] 池未就绪或为空，正在初始化...');
      await this.init();
    }

    if (this.proxies.length === 0) {
      console.warn('[ProxyPool] ❌ 初始化后仍无可用代理');
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
    console.log(`[ProxyPool] 切换IP: ${this.lastUsedProxy || 'none'} -> ${this.currentProxy}`);
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
