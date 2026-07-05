package com.sdk.sq.net.gateway;

import android.util.Base64;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GateWayUtils {
    public static IvParameterSpec generateIv(String str) {
        return new IvParameterSpec(str.getBytes());
    }

    public static String encrypt(String input, String key, IvParameterSpec iv) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, secretKeySpec, iv);
        return Base64.encodeToString(cipher.doFinal(input.getBytes()), 0);
    }

    public static String decryptUrlSafe(String cipherText, String key, IvParameterSpec iv) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, secretKeySpec, iv);
        try {
            return new String(cipher.doFinal(Base64.decode(cipherText, 8)));
        } catch (IllegalBlockSizeException unused) {
            return cipherText;
        }
    }

    public static String decryptDefault(String cipherText, String key, IvParameterSpec iv) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, secretKeySpec, iv);
        return new String(cipher.doFinal(Base64.decode(cipherText, 0)));
    }

    public static String toBase64Url(String base64) {
        return base64.replace('+', '-').replace('/', '_').replace(ShellAdbUtils.COMMAND_LINE_END, "").split(SimpleComparison.EQUAL_TO_OPERATION)[0];
    }

    public static String md5(String src) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(src.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
    }

    public static String randomData(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        Random random2 = new Random();
        for (int i = 0; i < length; i++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                sb.append(random2.nextInt(10));
            } else if (iNextInt == 1) {
                sb.append((char) (random2.nextInt(26) + 65));
            } else if (iNextInt == 2) {
                sb.append((char) (random2.nextInt(26) + 97));
            }
        }
        return sb.toString();
    }

    public static String getFixedKey(String appKey, String appSecret) {
        try {
            return decryptDefault(appSecret, appKey, generateIv(appKey));
        } catch (Exception unused) {
            return "";
        }
    }
}
