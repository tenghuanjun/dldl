package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ByteArrayRequest implements Request<byte[]> {
    private static final String TAG = "ByteArrayRequest";
    private byte[] data;

    ByteArrayRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(byte[] bArr) {
        this.data = bArr;
    }

    @Override // com.sq.libwebsocket.request.Request
    public byte[] getRequestData() {
        return this.data;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.send(this.data);
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releaseByteArrayRequest(this);
    }

    public String toString() {
        String str;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(hashCode());
        if (this.data == null) {
            str = "data:null";
        } else {
            str = "data.length:" + this.data.length;
        }
        objArr[1] = str;
        objArr[2] = Arrays.toString(this.data);
        return String.format("[@ByteArrayRequest%s,%s]\n%s", objArr);
    }
}
