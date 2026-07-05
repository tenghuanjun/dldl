package com.snail.antifake.deviceid.macaddress;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IWifiManagerUtil {
    private static String INVALID_ADDRESS = "02:00:00:00:00:00";

    public static String getMacAddress(Context context) {
        String macAddressLevel1 = getMacAddressLevel1(context);
        if (!TextUtils.isEmpty(macAddressLevel1) && !INVALID_ADDRESS.endsWith(macAddressLevel1)) {
            return macAddressLevel1;
        }
        String macAddressLevel0 = getMacAddressLevel0(context);
        if (TextUtils.isEmpty(macAddressLevel0) || !INVALID_ADDRESS.endsWith(macAddressLevel0)) {
        }
        return macAddressLevel0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String getMacAddressLevel0(Context context) {
        WifiManager wifiManager;
        String macAddress;
        WifiInfo connectionInfo = null;
        try {
            wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo connectionInfo2 = wifiManager == null ? null : wifiManager.getConnectionInfo();
            macAddress = connectionInfo2 != null ? connectionInfo2.getMacAddress() : null;
        } catch (Exception unused) {
        }
        try {
            if (!TextUtils.isEmpty(macAddress)) {
                return macAddress;
            }
            if (wifiManager != null && !wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
                wifiManager.setWifiEnabled(false);
            }
            if (wifiManager != null) {
                connectionInfo = wifiManager.getConnectionInfo();
            }
            return connectionInfo != null ? connectionInfo.getMacAddress() : macAddress;
        } catch (Exception unused2) {
            connectionInfo = macAddress;
            return connectionInfo;
        }
    }

    private static String getMacAddressLevel1(Context context) {
        String str = null;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            Field declaredField = wifiManager.getClass().getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(wifiManager);
            Method declaredMethod = obj.getClass().getDeclaredMethod("getConnectionInfo", new Class[0]);
            declaredMethod.setAccessible(true);
            WifiInfo wifiInfo = (WifiInfo) declaredMethod.invoke(obj, new Object[0]);
            if (wifiInfo == null && !wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
                wifiManager.setWifiEnabled(false);
                wifiInfo = (WifiInfo) declaredMethod.invoke(obj, new Object[0]);
            }
            try {
                Field declaredField2 = wifiInfo.getClass().getDeclaredField("mMacAddress");
                declaredField2.setAccessible(true);
                String str2 = (String) declaredField2.get(wifiInfo);
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        return str2;
                    }
                } catch (Exception unused) {
                }
                str = str2;
            } catch (Exception unused2) {
            }
            return wifiInfo != null ? wifiInfo.getMacAddress() : str;
        } catch (Exception unused3) {
            return null;
        }
    }
}
