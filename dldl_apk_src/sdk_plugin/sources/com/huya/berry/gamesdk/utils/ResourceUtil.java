package com.huya.berry.gamesdk.utils;

import android.app.Application;
import com.duowan.auk.ArkValue;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ResourceUtil {
    public static int getLayoutResIDByName(String str) {
        return getResIDByName(str, "layout");
    }

    public static int getIdResIDByName(String str) {
        return getResIDByName(str, SqTrackCommonKey.id);
    }

    public static int getStringResIDByName(String str) {
        return getResIDByName(str, "string");
    }

    public static int getDrawableResIDByName(String str) {
        return getResIDByName(str, "drawable");
    }

    public static int getMipmapResIDByName(String str) {
        return getResIDByName(str, "mipmap");
    }

    public static int getColorResIDByName(String str) {
        return getResIDByName(str, "color");
    }

    public static int getRawResIDByName(String str) {
        return getResIDByName(str, "raw");
    }

    public static int getDimenResIDByName(String str) {
        return getResIDByName(str, "dimen");
    }

    public static int[] getStyleableIntArray(String str) {
        Object resourceIdNew = getResourceIdNew(str, "styleable");
        if (resourceIdNew == null || !(resourceIdNew instanceof int[])) {
            return null;
        }
        return (int[]) resourceIdNew;
    }

    public static int getStyleableResIDByName(String str) {
        Object resourceIdNew = getResourceIdNew(str, "styleable");
        if (resourceIdNew == null || !(resourceIdNew instanceof Integer)) {
            return 0;
        }
        return ((Integer) getResourceIdNew(str, "styleable")).intValue();
    }

    public static int getStyleResIDByName(String str) {
        return getResIDByName(str, "style");
    }

    public static int getResIDByName(String str, String str2) {
        Application application = ArkValue.gContext;
        return application.getResources().getIdentifier(str, str2, application.getPackageName());
    }

    private static Object getResourceIdNew(String str, String str2) {
        int i;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Class<?> cls : Class.forName(ArkValue.gContext.getPackageName() + ".R").getClasses()) {
            if (cls.getSimpleName().equals(str2)) {
                for (Field field : cls.getFields()) {
                    if (field.getName().equals(str)) {
                        return field.get(null);
                    }
                    return null;
                }
            }
        }
        return null;
    }
}
