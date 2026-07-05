package com.huya.statistics.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.huya.statistics.log.SLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TaskDataSqLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public TaskDataSqLiteDBHelper(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS task_data(id integer primary key ,uuid blob, content blob, createTime integer, lastSendTime integer, sendCount integer, isSuccess integer)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            sQLiteDatabase.enableWriteAheadLogging();
            SLog.info("TaskDataSqLiteDBHelper", "wal is " + sQLiteDatabase.isWriteAheadLoggingEnabled(), new Object[0]);
        } catch (Exception unused) {
            SLog.info("TaskDataSqLiteDBHelper", "wal is " + sQLiteDatabase.isWriteAheadLoggingEnabled(), new Object[0]);
        } catch (Throwable th) {
            SLog.info("TaskDataSqLiteDBHelper", "wal is " + sQLiteDatabase.isWriteAheadLoggingEnabled(), new Object[0]);
            throw th;
        }
    }
}
