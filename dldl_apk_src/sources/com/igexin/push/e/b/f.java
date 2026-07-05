package com.igexin.push.e.b;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class f extends com.igexin.b.a.d.f {
    long d;

    private f(long j) {
        super(5);
        this.d = j;
        a(this.d, TimeUnit.MILLISECONDS);
    }

    public f(long j, byte b) {
        this(j);
    }

    protected abstract void b();

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        b();
    }

    @Override // com.igexin.b.a.d.f
    public final void e() {
    }

    @Override // com.igexin.b.a.d.f
    public final void f() {
    }
}
