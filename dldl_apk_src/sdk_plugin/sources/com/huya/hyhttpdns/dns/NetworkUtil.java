package com.huya.hyhttpdns.dns;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class NetworkUtil {
    public static final String NET_TYPE_2G = "2G";
    public static final String NET_TYPE_3G = "3G";
    public static final String NET_TYPE_4G = "4G";
    public static final String NET_TYPE_COMMON = "common";
    public static final String NET_TYPE_MOBILE = "mobile";
    public static final String NET_TYPE_NONE = "none";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";

    NetworkUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getNetworkName(android.content.Context r6) {
        /*
            java.lang.String r0 = getNetWorkType(r6)
            int r1 = r0.hashCode()
            r2 = 1621(0x655, float:2.272E-42)
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == r2) goto L3b
            r2 = 1652(0x674, float:2.315E-42)
            if (r1 == r2) goto L31
            r2 = 1683(0x693, float:2.358E-42)
            if (r1 == r2) goto L27
            r2 = 3649301(0x37af15, float:5.11376E-39)
            if (r1 == r2) goto L1d
            goto L45
        L1d:
            java.lang.String r1 = "wifi"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L45
            r1 = 3
            goto L46
        L27:
            java.lang.String r1 = "4G"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L45
            r1 = 2
            goto L46
        L31:
            java.lang.String r1 = "3G"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L45
            r1 = 1
            goto L46
        L3b:
            java.lang.String r1 = "2G"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L45
            r1 = 0
            goto L46
        L45:
            r1 = -1
        L46:
            if (r1 == 0) goto L65
            if (r1 == r5) goto L65
            if (r1 == r4) goto L65
            if (r1 == r3) goto L4f
            goto L67
        L4f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "wifi-"
            r0.append(r1)
            java.lang.String r6 = getWifiSSID(r6)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            goto L67
        L65:
            java.lang.String r0 = "mobile"
        L67:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.hyhttpdns.dns.NetworkUtil.getNetworkName(android.content.Context):java.lang.String");
    }

    public static String getWifiSSID(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getSSID() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean isWifiActive(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception unused) {
            connectivityManager = null;
        }
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    public static String getNetWorkType(Context context) {
        return !isNetworkAvailable(context) ? "none" : isWifiActive(context) ? "wifi" : getNetWorkSubType(context);
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

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            if (!activeNetworkInfo.isConnected()) {
                if (!activeNetworkInfo.isAvailable()) {
                    return false;
                }
                if (!activeNetworkInfo.isConnectedOrConnecting()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isIpAddress(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(str).matches();
    }
}
