package com.aliyun.aliyunface.camera.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class DisplayUtil {
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static float getScreenRate(Context context) {
        Point screenMetrics = getScreenMetrics(context);
        return screenMetrics.y / screenMetrics.x;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
