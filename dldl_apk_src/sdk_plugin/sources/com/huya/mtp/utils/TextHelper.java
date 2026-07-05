package com.huya.mtp.utils;

import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TextHelper {
    public static String subString(String str, int i) {
        if (str == null) {
            return null;
        }
        if (str.length() < i / 2) {
            return str;
        }
        char[] charArray = str.toCharArray();
        int i2 = 0;
        int charLength = 0;
        while (i2 < charArray.length && (charLength = charLength + getCharLength(charArray[i2])) <= i) {
            i2++;
        }
        return i2 < charArray.length ? String.format("%s…", safelySubstring(str, 0, i2)) : str;
    }

    public static String subNickName(String str, int i) {
        if (str == null) {
            return "";
        }
        if (str.length() < 2) {
            return str + " ";
        }
        return subString(str, i);
    }

    public static String safelySubstring(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(str.offsetByCodePoints(0, i), str.offsetByCodePoints(0, i2));
        } catch (IndexOutOfBoundsException unused) {
            MTPApi.LOGGER.debug("TestNobleText", "[safelySubstring-IndexOutOfBoundsException] source=%s, start=%d, end=%d", str, Integer.valueOf(i), Integer.valueOf(i2));
            return str;
        }
    }

    public static int getCharLength(char c) {
        return (isDigit(c) || isLetter(c) || c == ' ') ? 1 : 2;
    }

    public static boolean isDigit(char c) {
        return Pattern.compile("[0-9]*").matcher(String.valueOf(c)).matches();
    }

    public static boolean isLetter(char c) {
        return Pattern.compile("[a-zA-Z]").matcher(String.valueOf(c)).matches();
    }
}
