package com.duowan.auk.http.v2;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class HttpDelegateFunction<Rsp, DelegateRsp> extends HttpFunction<Rsp> {
    private HttpFunction<DelegateRsp> mHttpFunction;

    protected abstract Rsp convertToOwnRsp(HttpFunction<DelegateRsp> httpFunction, DelegateRsp delegatersp);

    public HttpDelegateFunction(HttpFunction<DelegateRsp> httpFunction) {
        setHttpFunction(httpFunction);
    }

    public HttpDelegateFunction() {
        setHttpFunction(null);
    }

    public HttpDelegateFunction setHttpFunction(HttpFunction<DelegateRsp> httpFunction) {
        this.mHttpFunction = httpFunction;
        return this;
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public int getMethod() {
        return this.mHttpFunction.getMethod();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public int getTimeout() {
        return this.mHttpFunction.getTimeout();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public int getMaxRetryTimes() {
        return this.mHttpFunction.getMaxRetryTimes();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public int getBackoffMultiplier() {
        return this.mHttpFunction.getBackoffMultiplier();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public long getCacheExpireTimeMillis() {
        return this.mHttpFunction.getCacheExpireTimeMillis();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public long getCacheRefreshTimeMillis() {
        return this.mHttpFunction.getCacheRefreshTimeMillis();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public Map<String, String> getHeaders() {
        return this.mHttpFunction.getHeaders();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public Map<String, String> getParams() {
        return this.mHttpFunction.getParams();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public Request.Priority getPriority() {
        return this.mHttpFunction.getPriority();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public boolean shouldUseCustomCache() {
        return this.mHttpFunction.shouldUseCustomCache();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public RspCache<Rsp> getCache() {
        RspCache<DelegateRsp> cache = this.mHttpFunction.getCache();
        if (cache == null) {
            return RspCache.emptyCache();
        }
        return new RspCache<>(convertToOwnRsp(this.mHttpFunction, cache.data), cache.ttl, cache.softTtl);
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    protected Rsp onReadResponse(NetworkResponse networkResponse) throws VolleyError {
        return convertToOwnRsp(this.mHttpFunction, this.mHttpFunction.onReadResponse(networkResponse));
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getUrl() {
        return this.mHttpFunction.getUrl();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getBodyContentType() {
        return this.mHttpFunction.getBodyContentType();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public byte[] getBody() {
        return this.mHttpFunction.getBody();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getCacheKey() {
        return this.mHttpFunction.getCacheKey();
    }
}
