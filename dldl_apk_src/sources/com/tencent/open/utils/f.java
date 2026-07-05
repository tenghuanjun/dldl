package com.tencent.open.utils;

import android.content.Context;
import android.os.Bundle;
import java.io.File;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class f {
    private static Context a;

    public static final Context a() {
        Context context = a;
        if (context == null) {
            return null;
        }
        return context;
    }

    public static final void a(Context context) {
        a = context;
    }

    public static final String b() {
        return a() == null ? "" : a().getPackageName();
    }

    public static final File c() {
        if (a() == null) {
            return null;
        }
        return a().getFilesDir();
    }

    public static final File d() {
        Context contextA = a();
        if (contextA != null) {
            return contextA.getCacheDir();
        }
        return null;
    }

    public static final File e() {
        return a((String) null);
    }

    public static final File a(String str) {
        return k.h(a(), str);
    }

    /* JADX INFO: renamed from: com.tencent.open.utils.f$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    class AnonymousClass1 extends Thread {
        final /* synthetic */ Bundle a;

        AnonymousClass1(Bundle bundle) {
            this.a = bundle;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                f.a(f.this, k.d(HttpUtils.openUrl2(f.a(f.this), "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", "GET", this.a).a));
            } catch (Exception e) {
                e.printStackTrace();
            }
            f.a(f.this, 0);
        }
    }
}
