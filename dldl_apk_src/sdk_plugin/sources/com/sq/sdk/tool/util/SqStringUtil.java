package com.sq.sdk.tool.util;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqStringUtil {
    public static final String ZERO = "0";

    public static boolean isNull(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }
}
