# 斗罗大陆手游 原生 APP 登录鉴权机制完整分析

> 基于反编译 APK (`dldl_apk_src`) 源码分析  
> 分析日期：2026-07-06

---

## 一、整体架构

```
┌─────────────────────────────────────────────────────────────────────┐
│                        登录请求完整链路                              │
│                                                                     │
│  H5 层 (Cocos) → SdkMgr.onLogin()                                  │
│    → SQwanCore.login()                                              │
│      → Platform.loginSQ()                                           │
│        → SQwanManager.login()                                       │
│          → AccountModImpl.login()                                   │
│            → AccountLogic 处理登录逻辑                               │
│              ├── 密码 AES 加密 (APP_KEY 前 16 位)                    │
│              ├── SignV3 签名 (参数排序 + MD5)                        │
│              └── D-Token 设备指纹（请求头）                          │
│                                                                     │
│  HTTP 请求 → s-api-secure.37.com.cn/sdk/login/                     │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 二、关键密钥清单

| 用途 | 密钥 | 位置 |
|------|------|------|
| **APP_KEY**（SDK 初始化） | `CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1` | `MainActivity.java:60` |
| **密码 AES 加密 Key** | `CR.wdPyFoanb6Thv`（APP_KEY 前 16 位） | `AccountLogic.java:1016-1041` |
| **设备指纹 AES 加密 Key** | `17d2ff30df8d2042` | `DevicesFingerprint.java:117` |
| **设备指纹 AES IV** | `17d2ff30df8d2042`（与 Key 相同） | `DevicesFingerprint.java:116` |
| **SignV3 硬编码密钥** | `!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw` | `SignInterceptor.java:37` |
| **GameID** | `1004620` | 通过 `AppkeyHelper` 映射匹配 |

---

## 三、密码加密机制

### 算法
```
AES/ECB/PKCS5Padding  →  Base64 编码
```

### 密钥来源

```java
// AccountLogic.java
private String getEncodeKey() {
    String appKey = ConfigManager.getInstance(this.context).getAppKey();
    // "CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1"
    int length = appKey.length();
    if (length < 16) {
        StringBuilder sb = new StringBuilder(appKey);
        for (int i = 0; i < 16 - length; i++) { sb.append("0"); }
        return sb.toString();
    }
    return appKey.substring(0, 16);
    // 返回："CR.wdPyFoanb6Thv"
}
```

### Python 等价实现

```python
import base64
from Crypto.Cipher import AES

def encrypt_password(plain_pwd):
    key = b"CR.wdPyFoanb6Thv"  # APP_KEY 前 16 字节
    # AES/ECB/PKCS5Padding
    cipher = AES.new(key, AES.MODE_ECB)
    # PKCS5 padding
    pad_len = 16 - len(plain_pwd) % 16
    padded = plain_pwd + chr(pad_len) * pad_len
    encrypted = cipher.encrypt(padded.encode())
    return base64.b64encode(encrypted).decode()
```

### 发送格式

登录请求中 `upwd` 参数值为 AES 加密 + Base64 的结果。

---

## 四、D-Token 设备指纹机制

### 流程概览

```
首次启动 / Token 过期
  → 收集 50+ 个设备属性
    → JSON 序列化 → GZIP 压缩 → AES/CBC/PKCS5Padding 加密 → Base64
      → POST http://afflatus.37.com.cn/afflatus/get_token/android
        → 服务器返回 token（有效期 3 天）
          → 存入 SharedPreferences
            → 后续请求 Header: D-Token: {token}
