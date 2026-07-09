@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === [1] 切换到 gh-pages ===
git checkout gh-pages
if errorlevel 1 goto :fail

echo === [2] 拉取 tauri 最新 mobile.html 源码(保留未混淆调试版) ===
git checkout tauri -- mobile.html
if errorlevel 1 goto :fail

echo === [3] 构建混淆产物 (mobile.min.html + app.min.js) ===
call npm run build
if errorlevel 1 goto :buildfail

echo === [4] 提交并推送到线上 ===
git add mobile.html mobile.min.html app.min.js
git commit -m "发布混淆版前端(移动端扫码优化)"
git push github gh-pages
if errorlevel 1 goto :pushfail

echo === [5] 切回 tauri ===
git checkout tauri

echo.
echo === 完成！===
echo 生产入口(混淆): https://tenghuanjun.github.io/dldl/mobile.min.html
echo 调试入口(未混淆): https://tenghuanjun.github.io/dldl/mobile.html
echo 等待 1-2 分钟后访问
pause
exit /b 0

:buildfail
echo.
echo [错误] 混淆构建失败。请确认 node_modules 含 javascript-obfuscator：
echo   npm i javascript-obfuscator --no-save
git checkout tauri
pause
exit /b 1

:pushfail
echo.
echo [错误] 推送失败，请检查网络/权限
git checkout tauri
pause
exit /b 1

:fail
echo.
echo [错误] git 操作失败（可能有未提交改动）
pause
exit /b 1
