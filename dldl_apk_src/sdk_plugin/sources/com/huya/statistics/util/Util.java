package com.huya.statistics.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.huya.mtp.deviceid.utils.DeviceUtils;
import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Util {
    private static final String HUYA_ID = "KEY_HUYA_ID";
    private static final String IMEI = "KEY_IMEI";
    private static final String MAC_ADDRESS = "KEY_MAC_ADDRESS";
    private static final int NET_2G = 1;
    private static final int NET_3G = 2;
    private static final int NET_4G = 4;
    private static final int NET_UNKNOWN = 0;
    private static final int NET_WIFI = 3;
    private static final String SP_NAME = "statistics_huya";
    private static String macAddress;
    private static final Object macAddressSyncKey = new Object();
    private static String huyaId = null;
    public static String lang = null;
    public static String os = null;
    private static String screenResolution = null;
    private static String sCpuType = null;
    private static boolean sCpuTypeDone = false;
    public static String imei = null;
    public static boolean imeiInited = false;
    public static String sPkgName = null;

    public static String asEmptyOnNull(String str) {
        return str == null ? "" : str;
    }

    public static String getAndroidId(Context context) {
        return DeviceUtils.getAndroidId(context);
    }

    public static String getHuyaId(Context context) {
        if (!TextUtils.isEmpty(huyaId)) {
            return huyaId;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
            String string = sharedPreferences.getString(HUYA_ID, null);
            huyaId = string;
            if (!TextUtils.isEmpty(string)) {
                return huyaId;
            }
            if (!TextUtils.isEmpty(huyaId)) {
                return huyaId;
            }
            huyaId = encryptMD5(getAndroidId(context) + "_" + getMac(context));
            sharedPreferences.edit().putString(HUYA_ID, huyaId).commit();
            return huyaId;
        } catch (Exception unused) {
            return huyaId;
        }
    }

    public static String getLang() {
        String str = lang;
        if (str != null) {
            return str;
        }
        Locale locale = Locale.getDefault();
        String str2 = locale.getLanguage() + "-" + locale.getCountry();
        lang = str2;
        return str2;
    }

    public static String getOS() {
        String str = os;
        if (str != null) {
            return str;
        }
        String str2 = "Android" + Build.VERSION.RELEASE;
        os = str2;
        return str2;
    }

    public static String getSjp() {
        return Build.BRAND;
    }

    public static String getSjm() {
        return Build.MODEL;
    }

    public static String getScreenResolution(Context context) {
        WindowManager windowManager;
        String str = screenResolution;
        if (str != null) {
            return str;
        }
        try {
            windowManager = (WindowManager) context.getSystemService("window");
        } catch (Exception unused) {
        }
        if (windowManager == null) {
            screenResolution = "";
            return "";
        }
        Point point = new Point();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        point.x = defaultDisplay.getWidth();
        point.y = defaultDisplay.getHeight();
        screenResolution = point.x + FileUtil.FILE_EXTENSION_SEPARATOR + point.y;
        return screenResolution;
    }

    public static String encryptMD5(String str) throws Exception {
        return bytesToHexString(MessageDigest.getInstance(FeedBackConstants.KEY_LOG_MD5).digest(StringToBytes(str)));
    }

    public static String bytesToHexString(byte[] bArr) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
        }
        return sb.toString();
    }

    public static byte[] StringToBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    public static byte[] uuid2Bytes(UUID uuid) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[16]);
        byteBufferWrap.putLong(uuid.getMostSignificantBits());
        byteBufferWrap.putLong(uuid.getLeastSignificantBits());
        return byteBufferWrap.array();
    }

    public static String generateSession() {
        try {
            return encryptMD5(UUID.randomUUID().toString());
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean empty(String str) {
        return str == null || str.length() == 0;
    }

    public static String getCpuType() {
        BufferedReader bufferedReader;
        if (sCpuTypeDone) {
            return sCpuType;
        }
        sCpuTypeDone = true;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
        } catch (Throwable unused) {
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            if (line.contains("Hardware")) {
                sCpuType = line.split(":")[1].toLowerCase();
            }
            return sCpuType;
        }
        bufferedReader.close();
        return sCpuType;
    }

    public static String getMac(Context context) {
        if (!TextUtils.isEmpty(macAddress)) {
            return macAddress;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        String string = sharedPreferences.getString(MAC_ADDRESS, null);
        macAddress = string;
        if (!TextUtils.isEmpty(string)) {
            return macAddress;
        }
        synchronized (macAddressSyncKey) {
            if (!TextUtils.isEmpty(macAddress)) {
                return macAddress;
            }
            String macAddr = getMacAddr(context);
            macAddress = macAddr;
            if (!isValidMac(macAddr)) {
                String macAddr2 = getMacAddr2();
                if (isValidMac(macAddr2)) {
                    macAddress = macAddr2;
                }
            }
            if (!TextUtils.isEmpty(macAddress)) {
                sharedPreferences.edit().putString(MAC_ADDRESS, macAddress).apply();
                return macAddress;
            }
            return macAddress;
        }
    }

    public static String getIMEI(Context context) {
        if (imeiInited) {
            return imei;
        }
        imeiInited = true;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
            String string = sharedPreferences.getString(IMEI, null);
            imei = string;
            if (string != null) {
                return string;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            imei = telephonyManager.getDeviceId();
            sharedPreferences.edit().putString(IMEI, imei).apply();
            return imei;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
        }
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return 3;
        }
        if (type == 0) {
            int subtype = activeNetworkInfo.getSubtype();
            if (subtype == 7 || subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 10 || subtype == 9) {
                return 2;
            }
            return (subtype < 12 || subtype > 15) ? 1 : 4;
        }
        return 0;
    }

    public static String getNetworkTypeName(Context context) {
        int networkType = getNetworkType(context);
        return networkType != 0 ? networkType != 1 ? networkType != 2 ? networkType != 3 ? networkType != 4 ? "UNKNOWN" : "4G" : "WIFI" : "3G" : "2G" : "UNKNOWN";
    }

    public static String getDateString() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        if (i2 < 10) {
            sb.append(0);
            sb.append(i2);
        } else {
            sb.append(i2);
        }
        if (i3 < 10) {
            sb.append(0);
            sb.append(i3);
        } else {
            sb.append(i3);
        }
        if (i4 < 10) {
            sb.append(0);
            sb.append(i4);
        } else {
            sb.append(i4);
        }
        if (i5 < 10) {
            sb.append(0);
            sb.append(i5);
        } else {
            sb.append(i5);
        }
        if (i6 < 10) {
            sb.append(0);
            sb.append(i6);
        } else {
            sb.append(i6);
        }
        return sb.toString();
    }

    public static boolean isForeground(Context context) {
        try {
        } catch (Throwable unused) {
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 100) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static String getMacAddr(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getMacAddr2() {
        byte[] hardwareAddress;
        String string = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.getName().equalsIgnoreCase("wlan0") && (hardwareAddress = networkInterfaceNextElement.getHardwareAddress()) != null && hardwareAddress.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    string = sb.toString();
                }
            }
            return string;
        } catch (Throwable unused) {
            return string;
        }
    }

    public static SharedPreferences getConfig(Context context) {
        return context.getSharedPreferences(SP_NAME, 0);
    }

    public static int getVersionNo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return -1;
        }
    }

    private static boolean isValidMac(String str) {
        return (TextUtils.isEmpty(str) || str.equalsIgnoreCase("02:00:00:00:00:00")) ? false : true;
    }

    public static Map<String, String> getApps(Context context, int i) {
        HashMap map = new HashMap();
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            for (int i2 = 0; i2 < installedPackages.size(); i2++) {
                PackageInfo packageInfo = installedPackages.get(i2);
                int i3 = 1;
                if ((packageInfo.applicationInfo.flags & 1) <= 0) {
                    i3 = 2;
                }
                if (i == i3) {
                    map.put(packageInfo.applicationInfo.packageName, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
                }
            }
        } catch (Exception unused) {
        }
        return map;
    }

    public static String getPackageName(Context context) {
        if (sPkgName == null) {
            try {
                sPkgName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Throwable unused) {
            }
        }
        return sPkgName;
    }

    public static boolean isMainProcess(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.equals(getProcessName(context));
    }

    public static String getProcessName(Context context) {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }
}
