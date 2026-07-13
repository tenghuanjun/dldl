package com.danikula.videocache;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class HttpProxyCacheDebuger {
    static boolean DEBUG_TAG = true;
    static final String LOG_TAG = "HttpProxyCacheDebuger";

    public static void enable() {
        DEBUG_TAG = true;
    }

    public static void disable() {
        DEBUG_TAG = false;
    }

    public static boolean getDebugMode() {
        return DEBUG_TAG;
    }

    public static void printfLog(String str, String str2) {
        if (!DEBUG_TAG || str2 == null || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
    }

    public static void printfLog(String str) {
        printfLog(LOG_TAG, str);
    }

    public static void printfWarning(String str, String str2) {
        if (!DEBUG_TAG || str2 == null || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, str2);
    }

    public static void printfWarning(String str) {
        printfWarning(LOG_TAG, str);
    }

    public static void printfError(String str) {
        if (!DEBUG_TAG || TextUtils.isEmpty(str)) {
            return;
        }
        Log.e(LOG_TAG, str);
    }

    public static void printfError(String str, String str2) {
        if (!DEBUG_TAG || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, str2);
    }

    public static void printfError(String str, Exception exc) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(str)) {
                Log.e(LOG_TAG, str);
            }
            exc.printStackTrace();
        }
    }

    public static void Toast(Activity activity, String str) {
        if (!DEBUG_TAG || TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(activity, str, 0).show();
    }
}
