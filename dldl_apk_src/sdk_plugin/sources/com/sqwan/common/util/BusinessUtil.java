package com.sqwan.common.util;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BusinessUtil {
    public static final String hasCheckUserAuthPermission = "hasCheckUserAuthPermission";

    public static boolean getAuthPermission(Context context) {
        if (context == null) {
            return false;
        }
        return SpUtils.get(context).getBoolean(hasCheckUserAuthPermission, false);
    }

    public static void setAuthPermission(Context context, Boolean bool) {
        if (context == null) {
            return;
        }
        SpUtils.get(context).put(hasCheckUserAuthPermission, bool.booleanValue());
    }
}
