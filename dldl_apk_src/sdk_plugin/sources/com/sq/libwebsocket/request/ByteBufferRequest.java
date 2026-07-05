package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;
import java.nio.ByteBuffer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ByteBufferRequest implements Request<ByteBuffer> {
    private ByteBuffer data;

    ByteBufferRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    @Override // com.sq.libwebsocket.request.Request
    public ByteBuffer getRequestData() {
        return this.data;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.send(this.data);
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releaseByteBufferRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        ByteBuffer byteBuffer = this.data;
        objArr[1] = byteBuffer == null ? AbstractJsonLexerKt.NULL : byteBuffer.toString();
        return String.format("[@ByteBufferRequest%s,ByteBuffer:%s]", objArr);
    }
}
