package com.donkingliang.consecutivescroller;

import android.content.res.Resources;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Util {
    private static float density = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(float dpValue) {
        return (int) ((dpValue * density) + 0.5f);
    }

    public static float px2dp(int pxValue) {
        return pxValue / density;
    }
}
