// 在 account.html 页面按 F12 打开控制台，粘贴以下代码运行
// 将 ceshi、zhanshenfei、ceshiwang 三个用户的密码重置为 123456

const SUPABASE_URL = 'https://xywlbjsyhpyyxboznmct.supabase.co';
const SUPABASE_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh5d2xianN5aHB5eXhib3pubWN0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkwODgwNzIsImV4cCI6MjA5NDY2NDA3Mn0.Q0KzoMgwNInH4gi30DEK_d1NbZCwl5yFjnTjubm_gYs';

const headers = {
  'apikey': SUPABASE_KEY,
  'Authorization': 'Bearer ' + SUPABASE_KEY,
  'Content-Type': 'application/json',
  'Prefer': 'return=representation'
};

const newPassword = '123456';
const password_hash = '0cff774f73ff9af8d43912e47852374510e341a25370c292845a319373c8b1d46ee5b13b1a132a8a9aaa908487275c00d2679b157de884bed818da0125f9ea65';
const encrypted_pwd = 'U2FsdGVkX18mAJuJ7zE41oHHgFvEeTxuBu0ROixGQZ8=';

const users = ['ceshi', 'zhanshenfei', 'ceshiwang'];

async function resetPasswords() {
  for (const username of users) {
    try {
      const res = await fetch(`${SUPABASE_URL}/rest/v1/users?username=eq.${username}`, {
        method: 'PATCH',
        headers,
        body: JSON.stringify({ password_hash, encrypted_pwd })
      });
      const data = await res.json();
      console.log(`${username}:`, res.ok ? '重置成功' : '失败', data);
      if (res.ok) {
        console.log(`  新密码: ${newPassword}`);
      }
    } catch (e) {
      console.error(`${username} 重置失败:`, e.message);
    }
  }
  console.log('处理完成！');
}

resetPasswords();
