package com.aliyun.aliyunface.network;

import com.aliyun.aliyunface.network.model.OCRInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ZimOcrIdentifyCallback {
    void onError(String str, String str2);

    void onServerError(String str, String str2);

    void onSuccess(OCRInfo oCRInfo);
}
