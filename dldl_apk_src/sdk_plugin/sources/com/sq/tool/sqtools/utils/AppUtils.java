package com.sq.tool.sqtools.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AppUtils {
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static boolean isMobileNO(String str) {
        boolean z = str.length() == 11;
        if (z) {
            for (int i = 0; i < str.length(); i++) {
                try {
                    Integer.parseInt(str.charAt(i) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return z;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static long getAppInstallTime(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getAppUpdateTime(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0.0";
        }
    }

    public static String getChannelSdkVersion(Context context) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open("multiconfig");
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties.getProperty("channel_sdk_version");
        } catch (IOException unused) {
            return "";
        }
    }

    public static String getChannelName(Context context) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open("multiconfig");
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties.getProperty("channelName");
        } catch (IOException unused) {
            return "";
        }
    }
}
