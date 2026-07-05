package cn.thinkingdata.android.utils;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TASensitiveInfo {
    public String getAndroidID(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
