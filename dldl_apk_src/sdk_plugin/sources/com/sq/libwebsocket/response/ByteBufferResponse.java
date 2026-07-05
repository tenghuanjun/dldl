package com.sq.libwebsocket.response;

import com.sq.libwebsocket.dispatcher.IResponseDispatcher;
import com.sq.libwebsocket.dispatcher.ResponseDelivery;
import com.sq.libwebsocket.util.LogUtil;
import java.nio.ByteBuffer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ByteBufferResponse implements Response<ByteBuffer> {
    private final String TAG = getClass().getSimpleName();
    private ByteBuffer data;

    ByteBufferResponse() {
    }

    @Override // com.sq.libwebsocket.response.Response
    public ByteBuffer getResponseData() {
        return this.data;
    }

    @Override // com.sq.libwebsocket.response.Response
    public void setResponseData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    @Override // com.sq.libwebsocket.response.Response
    public void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        LogUtil.i(this.TAG, "onResponse");
        iResponseDispatcher.onMessage(this.data, responseDelivery);
        release();
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        ByteBuffer byteBuffer = this.data;
        objArr[1] = byteBuffer == null ? AbstractJsonLexerKt.NULL : byteBuffer.toString();
        return String.format("[@ByteBufferResponse%s->ByteBuffer:%s]", objArr);
    }

    @Override // com.sq.libwebsocket.response.Response
    public void release() {
        ResponseFactory.releaseByteBufferResponse(this);
    }
}
