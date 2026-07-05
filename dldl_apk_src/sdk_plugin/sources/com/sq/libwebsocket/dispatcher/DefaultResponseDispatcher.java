package com.sq.libwebsocket.dispatcher;

import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.sywebsocket.framing.Framedata;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultResponseDispatcher implements IResponseDispatcher {
    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onConnected(ResponseDelivery responseDelivery) {
        responseDelivery.onConnected();
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onConnectFailed(Throwable th, ResponseDelivery responseDelivery) {
        responseDelivery.onConnectFailed(th);
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onDisconnect(ResponseDelivery responseDelivery) {
        responseDelivery.onDisconnect();
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onMessage(String str, ResponseDelivery responseDelivery) {
        responseDelivery.onMessage(str, (Object) null);
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onMessage(ByteBuffer byteBuffer, ResponseDelivery responseDelivery) {
        responseDelivery.onMessage(byteBuffer, (Object) null);
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onPing(Framedata framedata, ResponseDelivery responseDelivery) {
        responseDelivery.onPing(framedata);
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onPong(Framedata framedata, ResponseDelivery responseDelivery) {
        responseDelivery.onPong(framedata);
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onSendDataError(ErrorResponse errorResponse, ResponseDelivery responseDelivery) {
        responseDelivery.onSendDataError(errorResponse);
    }
}
