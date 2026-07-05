package com.sq.libwebsocket.response;

import com.sq.libwebsocket.dispatcher.IResponseDispatcher;
import com.sq.libwebsocket.dispatcher.ResponseDelivery;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Response<T> {
    T getResponseData();

    void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery);

    void release();

    void setResponseData(T t);
}
