package com.bytedance.framwork.core.sdklog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.bytedance.framwork.core.sdklib.DBHelper;

/* JADX INFO: loaded from: classes2.dex */
class LogQueueManager {
    static final String[] QUEUE_COLS = {DBHelper.COL_ID, DBHelper.COL_VALUE, "type", "timestamp", "retry_count", DBHelper.COL_RETRY_TIME};
    private static final String WHERE_ID = "_id = ?";
    private static LogQueueManager sInstance;
    private SQLiteDatabase mDb;

    private LogQueueManager(Context context) {
        if (context == null) {
            return;
        }
        try {
            this.mDb = DBHelper.getInstance(context).getWritableDatabase();
        } catch (Throwable unused) {
        }
    }

    static LogQueueManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LogQueueManager.class) {
                if (sInstance == null) {
                    sInstance = new LogQueueManager(context);
                }
            }
        }
        return sInstance;
    }

    static void safeCloseCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    synchronized void cleanExpireLog(String str, int i, long j) {
        String[] strArr;
        String str2;
        if (isOpen()) {
            long jCurrentTimeMillis = System.currentTimeMillis() - j;
            if (TextUtils.isEmpty(str)) {
                str2 = "timestamp <= ? ";
                strArr = new String[]{String.valueOf(jCurrentTimeMillis)};
            } else {
                String str3 = "(timestamp <= ? OR retry_count > " + i + ") and type = ?";
                strArr = new String[]{String.valueOf(jCurrentTimeMillis), str};
                str2 = str3;
            }
            try {
                this.mDb.delete(DBHelper.TABLE_QUEUE, str2, strArr);
            } catch (Exception e) {
                LogQueue.log("delete expire log error:" + e);
            }
        }
    }

    synchronized void closeDatabase() {
        synchronized (this) {
            try {
                if (isOpen()) {
                    this.mDb.close();
                    this.mDb = null;
                }
            } finally {
            }
        }
    }

    synchronized long getEventCount(String str) {
        long j = 0;
        if (!isOpen()) {
            return 0L;
        }
        Cursor cursorRawQuery = null;
        try {
            String str2 = "select count(*) from queue";
            if (!TextUtils.isEmpty(str)) {
                str2 = "select count(*) from queue " + str;
            }
            cursorRawQuery = this.mDb.rawQuery(str2, null);
            if (cursorRawQuery.moveToNext()) {
                j = cursorRawQuery.getLong(0);
            }
        } catch (Throwable unused) {
        }
        safeCloseCursor(cursorRawQuery);
        return j;
    }

    synchronized LogItem getLog(long j) {
        Exception e;
        Cursor cursorQuery;
        LogItem logItem;
        Cursor cursor = null;
        LogItem logItem2 = null;
        if (!isOpen()) {
            return null;
        }
        try {
            try {
                cursorQuery = this.mDb.query(DBHelper.TABLE_QUEUE, QUEUE_COLS, "_id > ?", new String[]{String.valueOf(j)}, null, null, "_id ASC", "1");
            } catch (Exception e2) {
                e = e2;
                cursorQuery = null;
            }
            try {
                try {
                    if (cursorQuery.moveToNext()) {
                        logItem = new LogItem();
                        try {
                            logItem.id = cursorQuery.getLong(0);
                            logItem.value = cursorQuery.getBlob(1);
                            logItem.type = cursorQuery.getString(2);
                            logItem.timestamp = cursorQuery.getLong(3);
                            logItem.retryCount = cursorQuery.getInt(4);
                            logItem.retryTime = cursorQuery.getLong(5);
                            logItem2 = logItem;
                        } catch (Exception e3) {
                            e = e3;
                            cursor = cursorQuery;
                            LogQueue.log("getLog exception " + e);
                            safeCloseCursor(cursor);
                            logItem2 = logItem;
                        }
                    }
                    safeCloseCursor(cursorQuery);
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    safeCloseCursor(cursor);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                logItem = null;
                cursor = cursorQuery;
                LogQueue.log("getLog exception " + e);
                safeCloseCursor(cursor);
                logItem2 = logItem;
                return logItem2;
            }
            return logItem2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    synchronized long insertLog(String str, byte[] bArr) {
        if (isOpen() && bArr != null && bArr.length > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COL_VALUE, bArr);
            contentValues.put("type", str);
            contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("retry_count", (Integer) 0);
            contentValues.put(DBHelper.COL_RETRY_TIME, (Long) 0L);
            return this.mDb.insert(DBHelper.TABLE_QUEUE, null, contentValues);
        }
        return -1L;
    }

    synchronized boolean isOpen() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            return true;
        }
        LogQueue.log("db not establish and open");
        return false;
    }

    synchronized boolean onLogSent(long j, boolean z, long j2, int i) {
        if (isOpen() && j > 0) {
            String[] strArr = {String.valueOf(j)};
            if (!z) {
                Cursor cursorQuery = null;
                try {
                    cursorQuery = this.mDb.query(DBHelper.TABLE_QUEUE, new String[]{"timestamp", "retry_count"}, WHERE_ID, strArr, null, null, null);
                    if (!cursorQuery.moveToNext()) {
                        return false;
                    }
                    long j3 = cursorQuery.getLong(0);
                    int i2 = cursorQuery.getInt(1);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - j3 < j2 && i2 < i) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("retry_count", Integer.valueOf(i2 + 1));
                        contentValues.put(DBHelper.COL_RETRY_TIME, Long.valueOf(jCurrentTimeMillis));
                        this.mDb.update(DBHelper.TABLE_QUEUE, contentValues, WHERE_ID, strArr);
                        return true;
                    }
                } catch (Exception e) {
                    LogQueue.log("onLogSent exception: " + e);
                    return false;
                } finally {
                    safeCloseCursor(cursorQuery);
                }
            }
            try {
                this.mDb.delete(DBHelper.TABLE_QUEUE, WHERE_ID, strArr);
            } catch (Throwable unused) {
            }
            LogQueue.log("delete app_log: " + j);
            return false;
        }
        return false;
    }

    synchronized void recreateTableQueue() {
        if (isOpen()) {
            try {
                this.mDb.execSQL("DROP TABLE IF EXISTS queue");
                this.mDb.execSQL(DBHelper.CREATE_TABLE_QUEUE);
            } catch (Exception e) {
                LogQueue.log("recreateTableQueue db exception " + e);
            }
        }
    }
}
