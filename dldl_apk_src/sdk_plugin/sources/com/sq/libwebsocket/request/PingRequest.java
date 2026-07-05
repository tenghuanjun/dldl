package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PingRequest implements Request<Object> {
    @Override // com.sq.libwebsocket.request.Request
    public Object getRequestData() {
        return null;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(Object obj) {
    }

    PingRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.sendPing();
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releasePingRequest(this);
    }
}
