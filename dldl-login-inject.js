/* 由 login.php 经代理插入；需在上一行内联脚本中先设置 window.__PC_QR_FIXED_TIME_MS */
(function () {
  try {
    var p = new URLSearchParams(location.search || '');
    window.__DLDL_PROXY_UNAME = p.get('uname') || '';
    window.__DLDL_PROXY_UPWD = p.get('upwd') || '';
  } catch (_) {
    window.__DLDL_PROXY_UNAME = '';
    window.__DLDL_PROXY_UPWD = '';
  }

  function injectRefreshButton() {
    try {
      if (document.getElementById('dldl-refresh-token-btn')) return;
      var btn = document.createElement('button');
      btn.id = 'dldl-refresh-token-btn';
      btn.type = 'button';
      btn.textContent = '刷新 token';
      btn.style.cssText =
        'position:fixed;right:16px;top:16px;z-index:99999;background:#2f81f7;color:#fff;border:none;border-radius:8px;padding:10px 14px;font-size:14px;cursor:pointer;box-shadow:0 8px 24px rgba(0,0,0,.25);';
      btn.addEventListener('click', async function () {
        try {
          btn.disabled = true;
          btn.textContent = '刷新中...';
          var uname = window.__DLDL_PROXY_UNAME || '';
          var upwd = window.__DLDL_PROXY_UPWD || '';
          if (!uname || !upwd) throw new Error('缺少账号信息');
          var resp = await fetch('/accounts', { cache: 'no-store' });
          var json = await resp.json();
          var area = Array.isArray(json.area) ? json.area : [];
          var list = json.list && typeof json.list === 'object' ? json.list : {};
          var found = false;
          for (var i = 0; i < area.length; i++) {
            var key = String(area[i] || '');
            var arr = list[key];
            if (!Array.isArray(arr)) continue;
            for (var j = 0; j < arr.length; j++) {
              var acc = arr[j];
              if (String((acc && acc.uname) || '') === uname && String((acc && acc.upwd) || '') === upwd) {
                var time = String(Math.floor(Date.now() / 1000));
                var signParams = {
                  uname: uname,
                  upwd: upwd,
                  autoLogin: 'true',
                  pid: '46',
                  gid: '1003279',
                  sversion: 'undefined',
                  version: '1.0.4',
                  time: time,
                  dev: '9c71cbfa62ecfd4f5a1125d9c6c51367',
                  os: 'iOS',
                  over: '18.5'
                };
                var API_KEY = 'Jp*4Y8vQOYck2*&Z';
                var sorted = Object.keys(signParams).sort();
                var str = '';
                for (var s = 0; s < sorted.length; s++) str += sorted[s] + '=' + signParams[sorted[s]];
                var sign = hex_md5(str + API_KEY);
                var url =
                  'https://s-api.37.com.cn/h5sdk/login?' +
                  new URLSearchParams(Object.assign({}, signParams, { sign: sign })).toString();
                var payload = await new Promise(function (resolve, reject) {
                  var cb = '__dldl_refresh_' + Date.now() + '_' + Math.random().toString(36).slice(2);
                  var u = new URL(url);
                  u.searchParams.set('callback', cb);
                  var script = document.createElement('script');
                  var timer = setTimeout(function () {
                    cleanup();
                    reject(new Error('jsonp timeout'));
                  }, 15000);
                  function cleanup() {
                    if (script && script.parentNode) script.parentNode.removeChild(script);
                    try {
                      delete window[cb];
                    } catch (_) {
                      window[cb] = undefined;
                    }
                    clearTimeout(timer);
                  }
                  window[cb] = function (data) {
                    cleanup();
                    resolve(data);
                  };
                  script.onerror = function () {
                    cleanup();
                    reject(new Error('jsonp network error'));
                  };
                  script.src = u.toString();
                  document.head.appendChild(script);
                });
                if (!payload || payload.state !== 1 || !payload.data || !payload.data.token) {
                  throw new Error((payload && payload.msg) || 'login api failed');
                }
                acc.token = String(payload.data.token);
                acc.time = String(payload.data.time || time);
                acc.sign = String(payload.data.sign || '');
                found = true;
                break;
              }
            }
            if (found) break;
          }
          if (!found) throw new Error('未找到对应账号');
          var save = await fetch('/accounts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ accounts: { area: area, list: list } })
          });
          var saveJson = await save.json();
          if (!save.ok || !saveJson.ok) throw new Error((saveJson && saveJson.message) || '保存失败');
          btn.textContent = '刷新成功';
          setTimeout(function () {
            btn.disabled = false;
            btn.textContent = '刷新 token';
          }, 1200);
        } catch (err) {
          console.error(err);
          alert('刷新 token 失败：' + (err && err.message ? err.message : err));
          btn.disabled = false;
          btn.textContent = '刷新 token';
        }
      });
      document.body.appendChild(btn);
    } catch (_) {}
  }
  if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', injectRefreshButton);
  else injectRefreshButton();

  try {
    var _open = XMLHttpRequest.prototype.open;
    XMLHttpRequest.prototype.open = function (method, url) {
      try {
        var uStr = String(url || '');
        if (uStr) {
          var a = window.__DLDL_PROXY_UNAME || '';
          var b = window.__DLDL_PROXY_UPWD || '';
          if ((uStr.indexOf('/pc/getId') === 0 || uStr.indexOf('/pc/getCodeInfo') === 0) && (a || b)) {
            var u = new URL(uStr, location.origin);
            if (a && !u.searchParams.has('uname')) u.searchParams.set('uname', a);
            if (b && !u.searchParams.has('upwd')) u.searchParams.set('upwd', b);
            url = u.pathname + (u.search || '');
            arguments[1] = url;
          }
        }
      } catch (_) {}
      return _open.apply(this, arguments);
    };
  } catch (_) {}
})();
