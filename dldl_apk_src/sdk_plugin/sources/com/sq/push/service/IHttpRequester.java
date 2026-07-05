package com.sq.push.service;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IHttpRequester {

    public interface IRequestCallback {
        void onFailure(int code, String message);

        void onSuccess(int statusCode, String jsonStr);
    }

    void get(String url, Map<String, String> headers, Map<String, String> params, IRequestCallback callback);

    void postJson(String url, Map<String, String> headers, Map<String, String> params, IRequestCallback callback);
}
