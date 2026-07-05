package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResShareAppLoginData {
    LoginData loginData;
    long loginSaveTime;
    String sourceAppID;
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getSourceAppID() {
        return this.sourceAppID;
    }

    public void setSourceAppID(String str) {
        this.sourceAppID = str;
    }

    public long getLoginSaveTime() {
        return this.loginSaveTime;
    }

    public void setLoginSaveTime(long j) {
        this.loginSaveTime = j;
    }

    public LoginData getLoginData() {
        return this.loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }
}
