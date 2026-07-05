package com.huya.mtp.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceUtils {
    private static final String TAG = "DeviceUtils";
    private static long mPhoneTotalMemory = 0;
    private static int mVersionCode = -1;
    private static String mVersionName;

    public static boolean isScreenLocked(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public static String getImei(Context context) {
        Config config = Config.getInstance(context);
        String string = config.getString("RANDOM_UUID", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        try {
            String deviceId = getDeviceId(context);
            if (TextUtils.isEmpty(deviceId)) {
                string = getSimSerialNumber(context);
                if (TextUtils.isEmpty(string)) {
                    String string2 = new UUID((Build.VERSION.SDK_INT > 3 ? getAndroidId(context) : "").hashCode(), getMacAddress(context).hashCode()).toString();
                    config.setString("RANDOM_UUID", string2);
                    return string2;
                }
                config.setString("RANDOM_UUID", string);
                return string;
            }
            config.setString("RANDOM_UUID", deviceId);
            return deviceId;
        } catch (Throwable unused) {
            config.setString("RANDOM_UUID", string);
            return "";
        }
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String getSimSerialNumber(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Throwable th) {
            MTPApi.LOGGER.error(DeviceUtils.class, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Throwable th) {
            MTPApi.LOGGER.error(DeviceUtils.class, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getMacAddress(Context context) {
        WifiInfo connectionInfo;
        try {
            connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Throwable th) {
            MTPApi.LOGGER.error(DeviceUtils.class, "getMacAddress failed " + th.toString());
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

    /* JADX WARN: Removed duplicated region for block: B:33:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long collectPhoneTotalMemory() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "collectPhoneTotalMemory close error"
            java.lang.String r1 = "DeviceUtils"
            long r2 = com.huya.mtp.utils.DeviceUtils.mPhoneTotalMemory
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto Ld
            return r2
        Ld:
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L42
            java.lang.String r4 = "/proc/meminfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L42
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            r4 = 8192(0x2000, float:1.148E-41)
            r2.<init>(r3, r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            java.lang.String r2 = r2.readLine()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            java.lang.String r4 = "\\s+"
            java.lang.String[] r2 = r2.split(r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            r4 = 1
            r2 = r2[r4]     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            long r4 = r2.longValue()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            r6 = 1024(0x400, double:5.06E-321)
            long r4 = r4 * r6
            com.huya.mtp.utils.DeviceUtils.mPhoneTotalMemory = r4     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5c
            r3.close()     // Catch: java.io.IOException -> L53
            goto L59
        L3b:
            r2 = move-exception
            goto L46
        L3d:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
            goto L5d
        L42:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
        L46:
            com.huya.mtp.api.LogApi r4 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L5c
            java.lang.String r5 = "collectPhoneTotalMemory"
            r4.error(r1, r5, r2)     // Catch: java.lang.Throwable -> L5c
            if (r3 == 0) goto L59
            r3.close()     // Catch: java.io.IOException -> L53
            goto L59
        L53:
            r2 = move-exception
            com.huya.mtp.api.LogApi r3 = com.huya.mtp.api.MTPApi.LOGGER
            r3.error(r1, r0, r2)
        L59:
            long r0 = com.huya.mtp.utils.DeviceUtils.mPhoneTotalMemory
            return r0
        L5c:
            r2 = move-exception
        L5d:
            if (r3 == 0) goto L69
            r3.close()     // Catch: java.io.IOException -> L63
            goto L69
        L63:
            r3 = move-exception
            com.huya.mtp.api.LogApi r4 = com.huya.mtp.api.MTPApi.LOGGER
            r4.error(r1, r0, r3)
        L69:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.DeviceUtils.collectPhoneTotalMemory():long");
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
