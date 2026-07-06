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
