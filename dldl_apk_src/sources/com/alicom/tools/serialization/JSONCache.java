package com.alicom.tools.serialization;

import android.util.LruCache;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class JSONCache {
    private LruCache<Class, JSONClass> mJsonCache = new LruCache<>(100);

    public void clearCache() {
        this.mJsonCache.evictAll();
    }

    public JSONClass getJsonClass(Class cls) {
        return this.mJsonCache.get(cls);
    }

    public void putJsonClass(Class cls, JSONClass jSONClass) {
        this.mJsonCache.put(cls, jSONClass);
    }
}
