package com.squareup.okhttp.internal.huc;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.http.CacheStrategy;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class CacheAdapter implements InternalCache {
    private final ResponseCache delegate;

    @Override // com.squareup.okhttp.internal.InternalCache
    public void remove(Request request) throws IOException {
    }

    @Override // com.squareup.okhttp.internal.InternalCache
    public void trackConditionalCacheHit() {
    }

    @Override // com.squareup.okhttp.internal.InternalCache
    public void trackResponse(CacheStrategy cacheStrategy) {
    }

    @Override // com.squareup.okhttp.internal.InternalCache
    public void update(Response response, Response response2) throws IOException {
    }

    public CacheAdapter(ResponseCache responseCache) {
        this.delegate = responseCache;
    }

    public ResponseCache getDelegate() {
        return this.delegate;
    }

    @Override // com.squareup.okhttp.internal.InternalCache
    public Response get(Request request) throws IOException {
        CacheResponse javaCachedResponse = getJavaCachedResponse(request);
        if (javaCachedResponse == null) {
            return null;
        }
        return JavaApiConverter.createOkResponse(request, javaCachedResponse);
    }

    @Override // com.squareup.okhttp.internal.InternalCache
    public CacheRequest put(Response response) throws IOException {
        return this.delegate.put(response.request().uri(), JavaApiConverter.createJavaUrlConnection(response));
    }

    private CacheResponse getJavaCachedResponse(Request request) throws IOException {
        return this.delegate.get(request.uri(), request.method(), JavaApiConverter.extractJavaHeaders(request));
    }
}
