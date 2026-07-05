package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;
import com.sq.sywebsocket.framing.Framedata;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FrameDataRequest implements Request<Framedata> {
    private Framedata framedata;

    FrameDataRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(Framedata framedata) {
        this.framedata = framedata;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sq.libwebsocket.request.Request
    public Framedata getRequestData() {
        return this.framedata;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.sendFrame(this.framedata);
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releaseFrameDataRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        Framedata framedata = this.framedata;
        objArr[1] = framedata == null ? AbstractJsonLexerKt.NULL : framedata.toString();
        return String.format("[@FrameDataRequest%s,Framedata:%s]", objArr);
    }
}
