package com.huyaudbunify.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.WindowManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaCfgUtil {
    public static String getChannel(Context context) {
        return getMetaData(context, "HY_CHANNEL");
    }

    public static String getAppId(Context context) {
        return getMetaData(context, "HY_APPID");
    }

    public static String getAppKey(Context context) {
        return getMetaData(context, "HY_APPKEY");
    }

    public static String getMetaData(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getSystemVersion() {
        return Build.MODEL + "," + Build.VERSION.SDK + "," + Build.VERSION.RELEASE;
    }

    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static int[] DisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        return new int[]{windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight()};
    }
}
