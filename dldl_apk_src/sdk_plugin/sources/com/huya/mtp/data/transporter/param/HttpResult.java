package com.huya.mtp.data.transporter.param;

import com.huya.mtp.http.NetworkResponse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpResult extends Result<NetworkResponse> {
    public int mRawDataSize;

    public HttpResult(NetworkResponse networkResponse) {
        this(networkResponse, networkResponse.data == null ? 0 : networkResponse.data.length);
    }

    public HttpResult(NetworkResponse networkResponse, int i) {
        super(networkResponse);
        this.mRawDataSize = i;
    }
}
