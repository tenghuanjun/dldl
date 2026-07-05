package com.alipay.deviceid.module.x;

import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class h {
    public static String a(String str) {
        try {
            if (e.a(str)) {
                return null;
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes("UTF-8"));
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
}
