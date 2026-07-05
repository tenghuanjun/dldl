package com.huya.mtp.deviceid.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.utils.Config;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceUtils {
    private static final String TAG = "DeviceUtils";
    private static String androidId;

    public static String getImei(Context context) {
        Config config = Config.getInstance(context);
        String string = config.getString("RANDOM_UUID", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        try {
            String deviceId = getDeviceId(context);
            if (TextUtils.isEmpty(deviceId)) {
                string = getSimSerialNumber(context);
                if (TextUtils.isEmpty(string)) {
                    String string2 = new UUID((Build.VERSION.SDK_INT > 3 ? getAndroidId(context) : "").hashCode(), getMacAddress(context).hashCode()).toString();
                    config.setString("RANDOM_UUID", string2);
                    return string2;
                }
                config.setString("RANDOM_UUID", string);
                return string;
            }
            config.setString("RANDOM_UUID", deviceId);
            return deviceId;
        } catch (Throwable unused) {
            config.setString("RANDOM_UUID", string);
            return "";
        }
    }

    public static String getAndroidId(Context context) {
        if (androidId != null) {
            return androidId;
        }
        androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return androidId;
    }

    public static String getSimSerialNumber(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Throwable th) {
            MTPApi.LOGGER.error(DeviceUtils.class, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Throwable th) {
            MTPApi.LOGGER.error(DeviceUtils.class, "getDeviceId fail: %s", th);
            return "";
        }
    }

    public static String getMacAddress(Context context) {
        WifiInfo connectionInfo;
        try {
            connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Throwable th) {
            MTPApi.LOGGER.error(DeviceUtils.class, "getMacAddress failed " + th.toString());
            connectionInfo = null;
        }
        return connectionInfo == null ? "" : connectionInfo.getMacAddress();
    }
}
