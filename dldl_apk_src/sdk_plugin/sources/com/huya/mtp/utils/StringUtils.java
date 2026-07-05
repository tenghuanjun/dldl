package com.huya.mtp.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Vector;
import kotlin.text.Typography;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StringUtils {
    public static final boolean IGNORE_CASE = true;
    public static final boolean IGNORE_WIDTH = true;

    public static String ensureNotNull(String str) {
        return str != null ? str : "";
    }

    public static char narrow(char c) {
        int i;
        if (c >= 65281 && c <= 65373) {
            i = c - 65248;
        } else {
            if (c != 12288) {
                if (c == 65377) {
                    return (char) 12290;
                }
                return (c == 12539 || c == 8226) ? Typography.middleDot : c;
            }
            i = (c - 12288) + 32;
        }
        return (char) i;
    }

    public static int ord(char c) {
        if ('a' <= c && c <= 'z') {
            return c;
        }
        if ('A' > c || c > 'Z') {
            return 0;
        }
        return (c - 'A') + 97;
    }

    public static boolean isNullOrEmpty(String str) {
        return FP.empty(str);
    }

    public static boolean isAllWhitespaces(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllDigits(String str) {
        if (str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean equal(String str, String str2) {
        return equal(str, str2, false);
    }

    public static boolean equal(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            return str == null && str2 == null;
        }
        if (z) {
            return str.equalsIgnoreCase(str2);
        }
        return str.equals(str2);
    }

    public static Vector<String> parseMediaUrls(String str, String str2, String str3) {
        Vector<String> vector = new Vector<>();
        if (!isNullOrEmpty(str)) {
            int iIndexOf = str.indexOf(str2, 0);
            int iIndexOf2 = str.indexOf(str3, 0);
            while (iIndexOf != -1 && iIndexOf2 != -1 && iIndexOf2 > iIndexOf) {
                String strSubstring = str.substring(iIndexOf + str2.length(), iIndexOf2);
                if (!isNullOrEmpty(strSubstring) && strSubstring.charAt(0) != '[') {
                    vector.add(strSubstring);
                }
                int length = iIndexOf2 + str3.length() + iIndexOf2;
                iIndexOf = str.indexOf(str2, length);
                iIndexOf2 = str.indexOf(str3, length);
            }
        }
        return vector;
    }

    public static int find(String str, String str2) {
        return find(str, str2, false);
    }

    public static int find(String str, String str2, boolean z) {
        return find(str, str2, z, false);
    }

    public static int find(String str, String str2, boolean z, boolean z2) {
        if (FP.empty(str2)) {
            return -1;
        }
        String strRef = FP.ref(str);
        if (z) {
            strRef = strRef.toLowerCase();
            str2 = str2.toLowerCase();
        }
        if (z2) {
            strRef = narrow(strRef);
            str2 = narrow(str2);
        }
        return str2.indexOf(strRef);
    }

    public static String narrow(String str) {
        if (FP.empty(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = narrow(charArray[i]);
        }
        return new String(charArray);
    }

    public static int compare(String str, String str2) {
        return FP.ref(str).compareTo(FP.ref(str2));
    }

    public static String toUtf8(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String();
        }
    }

    public static String fromUtf8(String str) {
        try {
            return new String(str.getBytes("UTF-8"), Charset.defaultCharset());
        } catch (UnsupportedEncodingException unused) {
            return new String();
        }
    }

    public static boolean containsFullChar(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if ((65377 > charArray[i] || charArray[i] > 65439) && (' ' > charArray[i] || charArray[i] > '~')) {
                return true;
            }
        }
        return false;
    }
}
