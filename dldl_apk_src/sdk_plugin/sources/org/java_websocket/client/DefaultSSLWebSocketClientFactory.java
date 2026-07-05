package org.java_websocket.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import org.java_websocket.SSLSocketChannel2;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DefaultSSLWebSocketClientFactory implements WebSocketClient.WebSocketClientFactory {
    protected ExecutorService exec;
    protected SSLContext sslcontext;

    @Override // org.java_websocket.WebSocketFactory
    public /* bridge */ /* synthetic */ WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List list, Socket socket) {
        return createWebSocket(webSocketAdapter, (List<Draft>) list, socket);
    }

    public DefaultSSLWebSocketClientFactory(SSLContext sSLContext) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor());
    }

    public DefaultSSLWebSocketClientFactory(SSLContext sSLContext, ExecutorService executorService) {
        if (sSLContext == null || executorService == null) {
            throw new IllegalArgumentException();
        }
        this.sslcontext = sSLContext;
        this.exec = executorService;
    }

    @Override // org.java_websocket.client.WebSocketClient.WebSocketClientFactory
    public ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey, String str, int i) throws IOException {
        SSLEngine sSLEngineCreateSSLEngine = this.sslcontext.createSSLEngine(str, i);
        sSLEngineCreateSSLEngine.setUseClientMode(true);
        return new SSLSocketChannel2(socketChannel, sSLEngineCreateSSLEngine, this.exec, selectionKey);
    }

    @Override // org.java_websocket.WebSocketFactory
    public WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket) {
        return new WebSocketImpl(webSocketAdapter, draft, socket);
    }

    @Override // org.java_websocket.WebSocketFactory
    public WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket) {
        return new WebSocketImpl(webSocketAdapter, list, socket);
    }
}
