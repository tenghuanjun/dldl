use std::path::PathBuf;
use std::process::{Child, Command, Stdio};
use std::sync::atomic::{AtomicU64, Ordering};
use std::sync::Mutex;

use anyhow::Context;
use tauri::webview::{NewWindowFeatures, NewWindowResponse};
use tauri::{App, AppHandle, Manager, RunEvent, Runtime, WebviewUrl, WebviewWindowBuilder};
use url::Url;

static POPUP_LABEL: AtomicU64 = AtomicU64::new(0);

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

fn resolve_node_binary(app: &AppHandle) -> PathBuf {
    if let Ok(p) = std::env::var("DLDL_NODE_PATH") {
        return PathBuf::from(p);
    }
    if !cfg!(debug_assertions) {
        if let Ok(dir) = app.path().resource_dir() {
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
    PathBuf::from("node")
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
    let mut cmd = Command::new(&node);
    cmd.stdin(Stdio::null())
        .stdout(Stdio::inherit())
        .stderr(Stdio::inherit())
        .current_dir(static_root)
        .env("DLDL_USER_DATA", user_data)
        .env("DLDL_STATIC_ROOT", static_root)
        .env("DLDL_PORT", port.to_string())
        .arg(&proxy_js);
    cmd.spawn().with_context(|| {
        format!(
            "无法启动 Node 代理（请确认已安装 Node 且在 PATH 中，或设置 DLDL_NODE_PATH）；node={:?} proxy={:?}",
            node, proxy_js
        )
    })
}

fn wait_for_port(port: u16, attempts: u32) -> anyhow::Result<()> {
    for i in 0..attempts {
        if std::net::TcpStream::connect(("127.0.0.1", port)).is_ok() {
            return Ok(());
        }
        if i + 1 == attempts {
            break;
        }
        std::thread::sleep(std::time::Duration::from_millis(120));
    }
    anyhow::bail!("等待本地代理 127.0.0.1:{port} 超时");
}

fn allow_popup_url(u: &Url) -> bool {
    matches!(u.scheme(), "http" | "https" | "about")
}

fn build_popup_window<R: Runtime>(
    app: &AppHandle<R>,
    url: Url,
    features: NewWindowFeatures,
) -> NewWindowResponse<R> {
    if !allow_popup_url(&url) {
        return NewWindowResponse::Deny;
    }
    let label = format!(
        "popup-{}",
        POPUP_LABEL.fetch_add(1, Ordering::SeqCst)
    );
    let h2 = app.clone();
    let mut b = WebviewWindowBuilder::new(app, &label, WebviewUrl::External(url.clone()))
        .window_features(features)
        .title(truncate_title(url.as_str()))
        .visible(true)
        .on_new_window(move |u2, f2| build_popup_window(&h2, u2, f2));
    if url.scheme() == "about" {
        b = b.inner_size(360.0, 660.0);
    }
    match b.build() {
        Ok(w) => NewWindowResponse::Create { window: w },
        Err(e) => {
            eprintln!("[dldl-proxy] 创建子窗口失败: {e}");
            NewWindowResponse::Deny
        }
    }
}

fn truncate_title(s: &str) -> String {
    const MAX: usize = 120;
    if s.len() <= MAX {
        s.to_string()
    } else {
        format!("{}…", &s[..MAX.saturating_sub(1)])
    }
}

fn try_setup(app: &mut App) -> anyhow::Result<()> {
    let app_handle = app.handle().clone();
    let static_root = resolve_static_root(&app_handle);
    let user_data = resolve_user_data(&app_handle);
    std::fs::create_dir_all(&user_data)?;
    let port = resolve_port();
    let mut child = spawn_proxy(&app_handle, &static_root, &user_data, port)?;
    if let Err(e) = wait_for_port(port, 250) {
        let _ = child.kill();
        let _ = child.wait();
        return Err(e);
    }
    app.manage(ProxyChild {
        inner: Mutex::new(Some(child)),
    });
    let account_url = Url::parse(&format!("http://127.0.0.1:{port}/account.html"))?;
    let popup_handle = app_handle.clone();
    let _main = WebviewWindowBuilder::new(&app_handle, "main", WebviewUrl::External(account_url))
        .title("DLDL-Proxy")
        .inner_size(1280.0, 800.0)
        .min_inner_size(800.0, 600.0)
        .on_new_window(move |url, features| build_popup_window(&popup_handle, url, features))
        .build()?;
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
                eprintln!("DLDL-Proxy 启动失败: {:#}", e);
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
