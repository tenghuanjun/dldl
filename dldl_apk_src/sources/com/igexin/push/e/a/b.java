package com.igexin.push.e.a;

import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class b extends com.igexin.b.a.d.b {
    public static final String d = "HttpPlugin";
    public String e;
    public byte[] f;
    public InputStream g;
    public long h;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    public boolean l;

    public b(String str) {
        com.igexin.b.a.c.a.a("HttpPluginhttp url:".concat(String.valueOf(str)), new Object[0]);
        this.e = str;
    }

    private void a(InputStream inputStream, long j) {
        this.g = inputStream;
        this.h = j;
    }

    private String b() {
        return this.e;
    }

    private void b(byte[] bArr) {
        this.f = bArr;
    }

    private byte[] d() {
        return this.f;
    }

    private InputStream e() {
        return this.g;
    }

    private long f() {
        return this.h;
    }

    @Override // com.igexin.b.a.d.a.a
    public final void a() {
    }

    public void a(Exception exc) {
    }

    public void a(byte[] bArr) throws Exception {
        this.l = false;
        if (bArr == null) {
            return;
        }
        new String(bArr);
        com.igexin.b.a.c.a.a("HttpPluginhttp:responseData: " + new String(bArr), new Object[0]);
        if (bArr.length >= 7 && bArr[5] == 111 && bArr[6] == 107) {
            this.l = true;
        }
    }
}
