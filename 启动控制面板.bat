@echo off
title DLDL-Proxy Panel
cd /d "%~dp0"

echo Starting proxy server...
start "DLDL-Proxy" cmd /k "node proxy.js"

echo Waiting for server...
timeout /t 3 /nobreak >nul

echo Opening panel in browser...
start http://localhost:8080/panel.html

echo Done. This window will close in 3 seconds.
timeout /t 3 /nobreak >nul
