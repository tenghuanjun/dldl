package com.sq.tools.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sq.tools.manager.SensitiveInfoManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class DeviceUtils {
    public static String getAAID(Context context) {
        return "";
    }

    public static String getDevId(Context context) {
        return "";
    }

    public static String getOAID(Context context) {
        return "";
    }

    public static String getUUID(Context context) {
        return "";
    }

    public static String getVAID(Context context) {
        return "";
    }

    public static String getIMEI(Context context) {
        return SensitiveInfoManager.getInstance().getIMEI(context);
    }

    public static String getIMSI(Context context) {
        return SensitiveInfoManager.getInstance().getIMSI(context);
    }

    public static String getSimSerialNumber(Context context) {
        TelephonyManager telephonyManager;
        try {
            if (!PermissionUtils.hasAndroidPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return "";
            }
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            return TextUtils.isEmpty(simSerialNumber) ? "" : simSerialNumber;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getAndroidId(Context context) {
        return SensitiveInfoManager.getInstance().getAndroidId(context);
    }

    public static String getMacAddress(Context context) {
        return SensitiveInfoManager.getInstance().getMacAddress(context);
    }

    public static String getLine1Number(Context context) {
        return SensitiveInfoManager.getInstance().getPhoneNumber(context);
    }

    private DeviceUtils() {
    }
}
