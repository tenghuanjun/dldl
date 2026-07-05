package com.sqwan.common.util;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqResUtils {
    private static Class MixResourceClass = null;
    private static final String TYPE_ANIM = "anim";
    private static final String TYPE_ATTR = "attr";
    private static final String TYPE_COLOR = "color";
    private static final String TYPE_DIMEN = "dimen";
    private static final String TYPE_DRAWABLE = "drawable";
    private static final String TYPE_ID = "id";
    private static final String TYPE_LAYOUT = "layout";
    private static final String TYPE_MENU = "menu";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STYLE = "style";

    public static int getAttr(Context context, String str) {
        return getIdentifier(context, str, TYPE_ATTR, context.getPackageName());
    }

    public static int getId(Context context, String str) {
        return getIdentifier(context, str, "id", context.getPackageName());
    }

    public static int getLayoutId(Context context, String str) {
        return getIdentifier(context, str, "layout", context.getPackageName());
    }

    public static int getStringId(Context context, String str) {
        return getIdentifier(context, str, TYPE_STRING, context.getPackageName());
    }

    public static String getStringByName(Context context, String str) {
        return context.getString(getStringId(context, str));
    }

    public static int getDrawableId(Context context, String str) {
        return getIdentifier(context, str, TYPE_DRAWABLE, context.getPackageName());
    }

    public static int getColorId(Context context, String str) {
        return getIdentifier(context, str, "color", context.getPackageName());
    }

    public static int getColorByName(Context context, String str) {
        return context.getResources().getColor(getColorId(context, str));
    }

    public static int getDimenId(Context context, String str) {
        return getIdentifier(context, str, TYPE_DIMEN, context.getPackageName());
    }

    public static int getStyleId(Context context, String str) {
        return getIdentifier(context, str, TYPE_STYLE, context.getPackageName());
    }

    public static int getAnimId(Context context, String str) {
        return getIdentifier(context, str, TYPE_ANIM, context.getPackageName());
    }

    public static int getMenuId(Context context, String str) {
        return getIdentifier(context, str, "menu", context.getPackageName());
    }

    public static int getIdByName(String str, String str2, Context context) {
        try {
            return getIdentifier(context, str, str2, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    public static int getIdByNameHostFirst(String str, String str2, Context context) {
        try {
            return getIdentifierHostFirst(context, str, str2, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    private static int getIdentifierHostFirst(Context context, String str, String str2, String str3) {
        int identifier = context.getResources().getIdentifier(str, str2, str3);
        return identifier != -1 ? identifier : getIdentifierFromPlugin(context, str, str2);
    }

    private static int getIdentifier(Context context, String str, String str2, String str3) {
        int identifierFromPlugin = getIdentifierFromPlugin(context, str, str2);
        return identifierFromPlugin != -1 ? identifierFromPlugin : context.getResources().getIdentifier(str, str2, str3);
    }

    private static int getIdentifierFromPlugin(Context context, String str, String str2) {
        try {
            if (MixResourceClass == null) {
                MixResourceClass = context.getClassLoader().loadClass("com.plugin.core.resources.MixResources");
            }
            if (MixResourceClass == null) {
                return -1;
            }
            return ((Integer) MixResourceClass.getMethod("getIdentifierFromPlugin", String.class, String.class).invoke(context.getResources(), str, str2)).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    public static int getDimensionPixelSize(Context context, String str) {
        return (int) context.getResources().getDimension(getDimenId(context, str));
    }
}
