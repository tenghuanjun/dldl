package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResCheckUser {
    String emailMask;
    ResponseHeander header;
    int isLoginMobile;
    String mobileMask;
    String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getMobileMask() {
        return this.mobileMask;
    }

    public void setMobileMask(String str) {
        this.mobileMask = str;
    }

    public String getEmailMask() {
        return this.emailMask;
    }

    public void setEmailMask(String str) {
        this.emailMask = str;
    }

    public int getIsLoginMobile() {
        return this.isLoginMobile;
    }

    public void setIsLoginMobile(int i) {
        this.isLoginMobile = i;
    }

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }
}
