package com.j256.ormlite.dao;

import java.sql.SQLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CloseableWrappedIterableImpl<T> implements CloseableWrappedIterable<T> {
    private final CloseableIterable<T> iterable;
    private CloseableIterator<T> iterator;

    public CloseableWrappedIterableImpl(CloseableIterable<T> closeableIterable) {
        this.iterable = closeableIterable;
    }

    @Override // java.lang.Iterable
    public CloseableIterator<T> iterator() {
        return closeableIterator();
    }

    @Override // com.j256.ormlite.dao.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        try {
            close();
        } catch (SQLException unused) {
        }
        CloseableIterator<T> closeableIterator = this.iterable.closeableIterator();
        this.iterator = closeableIterator;
        return closeableIterator;
    }

    @Override // com.j256.ormlite.dao.CloseableWrappedIterable
    public void close() throws SQLException {
        CloseableIterator<T> closeableIterator = this.iterator;
        if (closeableIterator != null) {
            closeableIterator.close();
            this.iterator = null;
        }
    }
}
