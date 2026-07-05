package net.security.device.api;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class LogUtil {
    private static boolean IS_DEBUG = true;
    private static String TAG = "AliyunRiskDetectJava";

    public static void i(String str) {
        if (IS_DEBUG) {
            Log.i(TAG + "-->:" + getTargetStackTraceElement(), str);
        }
    }

    public static void e(String str) {
        if (IS_DEBUG) {
            Log.e(TAG + "-->" + getTargetStackTraceElement(), str);
        }
    }

    public static void e(String str, Throwable th) {
        if (IS_DEBUG) {
            Log.e(TAG + "-->" + getTargetStackTraceElement(), str, th);
        }
    }

    public static void w(String str) {
        if (IS_DEBUG) {
            Log.w(TAG + "-->:" + getTargetStackTraceElement(), str);
        }
    }

    public static void v(String str) {
        if (IS_DEBUG) {
            Log.v(TAG + "-->:" + getTargetStackTraceElement(), str);
        }
    }

    public static void d(String str) {
        if (IS_DEBUG) {
            Log.d(TAG + "-->:" + getTargetStackTraceElement(), str);
        }
    }

    private static String getTargetStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace.length >= 4 ? stackTrace[4] : null;
        if (stackTraceElement == null) {
            return "";
        }
        return "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }
}
