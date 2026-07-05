package com.mobile.auth.b;

import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c {
    private static final String a = c.class.getSimpleName();
    private final int[] b = {1732584193, -271733879, -1732584194, 271733878, -1009589776};
    private int[] c = new int[5];
    private int[] d = new int[80];

    private int a(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private int a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    private int a(byte[] bArr, int i) {
        try {
            return (bArr[i + 3] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 24) | ((bArr[i + 1] & UByte.MAX_VALUE) << 16) | ((bArr[i + 2] & UByte.MAX_VALUE) << 8);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1;
            }
        }
    }

    private void a() {
        for (int i = 16; i <= 79; i++) {
            try {
                this.d[i] = a(((this.d[i - 3] ^ this.d[i - 8]) ^ this.d[i - 14]) ^ this.d[i - 16], 1);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return;
                }
            }
        }
        int[] iArr = new int[5];
        for (int i2 = 0; i2 < 5; i2++) {
            iArr[i2] = this.c[i2];
        }
        for (int i3 = 0; i3 <= 19; i3++) {
            int iA = a(iArr[0], 5) + a(iArr[1], iArr[2], iArr[3]) + iArr[4] + this.d[i3] + 1518500249;
            iArr[4] = iArr[3];
            iArr[3] = iArr[2];
            iArr[2] = a(iArr[1], 30);
            iArr[1] = iArr[0];
            iArr[0] = iA;
        }
        for (int i4 = 20; i4 <= 39; i4++) {
            int iA2 = a(iArr[0], 5) + b(iArr[1], iArr[2], iArr[3]) + iArr[4] + this.d[i4] + 1859775393;
            iArr[4] = iArr[3];
            iArr[3] = iArr[2];
            iArr[2] = a(iArr[1], 30);
            iArr[1] = iArr[0];
            iArr[0] = iA2;
        }
        for (int i5 = 40; i5 <= 59; i5++) {
            int iA3 = (((a(iArr[0], 5) + c(iArr[1], iArr[2], iArr[3])) + iArr[4]) + this.d[i5]) - 1894007588;
            iArr[4] = iArr[3];
            iArr[3] = iArr[2];
            iArr[2] = a(iArr[1], 30);
            iArr[1] = iArr[0];
            iArr[0] = iA3;
        }
        for (int i6 = 60; i6 <= 79; i6++) {
            int iA4 = (((a(iArr[0], 5) + b(iArr[1], iArr[2], iArr[3])) + iArr[4]) + this.d[i6]) - 899497514;
            iArr[4] = iArr[3];
            iArr[3] = iArr[2];
            iArr[2] = a(iArr[1], 30);
            iArr[1] = iArr[0];
            iArr[0] = iA4;
        }
        for (int i7 = 0; i7 < 5; i7++) {
            this.c[i7] = this.c[i7] + iArr[i7];
        }
        for (int i8 = 0; i8 < this.d.length; i8++) {
            this.d[i8] = 0;
        }
    }

    private void a(int i, byte[] bArr, int i2) {
        try {
            bArr[i2] = (byte) (i >>> 24);
            bArr[i2 + 1] = (byte) (i >>> 16);
            bArr[i2 + 2] = (byte) (i >>> 8);
            bArr[i2 + 3] = (byte) i;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public static byte[] a(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                com.mobile.auth.a.a.a(a, "when getHmacSHA1,the key is null");
                return null;
            }
            try {
                byte[] bArr = new byte[64];
                byte[] bArr2 = new byte[64];
                byte[] bArr3 = new byte[64];
                int length = str2.length();
                c cVar = new c();
                if (str2.length() > 64) {
                    byte[] bArrA = cVar.a(e.b(str2));
                    length = bArrA.length;
                    for (int i = 0; i < length; i++) {
                        bArr3[i] = bArrA[i];
                    }
                } else {
                    byte[] bArrB = e.b(str2);
                    for (int i2 = 0; i2 < bArrB.length; i2++) {
                        bArr3[i2] = bArrB[i2];
                    }
                }
                while (length < 64) {
                    bArr3[length] = 0;
                    length++;
                }
                for (int i3 = 0; i3 < 64; i3++) {
                    bArr[i3] = (byte) (bArr3[i3] ^ 54);
                    bArr2[i3] = (byte) (bArr3[i3] ^ 92);
                }
                return cVar.a(a(bArr2, cVar.a(a(bArr, e.b(str)))));
            } catch (Throwable th) {
                com.mobile.auth.a.a.a(a, "getHmacSHA1 error", th);
                return null;
            }
        } catch (Throwable th2) {
            try {
                ExceptionProcessor.processException(th2);
                return null;
            } catch (Throwable th3) {
                ExceptionProcessor.processException(th3);
                return null;
            }
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            byte[] bArr3 = new byte[bArr.length + bArr2.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr3[i] = bArr[i];
            }
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr3[bArr.length + i2] = bArr2[i2];
            }
            return bArr3;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private int b(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    public static String b(byte[] bArr) {
        try {
            StringBuilder sb = new StringBuilder("");
            if (bArr != null && bArr.length > 0) {
                for (byte b : bArr) {
                    String upperCase = Integer.toHexString(b & UByte.MAX_VALUE).toUpperCase(Locale.CHINA);
                    if (upperCase.length() < 2) {
                        sb.append(0);
                    }
                    sb.append(upperCase);
                }
                return sb.toString();
            }
            return null;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private int c(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int c(byte[] bArr) {
        try {
            System.arraycopy(this.b, 0, this.c, 0, this.b.length);
            byte[] bArrD = d(bArr);
            int length = bArrD.length / 64;
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < 16; i2++) {
                    this.d[i2] = a(bArrD, (i * 64) + (i2 * 4));
                }
                a();
            }
            return 20;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b A[Catch: all -> 0x008b, LOOP:0: B:10:0x0029->B:11:0x002b, LOOP_END, TryCatch #1 {all -> 0x008b, blocks: (B:2:0x0000, B:4:0x0009, B:5:0x000d, B:9:0x001c, B:11:0x002b, B:12:0x0033, B:7:0x0012, B:8:0x0015), top: B:23:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] d(byte[] r15) {
        /*
            r14 = this;
            int r0 = r15.length     // Catch: java.lang.Throwable -> L8b
            int r1 = r0 % 64
            r2 = 63
            r3 = 56
            if (r1 >= r3) goto L10
            int r2 = 55 - r1
            int r1 = r0 - r1
        Ld:
            int r1 = r1 + 64
            goto L1c
        L10:
            if (r1 != r3) goto L15
            int r1 = r0 + 8
            goto Ld
        L15:
            int r2 = r2 - r1
            int r2 = r2 + r3
            int r4 = r0 + 64
            int r4 = r4 - r1
            int r1 = r4 + 64
        L1c:
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L8b
            r4 = 0
            java.lang.System.arraycopy(r15, r4, r1, r4, r0)     // Catch: java.lang.Throwable -> L8b
            int r15 = r0 + 1
            r5 = -128(0xffffffffffffff80, float:NaN)
            r1[r0] = r5     // Catch: java.lang.Throwable -> L8b
            r5 = 0
        L29:
            if (r5 >= r2) goto L33
            int r6 = r15 + 1
            r1[r15] = r4     // Catch: java.lang.Throwable -> L8b
            int r5 = r5 + 1
            r15 = r6
            goto L29
        L33:
            long r4 = (long) r0     // Catch: java.lang.Throwable -> L8b
            r6 = 8
            long r4 = r4 * r6
            r6 = 255(0xff, double:1.26E-321)
            long r8 = r4 & r6
            int r0 = (int) r8     // Catch: java.lang.Throwable -> L8b
            byte r0 = (byte) r0     // Catch: java.lang.Throwable -> L8b
            r2 = 8
            long r8 = r4 >> r2
            long r8 = r8 & r6
            int r2 = (int) r8     // Catch: java.lang.Throwable -> L8b
            byte r2 = (byte) r2     // Catch: java.lang.Throwable -> L8b
            r8 = 16
            long r8 = r4 >> r8
            long r8 = r8 & r6
            int r9 = (int) r8     // Catch: java.lang.Throwable -> L8b
            byte r8 = (byte) r9     // Catch: java.lang.Throwable -> L8b
            r9 = 24
            long r9 = r4 >> r9
            long r9 = r9 & r6
            int r10 = (int) r9     // Catch: java.lang.Throwable -> L8b
            byte r9 = (byte) r10     // Catch: java.lang.Throwable -> L8b
            r10 = 32
            long r10 = r4 >> r10
            long r10 = r10 & r6
            int r11 = (int) r10     // Catch: java.lang.Throwable -> L8b
            byte r10 = (byte) r11     // Catch: java.lang.Throwable -> L8b
            r11 = 40
            long r11 = r4 >> r11
            long r11 = r11 & r6
            int r12 = (int) r11     // Catch: java.lang.Throwable -> L8b
            byte r11 = (byte) r12     // Catch: java.lang.Throwable -> L8b
            r12 = 48
            long r12 = r4 >> r12
            long r6 = r6 & r12
            int r7 = (int) r6     // Catch: java.lang.Throwable -> L8b
            byte r6 = (byte) r7     // Catch: java.lang.Throwable -> L8b
            long r3 = r4 >> r3
            int r4 = (int) r3     // Catch: java.lang.Throwable -> L8b
            byte r3 = (byte) r4     // Catch: java.lang.Throwable -> L8b
            int r4 = r15 + 1
            r1[r15] = r3     // Catch: java.lang.Throwable -> L8b
            int r15 = r4 + 1
            r1[r4] = r6     // Catch: java.lang.Throwable -> L8b
            int r3 = r15 + 1
            r1[r15] = r11     // Catch: java.lang.Throwable -> L8b
            int r15 = r3 + 1
            r1[r3] = r10     // Catch: java.lang.Throwable -> L8b
            int r3 = r15 + 1
            r1[r15] = r9     // Catch: java.lang.Throwable -> L8b
            int r15 = r3 + 1
            r1[r3] = r8     // Catch: java.lang.Throwable -> L8b
            int r3 = r15 + 1
            r1[r15] = r2     // Catch: java.lang.Throwable -> L8b
            r1[r3] = r0     // Catch: java.lang.Throwable -> L8b
            return r1
        L8b:
            r15 = move-exception
            r0 = 0
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r15)     // Catch: java.lang.Throwable -> L91
            return r0
        L91:
            r15 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.b.c.d(byte[]):byte[]");
    }

    public byte[] a(byte[] bArr) {
        try {
            c(bArr);
            byte[] bArr2 = new byte[20];
            for (int i = 0; i < this.c.length; i++) {
                a(this.c[i], bArr2, i * 4);
            }
            return bArr2;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
