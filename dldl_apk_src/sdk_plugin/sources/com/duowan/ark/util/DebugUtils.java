package com.duowan.ark.util;

import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DebugUtils {
    private static final String TAG = "DebugUtils";
    private static boolean sDebuggable;

    public static void setDebuggable(boolean z) {
        sDebuggable = z;
    }

    public static void crashIfDebug(String str, Object... objArr) {
        crashIfDebug(null, str, objArr);
    }

    public static void crashIfDebug(Throwable th, String str, Object... objArr) {
        String str2 = String.format(str, objArr);
        if (sDebuggable) {
            KLog.error(TAG, "crashIfDebug: %s", str2);
            if (th != null) {
                throw new RuntimeException(str2, th);
            }
            throw new RuntimeException(str2);
        }
        KLog.error(TAG, "crashIfDebug: " + str2, th);
    }

    public static void crashIfInMainThreadDebug(String str, Object... objArr) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            crashIfDebug(str, objArr);
        }
    }

    public static void crashIfNotInMainThreadDebug(String str, Object... objArr) {
        if (Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId()) {
            crashIfDebug(str, objArr);
        }
    }
}
