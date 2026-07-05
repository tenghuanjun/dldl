package com.mobile.auth.g;

import android.content.Context;
import android.content.Intent;
import com.cmic.sso.sdk.view.a;
import com.mobile.auth.g.e;
import com.mobile.auth.n.n;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a extends e {
    private static a f;
    private com.cmic.sso.sdk.view.a g;
    private com.cmic.sso.sdk.view.e h;

    /* JADX INFO: renamed from: com.mobile.auth.g.a$a, reason: collision with other inner class name */
    static /* synthetic */ class C0067a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.HUAWEI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.SAMSUNG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum b {
        UNKNOWN,
        SAMSUNG,
        HUAWEI
    }

    private a(Context context) {
        super(context);
        this.h = null;
    }

    public static a a(Context context) {
        if (f == null) {
            synchronized (a.class) {
                if (f == null) {
                    f = new a(context);
                }
            }
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, com.cmic.sso.sdk.a aVar) {
        String strB = aVar.b("traceId");
        Intent intent = new Intent();
        intent.putExtra("traceId", strB);
        com.mobile.auth.n.e.a(aVar.b("traceId"), aVar);
        intent.setClassName(context, "com.cmic.sso.sdk.view.LoginAuthActivity");
        intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        context.startActivity(intent);
    }

    public com.cmic.sso.sdk.view.a a() {
        if (this.g == null) {
            this.g = new a.C0033a().a();
        }
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mobile.auth.g.e
    public void a(com.cmic.sso.sdk.a aVar) {
        final e.a aVar2 = new e.a(aVar);
        this.d.postDelayed(aVar2, this.c);
        this.a.a(aVar, new d() { // from class: com.mobile.auth.g.a.2
            @Override // com.mobile.auth.g.d
            public void a(String str, String str2, com.cmic.sso.sdk.a aVar3, JSONObject jSONObject) {
                com.mobile.auth.n.c.b("onBusinessComplete", "onBusinessComplete");
                a.this.d.removeCallbacks(aVar2);
                if (!"103000".equals(str) || com.mobile.auth.n.e.a(aVar3.b("traceId"))) {
                    a.this.a(str, str2, aVar3, jSONObject);
                } else {
                    a.b(a.this.b, aVar3);
                }
            }
        });
    }

    @Override // com.mobile.auth.g.e
    public void a(String str, String str2, com.mobile.auth.g.b bVar) {
        a(str, str2, bVar, -1);
    }

    public void a(final String str, final String str2, final com.mobile.auth.g.b bVar, int i) {
        final com.cmic.sso.sdk.a aVarA = a(bVar);
        aVarA.a("SDKRequestCode", i);
        n.a(new n.a(this.b, aVarA) { // from class: com.mobile.auth.g.a.1
            @Override // com.mobile.auth.n.n.a
            protected void a() {
                if (a.this.a(aVarA, str, str2, "mobileAuth", 0, bVar)) {
                    a.super.a(aVarA);
                }
            }
        });
    }

    public void a(String str, JSONObject jSONObject) {
        com.cmic.sso.sdk.view.e eVar = this.h;
        if (eVar != null) {
            eVar.a(str, jSONObject);
        }
    }

    public void b() {
        try {
            if (com.cmic.sso.sdk.view.f.a().b() != null) {
                com.cmic.sso.sdk.view.f.a().b().a();
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.mobile.auth.n.c.a("AuthnHelper", "关闭授权页失败");
        }
    }

    public long c() {
        return this.c;
    }
}
