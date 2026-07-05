package com.huya.mtp.utils;

import android.util.Log;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Algorithms {
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            Log.w("Algorithms.java getMD5", Log.getStackTraceString(e));
            return null;
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = hexDigits;
            cArr[i] = cArr2[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }
}