```

### 加密参数

```java
// DevicesFingerprint.java:114-129
Key = "17d2ff30df8d2042"  (16 bytes)
IV  = "17d2ff30df8d2042"  (16 bytes, 与 Key 相同)
算法 = AES/CBC/PKCS5Padding
```

### Token 生命周期

| 状态 | 时长 | 处理 |
|------|------|------|
| 有效 | 3 天内 | 直接使用 |
| 过期 | 3~5 天 | 仍可使用，同时异步更新 |
| 失效 | 超过 5 天 | 不发送 token，fallback 到加密设备信息 |

### 收集的设备属性（部分关键字段）

| 类别 | 字段 |
|------|------|
| 系统信息 | `os`, `os_version`, `country_code` |
| CPU | `cpu_core`, `cpu_abi`, `cpu_hardware` |
| Build | `fingerprint`, `board`, `bootloader`, `hardware`, `display`, `manufacturer`, `model` |
| APK | `apk_name`, `version`, `install_time` |
| 网络 | `ssid`, `bssid`, `ip`, `network_type`, `carrier` |
| 设备标识 | `d_oaid`, `d_android_id`, `d_imei`, `d_mac` |
| Root/模拟器 | `isRoot`, `e_qemu`, `e_cpu_abi`, `e_flavor` |
| Xposed | `xposed_bridge`, `tracer_pid` |
| 传感器 | `sensor_list` |
| 电池 | `battery_level`, `battery_status` |
| 屏幕 | `screen_brightness`, `display_metrics` |
| SIM | `sim` |
| 业务参数 | `gid`, `pid`, `dev`, `uid`, `server_id` |

---

## 五、SignV3 签名算法

### 硬编码密钥

```java
// SignInterceptor.java:37
this.mV4 = new SignV3Interceptor("!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw");
```

### 签名流程

```java
// SignV3Interceptor.java:77-92
1. 收集所有请求参数，按 key 字母排序
2. 拼接：key1=value1key2=value2...keyN=valueN
3. 末尾追加硬编码密钥
4. MD5(str).toLowerCase()
```

### Python 等价实现

```python
import hashlib

def sign_v3(params: dict) -> str:
    key = "!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw"
    sorted_keys = sorted(params.keys())
    raw = "".join(f"{k}={params[k]}" for k in sorted_keys)
    raw += key
    return hashlib.md5(raw.encode()).hexdigest().lower()
```

---

## 六、"dev" 设备标识

### 算法

```java
// DevLogic.java:50
dev = MD5(IMEI值 + MAC值 + AndroidID + 当前毫秒时间戳)
```

### 取值策略

| 值 | 获取方式 | 默认值 |
|----|---------|--------|
| IMEI | `TelephonyManager.getDeviceId()` | `999` + 14位随机数字 |
| MAC | `SensitiveInfoManager` | `020000000000` |
| AndroidID | `Settings.Secure.ANDROID_ID` | 系统返回 |

---

## 七、登录请求完整参数

```
POST https://s-api-secure.37.com.cn/sdk/login/

Headers:
  Content-Type: application/x-www-form-urlencoded
  D-Token: {设备指纹 token}

Body (SignV3 签名):
  uname:    {用户名}
  upwd:     {AES加密后的密码}
  signType: all
  gid:      1004620
  pid:      {平台 ID}
  dev:      {MD5(IMEI+MAC+AndroidID+timestamp)}
  sign:     {SignV3 签名结果}
  ...
