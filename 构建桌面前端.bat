@echo off
chcp 65001 >nul
title 构建桌面前端（account.html -> account.min.html）
cd /d "%~dp0"

echo ============================================
echo  将 account.html 重新混淆打包为 account.min.html / account.min.js
echo  修改 account.html 后，必须先运行本脚本，再重启「启动应用.bat」才生效
echo ============================================
echo.

node tools/obfuscate-desktop.mjs

echo.
if %errorlevel% equ 0 (
  echo ✅ 构建完成。请彻底关闭 Electron 应用后，再双击「启动应用.bat」查看效果。
) else (
  echo ❌ 构建失败，请检查上面的报错信息。
)
echo.
pause
