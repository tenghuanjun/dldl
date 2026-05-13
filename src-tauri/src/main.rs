#![cfg_attr(all(not(debug_assertions), target_os = "windows"), windows_subsystem = "windows")]

/// 从访达 / Dock 启动时，stdin/stdout/stderr 往往接到「无人读取的管道」。
/// WebKit 与 Rust 侧若向 stdout/stderr 写日志，缓冲区满后会阻塞，表现为双击卡死；
/// 终端启动时 is_terminal() 为真，不重定向，便于排查。
#[cfg(all(target_os = "macos", not(debug_assertions)))]
fn detach_stdio_when_not_in_terminal() {
    use std::io::IsTerminal;
    if std::io::stdin().is_terminal()
        || std::io::stdout().is_terminal()
        || std::io::stderr().is_terminal()
    {
        return;
    }
    unsafe {
        let path = b"/dev/null\0";
        let fd = libc::open(path.as_ptr().cast::<libc::c_char>(), libc::O_RDWR);
        if fd < 0 {
            return;
        }
        libc::dup2(fd, libc::STDIN_FILENO);
        libc::dup2(fd, libc::STDOUT_FILENO);
        libc::dup2(fd, libc::STDERR_FILENO);
        if fd > 2 {
            libc::close(fd);
        }
    }
}

fn main() {
    #[cfg(all(target_os = "macos", not(debug_assertions)))]
    detach_stdio_when_not_in_terminal();
    dldl_proxy_lib::run();
}
