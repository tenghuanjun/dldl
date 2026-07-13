package com.mobile.auth.z;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static char[] f820a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, Base64.padSymbol, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String a(String str) {
        try {
            return str.replaceAll("\\+", "%2B");
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static String a(byte[] bArr) {
        String str;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                byte b2 = bArr[i];
                int i3 = b2 & UByte.MAX_VALUE;
                if (i2 == length) {
                    stringBuffer.append(f820a[i3 >>> 2]);
                    stringBuffer.append(f820a[(b2 & 3) << 4]);
                    str = "==";
                } else {
                    int i4 = i + 2;
                    byte b3 = bArr[i2];
                    if (i4 == length) {
                        stringBuffer.append(f820a[i3 >>> 2]);
                        stringBuffer.append(f820a[((b2 & 3) << 4) | ((b3 & 240) >>> 4)]);
                        stringBuffer.append(f820a[(b3 & 15) << 2]);
                        str = "=";
                    } else {
                        i += 3;
                        byte b4 = bArr[i4];
                        stringBuffer.append(f820a[i3 >>> 2]);
                        stringBuffer.append(f820a[((b2 & 3) << 4) | ((b3 & 240) >>> 4)]);
                        stringBuffer.append(f820a[((b3 & 15) << 2) | ((b4 & 192) >>> 6)]);
                        stringBuffer.append(f820a[b4 & Utf8.REPLACEMENT_BYTE]);
                    }
                }
                stringBuffer.append(str);
                break;
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0076, code lost:
    
        if (r2 == (-1)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
    
        r1.write(r2 | ((r5 & 3) << 6));
        r2 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] b(java.lang.String r8) {
        /*
            byte[] r8 = r8.getBytes()     // Catch: java.lang.Throwable -> L87
            int r0 = r8.length     // Catch: java.lang.Throwable -> L87
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L87
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L87
            r2 = 0
        Lb:
            if (r2 >= r0) goto L82
        Ld:
            byte[] r3 = com.mobile.auth.z.j.b     // Catch: java.lang.Throwable -> L87
            int r4 = r2 + 1
            r2 = r8[r2]     // Catch: java.lang.Throwable -> L87
            r2 = r3[r2]     // Catch: java.lang.Throwable -> L87
            r3 = -1
            if (r4 >= r0) goto L1d
            if (r2 == r3) goto L1b
            goto L1d
        L1b:
            r2 = r4
            goto Ld
        L1d:
            if (r2 == r3) goto L82
        L1f:
            byte[] r5 = com.mobile.auth.z.j.b     // Catch: java.lang.Throwable -> L87
            int r6 = r4 + 1
            r4 = r8[r4]     // Catch: java.lang.Throwable -> L87
            r4 = r5[r4]     // Catch: java.lang.Throwable -> L87
            if (r6 >= r0) goto L2e
            if (r4 == r3) goto L2c
            goto L2e
        L2c:
            r4 = r6
            goto L1f
        L2e:
            if (r4 == r3) goto L82
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            r1.write(r2)     // Catch: java.lang.Throwable -> L87
        L3a:
            int r2 = r6 + 1
            r5 = r8[r6]     // Catch: java.lang.Throwable -> L87
            r6 = 61
            if (r5 != r6) goto L47
            byte[] r8 = r1.toByteArray()     // Catch: java.lang.Throwable -> L87
            return r8
        L47:
            byte[] r7 = com.mobile.auth.z.j.b     // Catch: java.lang.Throwable -> L87
            r5 = r7[r5]     // Catch: java.lang.Throwable -> L87
            if (r2 >= r0) goto L52
            if (r5 == r3) goto L50
            goto L52
        L50:
            r6 = r2
            goto L3a
        L52:
            if (r5 == r3) goto L82
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            r1.write(r4)     // Catch: java.lang.Throwable -> L87
        L60:
            int r4 = r2 + 1
            r2 = r8[r2]     // Catch: java.lang.Throwable -> L87
            if (r2 != r6) goto L6b
            byte[] r8 = r1.toByteArray()     // Catch: java.lang.Throwable -> L87
            return r8
        L6b:
            byte[] r7 = com.mobile.auth.z.j.b     // Catch: java.lang.Throwable -> L87
            r2 = r7[r2]     // Catch: java.lang.Throwable -> L87
            if (r4 >= r0) goto L76
            if (r2 == r3) goto L74
            goto L76
        L74:
            r2 = r4
            goto L60
        L76:
            if (r2 == r3) goto L82
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            r1.write(r2)     // Catch: java.lang.Throwable -> L87
            r2 = r4
            goto Lb
        L82:
            byte[] r8 = r1.toByteArray()     // Catch: java.lang.Throwable -> L87
            return r8
        L87:
            r8 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r8)
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.z.j.b(java.lang.String):byte[]");
    }
}
