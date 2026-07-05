package com.huya.mtp.multithreaddownload.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class AbstractDao<T> {
    private DBOpenHelper mHelper;

    public AbstractDao(Context context) {
        this.mHelper = new DBOpenHelper(context);
    }

    protected SQLiteDatabase getWritableDatabase() {
        return this.mHelper.getWritableDatabase();
    }

    protected SQLiteDatabase getReadableDatabase() {
        return this.mHelper.getReadableDatabase();
    }

    public void close() {
        this.mHelper.close();
    }
}
