package com.sq.sdk.tool.util;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqLogUtil {
    private static final int LOG_A = 6;
    private static final int LOG_D = 2;
    private static final int LOG_E = 5;
    private static final int LOG_I = 3;
    private static int LOG_LEVEL = 0;
    private static final int LOG_V = 1;
    private static final int LOG_W = 4;
    private static final String TAG = "sq_sdk";

    public static void v(String str, String str2) {
        if (1 >= LOG_LEVEL) {
            Log.v(str, str2);
        }
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void d(String str, String str2) {
        if (2 >= LOG_LEVEL) {
            Log.d(str, str2);
        }
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static void i(String str, String str2) {
        if (3 >= LOG_LEVEL) {
            Log.i(str, str2);
        }
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void w(String str, String str2) {
        if (4 >= LOG_LEVEL) {
            Log.w(str, str2);
        }
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void e(String str, String str2) {
        if (5 >= LOG_LEVEL) {
            Log.e(str, str2);
        }
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void setLogEnable(boolean z) {
        if (z) {
            LOG_LEVEL = 1;
        } else {
            LOG_LEVEL = 7;
        }
    }
}
