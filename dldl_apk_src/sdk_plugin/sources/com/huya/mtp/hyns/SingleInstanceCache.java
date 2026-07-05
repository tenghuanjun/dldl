package com.huya.mtp.hyns;

import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class SingleInstanceCache<K, V> {
    private final Cache<K, V> mCache;

    public interface Cache<K, V> {
        V get(K k);

        void put(K k, V v);

        Collection<V> values();
    }

    protected abstract V newInstance(K k);

    public SingleInstanceCache(Cache<K, V> cache) {
        this.mCache = cache;
    }

    public void put(K k, V v) {
        synchronized (this) {
            this.mCache.put(k, v);
        }
    }

    public V get(K k) {
        V vNewInstance = this.mCache.get(k);
        if (vNewInstance == null) {
            synchronized (this) {
                vNewInstance = this.mCache.get(k);
                if (vNewInstance == null) {
                    vNewInstance = newInstance(k);
                    this.mCache.put(k, vNewInstance);
                }
            }
        }
        return vNewInstance;
    }

    public Collection<V> values() {
        return this.mCache.values();
    }
}
