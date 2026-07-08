@echo off
chcp 65001 >nul
cd /d "%~dp0"

set MSG=扫码整帧扫描+分辨率1920+帧率20，解决出帧但解不出码

REM 记录当前分支，后续无论在哪都能正确拉取最新改动
for /f "tokens=*" %%b in ('git branch --show-current') do set CUR=%%b
echo 当前分支: %CUR%
echo.

echo [1/7] 提交当前分支(%CUR%)的 mobile.html 改动（防止切换分支丢失）...
git add mobile.html
git diff --cached --quiet mobile.html
if %errorlevel% equ 1 (
    git commit -m "%MSG%"
    if %errorlevel% neq 0 ( pause & exit /b 1 )
) else (
    echo 当前分支无 mobile.html 改动，跳过提交
)

echo [2/7] 切换到 gh-pages...
git checkout gh-pages
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [3/7] 从 %CUR% 拉取最新 mobile.html...
git checkout %CUR% -- mobile.html
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [4/7] 提交到 gh-pages...
git add mobile.html
git commit -m "%MSG%"
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [5/7] 推送到 gh-pages...
git push github gh-pages
if %errorlevel% neq 0 (
    echo 推送失败，请检查网络/权限
    pause
    exit /b 1
)

echo [6/7] 切回 tauri 并同步最新 mobile.html（防止下次部署被旧版覆盖）...
git checkout tauri
if %errorlevel% neq 0 ( pause & exit /b 1 )
git checkout gh-pages -- mobile.html
git add mobile.html
git commit -m "同步 %MSG%" || echo tauri 已是最新，无需提交

echo.
echo ========================================
echo  部署完成！等待 1-2 分钟后刷新手机页面
echo  https://tenghuanjun.github.io/dldl/mobile.html
echo ========================================
pause
