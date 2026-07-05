/**
 * GitHub Actions Relay - 读取/写入 Gist 中的 token 数据
 *
 * 流程：
 *   1. 用户点击账号 → proxy 调用 writePending(uname, upwd) 写入 Gist
 *   2. GitHub Actions 定时 workflow 检测到 pending 请求，从新 Azure IP 获取 token
 *   3. proxy 调用 waitForToken(uname) 轮询直到拿到结果
 *
 * 使用：
 *   const relay = new GHActionsRelay({ gistId: 'xxx', gistToken: 'ghp_xxx' });
 *   await relay.writePending('account1', 'password1');
 *   const token = await relay.waitForToken('account1', 120000); // 最多等 2 分钟
 */

const https = require('https');

class GHActionsRelay {
  constructor(options = {}) {
    this.gistId = options.gistId || process.env.DLDL_GIST_ID || '';
    this.gistToken = options.gistToken || process.env.DLDL_GIST_TOKEN || '';
    this.rawUrl = options.rawUrl || process.env.DLDL_RAW_CACHE_URL || '';
    this.timeout = options.timeout || 5000;
    this.lastUpdatedAt = null;
  }

  // ==================== 底层 HTTP ====================

  _httpsRequest(opts) {
    return new Promise((resolve, reject) => {
      const req = https.request({
        hostname: opts.hostname,
        path: opts.path,
        method: opts.method || 'GET',
        timeout: opts.timeout || this.timeout,
        headers: {
          'User-Agent': 'DLDL-Relay/1.0',
          'Accept': 'application/vnd.github.v3+json',
          ...opts.headers
        }
      }, (res) => {
        let body = '';
        res.on('data', c => body += c);
        res.on('end', () => resolve({ status: res.statusCode, body }));
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
      if (opts.body) req.write(opts.body);
      req.end();
    });
  }

  _httpsGet(url) {
    return new Promise((resolve, reject) => {
      const u = new URL(url);
      const req = https.get(url, {
        timeout: this.timeout,
        headers: { 'User-Agent': 'DLDL-Relay/1.0' }
      }, (res) => {
        let body = '';
        res.on('data', c => body += c);
        res.on('end', () => resolve({ status: res.statusCode, body }));
      });
      req.on('error', reject);
      req.on('timeout', () => { req.destroy(); reject(new Error('timeout')); });
    });
  }

  // ==================== Gist 读写 ====================

  /**
   * 从 GitHub Gist API 读取当前完整数据
   */
  async _readGist() {
    const headers = {};
    if (this.gistToken) {
      headers['Authorization'] = 'Bearer ' + this.gistToken;
    }

    const { status, body } = await this._httpsRequest({
      hostname: 'api.github.com',
      path: '/gists/' + this.gistId,
      headers
    });

    if (status !== 200) {
      throw new Error('Gist 读取失败 HTTP ' + status);
    }

    const gist = JSON.parse(body);
    const file = gist.files && gist.files['dldl-tokens.json'];
    if (!file || !file.content) {
      return { tokens: {}, pending: {} };
    }
    try {
      return JSON.parse(file.content);
    } catch (e) {
      return { tokens: {}, pending: {} };
    }
  }

  /**
   * 写入 Gist（合并模式：保留已有数据，只更新指定字段）
   */
  async _writeGist(mergeData) {
    if (!this.gistToken) {
      throw new Error('需要 DLDL_GIST_TOKEN 才能写入 Gist');
    }

    // 先读当前数据
    let current = {};
    try {
      current = await this._readGist();
    } catch (e) {
      // 读取失败则用空对象
    }

    // 深度合并
    const merged = {
      tokens: { ...(current.tokens || {}), ...(mergeData.tokens || {}) },
      pending: { ...(current.pending || {}), ...(mergeData.pending || {}) },
      runnerIP: mergeData.runnerIP || current.runnerIP || 'unknown',
      updatedAt: mergeData.updatedAt || current.updatedAt || new Date().toISOString()
    };

    const json = JSON.stringify(merged);
    const { status, body } = await this._httpsRequest({
      hostname: 'api.github.com',
      path: '/gists/' + this.gistId,
      method: 'PATCH',
      headers: {
        'Authorization': 'Bearer ' + this.gistToken,
        'Content-Type': 'application/json',
        'Content-Length': Buffer.byteLength(json)
      },
      body: json
    });

    if (status !== 200) {
      throw new Error('Gist 写入失败 HTTP ' + status + ': ' + body.substring(0, 200));
    }

    return merged;
  }

  /**
   * 从 raw URL 读取（备用，无需 token）
   */
  async _readRaw() {
    if (!this.rawUrl) throw new Error('未配置 rawUrl');
    const { status, body } = await this._httpsGet(this.rawUrl);
    if (status !== 200) throw new Error('Raw 读取失败 HTTP ' + status);
    return JSON.parse(body);
  }

  /**
   * 获取最新数据（优先 API，兜底 raw URL）
   */
  async getLatest() {
    const errors = [];
    if (this.gistId) {
      try {
        const data = await this._readGist();
        console.log('[GH-Relay] Gist 命中, IP:', data.runnerIP);
        return data;
      } catch (e) { errors.push('Gist: ' + e.message); }
    }
    if (this.rawUrl) {
      try {
        const data = await this._readRaw();
        console.log('[GH-Relay] Raw 命中, IP:', data.runnerIP);
        return data;
      } catch (e) { errors.push('Raw: ' + e.message); }
    }
    throw new Error('无法获取 relay 数据: ' + errors.join('; '));
  }

  // ==================== 核心功能 ====================

  /**
   * 将账号写入 Gist 的 pending 队列
   * GitHub Actions 定时 workflow 检测到后会从新 IP 获取 token
   */
  async writePending(uname, upwd) {
    console.log('[GH-Relay] 写入 pending:', uname);
    const result = await this._writeGist({
      pending: {
        [uname]: {
          uname,
          upwd,
          requestedAt: new Date().toISOString()
        }
      }
    });
    return result;
  }

  /**
   * 轮询等待指定账号的 token 出现在 Gist 中
   * @param {string} uname 账号
   * @param {number} maxWaitMs 最大等待时间（毫秒），默认 130 秒
   * @param {number} pollInterval 轮询间隔（毫秒），默认 3 秒
   * @returns {Promise<{token, time, sign, runnerIP} | null>}
   */
  async waitForToken(uname, maxWaitMs = 130000, pollInterval = 3000) {
    const deadline = Date.now() + maxWaitMs;

    while (Date.now() < deadline) {
      try {
        const data = await this.getLatest();
        const entry = data.tokens && data.tokens[uname];

        // 检查有无新结果（fetchedAt 比 requestedAt 晚才算有效）
        if (entry && entry.token) {
          const pendingEntry = data.pending && data.pending[uname];
          const requestedAt = pendingEntry ? new Date(pendingEntry.requestedAt).getTime() : 0;
          const fetchedAt = entry.fetchedAt ? new Date(entry.fetchedAt).getTime() : 0;

          if (fetchedAt > requestedAt && entry.ok) {
            console.log('[GH-Relay] ✅ 拿到 token:', uname, 'IP:', entry.runnerIP);
            return {
              token: entry.token,
              time: entry.time,
              sign: entry.sign,
              runnerIP: entry.runnerIP,
              fetchedAt: entry.fetchedAt
            };
          }
        }
      } catch (e) {
        console.warn('[GH-Relay] 轮询失败，重试:', e.message);
      }

      // 等待后重试
      await new Promise(r => setTimeout(r, pollInterval));
    }

    console.warn('[GH-Relay] ⚠️ 等待超时:', uname);
    return null;
  }

  /**
   * 完整的「请求 token」流程：写入 pending + 轮询等待
   * @returns {Promise<{token, time, sign, runnerIP}>}
   */
  async requestToken(uname, upwd) {
    await this.writePending(uname, upwd);
    const result = await this.waitForToken(uname);
    if (!result) {
      throw new Error('获取 token 超时，请稍后重试（GitHub Actions 最多需要 2 分钟）');
    }
    return result;
  }

  // ==================== IP 切换（用于按钮展示） ====================

  /**
   * 切换 IP：读取最新数据，返回当前 runner IP 信息
   */
  async switchIP() {
    const data = await this.getLatest();
    const switched = this.lastUpdatedAt !== data.updatedAt;
    this.lastUpdatedAt = data.updatedAt;

    return {
      switched,
      runnerIP: data.runnerIP || 'unknown',
      updatedAt: data.updatedAt,
      tokenCount: Object.keys(data.tokens || {}).length
    };
  }

  /**
   * 获取已缓存的 token（不触发新请求）
   */
  async getToken(uname) {
    const data = await this.getLatest();
    const entry = data.tokens && data.tokens[uname];
    if (!entry || !entry.ok || !entry.token) return null;

    // 超过 5 分钟视为过期
    const age = Date.now() - new Date(entry.fetchedAt).getTime();
    if (age > 300000) return null;

    return {
      token: entry.token,
      time: entry.time,
      sign: entry.sign,
      runnerIP: entry.runnerIP
    };
  }

  getStatus() {
    return {
      gistConfigured: !!this.gistId,
      rawConfigured: !!this.rawUrl,
      lastUpdatedAt: this.lastUpdatedAt
    };
  }
}

module.exports = { GHActionsRelay };
