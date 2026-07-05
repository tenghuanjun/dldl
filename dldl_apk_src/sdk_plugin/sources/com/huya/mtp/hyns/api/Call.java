package com.huya.mtp.hyns.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Call {
    void cancel();

    void enqueue(Callback callback);

    Request request();
}
