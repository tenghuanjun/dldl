package com.duowan.live.one.util;

import android.text.TextUtils;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DecimalFormatHelper {

    public enum DecimalPattern {
        W_PATTERN("%.1fW", 10000, 0),
        K_PATTERN("%.1fK", 1000, 0);

        public final int mLowBound;
        public final String mPatternStr;
        public final int mUpBound;

        DecimalPattern(String str, int i, int i2) {
            this.mPatternStr = str;
            this.mUpBound = i;
            this.mLowBound = i2;
        }
    }

    public static String format(String str, DecimalPattern decimalPattern) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        int iIntValue = 0;
        try {
            iIntValue = Integer.valueOf(str).intValue();
        } catch (Exception unused) {
        }
        return format(iIntValue, decimalPattern);
    }

    public static String format(int i, DecimalPattern decimalPattern) {
        if (i >= decimalPattern.mUpBound) {
            return String.format(decimalPattern.mPatternStr, Float.valueOf((float) ((((double) i) * 1.0d) / ((double) decimalPattern.mUpBound))));
        }
        if (i < decimalPattern.mLowBound) {
            return String.valueOf(decimalPattern.mLowBound);
        }
        return String.valueOf(i);
    }

    public static String paserIntToStrUpbound(long j, long j2) {
        if (j > j2) {
            return paserIntToStr(j2) + Marker.ANY_NON_NULL_MARKER;
        }
        return paserIntToStr(j);
    }

    public static String paserIntToStr(long j) {
        String str;
        if (j <= 0) {
            return "0";
        }
        String strValueOf = String.valueOf(j);
        int length = strValueOf.length();
        int i = 3;
        if (length <= 3) {
            return strValueOf;
        }
        StringBuilder sb = new StringBuilder();
        if (length == 4) {
            str = "K";
        } else if (length >= 5) {
            str = "W";
            i = 4;
        } else {
            str = "";
            i = 0;
        }
        int length2 = strValueOf.length() - i;
        String strSubstring = strValueOf.substring(0, length2);
        char cCharAt = strValueOf.charAt(length2);
        sb.append(strSubstring);
        sb.append('.');
        sb.append(cCharAt);
        sb.append(str);
        return sb.toString();
    }

    public static String paserIntToStr1(long j) {
        String str;
        if (j <= 0) {
            return "0";
        }
        String strValueOf = String.valueOf(j);
        int length = strValueOf.length();
        int i = 3;
        if (length <= 3) {
            return strValueOf;
        }
        StringBuilder sb = new StringBuilder();
        if (length == 4) {
            str = "K";
        } else if (length >= 5) {
            str = "W";
            i = 4;
        } else {
            str = "";
            i = 0;
        }
        int length2 = strValueOf.length() - i;
        String strSubstring = strValueOf.substring(0, length2);
        char cCharAt = strValueOf.charAt(length2);
        if (cCharAt != '0') {
            sb.append(strSubstring);
            sb.append('.');
            sb.append(cCharAt);
            sb.append(str);
        } else {
            sb.append(strSubstring);
            sb.append(str);
        }
        return sb.toString();
    }

    public static int safelyParseInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }
}
