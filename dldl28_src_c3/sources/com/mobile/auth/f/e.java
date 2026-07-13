package com.mobile.auth.f;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.hjq.permissions.Permission;
import com.mobile.auth.m.g;
import com.mobile.auth.m.h;
import com.mobile.auth.m.j;
import com.mobile.auth.m.k;
import com.mobile.auth.m.m;
import com.mobile.auth.m.n;
import com.mobile.auth.m.o;
import com.mobile.auth.m.q;
import com.mobile.auth.m.r;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class e {
    private static e f;
    protected final c a;
    protected final Context b;
    protected final Handler d;
    protected String e;
    protected long c = 8000;
    private final Object g = new Object();

    protected class a implements Runnable {
        private final com.cmic.sso.sdk.a b;

        a(com.cmic.sso.sdk.a aVar) {
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObjectA = (r.a(e.this.b).a() || !this.b.b("doNetworkSwitch", false)) ? f.a("200023", "登录超时") : f.a("102508", "数据网络切换失败");
            e.this.a(jSONObjectA.optString("resultCode", "200023"), jSONObjectA.optString("resultString", "登录超时"), this.b, jSONObjectA);
        }
    }

    e(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.d = new Handler(applicationContext.getMainLooper());
        this.a = c.a(applicationContext);
        r.a(applicationContext);
        k.a(applicationContext);
        j.a(applicationContext);
        n.a(new n.a() { // from class: com.mobile.auth.f.e.1
            @Override // com.mobile.auth.m.n.a
            protected void a() {
                String strB = k.b("AID", "");
                com.mobile.auth.m.c.b("AuthnHelperCore", "aid = " + strB);
                if (TextUtils.isEmpty(strB)) {
                    e.this.a();
                }
                com.mobile.auth.m.c.b("AuthnHelperCore", com.mobile.auth.m.b.a(e.this.b, true) ? "生成androidkeystore成功" : "生成androidkeystore失败");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        String str = "%" + q.b();
        com.mobile.auth.m.c.b("AuthnHelperCore", "generate aid = " + str);
        k.a("AID", str);
    }

    private void a(final Context context, final String str, final com.cmic.sso.sdk.a aVar) {
        n.a(new n.a() { // from class: com.mobile.auth.f.e.5
            @Override // com.mobile.auth.m.n.a
            protected void a() {
                if ("200023".equals(str)) {
                    SystemClock.sleep(8000L);
                }
                new com.cmic.sso.sdk.d.d().a(context, str, aVar);
            }
        });
    }

    public static void a(boolean z) {
        com.mobile.auth.m.c.a(z);
    }

    public static e b(Context context) {
        if (f == null) {
            synchronized (e.class) {
                if (f == null) {
                    f = new e(context);
                }
            }
        }
        return f;
    }

    protected com.cmic.sso.sdk.a a(b bVar) {
        com.cmic.sso.sdk.a aVar = new com.cmic.sso.sdk.a(64);
        String strC = q.c();
        aVar.a(new com.cmic.sso.sdk.d.b());
        aVar.a("traceId", strC);
        com.mobile.auth.m.c.a("traceId", strC);
        if (bVar != null) {
            com.mobile.auth.m.e.a(strC, bVar);
        }
        return aVar;
    }

    public void a(long j) {
        this.c = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(com.cmic.sso.sdk.a aVar) {
        final a aVar2 = new a(aVar);
        this.d.postDelayed(aVar2, this.c);
        this.a.a(aVar, new d() { // from class: com.mobile.auth.f.e.3
            @Override // com.mobile.auth.f.d
            public void a(String str, String str2, com.cmic.sso.sdk.a aVar3, JSONObject jSONObject) {
                e.this.d.removeCallbacks(aVar2);
                e.this.a(str, str2, aVar3, jSONObject);
            }
        });
    }

    public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        try {
            String strB = aVar.b("traceId");
            final int iB = aVar.b("SDKRequestCode", -1);
            if (com.mobile.auth.m.e.a(strB)) {
                return;
            }
            synchronized (this) {
                final b bVarC = com.mobile.auth.m.e.c(strB);
                if (jSONObject == null || !jSONObject.optBoolean("keepListener", false)) {
                    com.mobile.auth.m.e.b(strB);
                }
                if (bVarC == null) {
                    return;
                }
                aVar.a("systemEndTime", SystemClock.elapsedRealtime());
                aVar.a("endtime", o.a());
                int iC = aVar.c("logintype");
                if (jSONObject == null) {
                    jSONObject = f.a(str, str2);
                }
                final JSONObject jSONObjectA = iC == 3 ? f.a(str, aVar, jSONObject) : f.a(str, str2, aVar, jSONObject);
                jSONObjectA.put("scripExpiresIn", String.valueOf(h.a()));
                this.d.post(new Runnable() { // from class: com.mobile.auth.f.e.4
                    @Override // java.lang.Runnable
                    public void run() {
                        bVarC.a(iB, jSONObjectA);
                    }
                });
                com.mobile.auth.e.c.a(this.b).a(aVar);
                if (!aVar.b().j() && !q.a(aVar.b())) {
                    a(this.b, str, aVar);
                }
                if (com.mobile.auth.m.e.a()) {
                    r.a(this.b).b();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(final String str, final String str2, final b bVar) {
        final com.cmic.sso.sdk.a aVarA = a(bVar);
        n.a(new n.a(this.b, aVarA) { // from class: com.mobile.auth.f.e.2
            @Override // com.mobile.auth.m.n.a
            protected void a() {
                if (e.this.a(aVarA, str, str2, "mobileAuth", 0, bVar)) {
                    e.this.a(aVarA);
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean a(com.cmic.sso.sdk.a r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, int r14, com.mobile.auth.f.b r15) {
        /*
            Method dump skipped, instruction units count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.f.e.a(com.cmic.sso.sdk.a, java.lang.String, java.lang.String, java.lang.String, int, com.mobile.auth.f.b):boolean");
    }

    public JSONObject c(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                boolean zA = m.a(this.b);
                com.mobile.auth.g.a.a().a(context, g.a(context, Permission.READ_PHONE_STATE), zA);
                String strA = j.a().a((String) null);
                int iA = m.a(context, zA);
                jSONObject.put("operatortype", strA);
                jSONObject.put("networktype", iA + "");
                StringBuilder sb = new StringBuilder("网络类型: ");
                sb.append(iA);
                com.mobile.auth.m.c.b("AuthnHelperCore", sb.toString());
                com.mobile.auth.m.c.b("AuthnHelperCore", "运营商类型: " + strA);
                return jSONObject;
            } catch (Exception unused) {
                jSONObject.put("errorDes", "发生未知错误");
                return jSONObject;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public void d() {
        try {
            h.a(true, true);
            com.mobile.auth.m.c.b("AuthnHelperCore", "删除scrip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
