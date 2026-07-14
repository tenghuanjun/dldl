# -*- coding: utf-8 -*-
"""
把 sync_engine.py 用 PyInstaller 打成单文件 exe (sync_engine_dist/sync_engine.exe)。

目的: 让 dldl 打包 (electron-builder) 后, 目标机器无需安装 Python 也能用窗口同步。
开发态默认仍用 python 直接跑 sync_engine.py; 只有打包时才需要本 exe。

用法:
    python tools/build_sync_engine.py
"""
import os
import sys
import shutil
import subprocess

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, "sync_engine.py")
OUT_DIR = os.path.join(ROOT, "sync_engine_dist")
DIST_EXE = os.path.join(OUT_DIR, "sync_engine.exe")

if not os.path.isfile(SRC):
    print("找不到源文件:", SRC)
    sys.exit(1)

# 清掉上次产物
if os.path.isdir(OUT_DIR):
    shutil.rmtree(OUT_DIR)
os.makedirs(OUT_DIR, exist_ok=True)

cmd = [
    sys.executable, "-m", "PyInstaller",
    "--noconfirm",
    "--onefile",
    "--name", "sync_engine",
    "--distpath", OUT_DIR,
    "--workpath", os.path.join(OUT_DIR, "build"),
    "--specpath", os.path.join(OUT_DIR, "spec"),
    "--hidden-import", "win32gui",
    "--hidden-import", "win32con",
    "--hidden-import", "win32api",
    SRC,
]
print(">>>", " ".join(cmd))
subprocess.check_call(cmd)

if os.path.isfile(DIST_EXE):
    print("BUILD_OK", DIST_EXE)
else:
    print("BUILD_FAIL: 未生成", DIST_EXE)
    sys.exit(1)
