package com.sq.sywebsocket.server;

import com.sq.sywebsocket.SSLSocketChannel2;
import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CustomSSLWebSocketServerFactory extends DefaultSSLWebSocketServerFactory {
    private final String[] enabledCiphersuites;
    private final String[] enabledProtocols;

    public CustomSSLWebSocketServerFactory(SSLContext sSLContext, String[] strArr, String[] strArr2) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor(), strArr, strArr2);
    }

    public CustomSSLWebSocketServerFactory(SSLContext sSLContext, ExecutorService executorService, String[] strArr, String[] strArr2) {
        super(sSLContext, executorService);
        this.enabledProtocols = strArr;
        this.enabledCiphersuites = strArr2;
    }

    @Override // com.sq.sywebsocket.server.DefaultSSLWebSocketServerFactory, com.sq.sywebsocket.WebSocketServerFactory
    public ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException {
        SSLEngine sSLEngineCreateSSLEngine = this.sslcontext.createSSLEngine();
        String[] strArr = this.enabledProtocols;
        if (strArr != null) {
            sSLEngineCreateSSLEngine.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.enabledCiphersuites;
        if (strArr2 != null) {
            sSLEngineCreateSSLEngine.setEnabledCipherSuites(strArr2);
        }
        sSLEngineCreateSSLEngine.setUseClientMode(false);
        return new SSLSocketChannel2(socketChannel, sSLEngineCreateSSLEngine, this.exec, selectionKey);
    }
}
