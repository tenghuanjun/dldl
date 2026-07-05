package com.bytedance.pangle.util;

import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class i {
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT < 21;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25;
    }

    public static boolean f() {
        return Build.VERSION.SDK_INT >= 26 && Build.VERSION.SDK_INT <= 28;
    }

    public static boolean g() {
        return Build.VERSION.SDK_INT >= 29 && Build.VERSION.SDK_INT <= 30;
    }

    public static boolean h() {
        return Build.VERSION.SDK_INT > 23;
    }

    public static boolean i() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean j() {
        if (Build.VERSION.SDK_INT < 28) {
            return Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0;
        }
        return true;
    }

    public static boolean k() {
        if (Build.VERSION.SDK_INT < 31) {
            return Build.VERSION.SDK_INT == 30 && Build.VERSION.PREVIEW_SDK_INT > 0;
        }
        return true;
    }
}
