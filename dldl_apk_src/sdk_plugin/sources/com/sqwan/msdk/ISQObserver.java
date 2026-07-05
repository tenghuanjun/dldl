package com.sqwan.msdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface ISQObserver<S, F> {
    void onFail(F f);

    void onSuccess(S s);
}
