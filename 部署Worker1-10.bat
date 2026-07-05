@echo off
chcp 65001 >nul
echo ========================================
echo  部署 Worker 1-10（更新通行证逻辑）
echo ========================================
echo.

cd /d "%~dp0"
powershell -ExecutionPolicy Bypass -File "deploy_workers.ps1"

echo.
echo ========================================
echo  完成！
echo ========================================
pause
