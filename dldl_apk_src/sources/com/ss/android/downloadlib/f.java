package com.ss.android.downloadlib;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class f {
    private static volatile f a;
    private com.ss.android.download.api.config.f b = null;

    private f() {
    }

    public static f a() {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }

    public com.ss.android.download.api.config.f b() {
        return this.b;
    }
}
