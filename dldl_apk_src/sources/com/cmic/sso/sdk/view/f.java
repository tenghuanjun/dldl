package com.cmic.sso.sdk.view;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class f {
    private static f a;
    private a b;

    public interface a {
        void a();
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

    public void a(a aVar) {
        this.b = aVar;
    }

    public a b() {
        return this.b;
    }

    public void c() {
        if (this.b != null) {
            this.b = null;
        }
    }
}
