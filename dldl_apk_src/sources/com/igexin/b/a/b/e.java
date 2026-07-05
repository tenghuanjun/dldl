package com.igexin.b.a.b;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class e extends com.igexin.b.a.d.g {
    private static volatile e I;
    public volatile long a;
    public volatile long b;
    public volatile long c;
    public volatile long d;
    public byte[] e;
    public byte[] f;
    public com.igexin.b.a.d.a.b<String, Integer, d, f> g;

    private e() {
    }

    public static e a() {
        if (I == null) {
            synchronized (e.class) {
                if (I == null) {
                    I = new e();
                }
            }
        }
        return I;
    }

    private f a(String str, int i, d dVar) {
        return a(str, i, dVar, null, false, -1, -1L, (byte) 0, null, null);
    }

    private f a(String str, int i, d dVar, Object obj) {
        return a(str, i, dVar, obj, false, -1, -1L, (byte) 0, null, null);
    }

    private f a(String str, int i, d dVar, Object obj, byte b) {
        return a(str, i, dVar, obj, false, -1, -1L, b, null, null);
    }

    private f a(String str, int i, d dVar, Object obj, int i2) {
        return a(str, i, dVar, obj, false, i2, -1L, (byte) 0, null, null);
    }

    private f a(String str, int i, d dVar, Object obj, long j) {
        return a(str, i, dVar, obj, false, -1, j, (byte) 0, null, null);
    }

    private f a(String str, int i, d dVar, Object obj, com.igexin.b.a.d.a.d dVar2) {
        return a(str, i, dVar, obj, false, -1, -1L, (byte) 0, null, dVar2);
    }

    private f a(String str, int i, d dVar, Object obj, Object obj2) {
        return a(str, i, dVar, obj, false, -1, -1L, (byte) 0, obj2, null);
    }

    private f a(String str, int i, d dVar, Object obj, boolean z, int i2, long j, byte b, Object obj2, com.igexin.b.a.d.a.d dVar2) {
        return a(str, i, dVar, obj, z, i2, j, b, obj2, dVar2, 0, null);
    }

    private f a(String str, int i, d dVar, Object obj, boolean z, int i2, long j, byte b, Object obj2, com.igexin.b.a.d.a.d dVar2, int i3, com.igexin.b.a.d.a.g gVar) {
        com.igexin.b.a.d.a.b<String, Integer, d, f> bVar = this.g;
        if (bVar == null) {
            return null;
        }
        Integer.valueOf(i);
        f fVar = (f) bVar.a(str, dVar);
        if (fVar == null || fVar.m()) {
            return null;
        }
        if (gVar != null) {
            fVar.a(i3, gVar);
        }
        a(fVar, obj, z, i2, j, b, obj2, dVar2);
        return fVar;
    }

    private void a(com.igexin.b.a.d.a.b<String, Integer, d, f> bVar) {
        this.g = bVar;
    }

    private void a(byte[] bArr) {
        this.e = bArr;
        this.f = com.igexin.b.b.a.a(bArr);
        byte[] bArr2 = this.f;
        if (bArr2 != null) {
            new String(bArr2);
        }
    }

    private boolean a(f fVar, Object obj, boolean z, int i, long j, byte b, Object obj2, com.igexin.b.a.d.a.d dVar) {
        fVar.d = obj;
        fVar.a(j, TimeUnit.MILLISECONDS);
        fVar.A = i;
        fVar.a((int) b);
        fVar.F = obj2;
        fVar.a(dVar);
        return a(fVar, z);
    }

    public static void c() {
        I.a = 0L;
        I.c = 0L;
        I.b = 0L;
        I.d = 0L;
    }

    private byte[] g() {
        return this.e;
    }

    private byte[] h() {
        return this.f;
    }

    private static void i() {
        I = null;
    }

    public final f a(String str, d dVar, Object obj) {
        return a(str, 3, dVar, obj, true, -1, -1L, (byte) 0, null, null);
    }

    public final f a(String str, d dVar, Object obj, int i, com.igexin.b.a.d.a.g gVar) {
        return a(str, 3, dVar, obj, true, -1, -1L, (byte) 0, null, null, i, gVar);
    }

    public final void b() {
        e();
    }
}
