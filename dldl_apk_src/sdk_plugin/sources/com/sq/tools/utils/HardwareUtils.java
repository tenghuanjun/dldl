package com.sq.tools.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class HardwareUtils {
    public static int getScreenHeightPx(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidthPx(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeightDp(Context context) {
        return (int) (getScreenHeightPx(context) / getDensity(context));
    }

    public static int getScreenWidthDp(Context context) {
        return (int) (getScreenWidthPx(context) / getDensity(context));
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static String getBrand() {
        return TextUtils.isEmpty(Build.BRAND) ? "" : Build.BRAND;
    }

    public static String getModel() {
        return TextUtils.isEmpty(Build.MODEL) ? "" : Build.MODEL;
    }

    public static long getTotalRam(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return 0L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    public static long getExternalTotalMemory() {
        if (!isSDCardEnable()) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong()) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static int getCpuCores() {
        try {
            return Runtime.getRuntime().availableProcessors();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getCpuModel() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String line;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException unused) {
                bufferedReader = null;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        }
        do {
            try {
                line = bufferedReader.readLine();
                if (!TextUtils.isEmpty(line)) {
                }
            } catch (IOException unused3) {
                fileReader2 = fileReader;
                fileReader = fileReader2;
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException unused4) {
                }
            }
            if (bufferedReader == null) {
                return "";
            }
            try {
                bufferedReader.close();
                return "";
            } catch (IOException unused5) {
                return "";
            }
        } while (!line.contains("Processor"));
        return line.split(":")[1].trim();
    }

    public static String getMaxCpuFreq() {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                sb.append(new String(bArr));
            }
            inputStream.close();
        } catch (Exception unused) {
            sb = new StringBuilder("N/A");
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
        return sb.toString().trim();
    }

    public static String getCpuArch() {
        return (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) ? TextUtils.isEmpty(Build.CPU_ABI) ? "" : Build.CPU_ABI : TextUtils.isEmpty(Build.SUPPORTED_ABIS[0]) ? "" : Build.SUPPORTED_ABIS[0];
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static int getCurrentSimNum(Context context) {
        if (!PermissionUtils.hasAndroidPermission(context, "android.permission.READ_PHONE_STATE") || Build.VERSION.SDK_INT < 22) {
            return 0;
        }
        try {
            SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
            if (subscriptionManager != null) {
                return subscriptionManager.getActiveSubscriptionInfoCount();
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public static int getSimSlotNum(Context context) {
        TelephonyManager telephonyManager;
        if (Build.VERSION.SDK_INT >= 23 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
            return telephonyManager.getPhoneCount();
        }
        return 0;
    }

    private HardwareUtils() {
    }
}
