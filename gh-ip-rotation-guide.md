# GitHub Actions IP 轮换方案 — 从 0 到 1 实现文档

## 一、背景与痛点

### 项目简介

`dldl` 是一个游戏账号代管代理服务，基于 Electron 桌面应用 + 本地 Express 代理服务器。用户通过 `account.html` 管理多账号，后台 `proxy.js` 作为中间代理拦截和转发请求到 `dldl.50pk.com`。

核心登录流程需要调用 `s-api.37.com.cn/h5sdk/login` 接口获取 token。

### 原始问题

```
本地 IP 直连 s-api.37.com.cn 获取 token
  → 频繁调用触发反爬/频率限制
  → 账号被临时封禁
  → 无法正常登录
```

### V1 方案：免费代理池（失败）

`proxy-pool.js` 从 `free-proxy-list.net` / `proxyscrape.com` 抓取免费代理，验证可用后做轮换。

**失败原因**：
- 免费代理存活率极低（<5%）
- 大部分已被目标站点标记/封禁
- 延迟极高，频繁超时
- 本质不可用

---

## 二、V2 方案：GitHub Actions IP 轮换（当前）

### 核心洞察

**GitHub Actions 的 Runner 运行在微软 Azure 虚拟机上，每次 Job 启动都会分配一个全新的 VM，出口 IP 从 Azure 庞大的 IP 池中随机分配。** 这些是正规数据中心 IP，目标服务器极难封禁。

### 整体架构

```
┌─────────────────────────────────────────────────────────┐
│                    GitHub Actions                        │
│                                                         │
│  每 5 分钟 cron 保活          用户触发 workflow_dispatch │
│       │                              │                  │
│       ▼                              ▼                  │
│  ┌─────────────────────────────────────────┐           │
│  │  Azure VM (ubuntu-latest)               │           │
│  │  全新出口 IP: 20.106.x.x                │           │
│  │  ├─ node token-fetcher-runner.js        │           │
│  │  │  ├─ 获取 Runner IP (api.ipify.org)   │           │
│  │  │  ├─ 调用 s-api.37.com.cn/h5sdk/login │           │
│  │  │  └─ 结果写入 GitHub Gist              │           │
│  └─────────────────────────────────────────┘           │
│                    │                                     │
└────────────────────┼────────────────────────────────────┘
                     │ 写入
                     ▼
          ┌──────────────────┐
          │   GitHub Gist    │  ← 跨 Runner/本地 共享存储
          │ dldl-tokens.json │
          │ {                │
          │   tokens: {...}, │
          │   runnerIP: "...",│
          │   updatedAt: "..."│
          │ }                │
          └──────────────────┘
                     │
                     │ 读取
                     ▼
┌─────────────────────────────────────────────────────────┐
│                    本地 proxy.js                         │
│                                                         │
│  gh-relay.js ─→ 读 Gist 获取最新 token                   │
│       │                                                 │
│       ├─ token 在 5 分钟内 → 缓存命中，直接用             │
│       └─ token 过期 → 触发 workflow_dispatch 获取新 token │
│                                                         │
│  GET /gh-relay/cached-token?uname=xxx                    │
│  POST /gh-relay/request-token  { uname, upwd }           │
│  POST /proxy-pool/switch  (切换 IP 按钮)                  │
└─────────────────────────────────────────────────────────┘
```

### 数据流（三次握手）

```
Step 1: 用户打开 login.php → proxy.js 检查 Gist 缓存
         └─ 命中（5分钟内）→ 直接用 ✅
         └─ 过期 → 进入 Step 2

Step 2: proxy.js 调用 ghRelay.requestToken(uname, upwd)
         ├─ writePending(uname, upwd) → 写入 Gist pending 队列
         ├─ 轮询 waitForToken(uname, 最多 130秒)
         └─ 检测到 tokens[uname] 有新数据 → 返回 ✅

Step 3: GitHub Actions 保活 cron（每 5 分钟）
         Runner 分配新 Azure IP
         ├─ 读 Gist → 更新 runnerIP + updatedAt
         └─ 不调用 API（保活模式不消耗配额）
```

---

## 三、涉及文件清单

### 新建文件（4 个）

