package com.igexin.push.b;

import com.igexin.push.b.a;
import com.igexin.push.b.b;
import com.igexin.push.config.SDKUrlConfig;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c {
    private static final String a = b.a + c.class.getName();
    private static c b;
    private static int c;

    private c() {
        c = com.igexin.push.f.c.c() ? b.EnumC0059b.a : b.EnumC0059b.b;
    }

    public static synchronized c a() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    public static void b() {
        if (SDKUrlConfig.hasMultipleXfr()) {
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) f.g(), false, true);
            return;
        }
        com.igexin.b.a.c.a.a(a + "|xfr len = 1, detect = false", new Object[0]);
    }

    public final void c() {
        if (SDKUrlConfig.hasMultipleXfr()) {
            try {
                f().e();
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a(a + "|" + th.toString(), new Object[0]);
            }
        }
    }

    public final a d() {
        return f().d;
    }

    public final void e() {
        StringBuilder sb;
        if (SDKUrlConfig.hasMultipleXfr()) {
            try {
                j.a();
                j.k();
                j.a().g();
                g.a().g();
                h hVarF = f();
                if (hVarF != null) {
                    hVarF.i();
                    return;
                }
                return;
            } catch (Throwable th) {
                th = th;
                sb = new StringBuilder();
            }
        } else {
            if (SDKUrlConfig.getXfrAddress().length == 1 && e.a != null) {
                try {
                    e.a.shutdownNow();
                    e.a = null;
                } catch (Throwable unused) {
                }
            }
            f.g().h();
            try {
                g.a().d.a((List<a.b>) null);
                j.a().d.a((List<a.b>) null);
                j.a().h();
                g.a().h();
                j.a();
                j.k();
                return;
            } catch (Throwable th2) {
                th = th2;
                sb = new StringBuilder();
            }
        }
        sb.append(a);
        sb.append("|");
        sb.append(th.toString());
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
    }

    public final synchronized h f() {
        h hVarA;
        hVarA = com.igexin.push.f.c.c() ? j.a() : g.a();
        int iC = hVarA.c();
        if (iC != c) {
            if (iC == b.EnumC0059b.a) {
                g.a().f();
            } else if (iC == b.EnumC0059b.b) {
                j.a().f();
            }
        }
        c = iC;
        return hVarA;
    }
}
