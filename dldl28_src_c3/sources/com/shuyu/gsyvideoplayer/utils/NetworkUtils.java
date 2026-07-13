package com.shuyu.gsyvideoplayer.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class NetworkUtils {
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_NO = -1;
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_IWLAN = 18;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_UNKNOWN = 5;
    public static final int NETWORK_WIFI = 1;

    private NetworkUtils() {
    }

    public static void openWirelessSettings(Context context) {
        context.startActivity(new Intent("android.settings.SETTINGS"));
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean isAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean is4G(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null && connectivityManager.getActiveNetworkInfo().getType() == 1;
    }

    public static String getNetworkOperatorName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public static int getPhoneType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getPhoneType();
        }
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0043 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getNetWorkType(android.content.Context r3) {
        /*
            android.net.NetworkInfo r3 = getActiveNetworkInfo(r3)
            if (r3 == 0) goto L48
            boolean r0 = r3.isAvailable()
            if (r0 == 0) goto L48
            int r0 = r3.getType()
            r1 = 1
            if (r0 != r1) goto L14
            goto L49
        L14:
            int r0 = r3.getType()
            r1 = 5
            if (r0 != 0) goto L49
            int r0 = r3.getSubtype()
            r2 = 3
            switch(r0) {
                case 1: goto L45;
                case 2: goto L45;
                case 3: goto L43;
                case 4: goto L45;
                case 5: goto L43;
                case 6: goto L43;
                case 7: goto L45;
                case 8: goto L43;
                case 9: goto L43;
                case 10: goto L43;
                case 11: goto L45;
                case 12: goto L43;
                case 13: goto L40;
                case 14: goto L43;
                case 15: goto L43;
                case 16: goto L45;
                case 17: goto L43;
                case 18: goto L40;
                default: goto L23;
            }
        L23:
            java.lang.String r3 = r3.getSubtypeName()
            java.lang.String r0 = "TD-SCDMA"
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 != 0) goto L43
            java.lang.String r0 = "WCDMA"
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 != 0) goto L43
            java.lang.String r0 = "CDMA2000"
            boolean r3 = r3.equalsIgnoreCase(r0)
            if (r3 == 0) goto L49
            goto L43
        L40:
            r3 = 4
            r1 = 4
            goto L49
        L43:
            r1 = 3
            goto L49
        L45:
            r3 = 2
            r1 = 2
            goto L49
        L48:
            r1 = -1
        L49:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shuyu.gsyvideoplayer.utils.NetworkUtils.getNetWorkType(android.content.Context):int");
    }

    public static String getNetWorkTypeName(Context context) {
        int netWorkType = getNetWorkType(context);
        if (netWorkType == -1) {
            return "NETWORK_NO";
        }
        if (netWorkType == 1) {
            return "NETWORK_WIFI";
        }
        if (netWorkType == 2) {
            return "NETWORK_2G";
        }
        if (netWorkType == 3) {
            return "NETWORK_3G";
        }
        if (netWorkType == 4) {
            return "NETWORK_4G";
        }
        return "NETWORK_UNKNOWN";
    }
}
