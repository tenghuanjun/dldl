package com.alipay.deviceid.module.x;

import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class by {
    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String b(byte[] bArr) {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(bArr);
                    byte[] bArrDigest = messageDigest.digest();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 16; i++) {
                        sb.append(String.format("%02x", Byte.valueOf(bArrDigest[i])));
                    }
                    return sb.toString();
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
