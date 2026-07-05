package com.bytedance.pangle.f.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    InputStream a;
    private int b;

    public b(InputStream inputStream) {
        a(inputStream);
    }

    final void a(InputStream inputStream) {
        this.a = inputStream;
        this.b = 0;
    }

    public final int a() throws IOException {
        int i = 0;
        for (int i2 = 0; i2 != 32; i2 += 8) {
            int i3 = this.a.read();
            if (i3 == -1) {
                throw new EOFException();
            }
            this.b++;
            i |= i3 << i2;
        }
        return i;
    }

    public final int[] a(int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i > 0) {
            iArr[i2] = a();
            i--;
            i2++;
        }
        return iArr;
    }

    public final void b() throws IOException {
        long jSkip = this.a.skip(4L);
        this.b = (int) (((long) this.b) + jSkip);
        if (jSkip != 4) {
            throw new EOFException();
        }
    }

    public final void b(int i) throws IOException {
        int iA = a();
        if (iA != i) {
            throw new IOException(String.format("Expected: 0x%08x got: 0x%08x", Integer.valueOf(i), Integer.valueOf(iA)));
        }
    }
}
