package com.bytedance.pangle.util.b.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    public final byte[] a = new byte[2];
    private final byte[] b = new byte[4];

    public final int a(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.b);
        return b(this.b);
    }

    public final int b(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.a);
        return a(this.a);
    }

    public final void a(ByteArrayOutputStream byteArrayOutputStream, int i) {
        a(this.a, i);
        byteArrayOutputStream.write(this.a);
    }

    public final void a(OutputStream outputStream, int i) throws IOException {
        b(this.b, i);
        outputStream.write(this.b);
    }

    private static int a(byte[] bArr) {
        return ((bArr[1] & UByte.MAX_VALUE) << 8) | (bArr[0] & UByte.MAX_VALUE);
    }

    private static int b(byte[] bArr) {
        return ((((bArr[3] & UByte.MAX_VALUE) << 8) | (bArr[2] & UByte.MAX_VALUE)) << 16) | (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8);
    }

    public static void a(byte[] bArr, int i) {
        bArr[1] = (byte) (i >>> 8);
        bArr[0] = (byte) (i & 255);
    }

    private static void b(byte[] bArr, int i) {
        bArr[3] = (byte) (i >>> 24);
        bArr[2] = (byte) (i >>> 16);
        bArr[1] = (byte) (i >>> 8);
        bArr[0] = (byte) (i & 255);
    }
}
