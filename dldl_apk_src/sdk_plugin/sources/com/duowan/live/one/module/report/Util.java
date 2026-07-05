package com.duowan.live.one.module.report;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.sqwan.bugless.util.FileUtil;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Util {
    private static final int NET_2G = 1;
    private static final int NET_3G = 2;
    private static final int NET_4G = 4;
    private static final int NET_UNKNOWN = 0;
    private static final int NET_WIFI = 3;
    public static final String REPORT_CONFIG_KEY = "record_app_status_disable_report";
    public static String androidId;
    public static String imsi;
    public static String lang;
    public static String mac;
    public static String os;
    private static String screenResolution;

    public static String getMacAddr(Context context) {
        if (mac != null) {
            return mac;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        String macAddress = null;
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null) {
            macAddress = connectionInfo.getMacAddress();
        }
        mac = macAddress;
        return mac;
    }

    public static String getAndroidId(Context context) {
        if (androidId != null) {
            return androidId;
        }
        androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return androidId;
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

    public static String getSjp(Context context) {
        return Build.MANUFACTURER;
    }

    public static String getSjm(Context context) {
        return Build.MODEL;
    }

    public static String getScreenResolution(Context context) {
        if (screenResolution != null) {
            return screenResolution;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
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

    public static String getPackageName(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static int getVersionNo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return -1;
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

    public static String format(long j, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(j));
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

    public static boolean isReportEnabled() {
        if (ArkValue.gIsSnapshot || ArkValue.debuggable()) {
            return Config.getInstance(ArkValue.gContext).getBoolean(REPORT_CONFIG_KEY, true);
        }
        return true;
    }
}
