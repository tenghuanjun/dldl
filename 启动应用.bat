@echo off
chcp 65001 >nul

:: 提权自检：非管理员则请求 UAC 提权并重跑本脚本
:: （否则可能杀不掉以管理员身份运行的已装版 DLDL-Proxy.exe，导致依然"点了没反应"）
fltmc >nul 2>&1
if %errorlevel% neq 0 (
    echo 当前非管理员权限，正在请求 UAC 提权...
    powershell -NoProfile -Command "Start-Process -FilePath '%~f0' -Verb RunAs"
    if %errorlevel% neq 0 echo 提权被取消，请以管理员身份运行本脚本。
    exit /b
)

title DLDL-Proxy App
cd /d "%~dp0"

echo ============================================
echo  DLDL-Proxy 启动器
echo ============================================
echo.

:: 先关闭所有可能占用单实例锁的旧实例
:: 1) 已安装的打包版 DLDL-Proxy.exe
:: 2) 之前用本脚本跑的源码实例（主窗口标题为 "DLDL-Proxy"）
echo [1/3] 正在关闭已运行的 DLDL-Proxy 实例...
taskkill /f /im "DLDL-Proxy.exe" >nul 2>&1
:: 用 PowerShell 按主窗口标题精确关闭源码实例（taskkill 的 WINDOWTITLE 过滤对 GUI 窗口不稳定）
powershell -NoProfile -Command "Get-Process | Where-Object { $_.MainWindowTitle -eq 'DLDL-Proxy' } | Stop-Process -Force" >nul 2>&1

:: 等待进程退出、释放单实例锁，避免新实例仍被判为"第二个实例"而无反应
echo [2/3] 等待旧实例退出...
timeout /t 1 /nobreak >nul

:: 启动源码版（electron .）
echo [3/3] 正在启动 DLDL-Proxy Electron App...
echo.
call npm start

:: 若 npm start 异常退出（如 electron 未安装/端口被占），保留窗口便于查看错误
echo.
echo 应用已退出（或启动失败）。按任意键关闭本窗口。
pause >nul
