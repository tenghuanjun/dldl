package com.sq.sdk.tool.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DESUtil {
    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, secretKeyGenerateSecret, secureRandom);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "".getBytes();
        }
    }

    public static byte[] decrypt(byte[] bArr, String str) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, secretKeyGenerateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }
}
