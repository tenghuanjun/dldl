# 多 Worker 突破服务器 IP 限制 — 原理说明

## 背景

37 游戏服务器（`s-api.37.com.cn`）对单个 IP 有严格的频率限制：**24 小时内仅允许 10~15 次 SDK 登录请求**，超额返回 40025 错误。

该限制使得单台服务器 / 单个 Worker 每天只能刷新 2~3 个游戏账号的 token，无法满足批量管理需求。

---

## 核心原理

### Cloudflare Workers 的出口 IP 特性

Cloudflare 的全球边缘网络有数百个数据中心。每个 Worker 运行时，其 `fetch()` 出站请求的源 IP 是所在边缘节点的出口 IP。

**关键发现**：同一账号下部署的不同 Worker，即使代码完全相同，也可能被调度到不同的边缘节点 → 获得不同的出口 IP。

### 思路来源：家庭宽带 DHCP

类比家庭宽带的动态 IP：
- 路由器重启 → ISP 重新分配 IP → 旧的频率限制计数器归零
- **本质是用「切换 IP」来绕开单 IP 限制**

我们无法重启 Cloudflare 边缘节点，但可以**部署多个 Worker → 获得多个出口 IP → 前端轮询切换**。

---

## 架构设计

```
                    ┌─────────────────────────────────────┐
                    │          Cloudflare 边缘网络          │
                    │                                     │
   手机页面 ──────▶  Worker #1  (IP-A) ──── 37服务器      │
   (轮询切换)       Worker #2  (IP-B) ──── 37服务器        │
                    Worker #3  (IP-C) ──── 37服务器        │
                    ...                                   │
                    Worker #100 (IP-?) ── 37服务器         │
                    └─────────────────────────────────────┘
```

### 流程

```
用户点击刷新
  │
  ▼
前端取当前 Worker URL
  │
  ▼
fetch(worker + '/api/app-login')
  │
  ├── 成功 → 返回 token → 结束
  │
  └── 40025 → 前端自动切到下一个 Worker → 重试 (最多3次)
```

### 前端 Worker 轮询代码

```javascript
// 自动生成 100 个 Worker URL
const WORKERS = (function() {
    var list = ['https://red-moon-ff95.tenghuanjun.workers.dev'];
    for (var i = 1; i <= 99; i++) {
        list.push('https://dlapi-' + i + '.tenghuanjun.workers.dev');
    }
    return list;
})();

let workerIdx = 0;
function getWorker() { return WORKERS[workerIdx % WORKERS.length]; }
function nextWorker() { workerIdx++; return getWorker(); }
```

---

## 请求流程优化

### 请求链路（简化后）

每个账号刷新仅需 3 步（去掉了非必要的 SDK 登录）：

```
1. pc/getId        → 获取扫码会话ID
2. h5sdk/login     → 生成游戏入口 token (h5sdk方式)
3. pc/postCodeInfo → 写入游戏参数
4. pc/getCodeInfo  → 取回完整入口链接
```

### 为什么不走 APP 加密通道

APP 原生流程需要先请求 `sdk-apix-secure.37.com.cn` 获取动态加密密钥，该域名对 Cloudflare Worker IP 返回 **401 Unauthorized**，直接拒绝连接。因此采用 h5sdk 通道，所有端点均可从 Worker 正常访问。

---

## 部署方式

### Worker 列表

| Worker 名称 | URL | 用途 |
|-------------|-----|------|
| red-moon-ff95 | `red-moon-ff95.tenghuanjun.workers.dev` | 主 Worker（历史保留） |
| dlapi-1 ~ dlapi-99 | `dlapi-N.tenghuanjun.workers.dev` | 轮询池（99个） |

### 部署命令

```powershell
# 主 Worker
npx wrangler deploy

# Worker 1-10
powershell -File deploy_workers.ps1

# Worker 11-99
powershell -File deploy_more_workers.ps1
```

所有 Worker 部署**完全相同的代码**（`cloudflare-worker.js`），仅名称不同。

---

## 容量估算

| 参数 | 值 |
|------|-----|
| 单个 IP 24h 限制 | ~12 次 |
| Worker 数量 | 100 个 |
| 理论最大请求/天 | ~1,200 次 |
| 每次刷新消耗 | ~3 次请求（pc/getId + h5sdk + postCodeInfo + getCodeInfo） |
| 理论最大刷新/天 | ~400 个账号 |

实际有效容量还受到以下因素影响：
- 不同 Worker 的出口 IP 可能有重叠（同一数据中心的 Worker 共享出口 IP）
- 37 服务器的其他维度的风控策略
- Cloudflare 免费计划的 Worker 每日请求配额（10万次/天）

---

## 技术要点总结

| 技术点 | 说明 |
|--------|------|
| 多 IP 轮询 | 利用 Cloudflare Worker 的不同边缘节点出口 IP |
| 前端无感切换 | 40025 错误自动触发 Worker 切换，最多重试 3 次 |
| 请求链路精简 | 去掉非必要的 SDK 登录步骤，减少 IP 消耗 |
| 统一部署 | 所有 Worker 运行相同代码，通过脚本批量部署 |
| 动态 Worker 列表 | 前端用循环生成 100 个 URL，新增 Worker 自动支持 |
