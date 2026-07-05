package com.social.sdk.platform;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class QQ implements Platform {
    private String appId = "";

    @Override // com.social.sdk.platform.Platform
    public PlatformType getName() {
        return PlatformType.QQ;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppId() {
        return this.appId;
    }
}
