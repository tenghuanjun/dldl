use std::io::{BufRead, BufReader, ErrorKind, Write};
use std::path::{Path, PathBuf};
use std::process::{Child, Command, Stdio};
use std::sync::Mutex;

use anyhow::Context;
use tauri::webview::{NewWindowFeatures, NewWindowResponse};
use tauri::{App, AppHandle, Manager, RunEvent, Runtime, WebviewUrl, WebviewWindowBuilder};
use url::Url;

struct ProxyChild {
    inner: Mutex<Option<Child>>,
}

fn resolve_static_root(app: &AppHandle) -> PathBuf {
    if cfg!(debug_assertions) {
        let manifest = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
        manifest
            .join("..")
            .canonicalize()
            .unwrap_or_else(|_| manifest.join(".."))
    } else if let Ok(dir) = app.path().resource_dir() {
        dir.join("app")
    } else {
        PathBuf::from(".")
    }
}

fn resolve_user_data(app: &AppHandle) -> PathBuf {
    app.path()
        .app_data_dir()
        .expect("app_data_dir")
        .join("dldl-proxy")
}

fn resolve_port() -> u16 {
    std::env::var("DLDL_PORT")
        .ok()
        .and_then(|s| s.parse().ok())
        .unwrap_or(8080)
}

/// 在 `base/<version>/rel` 中选字典序最后一项（通常对应较新的 Node 版本）。
fn newest_versioned_child_file(base: &Path, rel: &str) -> Option<PathBuf> {
    let mut dirs: Vec<PathBuf> = std::fs::read_dir(base)
        .ok()?
        .filter_map(|e| e.ok())
        .filter(|e| e.file_type().map(|t| t.is_dir()).unwrap_or(false))
        .map(|e| e.path())
        .collect();
    dirs.sort();
    for d in dirs.into_iter().rev() {
        let cand = d.join(rel);
        if cand.is_file() {
            return Some(cand);
        }
    }
    None
}

/// 从访达启动时没有 shell 注入的 PATH；补充常见 nvm / fnm / mise / Volta 路径。
#[cfg(target_os = "macos")]
fn discover_node_in_user_dirs() -> Option<PathBuf> {
    let home = std::env::var_os("HOME").map(PathBuf::from)?;
    let volta = home.join(".volta/bin/node");
    if volta.is_file() {
        return Some(volta);
    }
    if let Some(p) = newest_versioned_child_file(&home.join(".nvm/versions/node"), "bin/node") {
        return Some(p);
    }
    let fnm_root = home.join(".local/share/fnm/node-versions");
    if let Some(p) = newest_versioned_child_file(&fnm_root, "installation/bin/node") {
        return Some(p);
    }
    if let Some(p) = newest_versioned_child_file(&home.join(".local/share/mise/installs/node"), "bin/node") {
        return Some(p);
    }
    None
}

#[cfg(not(target_os = "macos"))]
fn discover_node_in_user_dirs() -> Option<PathBuf> {
    None
}

fn resolve_node_binary(app: &AppHandle) -> PathBuf {
    if let Ok(p) = std::env::var("DLDL_NODE_PATH") {
        return PathBuf::from(p);
    }
    if !cfg!(debug_assertions) {
        if let Ok(dir) = app.path().resource_dir() {
            // 与 tauri.conf.json 中 `_embed/nodejs` -> `app/nodejs` 对应（beforeBuildCommand 生成）
            let nix = dir.join("app/nodejs/bin/node");
            if nix.is_file() {
                return nix;
            }
            #[cfg(windows)]
            {
                let win = dir.join("app/nodejs/node.exe");
                if win.is_file() {
                    return win;
                }
            }
        }
    }
    // 从 Finder / Dock 启动时 PATH 往往不含 Homebrew，裸用 "node" 会找不到。
    #[cfg(target_os = "macos")]
    for p in ["/opt/homebrew/bin/node", "/usr/local/bin/node"] {
        let pb = PathBuf::from(p);
        if pb.is_file() {
            return pb;
        }
    }
    #[cfg(target_os = "macos")]
    if let Some(p) = discover_node_in_user_dirs() {
        return p;
    }
    PathBuf::from("node")
}

fn show_startup_error(message: &str) {
    let _ = rfd::MessageDialog::new()
        .set_title("DLDL-Proxy")
        .set_description(message)
        .set_level(rfd::MessageLevel::Error)
        .show();
}

