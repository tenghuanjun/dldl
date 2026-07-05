package com.taptap.sdk.themis.lite;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class LogUtil {
    public static final String TAG = "ThemisLite";
    private static boolean isDebug;

    public static void init(Context context) {
    }

    public static void info(String str) {
        if (isDebug) {
            Log.i(TAG, str);
        }
    }

    public static void err(String str) {
        if (isDebug) {
            Log.e(TAG, str);
        }
    }

    public static void debug(String str) {
        if (isDebug) {
            Log.d(TAG, str);
        }
    }

    public static void warn(String str) {
        if (isDebug) {
            Log.w(TAG, str);
        }
    }

    public static void verbose(String str) {
        if (isDebug) {
            Log.v(TAG, str);
        }
    }

    public static void err(String str, Throwable th) {
        if (isDebug) {
            Log.e(TAG, str, th);
        }
    }

    public static void info(String str, Object... objArr) {
        if (isDebug) {
            Log.i(TAG, String.format(str, objArr));
        }
    }

    public static void debug(String str, Object... objArr) {
        if (isDebug) {
            Log.d(TAG, String.format(str, objArr));
        }
    }

    public static void warn(String str, Object... objArr) {
        if (isDebug) {
            Log.w(TAG, String.format(str, objArr));
        }
    }

    public static void verbose(String str, Object... objArr) {
        if (isDebug) {
            Log.v(TAG, String.format(str, objArr));
        }
    }

    public static void err(String str, Object... objArr) {
        if (isDebug) {
            Log.e(TAG, String.format(str, objArr));
        }
    }
}
