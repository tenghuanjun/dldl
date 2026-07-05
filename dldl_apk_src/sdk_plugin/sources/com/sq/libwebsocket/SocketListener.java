package com.sq.libwebsocket;

import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.sywebsocket.framing.Framedata;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface SocketListener {
    void onConnectFailed(Throwable th);

    void onConnected();

    void onDisconnect();

    <T> void onMessage(String str, T t);

    <T> void onMessage(ByteBuffer byteBuffer, T t);

    void onPing(Framedata framedata);

    void onPong(Framedata framedata);

    void onSendDataError(ErrorResponse errorResponse);
}
