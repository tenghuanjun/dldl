package com.duowan.ark.preference;

import com.duowan.ark.util.Config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class IntegerPreference extends Preference<Integer> {
    public IntegerPreference(Integer num, String str) {
        super(num, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public Integer getConfigValue(Config config, String str, Integer num) {
        return Integer.valueOf(config.getInt(str, num.intValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public void updateConfig(Config config, String str, Integer num) {
        config.setInt(str, num.intValue());
    }
}
