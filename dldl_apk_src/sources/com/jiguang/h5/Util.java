package com.jiguang.h5;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class Util {
    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int GetLayoutIdByName(String str, Context context) {
        return getIdByName(str, "layout", context);
    }

    public static int GetStringByName(String str, Context context) {
        return getIdByName(str, "string", context);
    }

    public static String GetStringByName1(String str, Context context) {
        return context.getResources().getString(GetStringByName(str, context));
    }

    public static int GetIdByName(String str, Context context) {
        return getIdByName(str, "id", context);
    }

    public static int GetDrawableByName(String str, Context context) {
        return getIdByName(str, "drawable", context);
    }

    public static int GetStyleByName(String str, Context context) {
        return getIdByName(str, "style", context);
    }

    public static int GetRawByName(String str, Context context) {
        return getIdByName(str, "raw", context);
    }

    static int getIdByName(String str, String str2, Context context) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }
}
