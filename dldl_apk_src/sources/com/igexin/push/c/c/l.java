package com.igexin.push.c.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class l extends c {
    public static final int a = 36;
    public long b;

    public l() {
        this.m = 36;
        this.n = (byte) 20;
    }

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
        this.b = com.igexin.b.a.b.g.d(bArr, 0);
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        byte[] bArr = new byte[8];
        com.igexin.b.a.b.g.a(this.b, bArr, 0);
        return bArr;
    }
}
