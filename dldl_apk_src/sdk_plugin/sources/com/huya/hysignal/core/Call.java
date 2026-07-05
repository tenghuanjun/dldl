package com.huya.hysignal.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Call {
    void cancel();

    void enqueue(Callback callback);

    Response execute() throws Exception;

    com.huya.mtp.hyns.api.Request request();
}
