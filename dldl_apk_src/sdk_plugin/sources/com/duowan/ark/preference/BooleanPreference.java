package com.duowan.ark.preference;

import com.duowan.ark.util.Config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BooleanPreference extends Preference<Boolean> {
    public BooleanPreference(Boolean bool, String str) {
        super(bool, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public Boolean getConfigValue(Config config, String str, Boolean bool) {
        return Boolean.valueOf(config.getBoolean(str, bool.booleanValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public void updateConfig(Config config, String str, Boolean bool) {
        config.setBoolean(str, bool.booleanValue());
    }
}
