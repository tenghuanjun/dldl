package com.sqwan.common.util;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SdkVersionUtil {
    private static final String KEY_LAST_SDK_VERSION = "sq_last_version";

    public static boolean isNewVersion(Context context) {
        return !VersionUtil.getSdkVersion().equals(SpUtils.get(context).getString(KEY_LAST_SDK_VERSION));
    }

    public static void updateVersion(Context context) {
        String string = SpUtils.get(context).getString(KEY_LAST_SDK_VERSION);
        String sdkVersion = VersionUtil.getSdkVersion();
        if (sdkVersion.equals(string)) {
            return;
        }
        SpUtils.get(context).put(KEY_LAST_SDK_VERSION, sdkVersion);
    }
}
