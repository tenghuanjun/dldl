package com.sy37sdk.account.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.sq.sdk.tool.database.BaseDatabaseHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DataBaseHelper extends BaseDatabaseHelper {
    private static final String DB_NAME = "sq_sdk";
    private static final int DB_VERSION = 1;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL(LoginTriggerTable.SQL);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }
}