```

---

## 八、与当前 h5sdk 方案对比

| 对比项 | 原生 SDK（反编译发现） | 当前 h5sdk 方案 |
|--------|----------------------|-----------------|
| 登录端点 | `s-api-secure.37.com.cn/sdk/login/` | `s-api.37.com.cn/h5sdk/login` |
| 密码处理 | AES/ECB + Base64（Key=APP_KEY 前16位） | AES/ECB（Key 不同） |
| 签名方式 | SignV3（硬编码密钥 `!/DIzcJLYE...`） | MD5 自定义签名 |
| 设备指纹 | D-Token（50+属性→GZIP→AES/CBC→服务器） | 无 |
| APP_KEY | `CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1` | 不同的 Key |
| 是否原生鉴权 | ✅ 是 | ❌ 模拟 |

---

## 九、实现原生登录需要的改造

### 1. 密码加密改造
- 替换 AES Key 为 `CR.wdPyFoanb6Thv`（APP_KEY 前 16 位）
- 保持 AES/ECB/PKCS5Padding + Base64 不变

### 2. 切换登录接口
- 从 `s-api.37.com.cn/h5sdk/login` 切换到 `s-api-secure.37.com.cn/sdk/login/`

### 3. SignV3 签名
- 实现参数 key 排序 + MD5 签名
- 使用硬编码密钥 `!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw`

### 4. D-Token 设备指纹（可选但关键）
- 收集设备属性（Web 端能获取的有限）
- GZIP 压缩 → AES/CBC 加密 → 发送到 `afflatus.37.com.cn`
- 保存返回的 token，后续请求 Header 携带

### 5. "dev" 标识
- 在 Web 环境下生成模拟的 IMEI/MAC/AndroidID
- 计算 MD5 作为 dev 值

---

## 十、Web 端的局限性

| 原生能力 | Web 端限制 |
|----------|-----------|
| 50+ 设备属性采集 | 浏览器沙箱限制，只能获取基础信息（UA、屏幕、语言等） |
| IMEI/MAC/AndroidID | 完全不可用，只能模拟随机值 |
| SSID/BSSID | 需要用户授权，部分浏览器不支持 |
| 传感器列表 | 部分可通过 `Sensor API` 获取 |
| 签名计算 | ✅ 完全可模拟 |

**结论**：Web 端可以模拟接近 80% 的原生登录参数，但设备指纹完整度有限。关键突破口在于 **AES 密码加密 + SignV3 签名**——这两项完全可以精确还原。

---

## 十一、还原原生登录缺少的核心条件

> 当前 h5sdk 方案（cloudflare-worker.js）**已有部分**原生登录逻辑，
> 但与完整原生 APP 登录相比仍有关键缺口。

### 与当前 Worker 实现的详细对比

| 环节 | 原生 APP | 当前 Worker (`sdkLogin`) | 缺口 |
|------|---------|-------------------------|------|
| 登录端点 | `https://s-api-secure.37.com.cn/sdk/login/` | `http://s-api.37.com.cn/sdk/login/` | ❌ 不同域名 |
| SignV3 签名密钥 | `!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw` | `APP_KEY`（`CR.wdPyFoanb6Thv...`） | ❌ 不同密钥 |
| 密码加密 | AES/ECB + `CR.wdPyFoanb6Thv` | AES/ECB + `CR.wdPyFoanb6Thv` | ✅ 一致 |
| D-Token | 请求头携带设备指纹 Token | **无** | ❌ **完全缺失** |
| GameID | `1004620` | `1002997`（SDK_GID） | ⚠️ 待确认 |

### 核心缺失一：D-Token 设备指纹 Token（最关键）

D-Token 是原生 APP 登录**独有的鉴权维度**，当前系统中完全没有实现。

#### 获取流程

```
收集 50+ 设备属性（JSON）
  → GZIP 压缩（java.util.zip.GZIPOutputStream）
    → AES/CBC/PKCS5Padding 加密（Key=IV="17d2ff30df8d2042"）
      → Base64 编码
        → POST http://afflatus.37.com.cn/afflatus/get_token/android
          → 服务器返回 Token（有效期 3 天）
            → 缓存 Token
              → 登录请求 Header: D-Token: {token}
```

#### 需要模拟的设备属性（在 Worker 端）

Worker 运行在 Cloudflare 边缘节点，能模拟的字段有限：

| 类别 | 可模拟程度 | 说明 |
|------|-----------|------|
| 系统信息（os, os_version, country_code 等） | ✅ 完全可模拟 | 硬编码 Android 设备信息即可 |
| CPU / Build 信息 | ✅ 完全可模拟 | 伪造一套真实 Android 设备的 build.prop 值 |
| APK 信息（apk_name, version, install_time） | ✅ 完全可模拟 | 包里名 `com.m37.dldlsy.sy37`，版本从反编译获取 |
| 设备标识（IMEI, MAC, AndroidID） | ✅ 可随机生成 | 原生默认策略就是在拿不到时生成随机值 |
| Root/模拟器/ Xposed | ✅ 硬编码 `0` | 伪装成正常设备 |
| 传感器列表 | ✅ 可硬编码 | 复制一份真实 Android 设备传感器列表 |
| 电池/屏幕 | ✅ 可硬编码 | 静态值 |
| 网络（ssid, bssid, ip） | ⚠️ 部分可模拟 | Worker 出口 IP 可变，WiFi 信息难以伪造 |
| 业务参数（gid, pid, dev, uid） | ✅ 可模拟 | 与登录请求参数保持一致 |

