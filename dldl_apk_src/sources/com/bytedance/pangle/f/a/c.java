package com.bytedance.pangle.f.a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class c {
    int[] a = new int[32];
    int b;
    int c;

    public final void a() {
        b();
        int i = this.b;
        int[] iArr = this.a;
        iArr[i] = 0;
        iArr[i + 1] = 0;
        this.b = i + 2;
        this.c++;
    }

    final void b() {
        int[] iArr = this.a;
        int length = iArr.length;
        int i = this.b;
        int i2 = length - i;
        if (i2 <= 2) {
            int[] iArr2 = new int[(iArr.length + i2) * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.a = iArr2;
        }
    }
}
