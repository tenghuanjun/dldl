package com.aliyun.aliyunface.network;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ZimValidateCallback {
    void onError(String str, String str2);

    void onServerError(String str, String str2);

    void onSuccess();

    void onValidateFail(String str, String str2, String str3);
}
