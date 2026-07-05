package com.sq.tool.sqtools.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SystemPropertiesUtil {
    public static String getProperty(String str) {
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
