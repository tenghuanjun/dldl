package com.sq.libwebsocket;

import com.sq.libwebsocket.request.Request;
import com.sq.libwebsocket.response.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface SocketWrapperListener {
    void onConnectFailed(Throwable th);

    void onConnected();

    void onDisconnect();

    void onMessage(Response response);

    void onSendDataError(Request request, int i, Throwable th);
}
