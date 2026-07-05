package com.huya.mtp.hycloudgame.base.listener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ISocketStateListener {
    public static final int ERROR_CODE_INTERNAl = 2;
    public static final int ERROR_CODE_NETWORK = 3;
    public static final int ERROR_CODE_PARAMETER = 1;

    void onSocketConnected();

    void onSocketDisconnected();

    void onSocketError(int i, Throwable th);

    void onSocketInitCompleted();
}
