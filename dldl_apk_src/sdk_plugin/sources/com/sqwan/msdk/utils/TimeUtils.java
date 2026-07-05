package com.sqwan.msdk.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TimeUtils {
    public static int HOUR = 0;
    public static int MINUTE = 0;
    public static int SECOND = 1000;

    static {
        int i = 1000 * 60;
        MINUTE = i;
        HOUR = i * 60;
    }
}
