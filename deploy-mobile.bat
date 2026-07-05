@echo off
chcp 65001 >nul
echo ========================================
echo  部署 DLDL 手机端到 GitHub Pages
echo ========================================
echo.

cd /d "%~dp0"

echo [1/4] 推送 tauri 分支到 GitHub...
git push github tauri --force
if %errorlevel% neq 0 (
    echo 推送失败，请检查网络
    pause
    exit /b 1
)

echo [2/4] 同步 gh-pages 分支...
git fetch github gh-pages
git checkout gh-pages
git reset --hard github/gh-pages

echo [3/4] 从 tauri 复制最新文件并推送...
git restore --source tauri mobile.html cloudflare-worker.js .gitignore 2>nul
git add mobile.html cloudflare-worker.js .gitignore 2>nul
git commit -m "deploy: sync from tauri" --allow-empty
git push github gh-pages --force
if %errorlevel% neq 0 (
    echo 推送 gh-pages 失败
    pause
    exit /b 1
)

echo [4/4] 切回 tauri 分支...
git checkout tauri

echo.
echo ========================================
echo  部署完成！等待 1-2 分钟后刷新手机页面
echo  https://tenghuanjun.github.io/dldl/mobile.html
echo ========================================
pause
