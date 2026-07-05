package com.sq.diagnostic.assistant.http;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface OnHttpListener<T> {
    void onFailed(int i, String str);

    void onSuccess(HttpData<T> httpData);
}
