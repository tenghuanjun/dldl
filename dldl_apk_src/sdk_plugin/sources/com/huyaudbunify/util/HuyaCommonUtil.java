package com.huyaudbunify.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.duowan.live.login.LoginReportConstants;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaCommonUtil {

    public interface GetCarrierTypeCallback {
        void callback(int i);
    }

    public static String getThirdType(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "WB" : LoginReportConstants.REPORT_QQ : "WX" : "TW" : "FB" : "GG";
    }

    private static class CarrierType {
        static int NET_CLOSE = 1;
        static int NET_LAN = 3;
        static int NET_MOBILE_2G = 8;
        static int NET_MOBILE_3G = 9;
        static int NET_MOBILE_4G = 10;
        static int NET_TELECOM_2G = 11;
        static int NET_TELECOM_3G = 12;
        static int NET_TELECOM_4G = 13;
        static int NET_UNION_2G = 5;
        static int NET_UNION_3G = 6;
        static int NET_UNION_4G = 7;
        static int NET_UNKNOW = 2;
        static int NET_WIFI = 4;

        private CarrierType() {
        }
    }

    public static int getType(String str) {
        if (str != null && str.length() > 0) {
            if (TextUtils.isDigitsOnly(str) && str.length() == 11 && str.startsWith("1")) {
                return 3;
            }
            if (TextUtils.isDigitsOnly(str) && str.startsWith("00")) {
                return 3;
            }
            if (TextUtils.isDigitsOnly(str)) {
                return 2;
            }
            if (str.contains("@")) {
                return 1;
            }
            if (str.length() >= 1) {
                return 0;
            }
        }
        return 9;
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
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
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int getCarrierType(Context context) {
        if (context == null) {
            return CarrierType.NET_UNKNOW;
        }
        try {
            if (!isNetworkAvailable(context)) {
                return CarrierType.NET_CLOSE;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null && wifiManager.getWifiState() == 3) {
                return CarrierType.NET_WIFI;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo().getType() == 9) {
                return CarrierType.NET_LAN;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String simOperator = telephonyManager.getSimOperator();
            if (telephonyManager.getSimState() == 5) {
                if (!simOperator.equals("46000") && !simOperator.equals("46002") && !simOperator.equals("46007") && !simOperator.equals("46020")) {
                    if (!simOperator.equals("46001") && !simOperator.equals("46006")) {
                        if (simOperator.equals("46003") || simOperator.equals("46005")) {
                            int netSpeedType = getNetSpeedType(connectivityManager);
                            if (netSpeedType == 1) {
                                return CarrierType.NET_TELECOM_2G;
                            }
                            if (netSpeedType == 2) {
                                return CarrierType.NET_TELECOM_3G;
                            }
                            if (netSpeedType == 3) {
                                return CarrierType.NET_TELECOM_4G;
                            }
                            return CarrierType.NET_UNKNOW;
                        }
                    }
                    int netSpeedType2 = getNetSpeedType(connectivityManager);
                    if (netSpeedType2 == 1) {
                        return CarrierType.NET_UNION_2G;
                    }
                    if (netSpeedType2 == 2) {
                        return CarrierType.NET_UNION_3G;
                    }
                    if (netSpeedType2 == 3) {
                        return CarrierType.NET_UNION_4G;
                    }
                    return CarrierType.NET_UNKNOW;
                }
                int netSpeedType3 = getNetSpeedType(connectivityManager);
                if (netSpeedType3 == 1) {
                    return CarrierType.NET_MOBILE_2G;
                }
                if (netSpeedType3 == 2) {
                    return CarrierType.NET_MOBILE_3G;
                }
                if (netSpeedType3 == 3) {
                    return CarrierType.NET_MOBILE_4G;
                }
                return CarrierType.NET_UNKNOW;
            }
            return CarrierType.NET_UNKNOW;
        } catch (Exception unused) {
            return CarrierType.NET_UNKNOW;
        }
    }

    private static int getNetSpeedType(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo networkInfo;
        try {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            networkInfo = connectivityManager.getNetworkInfo(0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        if (networkInfo == null) {
            return 0;
        }
        NetworkInfo.State state = networkInfo.getState();
        String subtypeName = networkInfo.getSubtypeName();
        if (state == null) {
            return 0;
        }
        if (state != NetworkInfo.State.CONNECTED && state != NetworkInfo.State.CONNECTING) {
            return 0;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 1;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 2;
            case 13:
                return 3;
            default:
                if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA")) {
                    if (!subtypeName.equalsIgnoreCase("CDMA2000")) {
                        return 0;
                    }
                }
                return 2;
        }
        e.printStackTrace();
        return 0;
    }
}