| 文件 | 作用 | 位置 |
|------|------|------|
| `.github/workflows/token-fetcher.yml` | GitHub Actions 工作流定义 | 仓库根目录 |
| `token-fetcher-runner.js` | Runner 上执行的脚本（获取 token + 写 Gist） | 仓库根目录 |
| `gh-relay.js` | 本地 Gist 读写客户端（NPM 模块） | 仓库根目录 |
| `gh-ip-rotation-guide.md` | 本文档 | 仓库根目录 |

### 修改文件（1 个）

| 文件 | 修改内容 |
|------|----------|
| `proxy.js` | 引入 `gh-relay.js`，新增 6 个 API 端点，token 获取优先 GH-Relay |

### 未修改文件

`proxy-pool.js`、`electron-main.js`、`config.js`、`account.html` 等保持原有逻辑不变，作为兜底方案。

---

## 四、各文件详解

### 4.1 `.github/workflows/token-fetcher.yml`

```yaml
name: Token Relay (IP Rotator)

on:
  schedule:
    - cron: '*/5 * * * *'      # 每 5 分钟保活
  workflow_dispatch:            # 手动/API 触发（按需）
    inputs:
      uname:     { required: true }
      upwd:      { required: true }
      request_id: { required: false }

jobs:
  fetch-token:
    runs-on: ubuntu-latest     # ← Azure VM，每次全新 IP
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-node@v4
        with: { node-version: '22' }
      - name: Fetch token via fresh Azure IP
        env:
          INPUT_UNAME: ${{ github.event.inputs.uname }}
          INPUT_UPWD: ${{ github.event.inputs.upwd }}
          INPUT_REQUEST_ID: ${{ github.event.inputs.request_id }}
          GITHUB_EVENT_NAME: ${{ github.event_name }}
          GH_GIST_TOKEN: ${{ secrets.GH_GIST_TOKEN }}
          GH_GIST_ID: ${{ secrets.GH_GIST_ID }}
        run: node token-fetcher-runner.js
```

**关键点**：
- `runs-on: ubuntu-latest` 保证每次都是不同的 Azure VM
- `schedule` + `workflow_dispatch` 双触发模式
- Secrets 通过 `${{ secrets.XXX }}` 注入环境变量，不会暴露在日志中

### 4.2 `token-fetcher-runner.js`

在 GitHub Actions Runner 内执行的独立 Node.js 脚本。

**两种运行模式**：

```
触发类型          模式       行为
───────────────────────────────────────────
schedule (cron)   保活模式   仅获取 Runner IP + 写入 Gist，不调 API
workflow_dispatch  按需模式   调用 h5sdk/login 获取真实 token
```

**核心函数链**：

```
main()
 ├─ httpsGet('https://api.ipify.org')        → 获取当前 Azure IP
 ├─ 判断 eventName
 │
 ├─ [保活] schedule 触发
 │   ├─ readGist()                           → GET /gists/:id
 │   └─ updateGist({ tokens, pending, runnerIP, updatedAt })
 │
 └─ [按需] workflow_dispatch 触发
     ├─ h5sdkSign(params, apiKey)            → MD5 签名
     ├─ httpsGet('s-api.37.com.cn/h5sdk/login?params')
     ├─ 解析 JSONP 响应 → 提取 token
     ├─ readGist()                           → 读取已有 tokens
     ├─ 合并 tokens[uname] = newEntry
     └─ updateGist({ tokens, runnerIP, updatedAt })
```

**签名算法**（与 `config.js` 一致）：
```javascript
function h5sdkSign(params, apiKey) {
  const sorted = Object.keys(params).sort();
  let str = '';
  for (const k of sorted) str += k + '=' + params[k];
  return md5(str + apiKey);  // apiKey = 'Jp*4Y8vQOYck2*&Z'
}
```

**Gist PATCH API 正确格式**：
```javascript
// ❌ 错误（之前踩过的坑）
JSON.stringify({ tokens: {...}, runnerIP: "..." })
// GitHub 收到后不认识，静默忽略，返回 200 但什么都没更新！

// ✅ 正确
JSON.stringify({
  files: {
    'dldl-tokens.json': { content: JSON.stringify(data) }
  }
})
```

