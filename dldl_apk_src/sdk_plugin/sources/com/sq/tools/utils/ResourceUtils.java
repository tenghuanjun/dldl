package com.sq.tools.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class ResourceUtils {
    public static int getId(Context context, String str, String str2) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static int getColor(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "color", context.getPackageName())) == 0) {
            return 0;
        }
        return context.getResources().getColor(identifier);
    }

    public static float getDimension(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "dimen", context.getPackageName())) == 0) {
            return 0.0f;
        }
        return context.getResources().getDimension(identifier);
    }

    public static String getString(Context context, String str) {
        int identifier;
        return (context == null || (identifier = context.getResources().getIdentifier(str, "string", context.getPackageName())) == 0) ? "" : context.getResources().getString(identifier);
    }

    public static int[] getIntArray(Context context, String str) {
        if (context == null) {
            return new int[0];
        }
        int identifier = context.getResources().getIdentifier(str, "array", context.getPackageName());
        return identifier == 0 ? new int[0] : context.getResources().getIntArray(identifier);
    }

    public static String[] getStringArray(Context context, String str) {
        if (context == null) {
            return new String[0];
        }
        int identifier = context.getResources().getIdentifier(str, "array", context.getPackageName());
        return identifier == 0 ? new String[0] : context.getResources().getStringArray(identifier);
    }

    public static Boolean getBoolean(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "bool", context.getPackageName())) == 0) {
            return null;
        }
        return Boolean.valueOf(context.getResources().getBoolean(identifier));
    }

    public static Integer getInteger(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "integer", context.getPackageName())) == 0) {
            return null;
        }
        return Integer.valueOf(context.getResources().getInteger(identifier));
    }

    public static AssetManager getAsset(Context context) {
        if (context != null) {
            return context.getResources().getAssets();
        }
        return null;
    }

    public static XmlResourceParser getAnim(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "anim", context.getPackageName())) == 0) {
            return null;
        }
        return context.getResources().getAnimation(identifier);
    }

    public static Drawable getDrawable(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName())) == 0) {
            return null;
        }
        return context.getResources().getDrawable(identifier);
    }

    public static XmlResourceParser getLayout(Context context, String str) {
        int identifier;
        if (context == null || (identifier = context.getResources().getIdentifier(str, "layout", context.getPackageName())) == 0) {
            return null;
        }
        return context.getResources().getLayout(identifier);
    }

    public static int getLayoutIdByName(Context context, String str) {
        return getId(context, str, "layout");
    }

    public static int getColorIdByName(Context context, String str) {
        return getId(context, str, "color");
    }

    public static int getArrayIdByName(Context context, String str) {
        return getId(context, str, "array");
    }

    public static int getStringIdByName(Context context, String str) {
        return getId(context, str, "string");
    }

    public static String getStringByName(Context context, String str) {
        return context.getResources().getString(getId(context, str, "string"));
    }

    public static int getViewIdByName(Context context, String str) {
        return getId(context, str, SqTrackCommonKey.id);
    }

    public static int getDrawableIdByName(Context context, String str) {
        return getId(context, str, "drawable");
    }

    public static int getMipmapIdByName(Context context, String str) {
        return getId(context, str, "mipmap");
    }

    public static int getAnimIdByName(Context context, String str) {
        return getId(context, str, "anim");
    }

    public static int getStyleIdByName(Context context, String str) {
        return getId(context, str, "style");
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.String] */
    public static <T> T getAttributeSetValue(Context context, AttributeSet attributeSet, String str, Class<T> cls, T t) {
        if (TextUtils.isEmpty(str) || cls == null) {
            return t;
        }
        int attributeCount = attributeSet.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (str.equals(attributeSet.getAttributeName(i))) {
                ?? r4 = (T) attributeSet.getAttributeValue(i);
                if (cls.equals(String.class)) {
                    return r4;
                }
                if (cls.equals(Boolean.class)) {
                    return (T) Boolean.valueOf((String) r4);
                }
                if (cls.equals(Drawable.class)) {
                    return (T) context.getResources().getDrawable(Integer.parseInt(r4.replace("@", "")));
                }
                if (!cls.equals(View.class)) {
                    return t;
                }
                return (T) LayoutInflater.from(context).inflate(Integer.parseInt(r4.replace("@", "")), (ViewGroup) null);
            }
        }
        return t;
    }
}
