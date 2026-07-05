package com.huya.mtp.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HmacSha1Signature {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static String toHexString(byte[] bArr) {
        Formatter formatter = new Formatter();
        for (byte b : bArr) {
            formatter.format("%02x", Byte.valueOf(b));
        }
        return formatter.toString();
    }

    public static String calculateRFC2104HMAC(String str, String str2) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        return toHexString(mac.doFinal(str.getBytes()));
    }
}
