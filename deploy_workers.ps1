# 部署 10 个 Worker（无定时触发器）
for ($i = 1; $i -le 10; $i++) {
    $name = "dlapi-$i"
    Write-Host "=== 部署 Worker: $name ===" -ForegroundColor Yellow
    npx wrangler deploy --name $name --no-triggers
    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ Worker $name 部署失败" -ForegroundColor Red
    }
    Start-Sleep -Seconds 2
}
Write-Host "=== 完成 ===" -ForegroundColor Green
