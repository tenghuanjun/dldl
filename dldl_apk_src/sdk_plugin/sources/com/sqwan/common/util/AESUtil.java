package com.sqwan.common.util;

import android.text.TextUtils;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AESUtil {
    private static final String DEFAULT_PASSWORD = "2384039739279483";

    public static byte[] encrypt(String str) throws Exception {
        return encrypt(str, DEFAULT_PASSWORD);
    }

    public static byte[] encrypt(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2 != null ? str2.getBytes("UTF-8") : null, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(str.getBytes("UTF-8"));
    }

    public static byte[] decrypt(String str) throws Exception {
        return decrypt(str, DEFAULT_PASSWORD);
    }

    public static byte[] decrypt(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2 != null ? str2.getBytes("UTF-8") : null, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(Base64.decode(str));
    }

    public static String decryptString(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                LogUtil.i("AES 解密Key为null");
                return "";
            }
            if (str2.length() != 16) {
                LogUtil.i("AES 解密Key长度不是16位");
                return "";
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(Base64.decode(str)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LogUtil.i("AES 解密失败");
            e.printStackTrace();
            return "";
        }
    }
}
