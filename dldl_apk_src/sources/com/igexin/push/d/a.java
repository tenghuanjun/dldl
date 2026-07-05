package com.igexin.push.d;

import com.igexin.b.a.b.a.a.d;
import com.igexin.b.a.b.a.a.j;
import com.igexin.b.a.b.e;
import com.igexin.push.c.c;
import com.igexin.push.c.c.c;
import com.igexin.push.c.c.f;
import com.igexin.push.c.c.g;
import com.igexin.push.c.c.h;
import com.igexin.push.c.c.i;
import com.igexin.push.c.c.k;
import com.igexin.push.c.c.m;
import com.igexin.push.c.c.p;
import com.igexin.push.c.c.q;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import com.igexin.push.core.k;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a {
    public static String a = "com.igexin.push.d.a";
    public boolean b;

    private int a(String str, c cVar) {
        return a(str, cVar, false);
    }

    public static void a(int i) {
        if (i == j.a) {
            e.a().a(new com.igexin.push.c.b.b());
            e.a().b();
        } else if (i == j.b) {
            e.a().a(new com.igexin.push.c.b.a());
            e.a().b();
        }
    }

    public static void a(c cVar) {
        if (cVar == null) {
            return;
        }
        com.igexin.push.core.a.b.d();
        if (cVar != null) {
            com.igexin.push.core.a.a aVar = com.igexin.push.core.a.b.b.get(cVar.m);
            if ((cVar instanceof h) || (cVar instanceof k) || (cVar instanceof m) || (cVar instanceof p) || (cVar instanceof f) || (cVar instanceof q)) {
                cVar.getClass().getName();
                com.igexin.b.a.c.a.a("CoreAction|receive : " + cVar.getClass().getName() + " resp ~~~~", new Object[0]);
                d.a().a(cVar.getClass().getName());
            }
            if ((cVar instanceof k) || (cVar instanceof m) || (cVar instanceof p)) {
                com.igexin.push.core.e.L = 0L;
                com.igexin.push.b.c.a().d().b();
            }
            if (aVar != null) {
                aVar.a(cVar);
            }
            com.igexin.push.e.b.b.g().h();
        }
    }

    public static void a(boolean z) {
        com.igexin.b.a.c.a.a(a + "|call -> disconnect, reset delay = " + z, new Object[0]);
        if (z) {
            com.igexin.push.core.e.L = 0L;
        }
        d.a().c();
    }

    private void b(boolean z) {
        long j = com.igexin.push.core.e.L;
        com.igexin.b.a.c.a.a(a + "|call setActive, param active = " + z + "; this.active = " + this.b + "; reConnectDelayTime=" + com.igexin.push.core.e.L, new Object[0]);
        boolean z2 = this.b;
        if (z2 == z) {
            if (!z2 || com.igexin.push.core.e.r || com.igexin.push.core.e.L <= com.igexin.push.config.c.j) {
                return;
            }
            com.igexin.b.a.c.a.a(a + "|start active again, online = false, reset delay", new Object[0]);
            com.igexin.push.core.e.L = 0L;
            c();
            return;
        }
        this.b = z;
        if (this.b) {
            com.igexin.b.a.c.a.a(a + "|active = true, start connect~~~~", new Object[0]);
            g();
            return;
        }
        com.igexin.b.a.c.a.a(a + "|active = false, disconnect...", new Object[0]);
        a(true);
    }

    public static void c() {
        com.igexin.push.core.e.L = c.b.a.e.a();
        com.igexin.push.e.b.e.g().a(com.igexin.push.core.e.L);
    }

    public static boolean d() {
        return (com.igexin.push.core.e.m && com.igexin.push.core.e.p) ? false : true;
    }

    public static void e() {
        com.igexin.push.core.k.a().a(k.a.d);
        boolean zF = com.igexin.push.f.c.f();
        boolean z = com.igexin.push.core.e.k;
        com.igexin.b.a.c.a.a(a + "|network changed, available = " + zF + ", last = " + com.igexin.push.core.e.k, new Object[0]);
        c.b.a.a();
        if (!zF) {
            com.igexin.b.a.c.a.a(a + "|network changed, available = false, do nothing", new Object[0]);
            a(false);
        } else if (!com.igexin.push.core.e.k) {
            com.igexin.b.a.c.a.a(a + "|network changed, try connect reset delay", new Object[0]);
            g();
        }
        if (zF) {
            com.igexin.push.b.c.a().c();
        }
        com.igexin.push.core.e.k = zF;
    }

    private boolean f() {
        return this.b;
    }

    private static void g() {
        com.igexin.b.a.c.a.a(a + "|call -> tryConnect and reset delay = 0", new Object[0]);
        a(true);
    }

    private static void h() {
        StringBuilder sb;
        String str;
        com.igexin.push.b.c.a().d().c();
        com.igexin.push.b.a aVarD = com.igexin.push.b.c.a().d();
        com.igexin.push.core.k.a().a(k.a.c);
        aVarD.f();
        if (d()) {
            sb = new StringBuilder();
            sb.append(a);
            str = "|sdkOn = false or pushOn = false, disconect|user";
        } else {
            sb = new StringBuilder();
            sb.append(a);
            str = "|disconnect by network";
        }
        sb.append(str);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        com.igexin.b.a.d.e<com.igexin.b.a.d.f> eVar = e.a().s;
        if (eVar != null) {
            eVar.a(com.igexin.b.a.b.a.a.f.class);
        }
        a(false);
    }

    private static void i() {
        com.igexin.push.c.a.c.b = -1;
        if (com.igexin.push.core.e.n) {
            com.igexin.b.a.c.a.a(a + "|isAppidWrong = true", new Object[0]);
            com.igexin.b.a.c.a.d.a().a("isAppidWrong = true");
            return;
        }
        if (!com.igexin.push.f.h.a()) {
            com.igexin.b.a.c.a.a(a + "|so error ++++++++", new Object[0]);
            return;
        }
        if (com.igexin.push.core.e.aw) {
            c();
            return;
        }
        com.igexin.b.a.c.a.a(a + "|initSuccess = false", new Object[0]);
    }

    public final int a(String str, com.igexin.push.c.c.c cVar, boolean z) {
        if (str == null || cVar == null) {
            return -1;
        }
        if (!com.igexin.push.core.e.r && !(cVar instanceof g) && !(cVar instanceof i) && !(cVar instanceof com.igexin.push.c.c.d)) {
            com.igexin.b.a.c.a.a("networkLayer|sendData|not online|" + cVar.getClass().getName(), new Object[0]);
            return -3;
        }
        if (this.b) {
            if (z) {
                if (e.a().a(SDKUrlConfig.getConnectAddress(), d.a.a.h, cVar, com.igexin.push.config.d.e > 0 ? com.igexin.push.config.d.e : 10, new com.igexin.push.c.f()) == null) {
                    return -2;
                }
            } else if (e.a().a(SDKUrlConfig.getConnectAddress(), d.a.a.h, cVar) == null) {
                return -2;
            }
        }
        return 0;
    }

    public final void a() {
        boolean z = com.igexin.push.core.e.m;
        boolean z2 = com.igexin.push.core.e.p;
        boolean zA = com.igexin.push.f.c.a(System.currentTimeMillis());
        boolean zB = com.igexin.push.f.c.b();
        if (z && z2 && !zA && zB) {
            b(true);
        }
    }

    public final void b() {
        b(false);
        if (com.igexin.push.core.e.r) {
            com.igexin.push.core.e.r = false;
            com.igexin.push.core.m.a().b();
        }
        com.igexin.b.a.c.a.a(a + "|stop by user", new Object[0]);
        com.igexin.push.b.c.a().d().f();
    }
}
