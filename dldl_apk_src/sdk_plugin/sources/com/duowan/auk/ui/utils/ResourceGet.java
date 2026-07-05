package com.duowan.auk.ui.utils;

import android.content.Context;
import com.duowan.auk.util.L;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ResourceGet {
    public static String stringByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier > 0) {
            return context.getString(identifier);
        }
        L.error(context, "can not find string by name : %s", str);
        return "";
    }

    public static int drawableIdByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        if (identifier == 0) {
            L.error(context, "can not find drawableId by name : %s", str);
        }
        return identifier;
    }

    public static int xmlIdByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "xml", context.getPackageName());
        if (identifier == 0) {
            L.error(context, "can not find xmlId by name : %s", str);
        }
        return identifier;
    }

    public static int idIdByName(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, SqTrackCommonKey.id, context.getPackageName());
        if (identifier == 0) {
            L.error(context, "can not find idId by name : %s", str);
        }
        return identifier;
    }
}
