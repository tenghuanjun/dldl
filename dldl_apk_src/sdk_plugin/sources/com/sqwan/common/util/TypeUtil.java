package com.sqwan.common.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TypeUtil {
    public static int saveParseInteger(String str, int i) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    private float saveParseFloat(String str, float f) {
        try {
            return Float.valueOf(str).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
            return f;
        }
    }
}
