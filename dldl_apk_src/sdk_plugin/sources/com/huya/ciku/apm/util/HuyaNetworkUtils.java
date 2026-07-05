package com.huya.ciku.apm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import com.duowan.monitor.utility.MonitorLog;
import com.sqwan.bugless.core.Constant;
import com.sqwan.bugless.util.FileUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaNetworkUtils {
    public static final String NET_TYPE_2G = "2G";
    public static final String NET_TYPE_3G = "3G";
    public static final String NET_TYPE_4G = "4G";
    public static final String NET_TYPE_NONE = "none";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";
    public static final String TAG = HuyaNetworkUtils.class.getSimpleName();

    public static String getWifiIP(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled()) {
                return null;
            }
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
            return (ipAddress & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((ipAddress >> 8) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((ipAddress >> 16) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((ipAddress >> 24) & 255);
        } catch (Exception e) {
            MonitorLog.e(TAG, e.getMessage());
            return null;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnected() || (activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting());
    }

    public static boolean isNetworkStrictlyAvailable(Context context) {
        String string;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            return true;
        }
        if (activeNetworkInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network type = ");
            sb.append(activeNetworkInfo.getType());
            sb.append(", ");
            sb.append(activeNetworkInfo.isAvailable() ? "available" : "inavailable");
            sb.append(", ");
            sb.append(activeNetworkInfo.isConnected() ? "" : "not");
            sb.append(" connected");
            string = sb.toString();
        } else {
            string = "no active network";
        }
        MonitorLog.d(Constant.DEV_NETWORK, string);
        return false;
    }

    public static String getNetWorkSubType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "unknown";
        }
        switch (activeNetworkInfo.getSubtype()) {
        }
        return "unknown";
    }

    public static String getNetWorkType(Context context) {
        return !isNetworkAvailable(context) ? "none" : isWifiActive(context) ? "wifi" : getNetWorkSubType(context);
    }

    public static boolean is2GOr3GActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean isWifiActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }
}
