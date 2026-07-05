package com.duowan.auk.http.v2;

import com.android.volley.Request;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface HttpRequestDelegate {
    int getBackoffMultiplier();

    byte[] getBody();

    String getBodyContentType();

    String getCacheKey();

    Map<String, String> getHeaders();

    int getMaxRetryTimes();

    int getMethod();

    Map<String, String> getParams();

    Request.Priority getPriority();

    int getTimeout();

    String getUrl();
}
