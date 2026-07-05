package com.huya.mtp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.huya.mtp.utils.Config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultConfigImpl implements Config.IConfig {
    @Override // com.huya.mtp.utils.Config.IConfig
    public SharedPreferences getSpImpl(Context context, String str, boolean z) {
        return context.getSharedPreferences(str, 0);
    }
}
