package com.huya.mtp.hyns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface NSCallback<T> {
    void onCancelled();

    void onError(NSException nSException);

    void onResponse(NSResponse<T> nSResponse);
}
