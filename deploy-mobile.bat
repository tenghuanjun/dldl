@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === [1] 切换到 gh-pages ===
git checkout gh-pages

echo === [2] 从 tauri 拉取最新 mobile.html ===
git checkout tauri -- mobile.html

echo === [3] 提交 ===
git add mobile.html
git commit -m "扫码优化:全帧扫描+1920+帧率20"

echo === [4] 推送到线上 ===
git push github gh-pages

echo === [5] 切回 tauri ===
git checkout tauri

echo.
echo === 完成！1-2分钟后刷新手机 ===
pause
