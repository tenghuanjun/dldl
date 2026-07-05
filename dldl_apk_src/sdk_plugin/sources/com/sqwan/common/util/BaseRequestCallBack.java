package com.sqwan.common.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface BaseRequestCallBack {
    void onRequestError(int i, String str);

    @Deprecated
    void onRequestError(String str);

    void onRequestSuccess(String str);
}
