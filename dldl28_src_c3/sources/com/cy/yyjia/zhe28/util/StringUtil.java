package com.cy.yyjia.zhe28.util;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class StringUtil {
    private StringUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isSpace(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        int length;
        if (a == b) {
            return true;
        }
        if (a == null || b == null || (length = a.length()) != b.length()) {
            return false;
        }
        if ((a instanceof String) && (b instanceof String)) {
            return a.equals(b);
        }
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        return a == b || (b != null && a.length() == b.length() && a.regionMatches(true, 0, b, 0, b.length()));
    }

    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    public static int length(CharSequence s) {
        if (s == null) {
            return 0;
        }
        return s.length();
    }

    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - ' ')) + s.substring(1);
    }

    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + ' ')) + s.substring(1);
    }

    public static String reverse(String s) {
        int length = length(s);
        if (length <= 1) {
            return s;
        }
        int i = length >> 1;
        char[] charArray = s.toCharArray();
        for (int i2 = 0; i2 < i; i2++) {
            char c = charArray[i2];
            int i3 = (length - i2) - 1;
            charArray[i2] = charArray[i3];
            charArray[i3] = c;
        }
        return new String(charArray);
    }

    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == 12288) {
                charArray[i] = ' ';
            } else if (65281 <= c && c <= 65374) {
                charArray[i] = (char) (c - 65248);
            } else {
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }

    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == ' ') {
                charArray[i] = 12288;
            } else if ('!' <= c && c <= '~') {
                charArray[i] = (char) (c + 65248);
            } else {
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }
}
