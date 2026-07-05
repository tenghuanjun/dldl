package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Request<T> {
    T getRequestData();

    void release();

    void send(WebSocketClient webSocketClient);

    void setRequestData(T t);
}
