package com.mobile.auth.b;

import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.Locale;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f502a = "c";
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
                int[] iArr = this.d;
                iArr[i] = a(((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16], 1);
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
        int[] iArr2 = new int[5];
        for (int i2 = 0; i2 < 5; i2++) {
            iArr2[i2] = this.c[i2];
        }
        for (int i3 = 0; i3 <= 19; i3++) {
            int iA = a(iArr2[0], 5) + a(iArr2[1], iArr2[2], iArr2[3]) + iArr2[4] + this.d[i3] + 1518500249;
            iArr2[4] = iArr2[3];
            iArr2[3] = iArr2[2];
            iArr2[2] = a(iArr2[1], 30);
            iArr2[1] = iArr2[0];
            iArr2[0] = iA;
        }
        for (int i4 = 20; i4 <= 39; i4++) {
            int iA2 = a(iArr2[0], 5) + b(iArr2[1], iArr2[2], iArr2[3]) + iArr2[4] + this.d[i4] + 1859775393;
            iArr2[4] = iArr2[3];
            iArr2[3] = iArr2[2];
            iArr2[2] = a(iArr2[1], 30);
            iArr2[1] = iArr2[0];
            iArr2[0] = iA2;
        }
        for (int i5 = 40; i5 <= 59; i5++) {
            int iA3 = (((a(iArr2[0], 5) + c(iArr2[1], iArr2[2], iArr2[3])) + iArr2[4]) + this.d[i5]) - 1894007588;
            iArr2[4] = iArr2[3];
            iArr2[3] = iArr2[2];
            iArr2[2] = a(iArr2[1], 30);
            iArr2[1] = iArr2[0];
            iArr2[0] = iA3;
        }
        for (int i6 = 60; i6 <= 79; i6++) {
            int iA4 = (((a(iArr2[0], 5) + b(iArr2[1], iArr2[2], iArr2[3])) + iArr2[4]) + this.d[i6]) - 899497514;
            iArr2[4] = iArr2[3];
            iArr2[3] = iArr2[2];
            iArr2[2] = a(iArr2[1], 30);
            iArr2[1] = iArr2[0];
            iArr2[0] = iA4;
        }
        for (int i7 = 0; i7 < 5; i7++) {
            int[] iArr3 = this.c;
            iArr3[i7] = iArr3[i7] + iArr2[i7];
        }
        int i8 = 0;
        while (true) {
            int[] iArr4 = this.d;
            if (i8 >= iArr4.length) {
                return;
            }
            iArr4[i8] = 0;
            i8++;
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
                com.mobile.auth.a.a.a(f502a, "when getHmacSHA1,the key is null");
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
                com.mobile.auth.a.a.a(f502a, "getHmacSHA1 error", th);
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
        return (i & (i2 | i3)) | (i2 & i3);
    }

    private int c(byte[] bArr) {
        try {
            int[] iArr = this.b;
            System.arraycopy(iArr, 0, this.c, 0, iArr.length);
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

    private byte[] d(byte[] bArr) {
        int i;
        int i2;
        try {
            int length = bArr.length;
            int i3 = length % 64;
            if (i3 < 56) {
                i = 55 - i3;
                i2 = (length - i3) + 64;
            } else if (i3 == 56) {
                i2 = length + 72;
                i = 63;
            } else {
                i = 119 - i3;
                i2 = ((length + 64) - i3) + 64;
            }
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            int i4 = length + 1;
            bArr2[length] = ByteCompanionObject.MIN_VALUE;
            int i5 = 0;
            while (i5 < i) {
                bArr2[i4] = 0;
                i5++;
                i4++;
            }
            long j = ((long) length) * 8;
            bArr2[i4] = (byte) (j >> 56);
            bArr2[i4 + 1] = (byte) (255 & (j >> 48));
            bArr2[i4 + 2] = (byte) ((j >> 40) & 255);
            bArr2[i4 + 3] = (byte) ((j >> 32) & 255);
            bArr2[i4 + 4] = (byte) ((j >> 24) & 255);
            bArr2[i4 + 5] = (byte) ((j >> 16) & 255);
            bArr2[i4 + 6] = (byte) ((j >> 8) & 255);
            bArr2[i4 + 7] = (byte) (j & 255);
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

    public byte[] a(byte[] bArr) {
        try {
            c(bArr);
            byte[] bArr2 = new byte[20];
            int i = 0;
            while (true) {
                int[] iArr = this.c;
                if (i >= iArr.length) {
                    return bArr2;
                }
                a(iArr[i], bArr2, i * 4);
                i++;
            }
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
