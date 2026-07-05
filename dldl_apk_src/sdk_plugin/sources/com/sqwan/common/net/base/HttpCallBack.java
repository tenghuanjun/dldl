package com.sqwan.common.net.base;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface HttpCallBack<T> {
    void onRequestError(int i, String str);

    void onRequestSuccess(T t);
}
