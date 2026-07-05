package com.sq.libwebsocket;

import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.sywebsocket.framing.Framedata;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class SimpleListener implements SocketListener {
    private final String TAG = "SimpleListener";

    @Override // com.sq.libwebsocket.SocketListener
    public void onConnectFailed(Throwable th) {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public void onConnected() {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public void onDisconnect() {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public <T> void onMessage(String str, T t) {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public <T> void onMessage(ByteBuffer byteBuffer, T t) {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public void onPing(Framedata framedata) {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public void onPong(Framedata framedata) {
    }

    @Override // com.sq.libwebsocket.SocketListener
    public void onSendDataError(ErrorResponse errorResponse) {
    }
}
