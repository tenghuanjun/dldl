package com.igexin.push.c.c;

import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class k extends c {
    public static final int a = 5;
    public boolean b;
    public boolean c;
    public String d;
    public String e;
    public long f;

    public k() {
        this.m = 5;
    }

    @Override // com.igexin.push.c.c.c, com.igexin.b.a.d.a.a
    public final void a() {
        this.e = null;
        this.d = null;
    }

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
        byte b = bArr[0];
        int i = 1;
        this.b = (b & 64) != 0;
        this.c = (b & ByteCompanionObject.MIN_VALUE) != 0;
        if (this.c) {
            this.d = a(b);
            int iB = com.igexin.b.a.b.g.b(bArr, 1);
            i = 1 + iB + 2;
            try {
                this.e = new String(bArr, 3, iB, this.d);
            } catch (Exception unused) {
            }
        }
        if (bArr.length > i) {
            this.f = com.igexin.b.a.b.g.d(bArr, i);
            com.igexin.b.a.c.a.a("LoginResult|session = " + this.f, new Object[0]);
        }
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        int i;
        int i2;
        byte bA = this.b ? (byte) 64 : (byte) 0;
        int iB = 1;
        byte[] bytes = null;
        if (this.c) {
            byte b = (byte) (bA | ByteCompanionObject.MIN_VALUE);
            try {
                bytes = this.e.getBytes(this.d);
                int length = bytes.length;
                i2 = length;
                i = length + 3;
            } catch (Exception unused) {
                i = 3;
                i2 = 0;
            }
            bA = (byte) (b | a(this.d));
        } else {
            i = 1;
            i2 = 0;
        }
        byte[] bArr = new byte[i + 8];
        bArr[0] = bA;
        if (this.c) {
            iB = com.igexin.b.a.b.g.b(i2, bArr, 1);
            if (bytes != null) {
                iB = com.igexin.b.a.b.g.a(bytes, 0, bArr, 2, i2) + 2;
            }
        }
        com.igexin.b.a.b.g.a(this.f, bArr, iB);
        return bArr;
    }
}
