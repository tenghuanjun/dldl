package com.ishumei.O000O0000OOoO;

import android.text.TextUtils;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000Oo0O {
    public static String O0000O000000oO(int i) {
        char[] cArr = new char[i];
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = (char) (random.nextInt(26) + 97);
        }
        return new String(cArr);
    }

    public static boolean O0000O000000oO(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }

    public static boolean O0000O000000oO(String str, String str2) {
        return TextUtils.equals(str, str2);
    }

    public static byte[] O0000O000000oO(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (~bArr[i]);
        }
        return bArr2;
    }

    public static boolean O000O00000OoO(String str) {
        return !O0000O000000oO(str);
    }

    public static String O000O00000o0O(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static boolean O000O00000oO(String str) {
        return O0000O000000oO(O000O00000o0O(str));
    }

    public static String O000O0000O0oO(String str) {
        return str == null ? "" : str;
    }

    public static byte[] O000O0000OOoO(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) Integer.parseInt(new String(bytes, i, 2), 16);
        }
        return bArr;
    }

    public static String O000O0000Oo0O(String str) {
        return new String(O0000O000000oO(O000O0000OOoO(str)));
    }
}
