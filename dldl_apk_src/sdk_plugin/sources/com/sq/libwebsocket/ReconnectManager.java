package com.sq.libwebsocket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ReconnectManager {

    public interface OnConnectListener {
        void onConnected();

        void onDisconnect();
    }

    void destroy();

    void onConnectError(Throwable th);

    void onConnected();

    boolean reconnecting();

    void startReconnect();

    void stopReconnect();
}
