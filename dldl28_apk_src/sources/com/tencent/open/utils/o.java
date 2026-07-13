package com.tencent.open.utils;

import androidx.core.view.MotionEventCompat;
import kotlin.UByte;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public final class o implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1098a;

    public o(byte[] bArr) {
        this(bArr, 0);
    }

    public o(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        this.f1098a = i2;
        this.f1098a = i2 + (bArr[i] & UByte.MAX_VALUE);
    }

    public o(int i) {
        this.f1098a = i;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof o) && this.f1098a == ((o) obj).b();
    }

    public byte[] a() {
        int i = this.f1098a;
        return new byte[]{(byte) (i & 255), (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
    }

    public int b() {
        return this.f1098a;
    }

    public int hashCode() {
        return this.f1098a;
    }
}
