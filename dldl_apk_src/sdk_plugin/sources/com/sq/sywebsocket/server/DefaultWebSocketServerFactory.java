package com.sq.sywebsocket.server;

import com.sq.sywebsocket.WebSocket;
import com.sq.sywebsocket.WebSocketAdapter;
import com.sq.sywebsocket.WebSocketImpl;
import com.sq.sywebsocket.WebSocketServerFactory;
import com.sq.sywebsocket.drafts.Draft;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultWebSocketServerFactory implements WebSocketServerFactory {
    @Override // com.sq.sywebsocket.WebSocketServerFactory
    public void close() {
    }

    @Override // com.sq.sywebsocket.WebSocketServerFactory
    public SocketChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) {
        return socketChannel;
    }

    @Override // com.sq.sywebsocket.WebSocketFactory
    public /* bridge */ /* synthetic */ WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List list) {
        return createWebSocket(webSocketAdapter, (List<Draft>) list);
    }

    @Override // com.sq.sywebsocket.WebSocketFactory
    public WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft) {
        return new WebSocketImpl(webSocketAdapter, draft);
    }

    @Override // com.sq.sywebsocket.WebSocketServerFactory, com.sq.sywebsocket.WebSocketFactory
    public WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list) {
        return new WebSocketImpl(webSocketAdapter, list);
    }
}
