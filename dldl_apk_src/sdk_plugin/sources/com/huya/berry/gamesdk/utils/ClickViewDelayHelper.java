package com.huya.berry.gamesdk.utils;

import android.os.SystemClock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ClickViewDelayHelper {
    private static long lastClickTime;

    public static boolean enableClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - lastClickTime < 1000) {
            return false;
        }
        lastClickTime = jElapsedRealtime;
        return true;
    }

    public static boolean enableClick2() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - lastClickTime < 2000) {
            return false;
        }
        lastClickTime = jElapsedRealtime;
        return true;
    }

    public static boolean enableClick3() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - lastClickTime < 500) {
            return false;
        }
        lastClickTime = jElapsedRealtime;
        return true;
    }
}
