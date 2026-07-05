package com.ss.android.socialbase.appdownloader.f.a;

import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class f {
    private int[] a;
    private int[] b;

    public static f a(d dVar) throws IOException {
        b.a(dVar, 1835009);
        int iB = dVar.b();
        int iB2 = dVar.b();
        int iB3 = dVar.b();
        dVar.b();
        int iB4 = dVar.b();
        int iB5 = dVar.b();
        f fVar = new f();
        fVar.a = dVar.b(iB2);
        if (iB3 != 0) {
            dVar.b(iB3);
        }
        int i = (iB5 == 0 ? iB : iB5) - iB4;
        if (i % 4 != 0) {
            throw new IOException("String data size is not multiple of 4 (" + i + ").");
        }
        fVar.b = dVar.b(i / 4);
        if (iB5 != 0) {
            int i2 = iB - iB5;
            if (i2 % 4 != 0) {
                throw new IOException("Style data size is not multiple of 4 (" + i2 + ").");
            }
            dVar.b(i2 / 4);
        }
        return fVar;
    }

    public String a(int i) {
        int[] iArr;
        if (i < 0 || (iArr = this.a) == null || i >= iArr.length) {
            return null;
        }
        int i2 = iArr[i];
        int iA = a(this.b, i2);
        StringBuilder sb = new StringBuilder(iA);
        while (iA != 0) {
            i2 += 2;
            sb.append((char) a(this.b, i2));
            iA--;
        }
        return sb.toString();
    }

    private f() {
    }

    private static final int a(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        return (i % 4) / 2 == 0 ? i2 & 65535 : i2 >>> 16;
    }
}
