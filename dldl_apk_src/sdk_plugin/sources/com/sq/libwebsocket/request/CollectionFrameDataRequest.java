package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;
import com.sq.sywebsocket.framing.Framedata;
import java.util.Collection;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CollectionFrameDataRequest implements Request<Collection<Framedata>> {
    private Collection<Framedata> data;

    CollectionFrameDataRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(Collection<Framedata> collection) {
        this.data = collection;
    }

    @Override // com.sq.libwebsocket.request.Request
    public Collection<Framedata> getRequestData() {
        return this.data;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.sendFrame(this.data);
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releaseCollectionFrameRequest(this);
    }

    public String toString() {
        String str;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        if (this.data == null) {
            str = AbstractJsonLexerKt.NULL;
        } else {
            str = this.data.size() + " length";
        }
        objArr[1] = str;
        return String.format("[@CollectionFrameDataRequest%s,Collection<Framedata>:%s]", objArr);
    }
}
