@echo off
chcp 65001 >nul
echo ========================================
echo  部署 Worker 11-99（更新通行证逻辑）
echo ========================================
echo.
echo 预计需要 4-5 分钟，请耐心等待...
echo.

cd /d "%~dp0"
powershell -ExecutionPolicy Bypass -File "deploy_more_workers.ps1"

echo.
echo ========================================
echo  完成！
echo ========================================
pause
