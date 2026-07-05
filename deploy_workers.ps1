# 部署 10 个 Worker
for ($i = 1; $i -le 10; $i++) {
    $name = "dlapi-$i"
    Write-Host "=== 部署 Worker: $name ===" -ForegroundColor Yellow
    npx wrangler deploy --name $name
    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ Worker $name 部署失败" -ForegroundColor Red
    }
    Start-Sleep -Seconds 2
}
Write-Host "=== 完成 ===" -ForegroundColor Green
