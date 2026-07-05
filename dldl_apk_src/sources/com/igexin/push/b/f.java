package com.igexin.push.b;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class f extends com.igexin.push.e.b.f {
    private static f c;
    private boolean e;
    private static final String b = b.a + f.class.getName();
    public static final AtomicBoolean a = new AtomicBoolean(false);

    private f() {
        super(10L, (byte) 0);
        this.p = true;
    }

    private void a(long j) {
        a(j, TimeUnit.MILLISECONDS);
    }

    public static synchronized f g() {
        if (c == null) {
            c = new f();
        }
        return c;
    }

    @Override // com.igexin.push.e.b.f
    public final void b() {
        a(b.c, TimeUnit.MILLISECONDS);
        if (this.e) {
            com.igexin.b.a.c.a.a(b + "|detect task already stop", new Object[0]);
            return;
        }
        long j = b.c;
        com.igexin.b.a.c.a.a(b + "|" + (b.c / 1000) + "s passed, do task method, start redect ~~~~", new Object[0]);
        boolean zF = com.igexin.push.f.c.f();
        com.igexin.push.core.e.k = zF;
        if (zF) {
            c.a().c();
            return;
        }
        long j2 = b.c;
        com.igexin.b.a.c.a.a(b + "|" + (b.c / 1000) + "s passed, network is unavailable, stop ###", new Object[0]);
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return 20150607;
    }

    public final void h() {
        this.p = false;
        this.e = true;
        k();
    }
}
