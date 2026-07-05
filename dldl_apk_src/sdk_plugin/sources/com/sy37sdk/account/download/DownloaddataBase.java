package com.sy37sdk.account.download;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DownloaddataBase extends SQLiteOpenHelper {
    public static String DATABASE = "sqdownload.db";
    public static String TABLE = "sqdownload";
    private static DownloaddataBase base = null;
    private static int version = 300;

    public static DownloaddataBase getDownloaddataBase(Context context, String str) {
        if (base == null) {
            base = new DownloaddataBase(context, str);
        }
        return base;
    }

    private DownloaddataBase(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, version);
    }

    public DownloaddataBase(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists " + TABLE + " ( _id integer primary key ,  url varchar , downloading varchar , filename varchar , path varchar , id varchar ) ");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sQLiteDatabase);
    }
}
