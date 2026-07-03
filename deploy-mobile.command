#!/bin/bash
cd "$(dirname "$0")"
chmod +x deploy-mobile.sh
./deploy-mobile.sh
echo ""
echo "按任意键关闭窗口..."
read -n 1
