@echo off
chcp 65001 >nul
echo ========================================
echo  分布式刷新 - 部署 dlapi-11 ~ dlapi-99
echo ========================================
echo.

cd /d "%~dp0"
powershell -ExecutionPolicy Bypass -File "deploy_more_workers.ps1"

echo.
echo ========================================
echo  完成！
echo ========================================
pause
