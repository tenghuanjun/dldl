package com.sq.sdk.tool.observer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Observer<S, F, C> {
    void onComplete(C c);

    void onFail(F f);

    void onSuccess(S s);
}
