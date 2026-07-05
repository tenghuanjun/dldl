package com.sq.libwebsocket.response;

import android.text.TextUtils;
import com.sq.libwebsocket.dispatcher.IResponseDispatcher;
import com.sq.libwebsocket.dispatcher.ResponseDelivery;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TextResponse implements Response<String> {
    private String responseText;

    TextResponse() {
    }

    @Override // com.sq.libwebsocket.response.Response
    public String getResponseData() {
        return this.responseText;
    }

    @Override // com.sq.libwebsocket.response.Response
    public void setResponseData(String str) {
        this.responseText = str;
    }

    @Override // com.sq.libwebsocket.response.Response
    public void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        iResponseDispatcher.onMessage(this.responseText, responseDelivery);
        release();
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        objArr[1] = TextUtils.isEmpty(this.responseText) ? AbstractJsonLexerKt.NULL : this.responseText;
        return String.format("[@TextResponse%s->responseText:%s]", objArr);
    }

    @Override // com.sq.libwebsocket.response.Response
    public void release() {
        ResponseFactory.releaseTextResponse(this);
    }
}
