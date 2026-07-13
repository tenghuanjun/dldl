@echo off
chcp 65001 >nul 2>&1
setlocal

:: ============================================================
::  桌面端打包脚本（自动混淆加密）
::  双击本文件即可：混淆 account/preload -> 打包 Windows x64 安装包
::  全程日志会实时显示在下方，并保存到 pack_log_*.txt
:: ============================================================

set "ROOT=%~dp0"
for /f "tokens=*" %%a in ('powershell -NoProfile -Command "Get-Date -Format yyyyMMdd_HHmmss"') do set "TS=%%a"
set "LOGFILE=%ROOT%pack_log_%TS%.txt"

echo ======================================================
echo   桌面端打包（自动混淆加密）
echo   日志文件：%LOGFILE%
echo ======================================================
echo.

cd /d "%ROOT%"

:: 检测是否正在运行安装版，避免文件占用导致打包失败
tasklist /fi "imagename eq DLDL-Proxy.exe" 2>nul | findstr /i "DLDL-Proxy.exe" >nul
if not errorlevel 1 (
    echo [警告] 检测到 DLDL-Proxy.exe 正在运行！
    echo         打包可能因文件占用失败，建议先右键托盘「退出」再继续。
    echo.
)

echo 开始打包（含混淆加密），实时日志如下：
echo ======================================================
echo.

:: 实时输出到控制台，同时写入日志文件（Tee）
powershell -NoProfile -Command "npm run pack 2>&1 | Tee-Object -FilePath '%LOGFILE%'; if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }"

if errorlevel 1 (
    echo.
    echo ======================================================
    echo  [X] 打包失败！请查看日志定位问题：
    echo      %LOGFILE%
    echo ======================================================
    echo 按任意键用记事本打开日志...
    pause >nul
    start "" notepad "%LOGFILE%"
    exit /b 1
)

echo.
echo ======================================================
echo  [√] 打包成功！
echo      安装包：%ROOT%dist-electron\DLDL-Proxy Setup 1.0.0.exe
echo      日志：%LOGFILE%
echo ======================================================
echo.
pause
