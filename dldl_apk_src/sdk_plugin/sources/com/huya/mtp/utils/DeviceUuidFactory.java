package com.huya.mtp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceUuidFactory {
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static final String PREFS_FILE = "device_id.xml";
    protected static UUID uuid;

    public DeviceUuidFactory(Context context) {
        UUID uuidRandomUUID;
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, 0);
                    String deviceId = null;
                    String string = sharedPreferences.getString("device_id", null);
                    if (string != null) {
                        uuid = UUID.fromString(string);
                    } else {
                        String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                        try {
                            if (!"9774d56d682e549c".equals(string2)) {
                                uuid = UUID.nameUUIDFromBytes(string2.getBytes("utf8"));
                            } else {
                                try {
                                    deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (deviceId != null) {
                                    uuidRandomUUID = UUID.nameUUIDFromBytes(deviceId.getBytes("utf8"));
                                } else {
                                    uuidRandomUUID = UUID.randomUUID();
                                }
                                uuid = uuidRandomUUID;
                            }
                            sharedPreferences.edit().putString("device_id", uuid.toString()).commit();
                        } catch (UnsupportedEncodingException e2) {
                            throw new RuntimeException(e2);
                        }
                    }
                }
            }
        }
    }

    public UUID getDeviceUuid() {
        return uuid;
    }
}
