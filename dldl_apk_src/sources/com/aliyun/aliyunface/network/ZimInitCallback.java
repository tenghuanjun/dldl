package com.aliyun.aliyunface.network;

import com.aliyun.aliyunface.config.OSSConfig;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ZimInitCallback {
    void onError(String str, String str2);

    void onServerError(String str, String str2);

    void onSuccess(String str, OSSConfig oSSConfig);
}
