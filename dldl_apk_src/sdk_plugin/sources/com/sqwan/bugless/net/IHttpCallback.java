package com.sqwan.bugless.net;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IHttpCallback {
    void onFail(int code, String msg);

    void onSuccess(String response);
}
