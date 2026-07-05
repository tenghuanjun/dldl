package com.snail.antifake.deviceid.deviceid;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceIdUtil {
    public static String getDeviceId(Context context) {
        String deviceIdLevel2 = ITelephonyUtil.getDeviceIdLevel2(context);
        if (TextUtils.isEmpty(deviceIdLevel2)) {
            deviceIdLevel2 = IPhoneSubInfoUtil.getDeviceIdLevel2(context);
            if (TextUtils.isEmpty(deviceIdLevel2)) {
                deviceIdLevel2 = ITelephonyUtil.getDeviceIdLevel1(context);
                if (TextUtils.isEmpty(deviceIdLevel2)) {
                    deviceIdLevel2 = IPhoneSubInfoUtil.getDeviceIdLevel1(context);
                    if (TextUtils.isEmpty(deviceIdLevel2)) {
                        deviceIdLevel2 = IPhoneSubInfoUtil.getDeviceIdLevel0(context);
                        if (TextUtils.isEmpty(deviceIdLevel2)) {
                            deviceIdLevel2 = ITelephonyUtil.getDeviceIdLevel0(context);
                            if (TextUtils.isEmpty(deviceIdLevel2)) {
                                return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                            }
                        }
                    }
                }
            }
        }
        return deviceIdLevel2;
    }

    public static boolean isEmulatorFromDeviceId(Context context) {
        return isAllZero(getDeviceId(context));
    }

    private static boolean isAllZero(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
