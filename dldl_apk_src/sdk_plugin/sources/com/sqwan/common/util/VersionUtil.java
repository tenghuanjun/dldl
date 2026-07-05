package com.sqwan.common.util;

import android.content.Context;
import com.sq.data.BuildConfig;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class VersionUtil {
    public static final String gwversion = "4.6.7";
    public static String original_version = getSdkVersion();
    public static String sdkVersion = getSdkVersion();

    public static String getVersionStr(Context context) {
        if (ModHelper.getPluginMod().getPluginVersion() >= 0) {
            return sdkVersion + "_4.6.7_" + getPluginVersion(context);
        }
        return sdkVersion + "_4.6.7";
    }

    public static String getSdkVersion() {
        return "gds".equals(MultiSdkManager.getInstance().getScut3()) ? BuildConfig.gdsLibraryVersion : "3.7.9.6.1";
    }

    public static int getPluginVersion(Context context) {
        return ModHelper.getPluginMod().getPluginVersion();
    }

    public static void setOriginalVersion(String str) {
        original_version = str;
    }

    public static String getOriginalVersion() {
        return original_version;
    }
}
