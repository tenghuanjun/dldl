package com.sy37sdk.utils;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class DisplayUtil {
    public static int dip2px(float f, float f2) {
        return (int) ((f * f2) + 0.5f);
    }

    public static int px2dip(float f, float f2) {
        return (int) ((f / f2) + 0.5f);
    }

    public static int px2sp(float f, float f2) {
        return (int) ((f / f2) + 0.5f);
    }

    public static int sp2px(float f, float f2) {
        return (int) ((f * f2) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void setViewMeasurement(View view, int i, int i2, double d) {
        if (i < i2) {
            i = i2;
        }
        int i3 = (int) (((double) i) * d);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = i3;
        layoutParams.height = i3;
        layoutParams.setMargins(0, (i3 / 2) + i3, i3, 0);
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static int getScreenLong(Context context) {
        return Math.max(getScreenWidth(context), getScreenHeight(context));
    }

    public static int getScreenShort(Context context) {
        return Math.min(getScreenWidth(context), getScreenHeight(context));
    }
}
