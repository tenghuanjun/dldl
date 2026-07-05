package com.sq.libwebsocket.response;

import com.sq.libwebsocket.dispatcher.IResponseDispatcher;
import com.sq.libwebsocket.dispatcher.ResponseDelivery;
import com.sq.sywebsocket.framing.Framedata;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PingResponse implements Response<Framedata> {
    private Framedata framedata;

    PingResponse() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sq.libwebsocket.response.Response
    public Framedata getResponseData() {
        return this.framedata;
    }

    @Override // com.sq.libwebsocket.response.Response
    public void setResponseData(Framedata framedata) {
        this.framedata = framedata;
    }

    @Override // com.sq.libwebsocket.response.Response
    public void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        iResponseDispatcher.onPing(this.framedata, responseDelivery);
    }

    @Override // com.sq.libwebsocket.response.Response
    public void release() {
        this.framedata = null;
        ResponseFactory.releasePingResponse(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        Framedata framedata = this.framedata;
        objArr[1] = framedata == null ? AbstractJsonLexerKt.NULL : framedata.toString();
        return String.format("[@PingResponse%s->Framedata:%s]", objArr);
    }
}