**关键结论**：Worker 端可以模拟 90%+ 的设备属性，因为原生 APP 本身的 fallback 机制也是用随机/默认值填充。**但实际能否通过 `afflatus` 服务端的校验，必须发包测试才能确认。**

#### 不确定因素

- `afflatus.37.com.cn` 端点是否仍在运行、是否已迁移
- 设备属性 JSON 的精确结构（字段名、嵌套层级）未从反编译中完整还原
- 服务端是否有额外的防伪造检测（如 IP 归属地、请求频率等）

### 核心缺失二：s-api-secure 端点连通性

原生 APP 以**明文 form-urlencoded** 方式直接 POST 到 `s-api-secure.37.com.cn/sdk/login/`，

当前 Worker 中虽然已有 `SECURE_BASE` 常量（用于 Gateway 加密通道的 `get-url`），但尚未验证：

- Worker 节点能否以**非 Gateway 加密**方式访问 `s-api-secure` 上的 `/sdk/login/`
- 服务端是否强制要求 D-Token 头（无 D-Token 是否直接拒绝）

### 核心缺失三：SignV3 签名密钥切换

原生 APP 中 `SignInterceptor.java:37` 使用硬编码密钥 `!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw`，

当前 Worker 的 `sdkLogin()` 使用 `signV3(params, APP_KEY)`。

**需要确认**：`s-api-secure` 端点上的 `/sdk/login/` 接受哪个密钥签名的请求（可能是硬编码密钥，而非 APP_KEY）。

### 核心缺失四：GameID 确认

| 来源 | GameID | 用途 |
|------|--------|------|
| 分析文档（AppkeyHelper 映射） | `1004620` | 原生 APP 登录的 `gid` 参数 |
| 当前 Worker（SDK_GID） | `1002997` | 当前 sdkLogin 使用的 `gid` |
| 当前 Worker（GAME_GID） | `1003279` | 游戏入口 URL 使用的 `gid` |

原生登录时 `gid` 用哪个值，需要通过实际发包确认。

### 实现优先级建议

| 优先级 | 事项 | 依赖 | 风险 |
|--------|------|------|------|
| **P0** | 实现 D-Token 获取流程（模拟设备属性 → afflatus → 缓存 Token） | 需实测 afflatus 端点 | 高（端点可能不可用） |
| **P0** | 切换 SignV3 密钥为硬编码密钥 | 无 | 低（纯代码改动） |
| **P1** | 切换登录端点为 `s-api-secure` | 需实测连通性 | 中（可能有 IP 白名单） |
| **P2** | 确认并修正 GameID | 需实测 | 低 |

### 总结

| 类型 | 内容 |
|------|------|
| ✅ **已具备** | 所有算法（AES、SignV3、GZIP）、所有密钥、设备属性字段清单 |
| ⚠️ **可推断但需验证** | 设备属性 JSON 精确结构、签名密钥选哪个、GameID 用哪个 |
| ❌ **必须实测** | `afflatus.37.com.cn` 端点可用性、`s-api-secure` 直连连通性、D-Token 是否被服务端强制校验 |
| 🎯 **突破口** | 先用真实账号在 Worker 端构造一次完整原生登录请求发包，观察服务端返回的 `state` 和 `msg`，即可判断哪些环节是必须的 |

---

## 附：关键源码文件索引

| 文件 | 内容 |
|------|------|
| `MainActivity.java:60` | APP_KEY 硬编码值 |
| `AccountLogic.java:1005-1041` | 密码 AES 加密 + Key 获取 |
| `SignV3Interceptor.java:77-92` | SignV3 签名算法 |
| `SignInterceptor.java:37` | SignV3 硬编码密钥 |
| `DevicesFingerprint.java:114-151` | 设备指纹 AES 加密 + Token 管理 |
| `Detector.java:81-232` | 50+ 设备属性采集 |
| `DevLogic.java:50` | "dev" 值计算 |
| `LoginRequestManager.java:18` | D-Token 请求头使用 |
| `AppkeyHelper.java:20-36` | GameID → APP_KEY 映射表 |
| `AccountRequestManager.java:42-48` | 登录请求发送 |
