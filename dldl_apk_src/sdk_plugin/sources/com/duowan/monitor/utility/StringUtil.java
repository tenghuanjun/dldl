package com.duowan.monitor.utility;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class StringUtil {
    private StringUtil() {
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String formatString(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (('a' > cCharAt || cCharAt > 'z') && (('A' > cCharAt || cCharAt > 'Z') && (('0' > cCharAt || cCharAt > '9') && cCharAt != '-' && cCharAt != '_' && cCharAt != '.' && cCharAt != '/'))) {
                cCharAt = '_';
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }
}
