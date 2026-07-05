package com.alipay.zoloz.toyger;

import android.util.Log;
import toygerservice.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerLog {
    public static final String DIAGNOSE = "diagnose";
    private static final String TAG = "Toyger";
    private static final String TAG_PREFIX = "Toyger_";
    private static c sTargetLogger = new a();

    public static final class a extends c {
    }

    private ToygerLog() {
    }

    public static void d(String str) {
        sTargetLogger.getClass();
        Log.d(TAG, str);
    }

    public static void e(String str) {
        sTargetLogger.getClass();
        Log.e(TAG, str);
    }

    public static void i(String str) {
        sTargetLogger.getClass();
        Log.i(TAG, str);
    }

    public static void setLogger(c cVar) {
        sTargetLogger = cVar;
    }

    public static void v(String str) {
        sTargetLogger.getClass();
        Log.v(TAG, str);
    }

    public static void w(String str) {
        sTargetLogger.getClass();
        Log.w(TAG, str);
    }

    public static void d(String str, String str2) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.d(str3, str2);
    }

    public static void e(String str, String str2) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.e(str3, str2);
    }

    public static void i(String str, String str2) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.i(str3, str2);
    }

    public static void v(String str, String str2) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.v(str3, str2);
    }

    public static void w(String str, String str2) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.w(str3, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.e(str3, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static void w(String str, String str2, Throwable th) {
        c cVar = sTargetLogger;
        String str3 = TAG_PREFIX + str;
        cVar.getClass();
        Log.w(str3, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static void e(String str, Throwable th) {
        c cVar = sTargetLogger;
        String str2 = TAG_PREFIX + str;
        cVar.getClass();
        Log.e(str2, Log.getStackTraceString(th));
    }

    public static void w(String str, Throwable th) {
        c cVar = sTargetLogger;
        String str2 = TAG_PREFIX + str;
        cVar.getClass();
        Log.w(str2, Log.getStackTraceString(th));
    }

    public static void e(Throwable th) {
        sTargetLogger.getClass();
        Log.e(TAG, Log.getStackTraceString(th));
    }

    public static void w(Throwable th) {
        sTargetLogger.getClass();
        Log.w(TAG, Log.getStackTraceString(th));
    }
}
