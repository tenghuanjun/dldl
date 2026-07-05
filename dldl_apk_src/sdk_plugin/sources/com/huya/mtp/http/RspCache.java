package com.huya.mtp.http;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RspCache<T> {
    private static final long SOFT_TTL_EMPTY = -2;
    private static final long SOFT_TTL_NO_EXPIRE = -1;
    private static final long TTL_EMPTY = -2;
    private static final long TTL_NO_EXPIRE = -1;
    public final T data;
    public final long softTtl;
    public final long ttl;

    public RspCache(T t) {
        this(t, -1L, -1L);
    }

    public RspCache(T t, long j, long j2) {
        this.data = t;
        this.ttl = j;
        this.softTtl = j2;
    }

    public boolean isExpired() {
        long j = this.ttl;
        return j != -1 && j < System.currentTimeMillis();
    }

    public boolean refreshNeeded() {
        long j = this.softTtl;
        return j != -1 && j < System.currentTimeMillis();
    }

    public boolean isEmpty() {
        return this.data == null && this.ttl == -2 && this.softTtl == -2;
    }

    public static <T> RspCache<T> emptyCache() {
        return new RspCache<>(null, -2L, -2L);
    }
}
