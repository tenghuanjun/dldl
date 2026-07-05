package com.sqwan.msdk.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQAppConfigImpl extends SQAppConfig implements ISQAppConfig {
    private final String gameid;
    private final String partner;
    private final String refer;

    public SQAppConfigImpl(SQAppConfig sQAppConfig) {
        this.gameid = sQAppConfig.getGameid();
        this.partner = sQAppConfig.getPartner();
        this.refer = sQAppConfig.getRefer();
    }

    @Override // com.sqwan.msdk.api.ISQAppConfig
    public String getGameid() {
        return this.gameid;
    }

    @Override // com.sqwan.msdk.api.ISQAppConfig
    public String getPartner() {
        return this.partner;
    }

    @Override // com.sqwan.msdk.api.ISQAppConfig
    public String getRefer() {
        return this.refer;
    }
}
