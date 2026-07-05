package com.sqwan.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.base.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NetWorkUtils {
    public static final String NETWORK_TYPE_3G = "1";
    public static final String NETWORK_TYPE_4G = "2";
    public static final String NETWORK_TYPE_5G = "3";
    public static final String NETWORK_TYPE_OTHER = "5";
    public static final String NETWORK_TYPE_UNKNOWN = "0";
    public static final String NETWORK_TYPE_WIFI = "4";

    private NetWorkUtils() {
    }

    public static String getNetworkType(Context context) {
        if (isEthernet(context)) {
            return "5";
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                return "4";
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return "5";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return "1";
                    case 13:
                    case 18:
                        return "2";
                    case 19:
                    default:
                        String subtypeName = activeNetworkInfo.getSubtypeName();
                        return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? "1" : "0";
                    case 20:
                        return "3";
                }
            }
        }
        return "0";
    }

    private static boolean isEthernet(Context context) {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            return connectivityManager.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getIpAddress(Context context) {
        return SensitiveInfoManager.getInstance().getIpAddress(context);
    }

    public static boolean isWifiProxy() {
        try {
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = "-1";
            }
            return (TextUtils.isEmpty(property) || Integer.parseInt(property2) == -1) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null && allNetworkInfo.length > 0) {
            for (int i = 0; i < allNetworkInfo.length; i++) {
                System.out.println(i + "===状态===" + allNetworkInfo[i].getState());
                System.out.println(i + "===类型===" + allNetworkInfo[i].getTypeName());
                if (allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWifiIsEnable() {
        WifiManager wifiManager = (WifiManager) L.getApplicationContext().getSystemService("wifi");
        return wifiManager != null && wifiManager.isWifiEnabled();
    }
}
