package com.huya.mtp.http;

import com.huya.mtp.data.exception.DataException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ResponseListener<T> {
    void onCancelled();

    @Deprecated
    void onError(DataException dataException);

    void onError(DataException dataException, boolean z);

    void onProducerEvent(int i);

    void onResponse(T t, boolean z);
}
