package com.mobile.auth.gatewayauth.manager;

/* JADX INFO: loaded from: classes3.dex */
public interface RequestCallback<T, K> {
    void onError(K k);

    void onSuccess(T t);
}
