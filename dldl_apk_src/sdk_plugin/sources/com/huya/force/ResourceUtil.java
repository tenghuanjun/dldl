package com.huya.force;

import android.content.Context;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResourceUtil {
    public static int getLayoutResIDByName(Context context, String str) {
        return getResIDByName(context, str, "layout");
    }

    public static int getIdResIDByName(Context context, String str) {
        return getResIDByName(context, str, SqTrackCommonKey.id);
    }

    public static int getStringResIDByName(Context context, String str) {
        return getResIDByName(context, str, "string");
    }

    public static int getDrawableResIDByName(Context context, String str) {
        return getResIDByName(context, str, "drawable");
    }

    public static int getMipmapResIDByName(Context context, String str) {
        return getResIDByName(context, str, "mipmap");
    }

    public static int getColorResIDByName(Context context, String str) {
        return getResIDByName(context, str, "color");
    }

    public static int getRawResIDByName(Context context, String str) {
        return getResIDByName(context, str, "raw");
    }

    public static int getDimenResIDByName(Context context, String str) {
        return getResIDByName(context, str, "dimen");
    }

    public static int[] getStyleableIntArray(Context context, String str) {
        int i;
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        for (Field field : Class.forName(context.getPackageName() + ".R$styleable").getFields()) {
            if (field.getName().equals(str)) {
                return (int[]) field.get(null);
            }
            return null;
        }
        return null;
    }

    public static int getStyleableResIDByName(Context context, String str) {
        return getResIDByName(context, str, "styleable");
    }

    public static int getStyleResIDByName(Context context, String str) {
        return getResIDByName(context, str, "style");
    }

    public static int getResIDByName(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }
}
