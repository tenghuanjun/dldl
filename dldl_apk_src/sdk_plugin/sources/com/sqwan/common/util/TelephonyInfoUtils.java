package com.sqwan.common.util;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import com.sq.tools.manager.SensitiveInfoManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TelephonyInfoUtils {
    public static String getDeviceId(Context context) {
        return SensitiveInfoManager.getInstance().getIMEI(context);
    }

    public static String getLine1Number(Context context) {
        return SensitiveInfoManager.getInstance().getPhoneNumber(context);
    }

    public static String getSimSerialNumber(Context context) {
        return ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0 ? ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber() : "";
    }

    public static String getSubscriberId(Context context) {
        return SensitiveInfoManager.getInstance().getIMSI(context);
    }

    public static int getSimState(Context context) {
        TelephonyManager telephonyManager;
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return -1;
        }
        return telephonyManager.getSimState();
    }

    public static boolean isSIMCardAvailable(Context context) {
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            try {
                return 5 == ((TelephonyManager) context.getSystemService("phone")).getSimState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getSettingAndroidId(Context context) {
        return getSettingAndroidId(context, true);
    }

    public static String getSettingAndroidId(Context context, boolean z) {
        if (!z) {
            return "";
        }
        try {
            return SensitiveInfoManager.getInstance().getAndroidId(context);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
