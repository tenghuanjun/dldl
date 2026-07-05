package cn.thinkingdata.android.q;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class e {
    private static final Object b = new Object();
    private static final Object c = new Object();
    private static e d;
    private final f a;

    private e(Context context) {
        this.a = new f(context);
    }

    public static e a(Context context) {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = new e(context);
                }
            }
        }
        return d;
    }

    public void a() {
        synchronized (c) {
            this.a.a(g.LOGIN_ID, null);
        }
    }

    public void a(Long l) {
        this.a.a(g.LAST_INSTALL, l);
    }

    public void a(String str) {
        this.a.a(g.DEVICE_ID, str);
    }

    public Long b() {
        return (Long) this.a.a(g.LAST_INSTALL);
    }

    public String c() {
        String str;
        synchronized (c) {
            str = (String) this.a.a(g.LOGIN_ID);
        }
        return str;
    }

    public String d() {
        return (String) this.a.a(g.DEVICE_ID);
    }

    public String e() {
        String str;
        synchronized (b) {
            str = (String) this.a.a(g.RANDOM_ID);
        }
        return str;
    }
}
