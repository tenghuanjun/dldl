@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === [1] 切换到 gh-pages ===
git checkout gh-pages
if errorlevel 1 goto :fail

echo === [2] 取 tauri 源码 mobile.html (仅本地构建用，不部署) ===
git checkout tauri -- mobile.html
git reset HEAD mobile.html >nul 2>nul

echo === [3] 构建混淆产物 (mobile.min.html + app.min.js) ===
git checkout tauri -- tools/obfuscate.mjs
if errorlevel 1 goto :buildfail
node tools/obfuscate.mjs
if errorlevel 1 goto :buildfail

echo === [4] 提交混淆产物，并确保 mobile.html 不下线 ===
git rm -f mobile.html >nul 2>nul
git add mobile.min.html app.min.js
git commit -m "发布混淆版前端(下线未混淆 mobile.html)"
git push github gh-pages
if errorlevel 1 goto :pushfail

echo === [5] 切回 tauri ===
git checkout tauri

echo.
echo === 完成！混淆版已上线，mobile.html 已下线 ===
echo 生产入口: https://tenghuanjun.github.io/dldl/mobile.min.html
pause
exit /b 0

:buildfail
echo.
echo [错误] 混淆构建失败
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
