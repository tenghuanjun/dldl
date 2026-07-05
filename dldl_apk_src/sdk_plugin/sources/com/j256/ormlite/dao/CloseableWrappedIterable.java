package com.j256.ormlite.dao;

import java.sql.SQLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface CloseableWrappedIterable<T> extends CloseableIterable<T> {
    void close() throws SQLException;
}
