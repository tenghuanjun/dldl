package com.tencent.open.utils;

import kotlin.KotlinVersion;
import kotlin.UByte;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class o implements Cloneable {
    private int a;

    public o(byte[] bArr) {
        this(bArr, 0);
    }

    public o(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] << 8) & 65280;
        this.a = i2;
        this.a = i2 + (bArr[i] & UByte.MAX_VALUE);
    }

    public o(int i) {
        this.a = i;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof o) && this.a == ((o) obj).b();
    }

    public byte[] a() {
        int i = this.a;
        return new byte[]{(byte) (i & KotlinVersion.MAX_COMPONENT_VALUE), (byte) ((i & 65280) >> 8)};
    }

    public int b() {
        return this.a;
    }

    public int hashCode() {
        return this.a;
    }
}
