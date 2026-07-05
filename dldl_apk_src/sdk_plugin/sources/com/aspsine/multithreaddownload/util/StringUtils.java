package com.aspsine.multithreaddownload.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StringUtils {
    public static boolean equal(String str, String str2, boolean z) {
        return (str == null || str2 == null) ? str == null && str2 == null : z ? str.equalsIgnoreCase(str2) : str.equals(str2);
    }
}
