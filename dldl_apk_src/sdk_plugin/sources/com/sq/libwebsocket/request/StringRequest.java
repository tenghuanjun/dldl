package com.sq.libwebsocket.request;

import android.text.TextUtils;
import com.sq.sywebsocket.client.WebSocketClient;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StringRequest implements Request<String> {
    private String requestText;

    StringRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(String str) {
        this.requestText = str;
    }

    @Override // com.sq.libwebsocket.request.Request
    public String getRequestData() {
        return this.requestText;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.send(this.requestText);
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releaseStringRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        objArr[1] = TextUtils.isEmpty(this.requestText) ? AbstractJsonLexerKt.NULL : this.requestText;
        return String.format("@StringRequest%s,requestText:%s", objArr);
    }
}
