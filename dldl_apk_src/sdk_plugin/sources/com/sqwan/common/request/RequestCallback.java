package com.sqwan.common.request;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface RequestCallback {
    void onFailure(int i, String str);

    void onSuccess(BaseResponseBean baseResponseBean);
}
