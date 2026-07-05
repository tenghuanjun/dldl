package com.ss.android.socialbase.appdownloader.f.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private InputStream a;
    private boolean b;
    private int c;

    public d() {
    }

    public d(InputStream inputStream, boolean z) {
        a(inputStream, z);
    }

    public final void a(InputStream inputStream, boolean z) {
        this.a = inputStream;
        this.b = z;
        this.c = 0;
    }

    public final void a() {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            a((InputStream) null, false);
        }
    }

    public final int b() throws IOException {
        return a(4);
    }

    public final int a(int i) throws IOException {
        if (i >= 0 && i <= 4) {
            int i2 = 0;
            if (this.b) {
                for (int i3 = (i - 1) * 8; i3 >= 0; i3 -= 8) {
                    int i4 = this.a.read();
                    if (i4 == -1) {
                        throw new EOFException();
                    }
                    this.c++;
                    i2 |= i4 << i3;
                }
                return i2;
            }
            int i5 = i * 8;
            int i6 = 0;
            while (i2 != i5) {
                int i7 = this.a.read();
                if (i7 == -1) {
                    throw new EOFException();
                }
                this.c++;
                i6 |= i7 << i2;
                i2 += 8;
            }
            return i6;
        }
        throw new IllegalArgumentException();
    }

    public final int[] b(int i) throws IOException {
        int[] iArr = new int[i];
        a(iArr, 0, i);
        return iArr;
    }

    public final void a(int[] iArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            iArr[i] = b();
            i2--;
            i++;
        }
    }

    public final void c(int i) throws IOException {
        if (i > 0) {
            long j = i;
            long jSkip = this.a.skip(j);
            this.c = (int) (((long) this.c) + jSkip);
            if (jSkip != j) {
                throw new EOFException();
            }
        }
    }

    public final void c() throws IOException {
        c(4);
    }
}
