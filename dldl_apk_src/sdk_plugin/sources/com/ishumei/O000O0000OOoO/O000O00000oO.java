package com.ishumei.O000O0000OOoO;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000oO {
    private static boolean O0000O000000oO = false;
    private static int O000O00000OoO = 5;

    private static String O0000O000000oO(String str, Object... objArr) {
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof String[]) {
                objArr[i] = O0000O000000oO((String[]) objArr[i]);
            }
        }
        return "[" + Thread.currentThread().getId() + "] " + String.format(str, objArr);
    }

    private static String O0000O000000oO(String[] strArr) {
        if (strArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
            sb.append(", ");
        }
        sb.append(strArr[length]);
        sb.append("]");
        return sb.toString();
    }

    public static void O0000O000000oO(String str, String str2) {
        if (!O0000O000000oO || O000O00000OoO > 3) {
            return;
        }
        Log.d(str, str2);
    }

    public static void O0000O000000oO(String str, String str2, Object... objArr) {
        if (!O0000O000000oO || O000O00000OoO > 6) {
            return;
        }
        Log.e(str, O0000O000000oO(str2, objArr));
    }

    public static void O0000O000000oO(Throwable th) {
        if (O0000O000000oO) {
            th.printStackTrace();
        }
    }

    public static void O000O00000OoO(String str, String str2) {
        if (!O0000O000000oO || O000O00000OoO > 4) {
            return;
        }
        Log.i(str, str2);
    }

    public static void O000O00000o0O(String str, String str2) {
        if (!O0000O000000oO || O000O00000OoO > 5) {
            return;
        }
        Log.w(str, str2);
    }

    public static void O000O00000oO(String str, String str2) {
        if (!O0000O000000oO || O000O00000OoO > 6) {
            return;
        }
        Log.e(str, str2);
    }
}
