package com.igexin.push.c.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public int a;
    public byte b;
    public byte c;
    public byte d;
    public byte[] e;
    public int f;
    public byte g;

    private byte[] a() {
        if (this.e == null) {
            return null;
        }
        byte[] bArr = new byte[this.a + 11];
        com.igexin.b.a.b.g.a(com.igexin.push.f.h.e(), bArr, 0);
        com.igexin.b.a.b.g.a((int) (System.currentTimeMillis() / 1000), bArr, 4);
        com.igexin.b.a.b.g.b(this.a, bArr, 8);
        bArr[10] = this.b;
        byte[] bArr2 = this.e;
        com.igexin.b.a.b.g.a(bArr2, 0, bArr, 11, bArr2.length);
        return bArr;
    }

    public final void a(byte[] bArr) {
        int length;
        if (bArr == null) {
            length = 0;
        } else {
            this.e = bArr;
            length = bArr.length;
        }
        this.a = length;
    }
}
