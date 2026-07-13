package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bn extends m implements Cloneable {
    static byte[] d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f923a;
    public String b;
    public byte[] c;

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
    }

    public bn() {
        this.f923a = (byte) 0;
        this.b = "";
        this.c = null;
    }

    public bn(byte b, String str, byte[] bArr) {
        this.f923a = b;
        this.b = str;
        this.c = bArr;
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f923a, 0);
        lVar.a(this.b, 1);
        byte[] bArr = this.c;
        if (bArr != null) {
            lVar.a(bArr, 2);
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f923a = kVar.a(this.f923a, 0, true);
        this.b = kVar.b(1, true);
        if (d == null) {
            d = new byte[]{0};
        }
        this.c = kVar.c(2, false);
    }
}
