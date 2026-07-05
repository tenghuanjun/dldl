package com.sq.tool.sqtools.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class XposedUtils {
    public static boolean checkXposedBridge() {
        try {
            Class.forName("de.robv.android.xposed.XposedBridge");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkXposedHelpers() {
        try {
            Class.forName("de.robv.android.xposed.XposedHelpers");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
