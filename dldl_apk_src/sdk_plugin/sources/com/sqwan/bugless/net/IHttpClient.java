package com.sqwan.bugless.net;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IHttpClient {
    void postString(String url, String body, Map<String, String> headers, IHttpCallback callback);
}
