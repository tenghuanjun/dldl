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
    echo 推送失败，请检查网络或手动处理
    pause
    exit /b 1
)

echo [2/4] 切换到 gh-pages 分支...
git checkout gh-pages
if %errorlevel% neq 0 ( pause & exit /b 1 )

echo [3/4] 同步 mobile.html 并推送...
git checkout tauri -- mobile.html
git add mobile.html
git commit -m "deploy: 移动端最新代码" --allow-empty
git push github gh-pages
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
