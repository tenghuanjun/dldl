package com.huya.mtp.hyns.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSConnectConfig {
    private final String bizId;
    private boolean isTestEnv;
    private final String targetUrl;
    private final UserId userId;

    public NSConnectConfig(String str, UserId userId, String str2, boolean z) {
        this.isTestEnv = z;
        this.targetUrl = str;
        this.userId = userId;
        this.bizId = str2;
    }

    public boolean isTestEnv() {
        return this.isTestEnv;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public UserId getUserId() {
        return this.userId;
    }

    public String getBizId() {
        return this.bizId;
    }
}
