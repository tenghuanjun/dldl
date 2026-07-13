package com.google.protobuf;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface RpcController {
    String errorText();

    boolean failed();

    boolean isCanceled();

    void notifyOnCancel(RpcCallback<Object> rpcCallback);

    void reset();

    void setFailed(String str);

    void startCancel();
}
