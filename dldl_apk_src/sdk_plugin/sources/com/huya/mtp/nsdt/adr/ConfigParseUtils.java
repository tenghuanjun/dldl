package com.huya.mtp.nsdt.adr;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ConfigParseUtils {
    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }
}
