package com.duowan.auk.http.v2;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.http.v2.cachestrategy.BaseCacheStrategy;
import com.duowan.auk.http.v2.cachestrategy.CacheStrategyFactory;
import com.duowan.auk.http.v2.exception.NullResponseException;
import com.duowan.auk.http.v2.executor.FunctionExecutor;
import com.duowan.auk.http.v2.executor.VolleyRequestExecutor;
import com.duowan.auk.util.L;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class HttpFunction<Rsp> extends Function<Rsp> implements HttpRequestDelegate, HttpResponseDelegate<Rsp> {
    public static final int DEFAULT_BACKOFF_MULTIPLIER = 0;
    private static final long DEFAULT_CACHE_EXPIRE_TIME = 86400000;
    private static final long DEFAULT_CACHE_REFRESH_TIME = 43200000;
    public static final int DEFAULT_RETRY_TIME = 1;
    public static final int DEFAULT_TIME_OUT = 10000;
    private static final String TAG = "HttpFunction";
    private BaseCacheStrategy<Rsp> mCacheStrategy;
    private FunctionExecutor mFunctionExecutor;

    public int getBackoffMultiplier() {
        return 0;
    }

    public int getMaxRetryTimes() {
        return 1;
    }

    public int getTimeout() {
        return 10000;
    }

    protected abstract Rsp onReadResponse(NetworkResponse networkResponse) throws VolleyError;

    public boolean shouldUseCustomCache() {
        return false;
    }

    public HttpFunction() {
        setFunctionExecutor(new VolleyRequestExecutor());
    }

    protected void setFunctionExecutor(FunctionExecutor functionExecutor) {
        this.mFunctionExecutor = functionExecutor;
    }

    public long getCacheExpireTimeMillis() {
        return shouldUseCustomCache() ? 86400000L : 0L;
    }

    public long getCacheRefreshTimeMillis() {
        if (shouldUseCustomCache()) {
            return DEFAULT_CACHE_REFRESH_TIME;
        }
        return 0L;
    }

    public Map<String, String> getHeaders() {
        return new HashMap();
    }

    public Map<String, String> getParams() {
        return new HashMap();
    }

    public Request.Priority getPriority() {
        return Request.Priority.NORMAL;
    }

    public RspCache<Rsp> getCache() {
        return readCacheFromStorage();
    }

    @Override // com.duowan.auk.http.v2.Function
    public void execute() {
        execute(CacheType.AsConfig);
    }

    public void execute(CacheType cacheType) {
        L.info(TAG, "execute, cacheKey = %s, cacheType = %s", getCacheKey(), cacheType);
        if (L.isLogLevelEnabled(3)) {
            L.debug(TAG, "function entity = %s", this);
        }
        BaseCacheStrategy<Rsp> baseCacheStrategyCreateCacheStrategy = CacheStrategyFactory.createCacheStrategy(cacheType, this);
        this.mCacheStrategy = baseCacheStrategyCreateCacheStrategy;
        baseCacheStrategyCreateCacheStrategy.execute();
    }

    public void executeFromNet() {
        FunctionExecutor functionExecutor = this.mFunctionExecutor;
        if (functionExecutor != null) {
            functionExecutor.execute(this, this);
        }
    }

    public void cancel() {
        FunctionExecutor functionExecutor = this.mFunctionExecutor;
        if (functionExecutor != null) {
            functionExecutor.cancel(this, this);
        }
    }

    @Override // com.duowan.auk.http.v2.HttpResponseDelegate
    public Rsp parseResponse(NetworkResponse networkResponse) throws VolleyError {
        Rsp rspOnReadResponse = onReadResponse(networkResponse);
        postParseResponse(networkResponse, rspOnReadResponse);
        return rspOnReadResponse;
    }

    protected void postParseResponse(NetworkResponse networkResponse, Rsp rsp) throws VolleyError {
        validateResponse(rsp);
        if (shouldUseCustomCache()) {
            saveCacheToStorage(networkResponse);
        }
    }

    protected void validateResponse(Rsp rsp) throws VolleyError {
        if (rsp == null) {
            throw new NullResponseException();
        }
    }

    @Override // com.duowan.auk.http.v2.HttpResponseDelegate
    public final void deliverError(VolleyError volleyError) {
        if (this.mCacheStrategy.handleNetworkError(volleyError)) {
            return;
        }
        doDeliverError(volleyError);
    }

    @Override // com.duowan.auk.http.v2.HttpResponseDelegate
    public final void deliverResponse(Rsp rsp) {
        if (this.mCacheStrategy.handleNetworkResponse(rsp)) {
            return;
        }
        doDeliverResponse(rsp, false);
    }

    protected void doDeliverError(VolleyError volleyError) {
        L.info(TAG, "deliverError for request:%s", getCacheKey());
        L.error(TAG, (Throwable) volleyError);
        onError(volleyError);
    }

    protected void doDeliverResponse(Rsp rsp, boolean z) {
        L.info(TAG, "deliverResponse, cacheKey = %s, fromCache = %b", getCacheKey(), Boolean.valueOf(z));
        if (L.isLogLevelEnabled(3)) {
            L.debug(TAG, "response = %s", rsp);
        }
        onResponse(rsp, z);
    }

    public void deliverResponseFromCache(Rsp rsp) {
        doDeliverResponse(rsp, true);
    }

    public void deliverErrorFromCache(VolleyError volleyError) {
        doDeliverError(volleyError);
    }

    protected void saveCacheToStorage(NetworkResponse networkResponse) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Cache.Entry entry = new Cache.Entry();
        long cacheRefreshTimeMillis = getCacheRefreshTimeMillis() + jCurrentTimeMillis;
        long cacheExpireTimeMillis = getCacheExpireTimeMillis() + jCurrentTimeMillis;
        entry.data = networkResponse.data;
        entry.softTtl = cacheRefreshTimeMillis;
        entry.ttl = cacheExpireTimeMillis;
        entry.responseHeaders = networkResponse.headers;
        HttpClient.setCache(getCacheKey(), entry);
    }

    private RspCache<Rsp> readCacheFromStorage() {
        Cache.Entry cache = HttpClient.getCache(getCacheKey());
        if (cache == null) {
            return RspCache.emptyCache();
        }
        try {
            return new RspCache<>(onReadResponse(new NetworkResponse(cache.data, cache.responseHeaders)), cache.ttl, cache.softTtl);
        } catch (VolleyError e) {
            e.printStackTrace();
            return RspCache.emptyCache();
        }
    }
}
