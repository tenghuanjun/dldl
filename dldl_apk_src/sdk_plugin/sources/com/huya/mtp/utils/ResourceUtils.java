package com.huya.mtp.utils;

import android.content.Context;
import android.os.Bundle;
import android.util.AndroidException;
import com.huya.mtp.api.MTPApi;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResourceUtils {
    public static String getMetaValue(Context context, String str) {
        return getMetaValue(context, str, "");
    }

    public static synchronized String getMetaValue(Context context, String str, String str2) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return str2;
            }
            String string = bundle.getString(str, "");
            if (!FP.empty(string)) {
                return string;
            }
            int i = bundle.getInt(str, 0);
            if (i != 0) {
                str2 = String.valueOf(i);
            }
            return str2;
        } catch (AndroidException unused) {
            return str2;
        }
    }

    public static String getStringByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier > 0) {
            return context.getString(identifier);
        }
        MTPApi.LOGGER.error(context, "can not find string by name : %s", str);
        return "";
    }

    public static int getDrawableIdByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        if (identifier == 0) {
            MTPApi.LOGGER.error(context, "can not find drawableId by name : %s", str);
        }
        return identifier;
    }

    public static int getXmlIdByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "xml", context.getPackageName());
        if (identifier == 0) {
            MTPApi.LOGGER.error(context, "can not find xmlId by name : %s", str);
        }
        return identifier;
    }

    public static int getIdIdByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, SqTrackCommonKey.id, context.getPackageName());
        if (identifier == 0) {
            MTPApi.LOGGER.error(context, "can not find idId by name : %s", str);
        }
        return identifier;
    }
}
