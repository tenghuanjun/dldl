package com.duowan.ark.preference;

import com.duowan.ark.util.Config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LongPreference extends Preference<Long> {
    public LongPreference(Long l, String str) {
        super(l, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public Long getConfigValue(Config config, String str, Long l) {
        return Long.valueOf(config.getLong(str, l.longValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.preference.Preference
    public void updateConfig(Config config, String str, Long l) {
        config.setLong(str, l.longValue());
    }
}
