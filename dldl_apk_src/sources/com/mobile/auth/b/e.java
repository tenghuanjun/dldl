package com.mobile.auth.b;

import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class e {
    private static final String a = e.class.getSimpleName();
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            if (bArr.length == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bArr.length; i++) {
                sb.append(b[(bArr[i] >> 4) & 15]);
                sb.append(b[bArr[i] & 15]);
            }
            return sb.toString();
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

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        try {
            char[] charArray = str.toCharArray();
            int length = charArray.length / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                int iDigit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
                if (iDigit > 127) {
                    iDigit -= 256;
                }
                bArr[i] = (byte) iDigit;
            }
            return bArr;
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

    public static byte[] b(String str) {
        try {
            byte[] bArr = new byte[0];
            try {
                return str.getBytes("UTF-8");
            } catch (Throwable th) {
                com.mobile.auth.a.a.a(a, "getBytes error", th);
                return bArr;
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
}
