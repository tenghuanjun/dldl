package com.taptap.sdk.kit.internal.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.duowan.live.common.CpuUtils;
import com.taptap.sdk.kit.internal.TapLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DeviceUtils {
    private static String TAG = "DeviceUtils";
    public static String cpuAbi;

    public static String getPlatform() {
        return "Android";
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getCurLanguageDisplayName() {
        return CommonUtils.getCurrentLocale().toLanguageTag();
    }

    public static long getDeviceTotalRam(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager == null) {
                return 0L;
            }
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getDeviceTotalRom(Context context) {
        long blockCount;
        long blockSize;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockCount = statFs.getBlockCountLong();
                blockSize = statFs.getBlockSizeLong();
            } else {
                blockCount = statFs.getBlockCount();
                blockSize = statFs.getBlockSize();
            }
            return blockCount * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getRemainingRamSize(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        } catch (Exception e) {
            TapLogger.logd(TAG, e);
            return 0L;
        }
    }

    public static long getRemainingRomSize() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (Exception e) {
            TapLogger.logd(TAG, e);
            return 0L;
        }
    }

    public static String getCpuInfo() {
        return Build.CPU_ABI;
    }

    public static String getCpuABIS() {
        String[] strArr = Build.SUPPORTED_ABIS;
        return (strArr == null || strArr.length == 0) ? "" : Arrays.toString(strArr);
    }

    public static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getPackageVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isRunInSandbox() {
        return System.getProperties().get("flag_running_in_sandbox") != null;
    }

    public static boolean isRunInCloud() {
        if (TextUtils.isEmpty(Build.MODEL)) {
            return false;
        }
        return Build.MODEL.contains("haima_cloudplay") || Build.MODEL.toLowerCase().contains("taptap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        r0 = r1.split(":")[1];
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x002b -> B:28:0x003c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCPUHardware() {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L31
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L31
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L31
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L31
        Lf:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L26
            java.lang.String r2 = "Hardware"
            boolean r2 = r1.contains(r2)     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto Lf
            java.lang.String r2 = ":"
            java.lang.String[] r1 = r1.split(r2)     // Catch: java.lang.Throwable -> L2f
            r2 = 1
            r0 = r1[r2]     // Catch: java.lang.Throwable -> L2f
        L26:
            r3.close()     // Catch: java.io.IOException -> L2a
            goto L3c
        L2a:
            r1 = move-exception
            r1.printStackTrace()
            goto L3c
        L2f:
            r1 = move-exception
            goto L34
        L31:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L34:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L3c
            r3.close()     // Catch: java.io.IOException -> L2a
        L3c:
            return r0
        L3d:
            r0 = move-exception
            if (r3 == 0) goto L48
            r3.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r1 = move-exception
            r1.printStackTrace()
        L48:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.utils.DeviceUtils.getCPUHardware():java.lang.String");
    }

    public static String getDeviceBrand() {
        return !TextUtils.isEmpty(Build.BRAND) ? Build.BRAND : "";
    }

    public static String getDeviceModel() {
        return !TextUtils.isEmpty(Build.MODEL) ? Build.MODEL : "";
    }

    public static int[] getDeviceSize(Context context) {
        int[] iArr = new int[2];
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            i = i2;
            i2 = i;
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public static String getOS() {
        return Build.VERSION.RELEASE == null ? "UNKNOWN" : Build.VERSION.RELEASE;
    }

    public static String getOSType() {
        return Build.SUPPORTED_64_BIT_ABIS.length > 0 ? CpuUtils.CPU_ARCHITECTURE_TYPE_64 : CpuUtils.CPU_ARCHITECTURE_TYPE_32;
    }

    public static String getCPUAbi() {
        if (TextUtils.isEmpty(cpuAbi)) {
            try {
                String line = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream())).readLine();
                if (line.contains("x86")) {
                    cpuAbi = "x86";
                } else if (line.contains("armeabi-v7a")) {
                    cpuAbi = "armeabi-v7a";
                } else if (line.contains("arm64-v8a")) {
                    cpuAbi = "arm64-v8a";
                } else {
                    cpuAbi = "armeabi";
                }
            } catch (Exception unused) {
                cpuAbi = "armeabi";
            }
        }
        return cpuAbi;
    }

    public static String getCarrier(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (telephonyManager == null || TextUtils.isEmpty(telephonyManager.getNetworkOperatorName())) ? "unknown" : telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            TapLogger.logd(TAG, e);
            return null;
        }
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager;
        String deviceId;
        try {
            if (!hasReadPhoneStatePermission(context) || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT > 28) {
                if (telephonyManager.hasCarrierPrivileges()) {
                    deviceId = telephonyManager.getImei();
                } else {
                    TapLogger.logd(TAG, "Can not get IMEI info.");
                    return "";
                }
            } else if (Build.VERSION.SDK_INT >= 26) {
                deviceId = telephonyManager.getImei();
            } else {
                deviceId = telephonyManager.getDeviceId();
            }
            return deviceId;
        } catch (Exception e) {
            TapLogger.logd(TAG, e);
            return "";
        }
    }

    private static boolean hasReadPhoneStatePermission(Context context) {
        if (Build.VERSION.SDK_INT > 28) {
            if (checkHasPermission(context, "android.permission.READ_PRECISE_PHONE_STATE")) {
                return true;
            }
            TapLogger.logd(TAG, "Don't have permission android.permission.READ_PRECISE_PHONE_STATE,getDeviceID failed");
            return false;
        }
        if (checkHasPermission(context, "android.permission.READ_PHONE_STATE")) {
            return true;
        }
        TapLogger.logd(TAG, "Don't have permission android.permission.READ_PHONE_STATE,getDeviceID failed");
        return false;
    }

    public static boolean checkHasPermission(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
