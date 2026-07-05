package com.social.sdk.platform;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Wechat implements Platform {
    public static final String SCOPE = "snsapi_userinfo";
    public static final String STATE = "login_state";
    private String appId;
    private String appKey;
    private PlatformType platform;

    public Wechat() {
        this(PlatformType.WECHAT);
    }

    public Wechat(PlatformType platformType) {
        this.appId = "";
        this.appKey = "";
        this.platform = platformType;
    }

    @Override // com.social.sdk.platform.Platform
    public PlatformType getName() {
        return this.platform;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }
}
