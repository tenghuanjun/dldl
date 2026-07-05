package com.igexin.b.a.b;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends OutputStream {
    private OutputStream a;
    private int b;
    private int c;
    private int d;
    private int e;

    private b(OutputStream outputStream) {
        this(outputStream, 76);
    }

    public b(OutputStream outputStream, int i) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.a = outputStream;
        this.e = i;
    }

    protected final void a() throws IOException {
        if (this.c > 0) {
            int i = this.e;
            if (i > 0 && this.d == i) {
                this.a.write("\r\n".getBytes());
                this.d = 0;
            }
            char cCharAt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 8) >>> 26);
            char cCharAt2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 14) >>> 26);
            char cCharAt3 = this.c < 2 ? '=' : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 20) >>> 26);
            char cCharAt4 = this.c >= 3 ? "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((this.b << 26) >>> 26) : '=';
            this.a.write(cCharAt);
            this.a.write(cCharAt2);
            this.a.write(cCharAt3);
            this.a.write(cCharAt4);
            this.d += 4;
            this.c = 0;
            this.b = 0;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        a();
        this.a.close();
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        int i2 = this.c;
        this.b = ((i & 255) << (16 - (i2 * 8))) | this.b;
        this.c = i2 + 1;
        if (this.c == 3) {
            a();
        }
    }
}
