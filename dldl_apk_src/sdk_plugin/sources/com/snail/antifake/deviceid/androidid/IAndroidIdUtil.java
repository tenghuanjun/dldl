package com.snail.antifake.deviceid.androidid;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IAndroidIdUtil {
    public static String getAndroidId(Context context) {
        String androidPropertyLevel1 = ISettingUtils.getAndroidPropertyLevel1(context, "android_id");
        if (TextUtils.isEmpty(androidPropertyLevel1)) {
            androidPropertyLevel1 = ISettingUtils.getAndroidProperty(context, "android_id");
            if (TextUtils.isEmpty(androidPropertyLevel1)) {
                return Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
        }
        return androidPropertyLevel1;
    }
}
