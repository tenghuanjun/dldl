package com.cmic.sso.sdk.view;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static f f427a;
    private a b;

    public interface a {
        void a();
    }

    public static f a() {
        if (f427a == null) {
            synchronized (f.class) {
                if (f427a == null) {
                    f427a = new f();
                }
            }
        }
        return f427a;
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
