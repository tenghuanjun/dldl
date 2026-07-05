package com.aliyun.aliyunface.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class MobileUtil {
    public static final String NETWORK_2G = "2G";
    public static final String NETWORK_3G = "3G";
    public static final String NETWORK_4G = "4G";
    public static final String NETWORK_MOBILE = "Mobile";
    public static final String NETWORK_NONE = "None";
    public static final String NETWORK_WIFI = "WIFI";

    public static String getNetworkType(Context context) {
        return NETWORK_WIFI;
    }

    public static String getOperatorName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        try {
            return telephonyManager.getSimOperatorName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getMobileLan() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.getDisplayLanguage() : "";
    }

    public static String getDisplayMetrix(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }
}
