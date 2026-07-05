package com.sq.libwebsocket.dispatcher;

import com.sq.libwebsocket.SocketListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ResponseDelivery extends SocketListener {
    void addListener(SocketListener socketListener);

    void clear();

    void destroy();

    boolean isEmpty();

    void removeListener(SocketListener socketListener);
}
