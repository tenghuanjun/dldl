@echo off
chcp 65001 >nul
echo ========================================
echo  部署 DLDL 手机端到 GitHub Pages
echo ========================================
echo.

cd /d "%~dp0"

echo [1/6] 确保在 gh-pages 分支...
git checkout gh-pages
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [2/6] 暂存当前 mobile.html（工作树中的最新改动）...
git add mobile.html
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [3/6] 提交并推送到 gh-pages...
git commit -m "移动端扫码优化: 放宽取景框、提帧率、强制ZXing、contain对齐、加闪光灯与诊断"
if %errorlevel% neq 0 ( pause & exit /b 1 )
git push github gh-pages
if %errorlevel% neq 0 (
    echo 推送 gh-pages 失败
    pause
    exit /b 1
)

echo [4/6] 同步到 tauri 分支（防止下次部署被旧版覆盖）...
git checkout tauri
if %errorlevel% neq 0 ( pause & exit /b 1 )
git checkout gh-pages -- mobile.html
git add mobile.html
git commit -m "同步 mobile.html 扫码优化到 tauri"
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [5/6] 切回 gh-pages...
git checkout gh-pages
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo.
echo ========================================
echo  部署完成！等待 1-2 分钟后刷新手机页面
echo  https://tenghuanjun.github.io/dldl/mobile.html
echo ========================================
pause
