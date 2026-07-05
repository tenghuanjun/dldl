package com.duowan.kiwi.barrage;

import android.content.Context;
import android.content.SharedPreferences;
import com.duowan.ark.util.Config;
import com.tencent.mmkv.MMKV;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MmkvConfigImpl implements Config.IConfig {
    private static String KEY_MMKV_IMPORT = "mmkv_import";

    @Override // com.duowan.ark.util.Config.IConfig
    public SharedPreferences getSpImpl(Context context, String str) {
        MMKV.initialize(context);
        if (str == null) {
            return MMKV.defaultMMKV();
        }
        MMKV mmkvMmkvWithID = MMKV.mmkvWithID(str);
        if (mmkvMmkvWithID.getInt(KEY_MMKV_IMPORT, -1) < 0) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            mmkvMmkvWithID.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().apply();
            mmkvMmkvWithID.putInt(KEY_MMKV_IMPORT, 1);
        }
        return mmkvMmkvWithID;
    }
}
