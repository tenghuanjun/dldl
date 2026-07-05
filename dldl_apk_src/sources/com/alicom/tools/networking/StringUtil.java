package com.alicom.tools.networking;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class StringUtil {
    private static final String TAG = StringUtil.class.getSimpleName();
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] getBytes(String str) {
        byte[] bArr = new byte[0];
        try {
            return str.getBytes("UTF-8");
        } catch (Throwable th) {
            Log.e(TAG, "getBytes error", th);
            return bArr;
        }
    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int iDigit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
            if (iDigit > 127) {
                iDigit -= 256;
            }
            bArr[i] = (byte) iDigit;
        }
        return bArr;
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(HEX[(bArr[i] >> 4) & 15]);
            sb.append(HEX[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
