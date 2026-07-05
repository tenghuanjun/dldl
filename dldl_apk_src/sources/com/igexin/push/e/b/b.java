package com.igexin.push.e.b;

import com.igexin.push.core.k;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends f {
    public static final int a = -2147483642;
    private static final String b = "HeartBeatTimerTask";
    private static b c;

    public b() {
        super(k.a().b(), (byte) 0);
        this.p = true;
    }

    public static b g() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    private static void p() {
    }

    @Override // com.igexin.push.e.b.f
    protected final void b() {
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.m();
        com.igexin.push.core.e.M = System.currentTimeMillis();
        if (!com.igexin.push.core.e.r) {
            com.igexin.b.a.c.a.a("HeartBeatTimerTask doTaskMethod isOnline = false, refresh wait time !!!!!!", new Object[0]);
            h();
        } else {
            System.currentTimeMillis();
            com.igexin.b.a.c.a.a("heartbeatReq", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.f();
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return a;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d() {
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d_() {
        super.d_();
        if (this.m) {
            return;
        }
        h();
    }

    public final void h() {
        a(k.a().b(), TimeUnit.MILLISECONDS);
    }
}
