package com.j256.ormlite.dao;

import java.sql.SQLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface RawRowMapper<T> {
    T mapRow(String[] strArr, String[] strArr2) throws SQLException;
}
