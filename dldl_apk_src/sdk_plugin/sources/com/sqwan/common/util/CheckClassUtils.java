package com.sqwan.common.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CheckClassUtils {
    public static boolean classExist(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
