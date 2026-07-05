package com.sq.sdk.tool.util;

import android.content.Context;
import android.util.AttributeSet;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqResUtil {
    public static int getStyleId(String str, Context context) {
        return context.getResources().getIdentifier(str, "style", context.getPackageName());
    }

    public static int getLayoutId(String str, Context context) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    public static int getAttrByName(String str, Context context) {
        return context.getResources().getIdentifier(str, "attr", context.getPackageName());
    }

    public static int getDrawableId(String str, Context context) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    public static int getDimenId(String str, Context context) {
        return context.getResources().getIdentifier(str, "dimen", context.getPackageName());
    }

    public static int getStringId(String str, Context context) {
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }

    public static int getId(String str, Context context) {
        return context.getResources().getIdentifier(str, SqTrackCommonKey.id, context.getPackageName());
    }

    public static int getAnimId(String str, Context context) {
        return context.getResources().getIdentifier(str, "anim", context.getPackageName());
    }

    public static int getColorId(String str, Context context) {
        return context.getResources().getIdentifier(str, "color", context.getPackageName());
    }

    public static String getAttrString(Context context, AttributeSet attributeSet, String str) {
        int identifier = context.getResources().getIdentifier(str, "attr", context.getPackageName());
        String attributeValue = attributeSet.getAttributeValue(getAttrIndex(attributeSet, identifier));
        return attributeValue.startsWith("@") ? context.getString(attributeSet.getAttributeResourceValue(getAttrIndex(attributeSet, identifier), -1)) : attributeValue;
    }

    public static int getAttrIndex(AttributeSet attributeSet, int i) {
        for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
            if (attributeSet.getAttributeNameResource(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public static int getIdByName(String str, String str2) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        try {
            return applicationContext.getResources().getIdentifier(str, str2, applicationContext.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            SqLogUtil.e(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    public static int getId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, SqTrackCommonKey.id, applicationContext.getPackageName());
    }

    public static int getStringId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "string", applicationContext.getPackageName());
    }

    public static String getStringByName(String str) {
        return SQContextWrapper.getApplicationContext().getString(getStringId(str));
    }

    public static int getDrawableId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "drawable", applicationContext.getPackageName());
    }

    public static int getLayoutId(String str) {
        return SQContextWrapper.getApplicationContext().getResources().getIdentifier(str, "layout", SQContextWrapper.getApplicationContext().getPackageName());
    }

    public static int getStyleId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "style", applicationContext.getPackageName());
    }

    public static int getColorId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "color", applicationContext.getPackageName());
    }

    public static int getDimenId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "dimen", applicationContext.getPackageName());
    }

    public static int getAnimId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "anim", applicationContext.getPackageName());
    }

    public static int getMenuId(String str) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str, "menu", applicationContext.getPackageName());
    }
}
