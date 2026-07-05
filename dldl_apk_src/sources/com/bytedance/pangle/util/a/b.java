package com.bytedance.pangle.util.a;

import com.bytedance.pangle.util.f;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    private static String a = "";

    public static String[] a(File file) throws Throwable {
        String str;
        ByteBuffer byteBufferB;
        boolean z;
        String strA = "";
        try {
            byteBufferB = b(file);
        } catch (Exception unused) {
            str = "";
        }
        if (byteBufferB.order() == ByteOrder.LITTLE_ENDIAN) {
            ByteBuffer byteBufferA = a(byteBufferB, byteBufferB.capacity() - 24);
            int i = 0;
            while (byteBufferA.hasRemaining()) {
                i++;
                if (byteBufferA.remaining() < 8) {
                    throw new Exception("Insufficient data to read size of APK Signing Block entry #".concat(String.valueOf(i)));
                }
                long j = byteBufferA.getLong();
                if (j < 4 || j > 2147483647L) {
                    throw new Exception("APK Signing Block entry #" + i + " size out of range: " + j);
                }
                int i2 = (int) j;
                int iPosition = byteBufferA.position() + i2;
                if (i2 > byteBufferA.remaining()) {
                    throw new Exception("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + byteBufferA.remaining());
                }
                int i3 = byteBufferA.getInt();
                if (i3 == -262969152) {
                    a = "V3";
                } else if (i3 == 1896449818) {
                    a = "V2";
                } else {
                    byteBufferA.position(iPosition);
                }
                z = true;
                break;
            }
            z = false;
            if (z) {
                strA = f.a(byteBufferB.array());
                str = "";
            } else {
                str = "without v2 & v3 signature.";
            }
            return new String[]{strA, a, str};
        }
        throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.nio.ByteBuffer b(java.io.File r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.util.a.b.b(java.io.File):java.nio.ByteBuffer");
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i) {
        if (i < 8) {
            throw new IllegalArgumentException("end < start: " + i + " < 8");
        }
        int iCapacity = byteBuffer.capacity();
        if (i > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i + " > " + iCapacity);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i);
            byteBuffer.position(8);
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            return byteBufferSlice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
        }
    }
}
