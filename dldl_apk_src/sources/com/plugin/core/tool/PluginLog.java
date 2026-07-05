package com.plugin.core.tool;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class PluginLog {
    public static String TAG = "SQ_Plugin";
    public static boolean DEBUG = Log.isLoggable("sysdk.debug.log.plugin", 3);
    public static boolean VERBOSE = Log.isLoggable("sysdk.debug.log.plugin", 2);

    private PluginLog() {
    }

    public static void v(String str) {
        if (VERBOSE) {
            Log.v(TAG, str);
        }
    }

    public static void v(String str, Object... objArr) {
        if (VERBOSE) {
            Log.v(TAG, buildMessage(str, objArr));
        }
    }

    public static void d(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public static void d(String str, Object... objArr) {
        if (DEBUG) {
            Log.d(TAG, buildMessage(str, objArr));
        }
    }

    public static void i(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }

    public static void i(String str, Object... objArr) {
        if (DEBUG) {
            Log.i(TAG, buildMessage(str, objArr));
        }
    }

    public static void w(String str) {
        if (DEBUG) {
            Log.w(TAG, str);
        }
    }

    public static void w(String str, Throwable th) {
        if (DEBUG) {
            Log.w(TAG, str, th);
        }
    }

    public static void w(Throwable th, String str, Object... objArr) {
        if (DEBUG) {
            Log.w(TAG, buildMessage(str, objArr), th);
        }
    }

    public static void e(String str) {
        if (DEBUG) {
            Log.e(TAG, str);
        }
    }

    public static void e(String str, Throwable th) {
        if (DEBUG) {
            Log.e(TAG, str, th);
        }
    }

    public static void e(String str, Object... objArr) {
        if (DEBUG) {
            Log.e(TAG, buildMessage(str, objArr));
        }
    }

    public static void e(Throwable th, String str, Object... objArr) {
        if (DEBUG) {
            Log.e(TAG, buildMessage(str, objArr), th);
        }
    }

    private static String buildMessage(String str, Object... objArr) {
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        return String.format(Locale.US, "[%d] %s", Long.valueOf(Thread.currentThread().getId()), str);
    }
}
