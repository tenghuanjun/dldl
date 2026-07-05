package com.sqwan.common.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NumberUtils {
    private static final String[] GROUP_UNITS = {"", "万", "亿", "兆"};
    private static final String[] BASE_UNITS = {"", "十", "百", "千"};
    private static final char[] SIMPLIFIED_NUMBERIC = {38646, 19968, 20108, 19977, 22235, 20116, 20845, 19971, 20843, 20061};
    private static final char[] TRADITIONAL_NUMBERIC = {38646, 22777, 36144, 21441, 32902, 20237, 38470, 26578, 25420, 29590};

    public static String toChinese(long j, boolean z) {
        char[] cArr = z ? TRADITIONAL_NUMBERIC : SIMPLIFIED_NUMBERIC;
        if (j == 0) {
            return String.valueOf(cArr[0]);
        }
        String strValueOf = String.valueOf(j);
        char[] charArray = strValueOf.toCharArray();
        int length = strValueOf.length();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = Integer.parseInt(String.valueOf(charArray[i2]));
            int i4 = (length - i2) - 1;
            int i5 = i4 % 4;
            if (i3 == 0) {
                i++;
                if ((i5 == 0 && i < 4) || (i4 % 8 == 0 && i < 8)) {
                    stringBuffer.append(getGroupUnit(i4));
                }
            } else {
                if (i > 0) {
                    stringBuffer.append(cArr[0]);
                }
                if (i5 == 0) {
                    stringBuffer.append(cArr[i3] + BASE_UNITS[i5] + getGroupUnit(i4));
                } else if (i5 == 1 && i2 == 0 && i3 == 1) {
                    stringBuffer.append(BASE_UNITS[i5]);
                } else {
                    stringBuffer.append(cArr[i3] + BASE_UNITS[i5]);
                }
            }
            i = 0;
        }
        return stringBuffer.toString();
    }

    public static String toChinese(long j) {
        return toChinese(j, false);
    }

    public static String toChinese(int i, boolean z) {
        return toChinese(i, z);
    }

    public static String toChinese(int i) {
        return toChinese(i, false);
    }

    private static String getGroupUnit(int i) {
        String str = GROUP_UNITS[(i / 4) % 2];
        if (i % 8 != 0 || i <= 0) {
            return str;
        }
        return str + GROUP_UNITS[(i / 8) + 1];
    }
}
