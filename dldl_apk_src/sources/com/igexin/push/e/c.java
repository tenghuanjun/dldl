package com.igexin.push.e;

import com.igexin.push.core.h;
import com.igexin.push.core.o;
import com.igexin.sdk.main.FeedbackImpl;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c implements com.igexin.push.e.b.c {
    public static final String a = "com.igexin.push.e.c";
    private static final long c = 3600000;
    public long b = 0;

    @Override // com.igexin.push.e.b.c
    public final void a() {
        com.igexin.b.a.c.a.a("start cron-keep task", new Object[0]);
        FeedbackImpl.getInstance().clearFeedbackMessage();
        o.a().c();
        o.a().f();
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.j();
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.l();
        h.a().b();
        h.a().c();
    }

    @Override // com.igexin.push.e.b.c
    public final void a(long j) {
        this.b = j;
    }

    @Override // com.igexin.push.e.b.c
    public final boolean b() {
        return System.currentTimeMillis() - this.b > 3600000;
    }
}
