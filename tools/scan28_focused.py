import zipfile, os, re

APK = r'd:\dldl\28手游斗罗.apk'
OUT = r'd:\dldl\dldl28_apk_extract'

FRAME = re.compile(
    r'(androidx|kotlin|okhttp|com/google|com/bumptech|org/json|java/|javax/|'
    r'android/(?!.*(28zhe|login|account|user|api)))', re.I)
KW = re.compile(
    r'(login|register|captcha|sms|sendcode|getcode|verify|user|account|'
    r'encrypt|aes|md5|sign|secret|password|pwd|token|refresh|'
    r'mobile\.28zhe|28zhe\.com|baseurl|base_url|/v1/|/v2/|/api/|passport|'
    r'gid|channel|appid|app_key|appkey|salt|nonce|timestamp)', re.I)

z = zipfile.ZipFile(APK)
report = {}
for name in sorted(z.namelist()):
    if not name.endswith('.dex'):
        continue
    data = z.read(name)
    strings = re.findall(rb'[\x20-\x7e]{4,}', data)
    seen = set(); hits = []
    for s in strings:
        try:
            t = s.decode('ascii')
        except Exception:
            continue
        if FRAME.search(t):
            continue
        if KW.search(t) and t not in seen:
            seen.add(t); hits.append(t)
    report[name] = hits

with open(os.path.join(OUT, 'focused_scan.txt'), 'w', encoding='utf-8') as f:
    for name, hits in report.items():
        f.write(f'\n===== {name} ({len(hits)}) =====\n')
        for h in hits:
            f.write(h + '\n')

print('聚焦扫描完成，各 dex 命中:')
for name, hits in report.items():
    print(f'  {name}: {len(hits)}')

print('\n--- 最相关（含 login/register/28zhe//v1//v2/encrypt/baseurl）---')
for name, hits in report.items():
    for h in hits:
        if re.search(r'(login|register|28zhe|/v1/|/v2/|/api/|encrypt|baseurl|base_url|app_key|appkey|secret)', h, re.I):
            print(f'  [{name}] {h}')
print('\n详细见', os.path.join(OUT, 'focused_scan.txt'))
