package com.sy37sdk.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class StringUtil {
    public static String convertStringToUTF8(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            try {
                char cCharAt = str.charAt(i);
                if (cCharAt >= 0 && cCharAt <= 255) {
                    stringBuffer.append(cCharAt);
                } else {
                    for (int i2 : Character.toString(cCharAt).getBytes("utf-8")) {
                        if (i2 < 0) {
                            i2 += 256;
                        }
                        stringBuffer.append(Integer.toHexString(i2).toUpperCase());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
