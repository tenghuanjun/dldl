package com.igexin.push.c.c;

import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d extends c {
    public static final int a = 6;
    String b;
    String c;
    String d;
    String e;

    public d() {
        this.m = 6;
        this.n = (byte) 20;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
    }

    public d(String str, String str2, String str3, String str4) {
        this.m = 6;
        this.n = (byte) 20;
        this.b = str == null ? "" : str;
        this.c = str2 == null ? "" : str2;
        this.d = str3 == null ? "" : str3;
        this.e = str4 == null ? "" : str4;
    }

    private String d() {
        return this.d;
    }

    @Override // com.igexin.push.c.c.c, com.igexin.b.a.d.a.a
    public final void a() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
        try {
            int i = bArr[0] & UByte.MAX_VALUE;
            this.b = new String(bArr, 1, i, "utf-8");
            int i2 = i + 1;
            int i3 = bArr[i2] & UByte.MAX_VALUE;
            int i4 = i2 + 1;
            this.c = new String(bArr, i4, i3, "utf-8");
            int i5 = i4 + i3;
            int i6 = bArr[i5] & UByte.MAX_VALUE;
            int i7 = i5 + 1;
            this.d = new String(bArr, i7, i6, "utf-8");
            int i8 = i7 + i6;
            this.e = new String(bArr, i8 + 1, bArr[i8] & UByte.MAX_VALUE, "utf-8");
        } catch (Exception unused) {
        }
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        byte[] bytes = this.c.getBytes();
        byte[] bytes2 = this.b.getBytes();
        byte[] bytes3 = this.d.getBytes();
        byte[] bytes4 = this.e.getBytes();
        byte[] bArr = new byte[bytes.length + bytes2.length + bytes3.length + bytes4.length + 4];
        bArr[0] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        int length = bytes.length + 1;
        int i = length + 1;
        bArr[length] = (byte) bytes2.length;
        System.arraycopy(bytes2, 0, bArr, i, bytes2.length);
        int length2 = i + bytes2.length;
        int i2 = length2 + 1;
        bArr[length2] = (byte) bytes3.length;
        System.arraycopy(bytes3, 0, bArr, i2, bytes3.length);
        int length3 = i2 + bytes3.length;
        bArr[length3] = (byte) bytes4.length;
        System.arraycopy(bytes4, 0, bArr, length3 + 1, bytes4.length);
        return bArr;
    }
}
