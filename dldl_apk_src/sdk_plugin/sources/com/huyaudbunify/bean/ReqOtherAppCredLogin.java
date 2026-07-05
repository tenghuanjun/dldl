package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqOtherAppCredLogin {
    String otp;
    String srcAppId;
    String targetAppId;
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String str) {
        this.otp = str;
    }

    public String getSrcAppId() {
        return this.srcAppId;
    }

    public void setSrcAppId(String str) {
        this.srcAppId = str;
    }

    public String getTargetAppId() {
        return this.targetAppId;
    }

    public void setTargetAppId(String str) {
        this.targetAppId = str;
    }
}
