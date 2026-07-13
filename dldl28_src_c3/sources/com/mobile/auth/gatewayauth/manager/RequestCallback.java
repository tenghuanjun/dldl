package com.mobile.auth.gatewayauth.manager;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface RequestCallback<T, K> {
    void onError(K k);

    void onSuccess(T t);
}
