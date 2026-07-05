package com.sqwan.common.util;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DensityUtil {
    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
