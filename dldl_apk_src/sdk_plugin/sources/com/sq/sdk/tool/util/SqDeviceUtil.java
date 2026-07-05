package com.sq.sdk.tool.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqDeviceUtil {
    public static String getNumber(Context context) {
        return "";
    }

    public static String getOS() {
        return "1";
    }

    public static String generateMac() {
        return randomDeverceNum(12);
    }

    public static String generateImEi() {
        return randomDeverceNum(15);
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0.0";
        }
    }

    private static String randomDeverceNum(int i) {
        Random random = new Random(System.currentTimeMillis());
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + random.nextInt(9);
        }
        return str;
    }

    public static int getWpixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHpixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static String getMode() {
        return Build.MODEL;
    }

    public static String getOver() {
        return "android+" + Build.VERSION.RELEASE;
    }

    public static String getBrand() {
        return Build.MODEL;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getNetType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : "NULL";
    }

    public static List<PackageInfo> getInstalledAppsInfo(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }

    public static boolean isScreenOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static String getLocalLanguage(Context context) {
        String lowerCase = Locale.getDefault().getLanguage().toLowerCase();
        if (lowerCase.length() != 2) {
            if (Build.VERSION.SDK_INT >= 24) {
                lowerCase = context.getResources().getConfiguration().getLocales().get(0).getLanguage().toLowerCase();
            } else {
                lowerCase = context.getResources().getConfiguration().locale.getLanguage().toLowerCase();
            }
        }
        if (lowerCase.length() > 2) {
            lowerCase = lowerCase.substring(0, 2);
        }
        return lowerCase + "-" + getCountry(context);
    }

    public static String getCountry(Context context) {
        String upperCase = Locale.getDefault().getCountry().toUpperCase();
        if (TextUtils.isEmpty(upperCase) && (upperCase = context.getResources().getConfiguration().locale.getCountry()) == null) {
            upperCase = "";
        }
        SqLogUtil.e("country: " + upperCase);
        return upperCase.length() > 2 ? upperCase.substring(0, 2) : upperCase;
    }

    public static String getCurrentTimeZone() {
        String displayName = TimeZone.getDefault().getDisplayName(false, 0);
        return TextUtils.isEmpty(displayName) ? "" : displayName;
    }
}
