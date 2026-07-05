package com.sq.sdk.tool.util;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqRegexUtils {
    public static boolean isMobilePhoneNumber(String str) {
        return Pattern.compile("^((1[358][0-9])|(14[579])|(16[6])|(17[0135678])|(19[89]))\\d{8}$").matcher(str).matches();
    }

    private static boolean isEmail(String str) {
        return Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$").matcher(str).matches();
    }
}
