package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResGetUserStatus {
    int hasPassword;
    ResponseHeander header;
    String mobileMask;

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public String getMobileMask() {
        return this.mobileMask;
    }

    public void setMobileMask(String str) {
        this.mobileMask = str;
    }

    public int getHasPassword() {
        return this.hasPassword;
    }

    public void setHasPassword(int i) {
        this.hasPassword = i;
    }
}
