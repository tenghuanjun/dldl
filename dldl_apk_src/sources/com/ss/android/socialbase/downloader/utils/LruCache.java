package com.ss.android.socialbase.downloader.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class LruCache<K, T> extends LinkedHashMap<K, T> {
    private static final int DEFAULT_SIZE = 4;
    private int mMaxSize;

    public LruCache() {
        this(4, 4);
    }

    public LruCache(int i, int i2) {
        this(i, i2, true);
    }

    public LruCache(int i, int i2, boolean z) {
        super(i, 0.75f, z);
        setMaxSize(i2);
    }

    public void setMaxSize(int i) {
        this.mMaxSize = i;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, T> entry) {
        return size() > this.mMaxSize;
    }
}
