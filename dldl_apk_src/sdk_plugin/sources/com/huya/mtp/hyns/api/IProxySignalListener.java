package com.huya.mtp.hyns.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IProxySignalListener {
    void onSocketConnected();

    void onSocketDisconnected();

    void onSocketError(int i, Throwable th);
}
