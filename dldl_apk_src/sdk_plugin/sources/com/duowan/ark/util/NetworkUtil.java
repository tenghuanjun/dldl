package com.duowan.ark.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.sqwan.bugless.core.Constant;
import java.net.InetSocketAddress;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkUtil {
    private static final int DEFAULT_PROXY_PORT = 80;
    private static final int MAX_PORT = 65535;
    private static final int MIN_PORT = 0;
    public static final String NET_TYPE_2G = "2G";
    public static final String NET_TYPE_3G = "3G";
    public static final String NET_TYPE_4G = "4G";
    public static final String NET_TYPE_MOBILE = "mobile";
    public static final String NET_TYPE_NONE = "none";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";
    private static final Pattern PATTERN_IS_IP_ADDRESS = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    private static NetworkUtilDelegate sDelegate;

    public interface NetworkUtilDelegate {
        boolean is2GOr3GActive(Context context);

        boolean isWifiActive(Context context);
    }

    public static void setDelegate(NetworkUtilDelegate networkUtilDelegate) {
        sDelegate = networkUtilDelegate;
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
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.ark.util.NetworkUtil.getNetworkName(android.content.Context):java.lang.String");
    }

    public static String getWifiSSID(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getSSID() : "";
        } catch (Throwable th) {
            KLog.error("NetworkUtil", th);
            return "";
        }
    }

    public static boolean isWifiActive(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkUtilDelegate networkUtilDelegate = sDelegate;
        if (networkUtilDelegate != null) {
            return networkUtilDelegate.isWifiActive(context);
        }
        ConnectivityManager connectivityManager = null;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e) {
            KLog.error("NetworkUtil", e);
        }
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    public static boolean is2GOr3GActive(Context context) {
        NetworkUtilDelegate networkUtilDelegate = sDelegate;
        if (networkUtilDelegate != null) {
            return networkUtilDelegate.is2GOr3GActive(context);
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType() == 0;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
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
        try {
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
            KLog.info(Constant.DEV_NETWORK, string);
        } catch (Exception unused) {
        }
        return false;
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
            intent.setFlags(268435456);
            context.startActivity(intent);
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
            KLog.info("getTunnelProxy", Utils.getOperator(context) + ", proxy = " + string + ", port = " + string2);
            if (string != null && string.length() > 0) {
                cursorQuery.close();
                int i = 80;
                try {
                    int i2 = Integer.parseInt(string2);
                    if (i2 >= 0 && i2 <= 65535) {
                        i = i2;
                    }
                } catch (Exception e) {
                    KLog.info("getTunnelProxy", "port is invalid, e = " + e);
                }
                try {
                    return new InetSocketAddress(string, i);
                } catch (Exception e2) {
                    KLog.info("getTunnelProxy", "create address failed, e = " + e2);
                    return null;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    public static boolean isIpAddress(String str) {
        if (str == null) {
            return false;
        }
        return PATTERN_IS_IP_ADDRESS.matcher(str).matches();
    }
}
