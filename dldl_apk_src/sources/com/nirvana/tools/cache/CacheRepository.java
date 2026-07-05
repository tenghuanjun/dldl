package com.nirvana.tools.cache;

import com.nirvana.tools.cache.RepositoryTemplate;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public abstract class CacheRepository<T extends RepositoryTemplate> {
    private T mTemplate;

    public CacheRepository(T t) {
        this.mTemplate = t;
    }

    abstract void clear();

    public T getTemplate() {
        return this.mTemplate;
    }

    abstract String read();

    abstract void write(String str);
}
