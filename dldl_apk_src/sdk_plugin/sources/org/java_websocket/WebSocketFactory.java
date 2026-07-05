package org.java_websocket;

import java.net.Socket;
import java.util.List;
import org.java_websocket.drafts.Draft;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface WebSocketFactory {
    WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket);

    WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket);
}
