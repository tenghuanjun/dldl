package com.huya.component.login.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TokenInfo {
    String token;
    int tokenType;
    long uid;

    public TokenInfo() {
    }

    public TokenInfo(int i, long j, String str) {
        this.tokenType = i;
        this.uid = j;
        this.token = str;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public int getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(int i) {
        this.tokenType = i;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