### 4.3 `gh-relay.js`

本地运行的 Node.js 模块，提供 Gist 读写封装。

**设计原则**：
- 双通道读取：优先 Gist API（需 token），兜底 Raw URL（无需认证）
- 本地缓存：`getToken(uname)` 返回 5 分钟内的 token，过期自动失效
- 按需请求：`requestToken(uname, upwd)` 写入 pending → 轮询等待 → 返回 token

**API 接口概览**：

| 方法 | 说明 |
|------|------|
| `getLatest()` | 读 Gist API / Raw URL，返回完整数据 |
| `getToken(uname)` | 读指定账号 token（含 5 分钟过期判断） |
| `writePending(uname, upwd)` | 写入 pending 队列 |
| `waitForToken(uname, 130s)` | 轮询等待 token 出现 |
| `requestToken(uname, upwd)` | writePending + waitForToken 的封装 |
| `switchIP()` | 返回当前 runner IP 信息（供按钮展示） |

### 4.4 `proxy.js`（修改点）

#### 新增引用
```javascript
const { GHActionsRelay } = require('./gh-relay');
```

#### 新增常量
```javascript
const TOKEN_MODE = process.env.DLDL_TOKEN_MODE || 'relay';
// "relay" → GitHub Actions（默认）
// "proxy-pool" → 免费代理池（兜底）
// "direct" → 直连
```

#### 新增初始化
```javascript
const ghRelay = new GHActionsRelay({
  gistId: process.env.DLDL_GIST_ID,
  gistToken: process.env.DLDL_GIST_TOKEN,
  rawUrl: process.env.DLDL_RAW_CACHE_URL,
  timeout: 5000
});
```

#### 新增 6 个 API 端点

| 端点 | 方法 | 作用 |
|------|------|------|
| `/gh-relay/status` | GET | 查看 relay 状态 |
| `/gh-relay/refresh` | POST | 手动刷新 Gist 缓存 |
| `/gh-relay/cached-token?uname=xxx` | GET | 仅读缓存，不触发请求（快速） |
| `/gh-relay/request-token` | POST | 写 pending + 轮询等 token（完整流程） |
| `/proxy-pool/switch` | POST | 统一 IP 切换入口（GH-Relay → 代理池 → 直连） |
| `/api/token/refresh` | POST | 刷新指定账号 token |

#### Token 获取优先级

```
fetchH5sdkLoginData(uname, upwd)
  │
  ├─ 优先级 1: GH-Relay（从 Gist 读缓存 token）
  │   └─ 失败 ↓
  │
  ├─ 优先级 2: 代理池直连 h5sdk/login
  │   └─ 失败 ↓
  │
  └─ 优先级 3: 抛异常
```

---

## 五、踩坑记录与修复

| # | 问题 | 现象 | 根因 | 修复 |
|---|------|------|------|------|
| 1 | YAML 语法错误 | Workflow 无法保存 | `.yml` 中出现两个 `env:` 块 | 删除重复块 |
| 2 | `shell: node` 已废弃 | Workflow 运行失败 | GitHub Actions 新版不再支持内联 JS | 改为 `run: node token-fetcher-runner.js` |
| 3 | 函数定义顺序 | Runner 脚本报错 `readGist is not defined` | `readGist`/`updateGist` 定义在调用之后 | 提前函数定义 |
| 4 | Secrets 名称不匹配 | `GH_GIST_TOKEN 已配置: false` | Secrets 页面命名为 `GIST_ID` / `GH_PAT`，YAML 引用的是 `GH_GIST_ID` / `GH_GIST_TOKEN` | 用户在 Settings 里重命名 |
| 5 | 保活覆盖 pending | 按需请求的 pending 队列被保活冲掉 | 保活模式写入时未保留 `pending` 字段 | `updateGist` 中加入 `pending: existing.pending \|\| {}` |
| 6 | **核心 BUG**: `readGist()` 不检查 HTTP 状态码 | Gist 永远是 `{}`，但日志无报错 | 401/403/404 也静默返回 `{}` | 加 `if (statusCode !== 200)` 判断 + 日志 |
| 7 | **核心 BUG**: 保活路径无 try-catch | `updateGist` 失败导致未捕获异常，workflow 默默 crash | 异常未处理 | 包裹 try-catch |
| 8 | **核心 BUG**: `updateGist()` API 格式错误 | 日志显示 "✅ Gist 更新成功" 但数据从未写入 | 请求体缺少 `files` 包装，GitHub 收到后忽略所有字段 | 改为 `{ files: { 'dldl-tokens.json': { content: "..." } } }` |

