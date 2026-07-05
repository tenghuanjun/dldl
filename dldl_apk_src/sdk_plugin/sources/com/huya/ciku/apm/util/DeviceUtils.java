package com.huya.ciku.apm.util;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Debug;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.duowan.monitor.utility.MonitorLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceUtils {
    private static final String TAG = "DeviceUtils";
    private static long mPhoneTotalMemory = 0;
    private static int mVersionCode = -1;
    private static String mVersionName;

    public static boolean isScreenLocked(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String getSimSerialNumber(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Throwable th) {
            MonitorLog.e(TAG, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Throwable th) {
            MonitorLog.e(TAG, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getMacAddress(Context context) {
        WifiInfo connectionInfo;
        try {
            connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Throwable th) {
            MonitorLog.e(TAG, "getMacAddress failed " + th.toString());
            connectionInfo = null;
        }
        return connectionInfo == null ? "" : connectionInfo.getMacAddress();
    }

    public static long getAllocateMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long getAppMemory(Context context) {
        Debug.MemoryInfo[] processMemoryInfo;
        if (context == null || (processMemoryInfo = ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()})) == null || processMemoryInfo.length <= 0) {
            return 0L;
        }
        return processMemoryInfo[0].getTotalPss() * 1024;
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public static double getPssRatio(Context context) throws Throwable {
        long jCollectPhoneTotalMemory = collectPhoneTotalMemory();
        if (jCollectPhoneTotalMemory > 0) {
            return (getAppMemory(context) * 1.0d) / jCollectPhoneTotalMemory;
        }
        return 0.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long collectPhoneTotalMemory() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "collectPhoneTotalMemory close error"
            java.lang.String r1 = "DeviceUtils"
            long r2 = com.huya.ciku.apm.util.DeviceUtils.mPhoneTotalMemory
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto Ld
            return r2
        Ld:
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L42
            java.lang.String r4 = "/proc/meminfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L42
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r4 = 8192(0x2000, float:1.148E-41)
            r2.<init>(r3, r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            java.lang.String r2 = r2.readLine()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            java.lang.String r4 = "\\s+"
            java.lang.String[] r2 = r2.split(r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r4 = 1
            r2 = r2[r4]     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            long r4 = r2.longValue()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r6 = 1024(0x400, double:5.06E-321)
            long r4 = r4 * r6
            com.huya.ciku.apm.util.DeviceUtils.mPhoneTotalMemory = r4     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r3.close()     // Catch: java.io.IOException -> L51
            goto L55
        L3b:
            r2 = move-exception
            goto L46
        L3d:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
            goto L59
        L42:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
        L46:
            java.lang.String r4 = "collectPhoneTotalMemory"
            com.duowan.monitor.utility.MonitorLog.e(r1, r4, r2)     // Catch: java.lang.Throwable -> L58
            if (r3 == 0) goto L55
            r3.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r2 = move-exception
            com.duowan.monitor.utility.MonitorLog.e(r1, r0, r2)
        L55:
            long r0 = com.huya.ciku.apm.util.DeviceUtils.mPhoneTotalMemory
            return r0
        L58:
            r2 = move-exception
        L59:
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r3 = move-exception
            com.duowan.monitor.utility.MonitorLog.e(r1, r0, r3)
        L63:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.ciku.apm.util.DeviceUtils.collectPhoneTotalMemory():long");
    }

    public static int getVersionCode(Context context) {
        if (mVersionCode == -1) {
            try {
                mVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                mVersionCode = 0;
            }
        }
        return mVersionCode;
    }

    public static String getVersionName(Context context) {
        if (mVersionName == null) {
            try {
                mVersionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
                mVersionName = "none";
            }
        }
        return mVersionName;
    }
}
