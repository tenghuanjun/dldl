package com.huya.mtp.hycloudgame.base.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.huya.mtp.api.MTPApi;
import com.sqwan.bugless.core.Constant;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetworkUtil {
    private static final int DEFAULT_PROXY_PORT = 80;
    private static final int MAX_PORT = 65535;
    private static final int MIN_PORT = 0;
    public static final String NET_TYPE_2G = "2G";
    public static final String NET_TYPE_3G = "3G";
    public static final String NET_TYPE_4G = "4G";
    public static final String NET_TYPE_NONE = "none";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";

    public static String getWifiSSID(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getSSID() : "";
        } catch (Throwable th) {
            MTPApi.LOGGER.error("NetworkUtil", th);
            return "";
        }
    }

    public static boolean isWifiActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static boolean is2GOr3GActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
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
        MTPApi.LOGGER.info(Constant.DEV_NETWORK, string);
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnected() || (activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting());
    }

    public static void openNetworkConfig(Context context) {
        Intent intent;
        if (Build.VERSION.SDK_INT > 10) {
            intent = new Intent("android.settings.WIRELESS_SETTINGS");
        } else {
            intent = new Intent();
            intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setAction("android.intent.action.MAIN");
        }
        try {
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static InetSocketAddress getTunnelProxy(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.WRITE_APN_SETTINGS") == -1) {
            return null;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("proxy"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("port"));
            MTPApi.LOGGER.info("getTunnelProxy", ", proxy = " + string + ", port = " + string2);
            if (string != null && string.length() > 0) {
                cursorQuery.close();
                int i = 80;
                try {
                    int i2 = Integer.parseInt(string2);
                    if (i2 >= 0 && i2 <= 65535) {
                        i = i2;
                    }
                } catch (Exception e) {
                    MTPApi.LOGGER.info("getTunnelProxy", "port is invalid, e = " + e);
                }
                try {
                    return new InetSocketAddress(string, i);
                } catch (Exception e2) {
                    MTPApi.LOGGER.info("getTunnelProxy", "create address failed, e = " + e2);
                    return null;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }
}
