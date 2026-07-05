package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqBindVerifySms {
    String mobile;
    String otp;
    String sha1Psw;
    String smscode;
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

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getSmscode() {
        return this.smscode;
    }

    public void setSmscode(String str) {
        this.smscode = str;
    }

    public String getSha1Psw() {
        return this.sha1Psw;
    }

    public void setSha1Psw(String str) {
        this.sha1Psw = str;
    }
}
