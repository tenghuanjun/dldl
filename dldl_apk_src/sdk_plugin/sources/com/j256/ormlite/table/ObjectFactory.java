package com.j256.ormlite.table;

import java.lang.reflect.Constructor;
import java.sql.SQLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ObjectFactory<T> {
    T createObject(Constructor<T> constructor, Class<T> cls) throws SQLException;
}
