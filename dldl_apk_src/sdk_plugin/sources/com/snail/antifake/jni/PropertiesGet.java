package com.snail.antifake.jni;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PropertiesGet {
    private static native String native_get(String str);

    private static native String native_get(String str, String str2);

    static {
        System.loadLibrary("property_get");
    }

    public static String getString(String str) {
        return native_get(str);
    }

    public static String getString(String str, String str2) {
        return native_get(str, str2);
    }
}
