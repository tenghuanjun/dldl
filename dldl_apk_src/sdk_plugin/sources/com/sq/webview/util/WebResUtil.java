package com.sq.webview.util;

import android.content.Context;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebResUtil {
    public static int getId(Context context, String name) {
        return context.getResources().getIdentifier(name, SqTrackCommonKey.id, context.getPackageName());
    }

    public static int getLayoutId(Context context, String name) {
        return context.getResources().getIdentifier(name, "layout", context.getPackageName());
    }

    public static int getDrawableId(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static int getStyleId(Context context, String themeName) {
        return context.getResources().getIdentifier(themeName, "style", context.getPackageName());
    }
}
