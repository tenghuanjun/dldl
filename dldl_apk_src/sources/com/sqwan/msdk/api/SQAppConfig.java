package com.sqwan.msdk.api;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SQAppConfig implements ISQAppConfig {
    private String gameid;
    private String partner;
    private String refer;

    public SQAppConfig() {
    }

    public SQAppConfig(String str, String str2, String str3) {
        this.gameid = str;
        this.partner = str2;
        this.refer = str3;
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
