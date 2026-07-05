package com.huya.mtp.multithreaddownload.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "download.db";
    private static final int DB_VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    void createTable(SQLiteDatabase sQLiteDatabase) {
        ThreadInfoDao.createTable(sQLiteDatabase);
    }

    void dropTable(SQLiteDatabase sQLiteDatabase) {
        ThreadInfoDao.dropTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        dropTable(sQLiteDatabase);
        createTable(sQLiteDatabase);
    }
}
