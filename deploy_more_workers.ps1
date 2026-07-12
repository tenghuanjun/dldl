# 部署 Worker 11-99
for ($i = 11; $i -le 99; $i++) {
    $name = "dlapi-$i"
    Write-Host "=== Worker $i/99: $name ===" -ForegroundColor Yellow
    npx wrangler deploy --name $name --config wrangler.dlapi.toml
    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ $name 失败" -ForegroundColor Red
    }
    Start-Sleep -Milliseconds 500
}
Write-Host "=== 全部完成 ===" -ForegroundColor Green
