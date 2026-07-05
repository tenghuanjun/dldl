package com.sqinject.core.util;

import android.content.Context;
import android.view.View;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IdUtils {
    public static String getStringByName(String str, Context context, String str2) {
        String string = context.getString(context.getResources().getIdentifier(str, "string", context.getPackageName()));
        if (string != null) {
            return string;
        }
        throw new IllegalStateException("Required string '" + str + " for " + str2 + " was not found.");
    }

    public static int getIntByName(String str, Context context) {
        return context.getResources().getInteger(context.getResources().getIdentifier(str, "integer", context.getPackageName()));
    }

    public static int getColorByName(String str, Context context) {
        return context.getResources().getColor(context.getResources().getIdentifier(str, "color", context.getPackageName()));
    }

    public static int getIdByName(String str, String str2, Context context) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static View findViewByName(String str, View view) {
        return view.findViewById(view.getResources().getIdentifier(str, SqTrackCommonKey.id, view.getContext().getPackageName()));
    }
}
