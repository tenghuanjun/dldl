package com.social.sdk.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class EncryptUtils {
    public static String getSHA(String str) {
        byte[] bArrDigest;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes());
            bArrDigest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            bArrDigest = null;
        }
        return byte2hex(bArrDigest);
    }

    private static String byte2hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
        }
        return str;
    }
}