fn spawn_proxy(
    app: &AppHandle,
    static_root: &PathBuf,
    user_data: &PathBuf,
    port: u16,
) -> anyhow::Result<Child> {
    let proxy_js = static_root.join("proxy.js");
    anyhow::ensure!(
        proxy_js.is_file(),
        "未找到代理脚本: {}",
        proxy_js.display()
    );
    let node = resolve_node_binary(app);
    let node_is_bare = node.as_os_str() == std::ffi::OsStr::new("node");
    if !node_is_bare && !node.is_file() {
        anyhow::bail!(
            "未找到 Node 可执行文件: {}。请安装 Node，或将 DLDL_NODE_PATH 设为 node 的绝对路径。",
            node.display()
        );
    }
    let mut cmd = Command::new(&node);
    cmd.stdin(Stdio::null())
        .current_dir(static_root)
        .env("DLDL_USER_DATA", user_data)
        .env("DLDL_STATIC_ROOT", static_root)
        .env("DLDL_PORT", port.to_string())
        .arg(&proxy_js);
    // 从访达启动时没有终端：子进程 inherit 父进程的 stdout/stderr 时，写入可能阻塞在满管道上，
    // Node 大量 console.log 会导致代理进程卡住，表现为整个应用「卡死」。开发模式仍继承终端便于调试。
    if cfg!(debug_assertions) {
        cmd.stdout(Stdio::inherit()).stderr(Stdio::inherit());
    } else {
        cmd.stdout(Stdio::null()).stderr(Stdio::null());
    }
    match cmd.spawn() {
        Ok(child) => Ok(child),
        Err(e) if e.kind() == ErrorKind::NotFound => {
            anyhow::bail!(
                "无法启动 Node 代理：系统找不到可执行文件「{}」。\n\
                 从访达或程序坞启动时，PATH 通常不含终端里的 Node（若使用 nvm、fnm、mise 等更容易出现）。\n\
                 请将 Node 安装到 /opt/homebrew/bin 或 /usr/local/bin，或设置环境变量 DLDL_NODE_PATH，例如：\n\
                 export DLDL_NODE_PATH=\"$(which node)\"\n\
                 open -a \"DLDL-Proxy\"\n\
                 \n\
                 （排查）node={:?}，代理脚本={}",
                node.display(),
                node,
                proxy_js.display()
            );
        }
        Err(e) => Err(e).with_context(|| {
            format!(
                "无法启动 Node 代理；node={:?} proxy={:?}",
                node, proxy_js
            )
        }),
    }
}

/// 等待 Node 侧 `GET /__dldl_ready` 返回 200（SQLite 等初始化完成），避免仅 TCP 连通但页面 503。
fn http_get_status_line(port: u16, path: &str) -> Option<String> {
    let addr = std::net::SocketAddr::from(([127, 0, 0, 1], port));
    let mut stream = std::net::TcpStream::connect_timeout(&addr, std::time::Duration::from_millis(500))
        .ok()?;
    let _ = stream.set_read_timeout(Some(std::time::Duration::from_secs(2)));
    let req = format!(
        "GET {path} HTTP/1.1\r\nHost: 127.0.0.1\r\nConnection: close\r\nAccept: */*\r\n\r\n"
    );
    stream.write_all(req.as_bytes()).ok()?;
    let mut reader = BufReader::new(stream);
    let mut line = String::new();
    reader.read_line(&mut line).ok()?;
    Some(line)
}

fn parse_http_status(first_line: &str) -> Option<u16> {
    let mut parts = first_line.split_whitespace();
    let _http = parts.next()?;
    let code = parts.next()?.parse().ok()?;
    Some(code)
}

fn wait_for_proxy_ready(port: u16, attempts: u32) -> anyhow::Result<()> {
    for i in 0..attempts {
        if let Some(line) = http_get_status_line(port, "/__dldl_ready") {
            if parse_http_status(&line) == Some(200) {
                return Ok(());
            }
        }
        if i + 1 == attempts {
            break;
        }
        std::thread::sleep(std::time::Duration::from_millis(120));
    }
    anyhow::bail!("等待本地代理就绪 127.0.0.1:{port}/__dldl_ready 超时（SQLite 初始化过慢或 Node 启动失败）");
}

fn allow_popup_url(u: &Url) -> bool {
    matches!(u.scheme(), "http" | "https" | "about")
}

