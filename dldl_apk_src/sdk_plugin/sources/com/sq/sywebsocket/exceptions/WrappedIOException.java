package com.sq.sywebsocket.exceptions;

import com.sq.sywebsocket.WebSocket;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WrappedIOException extends Exception {
    private final transient WebSocket connection;
    private final IOException ioException;

    public WrappedIOException(WebSocket webSocket, IOException iOException) {
        this.connection = webSocket;
        this.ioException = iOException;
    }

    public WebSocket getConnection() {
        return this.connection;
    }

    public IOException getIOException() {
        return this.ioException;
    }
}
