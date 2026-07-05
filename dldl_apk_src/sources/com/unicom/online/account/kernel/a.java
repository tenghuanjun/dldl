package com.unicom.online.account.kernel;

import android.content.Context;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class a {
    private static float a;
    private static DisplayMetrics b;
    private static float c;

    public static void a(Context context) {
        b = new DisplayMetrics();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        b = displayMetrics;
        float f = displayMetrics.densityDpi;
        a = f;
        c = f / 160.0f;
    }

    public final String toString() {
        return " dmDensityDpi:" + a;
    }
}
