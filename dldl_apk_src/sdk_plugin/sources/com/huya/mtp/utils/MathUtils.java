package com.huya.mtp.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MathUtils {
    public static long getUnsignedInt(int i) {
        return ((long) i) & 4294967295L;
    }

    public static int random(int i, int i2) {
        return (int) ((Math.random() * ((double) (i2 - i))) + ((double) i));
    }

    public static float min(float... fArr) {
        int length = fArr.length;
        if (length == 0) {
            return 0.0f;
        }
        float fMin = fArr[0];
        for (int i = 1; i < length; i++) {
            fMin = Math.min(fArr[i], fMin);
        }
        return fMin;
    }

    public static float max(float... fArr) {
        int length = fArr.length;
        if (length == 0) {
            return 0.0f;
        }
        float fMax = fArr[0];
        for (int i = 1; i < length; i++) {
            fMax = Math.max(fArr[i], fMax);
        }
        return fMax;
    }

    public static long intBackToLong(int i) {
        return Long.valueOf(Integer.toBinaryString(i), 2).longValue();
    }
}
