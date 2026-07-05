package com.huya.berry.endlive.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DebugConfig {
    private static boolean mBarragePrintFourth = false;
    private static boolean mBarrageSwitcher = true;
    private static boolean mIsBarrageToastOn;

    public static boolean isBarragePrintFourth() {
        return mBarragePrintFourth;
    }

    public static boolean isBarrageOn() {
        return mBarrageSwitcher;
    }
}
