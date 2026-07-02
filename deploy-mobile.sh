#!/bin/bash
set -e

cd "$(dirname "$0")"

echo "====== 部署 mobile.html 到 GitHub Pages ======"

# 1. 检查是否有改动
if git diff --quiet mobile.html; then
    echo "[跳过] mobile.html 无改动"
else
    echo "[1/5] 提交 mobile.html 到 tauri 分支..."
    git add mobile.html
    git commit -m "更新 mobile.html $(date '+%Y-%m-%d %H:%M')"
fi

# 2. 推送 tauri 分支
echo "[2/5] 推送 tauri 分支..."
git push github tauri

# 3. 切到 gh-pages，取最新 mobile.html
echo "[3/5] 切换到 gh-pages 分支..."
git checkout gh-pages
git pull github gh-pages 2>/dev/null || true

git checkout tauri -- mobile.html

# 4. 提交并推送 gh-pages
echo "[4/5] 推送 gh-pages 分支..."
git add mobile.html
git commit -m "部署 mobile.html $(date '+%Y-%m-%d %H:%M')" || echo "[跳过] 无新增改动"
git push github gh-pages

# 5. 切回 tauri
echo "[5/5] 切回 tauri 分支..."
git checkout tauri

echo ""
echo "====== 部署完成 ======"
echo "等待 1-2 分钟后访问: https://tenghuanjun.github.io/dldl/mobile.html"
