package com.sq.sdk.tool.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseDatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_MIN_VERSION = 1;
    protected boolean mIsNewDB;
    protected boolean mIsUpgrade;

    public BaseDatabaseHelper(Context context, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.mIsUpgrade = true;
        this.mIsNewDB = false;
    }

    public boolean isNewDB() {
        return this.mIsNewDB;
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return DatabaseUtils.rawQuery(this, str, strArr);
    }

    public boolean execSQL(String... strArr) {
        return DatabaseUtils.execSQL((SQLiteOpenHelper) this, strArr);
    }

    public boolean execSQL(Object... objArr) {
        return DatabaseUtils.execSQL(this, objArr);
    }

    public static abstract class AbstractDatabaseUpgrade {
        public abstract boolean upgradeDb(SQLiteDatabase sQLiteDatabase, int i, int i2);

        public boolean isExistTable(SQLiteDatabase sQLiteDatabase, String str) {
            return DatabaseUtils.isExistTable(sQLiteDatabase, str);
        }

        public boolean isExistColumnInTable(SQLiteDatabase sQLiteDatabase, String str, String str2) {
            return DatabaseUtils.isExistColumnInTable(sQLiteDatabase, str, str2);
        }

        public void addColumnToTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
            DatabaseUtils.addColumnToTable(sQLiteDatabase, str, str2, str3, str4);
        }
    }
}
