package com.huya.live.utils;

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
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            L.error(DeviceUtils.class, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Throwable th) {
            L.error(DeviceUtils.class, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getMacAddress(Context context) {
        WifiInfo connectionInfo;
        try {
            connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Throwable th) {
            L.error(DeviceUtils.class, "getMacAddress failed " + th.toString());
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v3, types: [long] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [int] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    private static long collectPhoneTotalMemory() throws Throwable {
        Throwable th;
        FileReader fileReader;
        BufferedReader bufferedReader;
        Exception e;
        ?? r0 = "collectPhoneTotalMemory close error";
        long j = mPhoneTotalMemory;
        ?? r6 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r6 > 0) {
            return j;
        }
        int i = 1;
        i = 1;
        i = 1;
        i = 1;
        i = 1;
        i = 1;
        try {
            try {
                fileReader = new FileReader("/proc/meminfo");
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
                try {
                    mPhoneTotalMemory = Long.valueOf(bufferedReader.readLine().split("\\s+")[1]).longValue() * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    try {
                        bufferedReader.close();
                        r6 = bufferedReader;
                    } catch (IOException e2) {
                        Object[] objArr = {e2};
                        L.error(TAG, "collectPhoneTotalMemory close error", objArr);
                        r6 = objArr;
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e3) {
                        i = new Object[]{e3};
                        L.error(TAG, "collectPhoneTotalMemory close error", i);
                    }
                } catch (Exception e4) {
                    e = e4;
                    L.error(TAG, "collectPhoneTotalMemory", e);
                    r6 = bufferedReader;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                            r6 = bufferedReader;
                        } catch (IOException e5) {
                            Object[] objArr2 = {e5};
                            L.error(TAG, "collectPhoneTotalMemory close error", objArr2);
                            r6 = objArr2;
                        }
                    }
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e6) {
                            i = new Object[]{e6};
                            L.error(TAG, "collectPhoneTotalMemory close error", i);
                        }
                    }
                }
            } catch (Exception e7) {
                bufferedReader = null;
                e = e7;
            } catch (Throwable th3) {
                r6 = 0;
                th = th3;
                if (r6 != 0) {
                    try {
                        r6.close();
                    } catch (IOException e8) {
                        Object[] objArr3 = new Object[i];
                        objArr3[0] = e8;
                        L.error(TAG, r0, objArr3);
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                        throw th;
                    } catch (IOException e9) {
                        Object[] objArr4 = new Object[i];
                        objArr4[0] = e9;
                        L.error(TAG, r0, objArr4);
                        throw th;
                    }
                }
                throw th;
            }
        } catch (Exception e10) {
            bufferedReader = null;
            e = e10;
            fileReader = null;
        } catch (Throwable th4) {
            r6 = 0;
            th = th4;
            fileReader = null;
        }
        r0 = mPhoneTotalMemory;
        return r0;
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