/// `window.open` 策略：仅允许 http(s)/about。
///
/// 使用 [`NewWindowResponse::Allow`] 交给 Wry/WebKit 按系统方式创建弹窗，而不是
/// [`NewWindowResponse::Create`] 自建 `WebviewWindow`。后者在部分版本/时机下与
/// Wry 桥接时可能在运行时 `unwrap` 失败，导致**整进程闪退**；批量打开扫码页时
/// 连续触发，更容易复现。
fn popup_window_policy<R: Runtime>(
    url: Url,
    _features: NewWindowFeatures,
) -> NewWindowResponse<R> {
    if allow_popup_url(&url) {
        NewWindowResponse::Allow
    } else {
        NewWindowResponse::Deny
    }
}


fn append_startup_log(handle: &AppHandle, msg: &str) {
    let Ok(base) = handle.path().app_data_dir() else {
        return;
    };
    let log = base.join("dldl-proxy").join("startup.log");
    let Some(parent) = log.parent() else {
        return;
    };
    let _ = std::fs::create_dir_all(parent);
    if let Ok(mut f) = std::fs::OpenOptions::new()
        .create(true)
        .append(true)
        .open(&log)
    {
        let ts = std::time::SystemTime::now()
            .duration_since(std::time::UNIX_EPOCH)
            .map(|d| d.as_secs())
            .unwrap_or(0);
        let line: String = msg.chars().map(|c| if c == '\n' { ' ' } else { c }).collect();
        let _ = writeln!(f, "{ts}\t{line}");
    }
}

fn try_setup(app: &mut App) -> anyhow::Result<()> {
    let app_handle = app.handle().clone();
    append_startup_log(&app_handle, "try_setup 开始");
    let static_root = resolve_static_root(&app_handle);
    let user_data = resolve_user_data(&app_handle);
    append_startup_log(
        &app_handle,
        &format!(
            "static_root={} user_data={}",
            static_root.display(),
            user_data.display()
        ),
    );
    std::fs::create_dir_all(&user_data)?;
    let port = resolve_port();
    let mut child = spawn_proxy(&app_handle, &static_root, &user_data, port)?;
    append_startup_log(&app_handle, "已 spawn Node 子进程，等待 /__dldl_ready");
    if let Err(e) = wait_for_proxy_ready(port, 500) {
        append_startup_log(&app_handle, &format!("等待代理就绪失败: {e:#}"));
        let _ = child.kill();
        let _ = child.wait();
        return Err(e);
    }
    app.manage(ProxyChild {
        inner: Mutex::new(Some(child)),
    });
    let account_url = Url::parse(&format!("http://127.0.0.1:{port}/account.html"))?;
    let _main = WebviewWindowBuilder::new(&app_handle, "main", WebviewUrl::External(account_url))
        .title("DLDL-Proxy")
        .inner_size(1280.0, 800.0)
        .min_inner_size(800.0, 600.0)
        .on_new_window(|url, features| popup_window_policy(url, features))
        .build()
        .map_err(|e| {
            append_startup_log(&app_handle, &format!("创建主窗口失败: {e}"));
            e
        })?;
    
    append_startup_log(&app_handle, "主窗口已创建");
    Ok(())
}

#[cfg_attr(mobile, tauri::mobile_entry_point)]
pub fn run() {
    tauri::Builder::default()
        .plugin(tauri_plugin_single_instance::init(|app, _argv, _cwd| {
            if let Some(w) = app.get_webview_window("main") {
                let _ = w.unminimize();
                let _ = w.set_focus();
            }
        }))
        .setup(|app| {
            if let Err(e) = try_setup(app) {
                let handle = app.handle().clone();
                append_startup_log(&handle, &format!("启动失败: {e:#}"));
                let msg = format!("{:#}\n\n若终端里能运行 node，可在终端执行：\nexport DLDL_NODE_PATH=\"$(which node)\"\nopen -a \"DLDL-Proxy\"\n\n启动日志（便于排查）：\n~/Library/Application Support/com.dldl.localproxy/dldl-proxy/startup.log", e);
                eprintln!("DLDL-Proxy 启动失败: {msg}");
                show_startup_error(&msg);
                std::process::exit(1);
            }
            Ok(())
        })
        .build(tauri::generate_context!())
        .expect("error while building tauri application")
        .run(|handle, event| {
            match event {
                RunEvent::Exit => {
                    if let Some(state) = handle.try_state::<ProxyChild>() {
                        if let Ok(mut g) = state.inner.lock() {
                            if let Some(mut c) = g.take() {
                                let _ = c.kill();
                                let _ = c.wait();
                            }
                        }
                    }
                }
                _ => {}
            }
        });
}
