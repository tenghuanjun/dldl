package com.mobile.auth.gatewayauth.manager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public interface RequestCallback<T, K> {
    void onError(K k);

    void onSuccess(T t);
}
