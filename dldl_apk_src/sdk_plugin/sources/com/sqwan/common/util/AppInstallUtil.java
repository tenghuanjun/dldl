package com.sqwan.common.util;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppInstallUtil {
    public static boolean getAppInstallResult(Context context, String str) {
        try {
            if (context.getPackageManager().getLaunchIntentForPackage(str) == null) {
                return false;
            }
            return !r2.queryIntentActivities(r3, 65536).isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
