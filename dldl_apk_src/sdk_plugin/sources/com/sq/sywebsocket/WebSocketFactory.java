package com.sq.sywebsocket;

import com.sq.sywebsocket.drafts.Draft;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface WebSocketFactory {
    WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft);

    WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list);
}