**最关键的 Bug 是 #8**：GitHub Gist PATCH API 要求请求体必须包含 `files` 字段，否则即便返回 200 也不会更新任何内容。这个问题导致所有前期测试都「看起来成功」但实际无效。

---

## 六、部署步骤

### Step 1: 创建 GitHub Gist

1. 打开 https://gist.github.com/
2. 创建新 Gist，文件名 `dldl-tokens.json`，内容 `{}`
3. 记录 Gist ID（URL 最后那段）：`0c324d942cb0414e69bd9fe6c263ecf0`

### Step 2: 创建 GitHub Personal Access Token (PAT)

1. GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Generate new token，勾选 **`gist`** 权限
3. 记录 token（格式 `ghp_xxxx...`）

### Step 3: 配置 GitHub Actions Secrets

仓库 Settings → Secrets and variables → Actions → New repository secret：

| Name | Value |
|------|-------|
| `GH_GIST_ID` | Step 1 的 Gist ID |
| `GH_GIST_TOKEN` | Step 2 的 PAT |

命名必须严格匹配，区分大小写。

### Step 4: 本地启动

```bash
cd /Users/tiffany/dldl
npm install

# 方式 A: Electron 桌面应用（推荐）
export DLDL_GIST_ID=0c324d942cb0414e69bd9fe6c263ecf0
export DLDL_GIST_TOKEN=ghp_你的PAT
npm start

# 方式 B: 纯命令行代理
export DLDL_GIST_ID=0c324d942cb0414e69bd9fe6c263ecf0
export DLDL_GIST_TOKEN=ghp_你的PAT
node proxy.js
```

---

## 七、环境变量汇总

| 变量 | 必需 | 说明 | 默认值 |
|------|:---:|------|--------|
| `DLDL_GIST_ID` | Relay 模式必需 | GitHub Gist ID | 无 |
| `DLDL_GIST_TOKEN` | Relay 模式必需 | GitHub PAT（含 `gist` scope） | 无 |
| `DLDL_RAW_CACHE_URL` | 可选 | Gist Raw URL（免认证读取） | 无 |
| `DLDL_TOKEN_MODE` | 可选 | `relay` / `proxy-pool` / `direct` | `relay` |
| `DLDL_PORT` | 可选 | 代理端口 | `8080` |
| `DLDL_UNAME` | 可选 | 默认账号 | 无 |
| `DLDL_UPWD` | 可选 | 默认密码 | 无 |

---

## 八、成本分析

| 项目 | 公开仓库 (Public) | 私有仓库 (Private) |
|------|:---:|:---:|
| Actions 分钟数 | **无限** | 2,000 分钟/月 |
| 保活 cron (每 5 分钟) | 免费 | ~2,160 分钟/月 → **超限** |
| Gist 存储 | 免费 | 免费 |
| API 限流 | 5,000 次/小时 | 5,000 次/小时 |

**结论**：只要仓库保持公开，此方案零成本永久可用。如需改为私有，建议将保活间隔调大到 10-15 分钟。

---

## 九、架构原则总结

```
┌──────────────────────────────────────────┐
│           单一职责                        │
│  token-fetcher-runner.js: 只管获取+写入   │
│  gh-relay.js: 只管本地读写封装             │
│  proxy.js: 只管代理+拦截+路由              │
├──────────────────────────────────────────┤
│           降级兜底                        │
│  GH-Relay 失败 → 代理池                   │
│  代理池失败 → 直连                        │
│  绝不因单一环节故障导致整体不可用            │
├──────────────────────────────────────────┤
│           读写分离                        │
│  写入：GitHub Actions Runner (Azure IP)   │
│  读取：本地 proxy.js (无 IP 消耗)          │
│  共享：GitHub Gist (免费 KV 存储)          │
└──────────────────────────────────────────┘
```
