package com.mobile.auth.b;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    private static final String a = a.class.getSimpleName();
    private static byte[] b = "0000000000000000".getBytes();
    private static byte[] c = "vrf5g7h0tededwx3".getBytes();

    public static String a(String str, String str2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(b);
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bytes = str.getBytes("utf-8");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return e.a(cipher.doFinal(bytes));
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a(a, "encryptAesNew error", th);
                return null;
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

    public static byte[] b(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("utf-8");
            int length = bytes.length;
            while (length % 16 != 0) {
                length++;
            }
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                if (i < bytes.length) {
                    bArr[i] = bytes[i];
                } else {
                    bArr[i] = 0;
                }
            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(c);
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a(a, "encrypt4Ux error", th);
                return null;
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

    public static String c(String str, String str2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(b);
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            byte[] bArrDoFinal = cipher.doFinal(e.a(str));
            if (bArrDoFinal != null) {
                return new String(bArrDoFinal);
            }
            com.mobile.auth.a.a.a(a, "Aes decrypt result is empty");
            return "";
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a(a, "decryptAesNew error", th);
                return "";
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
}
