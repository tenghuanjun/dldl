package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqBindAuth {
    String accesstoken;
    String authCode;
    String openid;
    int opentype;
    int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String str) {
        this.authCode = str;
    }

    public String getAccesstoken() {
        return this.accesstoken;
    }

    public void setAccesstoken(String str) {
        this.accesstoken = str;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String str) {
        this.openid = str;
    }

    public int getOpentype() {
        return this.opentype;
    }

    public void setOpentype(int i) {
        this.opentype = i;
    }
}
