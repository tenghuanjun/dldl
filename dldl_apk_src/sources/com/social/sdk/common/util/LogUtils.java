package com.social.sdk.common.util;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class LogUtils {
    public static final int D = 3;
    public static final int E = 6;
    public static final int I = 4;
    private static final String NULL = "null";
    private static final String NULL_TIPS = "Log with null object.";
    public static final int V = 2;
    public static final int W = 5;
    public static boolean isShowLog = true;
    private static String mGlobalTag = "socialsdk";

    public static void v(Object obj) {
        log(2, mGlobalTag, obj);
    }

    public static void v(String str, Object... objArr) {
        log(2, str, objArr);
    }

    public static void d(Object obj) {
        log(3, mGlobalTag, obj);
    }

    public static void d(String str, Object... objArr) {
        log(3, str, objArr);
    }

    public static void i(Object obj) {
        log(4, mGlobalTag, obj);
    }

    public static void i(String str, Object... objArr) {
        log(4, str, objArr);
    }

    public static void w(Object obj) {
        log(5, mGlobalTag, obj);
    }

    public static void w(String str, Object... objArr) {
        log(5, str, objArr);
    }

    public static void e(Object obj) {
        log(6, mGlobalTag, obj);
    }

    public static void e(String str, Object... objArr) {
        log(6, str, objArr);
    }

    private static void log(int i, String str, Object... objArr) {
        String string;
        if (isShowLog) {
            if (objArr == null || objArr.length <= 0) {
                string = NULL_TIPS;
            } else {
                string = "null";
                if (objArr.length == 1) {
                    Object obj = objArr[0];
                    if (obj != null) {
                        string = obj.toString();
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < objArr.length; i2++) {
                        Object obj2 = objArr[i2];
                        sb.append("  ");
                        sb.append(obj2 == null ? "null" : obj2.toString());
                    }
                    string = sb.toString();
                }
            }
            Log.println(i, str, string);
        }
    }
}
