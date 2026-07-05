package com.sqwan.bugless.util;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogUtil {
    private static final String TAG = "Bugless";
    private static boolean debug = true;

    public static void d(String log) {
        if (debug) {
            Log.d(TAG, log);
        }
    }

    public static void i(String log) {
        if (debug) {
            Log.i(TAG, log);
        }
    }

    public static void e(String log) {
        if (debug) {
            Log.e(TAG, log);
        }
    }

    public static void w(String log, Throwable e) {
        if (debug) {
            Log.w(TAG, log, e);
        }
    }

    public static void w(String log) {
        if (debug) {
            Log.w(TAG, log);
        }
    }

    public static void setDebug(boolean debug2) {
        debug = debug2;
    }
}
