package com.j256.ormlite.dao;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface CloseableIterable<T> extends Iterable<T> {
    CloseableIterator<T> closeableIterator();
}
