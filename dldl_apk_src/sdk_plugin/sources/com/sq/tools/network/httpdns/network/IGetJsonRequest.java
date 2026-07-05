package com.sq.tools.network.httpdns.network;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IGetJsonRequest {

    public interface IGetJsonRequestCallback {
        void onFailure(int code, String message);

        void onSuccess(int statusCode, String jsonStr);
    }

    void request(String url, Map<String, String> headers, Map<String, String> params, long timeout, IGetJsonRequestCallback callback);
}
