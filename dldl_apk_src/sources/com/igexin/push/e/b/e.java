package com.igexin.push.e.b;

import android.os.SystemClock;
import android.text.TextUtils;
import com.igexin.push.c.c.g;
import com.igexin.push.core.d;
import com.igexin.push.core.e.e.AnonymousClass16;
import com.igexin.push.core.l;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e extends f {
    public static final int b = -2147483641;
    private static final String c = "RNTT";
    private static e e;
    public long a;
    private long f;

    private e() {
        super(com.igexin.push.config.c.g, (byte) 0);
        this.p = true;
        this.f = System.currentTimeMillis();
        this.a = SystemClock.elapsedRealtime();
    }

    private void c(long j) {
        this.a = j;
    }

    public static synchronized e g() {
        if (e == null) {
            e = new e();
        }
        return e;
    }

    private void h() {
        a(com.igexin.push.core.e.L);
    }

    public final void a(long j) {
        com.igexin.b.a.c.a.a("RNTT|refreshDelayTime, delay = ".concat(String.valueOf(j)), new Object[0]);
        a(j, TimeUnit.MILLISECONDS);
    }

    @Override // com.igexin.push.e.b.f
    protected final void b() {
        String strConcat;
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.m();
        boolean zA = com.igexin.push.f.c.a(System.currentTimeMillis());
        boolean zB = com.igexin.push.f.c.b();
        com.igexin.push.core.e.k = com.igexin.push.f.c.f();
        boolean z = com.igexin.push.core.e.r;
        boolean z2 = com.igexin.push.core.e.m;
        boolean z3 = com.igexin.push.core.e.p;
        com.igexin.b.a.c.a.a("RNTT|networkAvailable = " + com.igexin.push.core.e.k + "|,sdkOnline = " + com.igexin.push.core.e.r + ", sdkOn= " + com.igexin.push.core.e.m + ", pushOn =" + com.igexin.push.core.e.p + ", isSilentTime= " + zA + ", blockEndTime= " + zB, new Object[0]);
        if (!com.igexin.push.core.e.k || !com.igexin.push.core.e.m || !com.igexin.push.core.e.p || com.igexin.push.core.e.r || zA || !zB) {
            com.igexin.b.a.c.a.a("RNTT reconnect timer task stop, connect interval= 20min #######", new Object[0]);
            a(com.igexin.push.config.c.g, TimeUnit.MILLISECONDS);
            return;
        }
        if (!com.igexin.push.f.c.g() && TextUtils.isEmpty(com.igexin.push.core.e.x)) {
            a(900000L, TimeUnit.MILLISECONDS);
            com.igexin.b.a.c.a.a("RNTT|date is error, set connect interval = 15min", new Object[0]);
            return;
        }
        com.igexin.b.a.c.a.a("RNTT reconnect timer task isOnline = false, try login...", new Object[0]);
        if (System.currentTimeMillis() - this.f < 2500) {
            com.igexin.push.core.e.o++;
        }
        if (com.igexin.push.core.e.o > 30 && Math.abs(SystemClock.elapsedRealtime() - this.a) < 72000.0d) {
            com.igexin.push.core.e.e.a();
            String str = com.igexin.push.core.e.x;
            com.igexin.b.a.c.a.a(com.igexin.push.core.e.e.a + "| found a duplicate cid " + com.igexin.push.core.e.x, new Object[0]);
            com.igexin.push.core.e.I = null;
            com.igexin.push.core.e.e.e();
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.core.e.e.a().new AnonymousClass16(com.igexin.push.core.e.I), false, true);
            com.igexin.push.core.e.e.a().c();
            com.igexin.push.core.e.o = 0;
            g().a = SystemClock.elapsedRealtime();
        }
        this.f = System.currentTimeMillis();
        l.a();
        if (com.igexin.push.core.e.p && !com.igexin.push.f.c.a(System.currentTimeMillis()) && com.igexin.push.f.c.b()) {
            g gVar = new g();
            gVar.b = com.igexin.push.core.e.a;
            strConcat = "LoginInteractor|keyNegotiate result=".concat(String.valueOf(d.a.a.i.a("K-", gVar, true)));
        } else {
            strConcat = "LoginInteractor|keyNegotiate stop ++++++++++";
        }
        com.igexin.b.a.c.a.a(strConcat, new Object[0]);
        a(1800000L, TimeUnit.MILLISECONDS);
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return b;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d() {
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d_() {
        super.d_();
    }
}
