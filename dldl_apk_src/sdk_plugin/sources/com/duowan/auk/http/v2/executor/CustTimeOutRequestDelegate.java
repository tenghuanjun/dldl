package com.duowan.auk.http.v2.executor;

import com.android.volley.Request;
import com.duowan.auk.http.v2.HttpRequestDelegate;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class CustTimeOutRequestDelegate implements HttpRequestDelegate {
    private HttpRequestDelegate mHttpRequestDelegate;

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public int getMaxRetryTimes() {
        return 0;
    }

    public CustTimeOutRequestDelegate(HttpRequestDelegate httpRequestDelegate) {
        this.mHttpRequestDelegate = httpRequestDelegate;
    }

    public int getRealMaxRetryTimes() {
        return this.mHttpRequestDelegate.getMaxRetryTimes();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public int getTimeout() {
        return this.mHttpRequestDelegate.getTimeout();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public int getBackoffMultiplier() {
        return this.mHttpRequestDelegate.getBackoffMultiplier();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getUrl() {
        return this.mHttpRequestDelegate.getUrl();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public Map<String, String> getHeaders() {
        return this.mHttpRequestDelegate.getHeaders();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getBodyContentType() {
        return this.mHttpRequestDelegate.getBodyContentType();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public Map<String, String> getParams() {
        return this.mHttpRequestDelegate.getParams();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public byte[] getBody() {
        return this.mHttpRequestDelegate.getBody();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getCacheKey() {
        return this.mHttpRequestDelegate.getCacheKey();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public Request.Priority getPriority() {
        return this.mHttpRequestDelegate.getPriority();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public int getMethod() {
        return this.mHttpRequestDelegate.getMethod();
    }
}
