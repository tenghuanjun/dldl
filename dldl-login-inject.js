/* 由 login.php 经代理插入；需在上一行内联脚本中先设置 window.__PC_QR_FIXED_TIME_MS */
(function () {
  var _DLDL_ACCOUNT_ID = '';
  try {
    var p = new URLSearchParams(location.search || '');
    _DLDL_ACCOUNT_ID = p.get('dldl_account_id') || '';
    window.__DLDL_PROXY_UNAME = '';
    window.__DLDL_PROXY_UPWD = '';
  } catch (_) {
    window.__DLDL_PROXY_UNAME = '';
    window.__DLDL_PROXY_UPWD = '';
  }

  // 通过后端 Supabase 查询解析账号凭据（不再从 URL 明文传 uname/upwd）
  (async function resolveAccount() {
    if (!_DLDL_ACCOUNT_ID) return;
    try {
      var resp = await fetch('/api/account/resolve', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ dldl_account_id: _DLDL_ACCOUNT_ID })
      });
      if (!resp.ok) throw new Error('resolve 请求失败: ' + resp.status);
      var data = await resp.json();
      if (data && data.ok) {
        window.__DLDL_PROXY_UNAME = data.uname || '';
        window.__DLDL_PROXY_UPWD = data.upwd || '';
      }
    } catch (err) {
      console.warn('[dldl-inject] 账号解析失败:', err && err.message ? err.message : err);
    }
  })();

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
          // 等待账号异步解析完成（最多等 2 秒）
          var waited = 0;
          while ((!window.__DLDL_PROXY_UNAME || !window.__DLDL_PROXY_UPWD) && waited < 2000) {
            await new Promise(function (r) { setTimeout(r, 100); });
            waited += 100;
          }
          var uname = window.__DLDL_PROXY_UNAME || '';
          var upwd = window.__DLDL_PROXY_UPWD || '';
          if (!uname || !upwd) throw new Error('缺少账号信息');
          var resp = await fetch('/api/token/refresh', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ uname: uname, upwd: upwd })
          });
          var data = await resp.json();
          if (!resp.ok || !data.ok || !data.token) {
            throw new Error((data && data.message) || '刷新失败');
          }
          if (window.opener && !window.opener.closed) {
            try {
              window.opener.postMessage(
                {
                  type: 'dldl_token_refreshed',
                  accountId: _DLDL_ACCOUNT_ID,
                  uname: uname,
                  token: String(data.token),
                  time: String(data.time || ''),
                  sign: String(data.sign || '')
                },
                window.location.origin
              );
            } catch (postErr) {
              console.warn('postMessage to opener failed', postErr);
            }
          }
          var u = new URL(location.href);
          u.searchParams.set('token', String(data.token));
          u.searchParams.set('time', String(data.time || ''));
          u.searchParams.set('sign', String(data.sign || ''));
          location.href = u.toString();
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
