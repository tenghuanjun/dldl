package com.tencent.tauth;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class UiError {
    public int errorCode;
    public String errorDetail;
    public String errorMessage;

    public UiError(int i, String str, String str2) {
        this.errorMessage = str;
        this.errorCode = i;
        this.errorDetail = str2;
    }

    public String toString() {
        return "errorCode: " + this.errorCode + ", errorMsg: " + this.errorMessage + ", errorDetail: " + this.errorDetail;
    }
}
