package com.duowan.live.base.utils;

import android.content.pm.Signature;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SignaturesMsg {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(HEX_DIGITS[(bArr[i] & 240) >>> 4]);
            sb.append(HEX_DIGITS[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public static String signatureSHA1(Signature[] signatureArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            if (signatureArr != null) {
                for (Signature signature : signatureArr) {
                    messageDigest.update(signature.toByteArray());
                }
            }
            return toHexString(messageDigest.digest());
        } catch (Exception unused) {
            return "";
        }
    }
}
