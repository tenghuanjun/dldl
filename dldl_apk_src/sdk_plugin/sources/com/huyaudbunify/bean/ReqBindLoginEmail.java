package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqBindLoginEmail {
    String email;
    String emailcode;
    String sessionData;
    String sha1Psw;
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getEmailcode() {
        return this.emailcode;
    }

    public void setEmailcode(String str) {
        this.emailcode = str;
    }

    public String getSessionData() {
        return this.sessionData;
    }

    public void setSessionData(String str) {
        this.sessionData = str;
    }

    public String getSha1Psw() {
        return this.sha1Psw;
    }

    public void setSha1Psw(String str) {
        this.sha1Psw = str;
    }
}
