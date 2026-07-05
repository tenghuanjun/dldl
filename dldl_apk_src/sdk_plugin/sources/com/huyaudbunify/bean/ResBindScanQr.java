package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResBindScanQr {
    ResponseHeander header;
    LoginData loginData;
    int qrStage;

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public LoginData getLoginData() {
        return this.loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public int getQrStage() {
        return this.qrStage;
    }

    public void setQrStage(int i) {
        this.qrStage = i;
    }
}
