package com.sq.tools.encrypt;

import com.sq.tools.Logger;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class DES {
    private DES() {
    }

    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(1, secretKeyGenerateSecret, secureRandom);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            Logger.error("Des encrypt Exception", e);
            return new byte[0];
        }
    }

    public static byte[] decrypt(byte[] bArr, String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(2, secretKeyGenerateSecret, secureRandom);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            Logger.error("Des encrypt Exception", e);
            return new byte[0];
        }
    }
}
