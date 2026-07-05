package com.sy37sdk.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class RequestCallBack {
    public void onRequestError(String str) {
    }

    public abstract void onRequestSuccess(String str);

    public void onRequestError(int i, String str) {
        onRequestError(str);
    }
}
