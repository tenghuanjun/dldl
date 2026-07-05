package com.social.sdk.platform;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Sina implements Platform {
    String appId = "";

    @Override // com.social.sdk.platform.Platform
    public PlatformType getName() {
        return PlatformType.SINA;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppId() {
        return this.appId;
    }
}
