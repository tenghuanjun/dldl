import zipfile, os, re

APK = r'd:\dldl\28手游斗罗.apk'
OUT = r'd:\dldl\dldl28_apk_extract'
os.makedirs(OUT, exist_ok=True)

print('[1] 解压 APK 资源（覆盖式，自动处理重复条目）...')
z = zipfile.ZipFile(APK)
z.extractall(OUT)
print('    解压文件数:', len(z.namelist()))

# 关键词：聚焦登录/接口/签名/加密
KW = re.compile(
    rb'(https?://|/sdk/|/login|/passport|/account|UrlConstant|APP_HOST|host=|'
    rb'passport|\.com\.cn|/api/|encrypt|aes|md5|signv|secret|appkey|app_key|token)',
    re.I)

print('[2] 扫描 DEX 字符串...')
report = []
for name in sorted(z.namelist()):
    if not name.endswith('.dex'):
        continue
    data = z.read(name)
    strings = re.findall(rb'[\x20-\x7e]{6,}', data)
    seen = set(); hits = []
    for s in strings:
        try:
            t = s.decode('ascii')
        except Exception:
            continue
        if KW.search(s) and t not in seen:
            seen.add(t); hits.append(t)
    report.append((name, hits))
    print(f'    {name}: 命中 {len(hits)} 条')

with open(os.path.join(OUT, 'strings_scan.txt'), 'w', encoding='utf-8') as f:
    for name, hits in report:
        f.write(f'\n===== {name} ({len(hits)}) =====\n')
        for h in hits:
            f.write(h + '\n')

print('\n[3] 疑似接口地址 (http/https):')
for name, hits in report:
    for h in hits:
        if h.lower().startswith('http'):
            print('   ', h)
print('\n完成。详细见', os.path.join(OUT, 'strings_scan.txt'))
