package com.igexin.push.e.b;

import com.igexin.push.c.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d extends f {
    public static final int a = 20160629;
    public static final long b = 604800000;
    private static final String c = "PollingTimerTask";
    private long e;
    private AtomicBoolean f;

    public static class a {
        private static final d a = new d();

        private a() {
        }
    }

    public d() {
        super(b, (byte) 0);
        this.e = com.igexin.push.config.d.C;
        this.f = new AtomicBoolean(false);
        this.p = true;
    }

    private void a(long j) {
        a(j, TimeUnit.MILLISECONDS);
    }

    private static d p() {
        return a.a;
    }

    @Override // com.igexin.push.e.b.f
    protected final void b() {
        a(this.e, TimeUnit.MILLISECONDS);
        boolean zA = com.igexin.push.f.c.a(System.currentTimeMillis());
        if (!com.igexin.push.core.e.r && com.igexin.push.core.e.k && com.igexin.push.core.e.m && com.igexin.push.core.e.p && !zA && com.igexin.push.f.c.b()) {
            com.igexin.b.a.c.a.a("PollingTimerTask|run = true", new Object[0]);
            com.igexin.push.c.c cVar = c.b.a;
            if (cVar.b && cVar.e != null && !(cVar.e instanceof com.igexin.push.c.d)) {
                cVar.e = new com.igexin.push.c.d();
            }
            com.igexin.push.core.e.L = 100L;
            e.g().a(com.igexin.push.core.e.L);
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return a;
    }

    public final void g() {
        if (!this.f.get()) {
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) this, false, true);
            this.f.set(true);
        }
        a(this.e);
    }

    public final void h() {
        a(b, TimeUnit.MILLISECONDS);
    }
}
