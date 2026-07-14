// 自动驱动桌面端（已安装版 DLDL-Proxy.exe）：连 CDP(9222)，按勾选顺序填码、发送、读回结果。
// 仅依赖 app 自带的 sendPassCode / _setPassCodeState / getPassCodeStatus，不依赖任何自定义按钮。
// 用法：node _drive_passcode.cjs <码1> <码2> [...]
//   前提：桌面端已带 --remote-debugging-port=9222 启动（用命令给已装 exe 加该参数重启即可）。
const argv = process.argv.slice(2);
const CODES = argv.map((s) => String(s).trim()).filter((s) => s.length > 0);
if (!CODES.length) {
  console.error('用法: node _drive_passcode.cjs <码1> <码2> [...]');
  process.exit(1);
}

let chromium;
try { chromium = require('playwright').chromium; }
catch (_) {
  try { chromium = require('playwright-core').chromium; }
  catch (_) { console.error('未找到 playwright / playwright-core，请先: npm i playwright-core'); process.exit(1); }
}

(async () => {
  let browser;
  try {
    browser = await chromium.connectOverCDP('http://127.0.0.1:9222');
  } catch (e) {
    console.error('连接 9222 失败，请确认桌面端已带 --remote-debugging-port=9222 启动:', e.message);
    process.exit(1);
  }

  let page = null;
  for (const ctx of browser.contexts()) {
    for (const p of ctx.pages()) {
      if ((p.url() || '').includes('account.html')) { page = p; break; }
    }
    if (page) break;
  }
  if (!page) { console.error('未在 CDP 中找到 account.html 页面'); await browser.close(); process.exit(1); }

  try {
    await page.waitForFunction(() => {
      const root = document.querySelector('#app');
      const inst = root && root.__vue_app__ && root.__vue_app__._instance;
      const vm = inst && inst.proxy;
      return !!(vm && vm.areas && vm.areas.length && vm.areas[0].accounts && vm.areas[0].accounts.length);
    }, { timeout: 30000 });
  } catch (_) {
    console.error('等待账号数据加载超时（30s）'); await browser.close(); process.exit(1);
  }

  const result = await page.evaluate(async (codes) => {
    const root = document.querySelector('#app');
    const vm = root.__vue_app__._instance.proxy;
    const groups = vm.areas || [];

    // 1. 收集当前已勾选（按界面顺序）
    const selected = [];
    for (const g of groups) {
      for (const a of (g.accounts || [])) {
        if (vm.accountSelectMap[vm.accountSelectKey(g.area, a)]) selected.push({ area: g.area, account: a });
      }
    }

    // 2. 选择目标：已精确勾选 codes.length 个则用用户的；否则自动选前 codes.length 个
    let chosen;
    if (selected.length === codes.length) {
      chosen = selected;
    } else {
      chosen = [];
      outer:
      for (const g of groups) {
        for (const a of (g.accounts || [])) {
          chosen.push({ area: g.area, account: a });
          if (chosen.length >= codes.length) break outer;
        }
      }
    }
    if (chosen.length < codes.length) {
      return { error: `账号不足 ${codes.length}（含已勾选共 ${chosen.length}）` };
    }

    // 3. 精确设置勾选集合为 chosen
    const chosenMap = {};
    for (const c of chosen) chosenMap[vm.accountSelectKey(c.area, c.account)] = true;
    vm.accountSelectMap = chosenMap;

    // 4. 逐个填值 + 发送（用 app 自带方法）
    const results = [];
    for (let i = 0; i < chosen.length; i++) {
      const { area, account } = chosen[i];
      const code = codes[i];
      vm._setPassCodeState(area, account, { value: code });
      try {
        await vm.sendPassCode(code, area, account);
      } catch (e) {
        // 忽略异常，后续以 status 为准
      }
      results.push({
        uname: account.uname || account.id || '(无用户名)',
        code,
        status: vm.getPassCodeStatus(area, account),
      });
    }

    return {
      selected: results.map((r) => r.uname),
      results,
    };
  }, CODES);

  console.log('=== 驱动结果 ===');
  console.log(JSON.stringify(result, null, 2));
  await browser.close();
})().catch((e) => { console.error('驱动异常:', e); process.exit(1); });
