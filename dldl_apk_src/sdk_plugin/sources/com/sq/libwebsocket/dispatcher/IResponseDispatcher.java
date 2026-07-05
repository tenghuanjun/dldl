package com.sq.libwebsocket.dispatcher;

import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.sywebsocket.framing.Framedata;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IResponseDispatcher {
    void onConnectFailed(Throwable th, ResponseDelivery responseDelivery);

    void onConnected(ResponseDelivery responseDelivery);

    void onDisconnect(ResponseDelivery responseDelivery);

    void onMessage(String str, ResponseDelivery responseDelivery);

    void onMessage(ByteBuffer byteBuffer, ResponseDelivery responseDelivery);

    void onPing(Framedata framedata, ResponseDelivery responseDelivery);

    void onPong(Framedata framedata, ResponseDelivery responseDelivery);

    void onSendDataError(ErrorResponse errorResponse, ResponseDelivery responseDelivery);
}
