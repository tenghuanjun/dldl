package com.donkingliang.imageselector.utils;

import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public class VersionUtils {
    public static boolean isAndroidL() {
        return true;
    }

    public static boolean isAndroidN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAndroidP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean isAndroidQ() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
