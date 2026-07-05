package com.duowan.ark.preference;

import com.duowan.ark.util.Config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StringPreference extends Preference<String> {
    public StringPreference(String str, String str2) {
        super(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public String getConfigValue(Config config, String str, String str2) {
        return config.getString(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public void updateConfig(Config config, String str, String str2) {
        config.setString(str, str2);
    }
}
