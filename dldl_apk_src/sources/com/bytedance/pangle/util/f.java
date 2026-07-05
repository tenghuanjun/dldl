package com.bytedance.pangle.util;

import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(String str) {
        return a(str.getBytes());
    }

    public static String a(byte[] bArr) {
        int length;
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(bArr);
                byte[] bArrDigest = messageDigest.digest();
                if (bArrDigest == null || (length = bArrDigest.length) <= 0) {
                    return null;
                }
                char[] cArr = new char[length << 1];
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i + 1;
                    cArr[i] = a[(bArrDigest[i2] >>> 4) & 15];
                    i = i3 + 1;
                    cArr[i3] = a[bArrDigest[i2] & 15];
                }
                return new String(cArr);
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
