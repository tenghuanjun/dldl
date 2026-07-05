package com.duowan.auk.http.v2;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class RspCache<T> {
    public final T data;
    public final long softTtl;
    public final long ttl;

    public RspCache(T t, long j, long j2) {
        this.data = t;
        this.ttl = j;
        this.softTtl = j2;
    }

    public boolean isExpired() {
        return this.ttl < System.currentTimeMillis();
    }

    public boolean refreshNeeded() {
        return this.softTtl < System.currentTimeMillis();
    }

    public static <T> RspCache<T> emptyCache() {
        return new RspCache<>(null, 0L, 0L);
    }
}
