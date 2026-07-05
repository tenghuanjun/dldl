@echo off
chcp 65001 >nul
title DLDL-Proxy App
cd /d "%~dp0"
echo Starting DLDL-Proxy Electron App...
call npm start
