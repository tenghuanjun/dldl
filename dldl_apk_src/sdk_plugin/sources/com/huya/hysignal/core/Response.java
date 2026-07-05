package com.huya.hysignal.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Response {
    public final byte[] data;
    public final HySignalError error;

    Response(byte[] bArr, HySignalError hySignalError) {
        this.data = bArr;
        this.error = hySignalError;
    }
}
