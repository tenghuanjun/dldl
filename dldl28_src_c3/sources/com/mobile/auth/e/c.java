package com.mobile.auth.e;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.BuildConfig;
import com.mobile.auth.e.b;
import com.mobile.auth.m.k;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c implements b.a {
    private static c a;
    private a b;
    private a c;
    private b d;
    private Context e;

    private c(Context context) {
        this.e = context;
        b();
    }

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(context);
                }
            }
        }
        return a;
    }

    private void b() {
        String strB = k.b("sdk_config_version", "");
        if (TextUtils.isEmpty(strB) || !BuildConfig.CMCC_SDK_VERSION.equals(strB)) {
            b bVarA = b.a(true);
            this.d = bVarA;
            this.b = bVarA.a();
            if (!TextUtils.isEmpty(strB)) {
                c();
            }
        } else {
            b bVarA2 = b.a(false);
            this.d = bVarA2;
            this.b = bVarA2.b();
        }
        this.d.a(this);
        this.c = this.d.a();
    }

    private void c() {
        com.mobile.auth.m.c.b("UmcConfigManager", "delete localConfig");
        this.d.c();
    }

    public a a() {
        try {
            return this.b.clone();
        } catch (CloneNotSupportedException unused) {
            return this.c;
        }
    }

    public void a(com.cmic.sso.sdk.a aVar) {
        this.d.a(aVar);
    }

    @Override // com.mobile.auth.e.b.a
    public void a(a aVar) {
        this.b = aVar;
    }
}
