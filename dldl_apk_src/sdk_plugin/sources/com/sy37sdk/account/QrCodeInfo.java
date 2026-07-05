package com.sy37sdk.account;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class QrCodeInfo {
    String qrCodeContent;
    String qrCodeImgUrl;

    public QrCodeInfo(String str, String str2) {
        this.qrCodeImgUrl = str;
        this.qrCodeContent = str2;
    }

    public String getQrCodeImgUrl() {
        return this.qrCodeImgUrl;
    }

    public void setQrCodeImgUrl(String str) {
        this.qrCodeImgUrl = str;
    }

    public String getQrCodeContent() {
        return this.qrCodeContent;
    }

    public void setQrCodeContent(String str) {
        this.qrCodeContent = str;
    }

    public String toString() {
        return "[imageUrl]: " + this.qrCodeImgUrl + ", [content]: " + this.qrCodeContent;
    }
}
