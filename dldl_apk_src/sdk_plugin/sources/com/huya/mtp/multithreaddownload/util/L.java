package com.huya.mtp.multithreaddownload.util;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class L {
    private static final String TAG = "MultiThreadDownload";

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }
}
