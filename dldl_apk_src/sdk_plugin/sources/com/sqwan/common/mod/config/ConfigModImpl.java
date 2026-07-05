package com.sqwan.common.mod.config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ConfigModImpl implements IConfigMod {
    private CommonConifg conifg = new CommonConifg();

    @Override // com.sqwan.common.mod.config.IConfigMod
    public CommonConifg getCommonConfig() {
        return this.conifg;
    }
}
