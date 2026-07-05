package com.huya.live.utils.cache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MD5 {
    public static String getMD5(byte[] bArr, boolean z) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bArr);
        byte[] bArrDigest = messageDigest.digest();
        return z ? bytesToHexString(bArrDigest) : getString(bArrDigest);
    }

    public static String getMD5(byte[] bArr) throws NoSuchAlgorithmException {
        return getMD5(bArr, true);
    }

    public static String getString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
        }
        return sb.toString();
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
