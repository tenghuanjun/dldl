package org.java_websocket.handshake;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface ServerHandshakeBuilder extends HandshakeBuilder, ServerHandshake {
    void setHttpStatus(short s);

    void setHttpStatusMessage(String str);
}
