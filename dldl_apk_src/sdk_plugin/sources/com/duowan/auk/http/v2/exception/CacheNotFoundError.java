package com.duowan.auk.http.v2.exception;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CacheNotFoundError extends HttpV2Error {
    public CacheNotFoundError(String str) {
        super(String.format("cache not found for cache key: %s", str));
    }
}
