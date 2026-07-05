package com.igexin.b.a.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class b implements com.igexin.b.a.d.a.e {
    private volatile boolean a;
    private long b;
    protected String y = getClass().getName();

    @Override // com.igexin.b.a.d.a.e
    public final void a(boolean z) {
        this.a = !z;
    }

    @Override // com.igexin.b.a.d.a.e
    public final void b(long j) {
        this.b = j;
    }

    @Override // com.igexin.b.a.d.a.e
    public final boolean i() {
        return this.a;
    }

    @Override // com.igexin.b.a.d.a.e
    public final long j() {
        return this.b;
    }
}
