package com.bytedance.framwork.core.sdklib;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.bytedance.framwork.core.sdklib.model.LocalLog;
import com.bytedance.framwork.core.sdklib.util.ListUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class LogStoreManager {
    private static final String CLAUSE_AID_EQUAL = "aid= ?";
    private static final String ORDER_BY_ID_ASC = "_id ASC ";
    static final String[] SAMPLE_LOG_COLS = {DBHelper.COL_ID, "data"};
    private static String SQL_GET_TOTAL_COUNT = "SELECT count(*) FROM monitor_log";
    private static String SQL_GET_TOTAL_COUNT_AID = "SELECT count(*) FROM monitor_log WHERE aid = ?";
    private static final String TAG = "LogStoreManager";
    static final int WEED_OUT_ROWS_SINGLE_TIME = 500;
    private static LogStoreManager instance = null;
    static boolean isCheckLegacyDb = false;
    static boolean isCheckedDbSize = false;
    static long sMaxLogSaveCount = 5000;
    private Context mContext;
    private SQLiteDatabase mDb;
    private Map<String, Integer> mAidLogCountMap = new HashMap(2);
    private int mFastReadSampleTimes = 0;
    String sql = "INSERT INTO monitor_log(aid,type,type2,time,data) VALUES ( ?, ?, ?, ?, ?)";

    private LogStoreManager(Context context) {
        this.mContext = context;
        this.mDb = DBHelper.getInstance(context).getWritableDatabase();
    }

    private void addLogCountBuffer(String str, int i) {
        if (this.mAidLogCountMap.containsKey(str) || i <= 0) {
            i = Math.max(0, i + this.mAidLogCountMap.get(str).intValue());
        }
        this.mAidLogCountMap.put(str, Integer.valueOf(i));
    }

    private void deleteDb(String str) {
        try {
            File databasePath = this.mContext.getDatabasePath(str + ".db");
            if (databasePath.exists()) {
                databasePath.delete();
            }
        } catch (Exception unused) {
        }
    }

    private void deleteLegacyDb() {
        deleteDb("psdkmon");
    }

    public static LogStoreManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new LogStoreManager(context);
                }
            }
        }
        return instance;
    }

    private synchronized int getLogCountFromDb() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        int i = -1;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return -1;
        }
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.mDb.rawQuery(SQL_GET_TOTAL_COUNT, null);
            if (cursorRawQuery.moveToNext()) {
                i = cursorRawQuery.getInt(0);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            safeCloseCursor(cursorRawQuery);
            throw th;
        }
        safeCloseCursor(cursorRawQuery);
        return i;
    }

    private synchronized int getLogCountFromDbAid(String str) {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        int i = -1;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return -1;
        }
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.mDb.rawQuery(SQL_GET_TOTAL_COUNT_AID, new String[]{str});
            if (cursorRawQuery.moveToNext()) {
                i = cursorRawQuery.getInt(0);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            safeCloseCursor(cursorRawQuery);
            throw th;
        }
        safeCloseCursor(cursorRawQuery);
        return i;
    }

    protected static void safeCloseCursor(Cursor cursor) {
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

    private synchronized void weedOutLogIfNeed() {
        if (!isCheckedDbSize) {
            isCheckedDbSize = true;
            if (getLogCountFromDb() >= sMaxLogSaveCount) {
                weedOutOldLogs(500L);
            }
        }
        if (!isCheckLegacyDb) {
            isCheckLegacyDb = true;
            deleteLegacyDb();
        }
    }

    public synchronized int deleteLogs(String str, long j) {
        if (this.mDb != null && j >= 0) {
            int iDelete = this.mDb.delete(DBHelper.T_LOCAL_LOG, "aid = ? AND _id<= ? ", new String[]{str, String.valueOf(j)});
            addLogCountBuffer(str, iDelete * (-1));
            return iDelete;
        }
        return -1;
    }

    public synchronized int getLogCount(String str) {
        int iIntValue;
        if (this.mFastReadSampleTimes > 10 || !this.mAidLogCountMap.containsKey(str)) {
            int logCountFromDbAid = getLogCountFromDbAid(str);
            this.mAidLogCountMap.put(str, Integer.valueOf(logCountFromDbAid));
            this.mFastReadSampleTimes = 0;
            iIntValue = logCountFromDbAid;
        } else {
            iIntValue = this.mAidLogCountMap.get(str).intValue();
            this.mFastReadSampleTimes++;
        }
        return iIntValue;
    }

    public List<LocalLog> getLogsLimit(int i, int i2) {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.mDb.query(DBHelper.T_LOCAL_LOG, SAMPLE_LOG_COLS, CLAUSE_AID_EQUAL, new String[]{String.valueOf(i)}, null, null, ORDER_BY_ID_ASC, i2 + "");
            try {
                if (cursorQuery.getCount() == 0) {
                    safeCloseCursor(cursorQuery);
                    return null;
                }
                LinkedList linkedList = new LinkedList();
                while (cursorQuery.moveToNext()) {
                    linkedList.add(new LocalLog(cursorQuery.getLong(cursorQuery.getColumnIndex(DBHelper.COL_ID)), cursorQuery.getString(cursorQuery.getColumnIndex("data"))));
                }
                safeCloseCursor(cursorQuery);
                return linkedList;
            } catch (Throwable unused) {
                cursor = cursorQuery;
                safeCloseCursor(cursor);
                return Collections.emptyList();
            }
        } catch (Throwable unused2) {
        }
    }

    public synchronized void insertLocalLogBatch(String str, List<LocalLog> list) {
        SQLiteDatabase sQLiteDatabase;
        if (this.mDb != null && !ListUtils.isEmpty(list)) {
            weedOutLogIfNeed();
            this.mDb.beginTransaction();
            try {
                try {
                    SQLiteStatement sQLiteStatementCompileStatement = this.mDb.compileStatement(this.sql);
                    for (LocalLog localLog : list) {
                        sQLiteStatementCompileStatement.bindString(1, String.valueOf(localLog.aid));
                        String str2 = localLog.type;
                        if (str2 == null) {
                            str2 = "";
                        }
                        sQLiteStatementCompileStatement.bindString(2, str2);
                        String str3 = localLog.type2;
                        if (str3 == null) {
                            str3 = "";
                        }
                        sQLiteStatementCompileStatement.bindString(3, str3);
                        sQLiteStatementCompileStatement.bindLong(4, localLog.createTime);
                        String str4 = localLog.data;
                        if (str4 == null) {
                            str4 = "";
                        }
                        sQLiteStatementCompileStatement.bindString(5, str4);
                        sQLiteStatementCompileStatement.executeInsert();
                    }
                    this.mDb.setTransactionSuccessful();
                    addLogCountBuffer(str, list.size());
                    sQLiteDatabase = this.mDb;
                } catch (Exception e) {
                    e.printStackTrace();
                    sQLiteDatabase = this.mDb;
                }
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                this.mDb.endTransaction();
                throw th;
            }
        }
    }

    synchronized boolean isOpen() {
        return true;
    }

    public synchronized void weedOutOldLogs(long j) {
        if (this.mDb == null || j <= 0) {
            return;
        }
        try {
            this.mDb.execSQL(" DELETE FROM monitor_log WHERE _id IN (SELECT _id FROM monitor_log ORDER BY _id ASC LIMIT " + j + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
