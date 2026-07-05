package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResNotifyScanCode {
    ResponseHeander header;
    String loginAppId;
    String loginAppName;
    String loginDeviceName;
    String loginPlace;
    int qrStage;

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public String getLoginAppId() {
        return this.loginAppId;
    }

    public void setLoginAppId(String str) {
        this.loginAppId = str;
    }

    public String getLoginAppName() {
        return this.loginAppName;
    }

    public void setLoginAppName(String str) {
        this.loginAppName = str;
    }

    public String getLoginDeviceName() {
        return this.loginDeviceName;
    }

    public void setLoginDeviceName(String str) {
        this.loginDeviceName = str;
    }

    public String getLoginPlace() {
        return this.loginPlace;
    }

    public void setLoginPlace(String str) {
        this.loginPlace = str;
    }

    public int getQrStage() {
        return this.qrStage;
    }

    public void setQrStage(int i) {
        this.qrStage = i;
    }
}
