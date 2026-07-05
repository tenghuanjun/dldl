package com.nirvana.tools.logger.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.nirvana.tools.logger.model.ACMRecord;
import com.nirvana.tools.logger.utils.ConsoleLogUtils;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public abstract class AbstractDatabase<T extends ACMRecord> {
    public static final int DEFAULT_LIMIT = 5242880;
    private static final String TAG = AbstractDatabase.class.getName();
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    protected String mTableName;
    private Semaphore semaphore = new Semaphore(1);

    public AbstractDatabase(String str, DBHelper dBHelper) {
        this.mTableName = str;
        this.mDbHelper = dBHelper;
        setMaxSizeLog(5242880L);
    }

    private <G> void numberList2StringArray(List<G> list, String[] strArr) {
        if (list.size() == strArr.length) {
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = String.valueOf(list.get(i));
            }
            return;
        }
        Log.e(TAG, "NumberList size(" + list.size() + ") not equals results length[" + strArr.length + "]");
    }

    private long parseIdFromCursor(Cursor cursor) {
        if (cursor == null) {
            return -1L;
        }
        return cursor.getLong(cursor.getColumnIndex(DBDefinition.ID));
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
            this.mDatabase = null;
        }
    }

    protected String contactIds(long j) {
        if (j <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("(");
        do {
            sb.append("?,");
            j--;
        } while (j > 0);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    protected synchronized boolean deleteOldest(SQLiteDatabase sQLiteDatabase, int i) throws DbException {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Cursor cursorQuery = sQLiteDatabase.query(this.mTableName, new String[]{DBDefinition.ID}, null, null, null, null, "timestamp ASC", i > 0 ? String.valueOf(i) : null);
            ArrayList arrayList = new ArrayList();
            while (cursorQuery.moveToNext()) {
                Long lValueOf = Long.valueOf(parseIdFromCursor(cursorQuery));
                if (lValueOf.longValue() != -1) {
                    arrayList.add(lValueOf);
                }
            }
            cursorQuery.close();
            ConsoleLogUtils.logcatV(TAG, "delete oldest: escape=" + (System.currentTimeMillis() - jCurrentTimeMillis));
            if (!arrayList.isEmpty()) {
                return deleteRecordsById(sQLiteDatabase, arrayList);
            }
        } catch (Exception e) {
            new DbException("Delete oldest exception!", e);
        }
        return false;
    }

    public synchronized boolean deleteRecords(List<T> list) throws DbException {
        if (list != null) {
            try {
                try {
                    if (!list.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(Long.valueOf(it.next().getId()));
                        }
                        return deleteRecordsById(getWriteDatabase(), arrayList);
                    }
                } catch (DbException e) {
                    throw e;
                }
            } finally {
                close();
            }
            close();
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized boolean deleteRecordsById(SQLiteDatabase sQLiteDatabase, List<Long> list) throws DbException {
        if (list != 0) {
            try {
                if (!list.isEmpty()) {
                    ConsoleLogUtils.logcatV(TAG, "delete: size=" + list.size());
                    StringBuilder sb = new StringBuilder("_id in ");
                    sb.append(contactIds((long) list.size()));
                    ConsoleLogUtils.logcatV(TAG, "delete: selection=" + ((Object) sb));
                    String[] strArr = new String[list.size()];
                    numberList2StringArray(list, strArr);
                    int iDelete = sQLiteDatabase.delete(this.mTableName, sb.toString(), strArr);
                    ConsoleLogUtils.logcatV(TAG, "delete: count=" + iDelete);
                    return iDelete == list.size();
                }
            } catch (Exception e) {
                throw new DbException("Delete records failed!", e);
            }
        }
        return true;
    }

    protected abstract ContentValues getContentValuesByRecord(T t);

    protected int getCount(SQLiteDatabase sQLiteDatabase) {
        return (int) DatabaseUtils.longForQuery(sQLiteDatabase, String.format("SELECT COUNT(%s) FROM %s", DBDefinition.ID, this.mTableName), null);
    }

    public synchronized long getCurrentSize() {
        long pageSize;
        try {
            pageSize = getReadDatabase().getPageSize() * DatabaseUtils.longForQuery(this.mDatabase, "PRAGMA page_count;", null);
            close();
        } catch (Throwable unused) {
            close();
            return -1L;
        }
        return pageSize;
    }

    public synchronized long getMaxSizeLog() {
        long maximumSize;
        try {
            maximumSize = getReadDatabase().getMaximumSize();
            close();
        } catch (Throwable unused) {
            close();
            return -1L;
        }
        return maximumSize;
    }

    public SQLiteDatabase getReadDatabase() {
        if (this.mDatabase == null) {
            this.mDatabase = this.mDbHelper.getReadableDatabase();
        }
        return this.mDatabase;
    }

    public synchronized SQLiteDatabase getWriteDatabase() {
        if (this.mDatabase == null) {
            this.mDatabase = this.mDbHelper.getWritableDatabase();
        }
        return this.mDatabase;
    }

    public synchronized boolean insert(T t) throws DbException {
        long jInsert = -1;
        if (t == null) {
            close();
            return false;
        }
        try {
            try {
                if (getCurrentSize() >= 5242880) {
                    ConsoleLogUtils.logcatV(TAG, "Table size is limited, clear half of data!");
                    deleteOldest(getWriteDatabase(), getCount(getWriteDatabase()) / 2);
                }
                ContentValues contentValuesByRecord = getContentValuesByRecord(t);
                jInsert = getWriteDatabase().insert(this.mTableName, null, contentValuesByRecord);
                if (jInsert < 0 && getCount(getWriteDatabase()) > 0) {
                    deleteOldest(getWriteDatabase(), getCount(getWriteDatabase()) / 2);
                    jInsert = getWriteDatabase().insert(this.mTableName, null, contentValuesByRecord);
                }
                ConsoleLogUtils.logcatV(TAG, "insert: id=" + jInsert);
                close();
                return jInsert >= 0;
            } catch (Exception e) {
                throw new DbException("Insert record failed!", e);
            }
        } catch (Throwable unused) {
            close();
            return jInsert >= 0;
        }
    }

    public synchronized boolean insertList(List<T> list) throws DbException {
        boolean z = true;
        if (list != null) {
            long jInsert = -1;
            try {
                try {
                    if (list.size() != 0) {
                        if (getCurrentSize() >= 5242880) {
                            ConsoleLogUtils.logcatV(TAG, "Table size is limited, clear half of data!");
                            deleteOldest(getWriteDatabase(), getCount(getWriteDatabase()) / 2);
                        }
                        this.semaphore.acquire();
                        ConsoleLogUtils.logcatV(TAG, "beginTransaction");
                        getWriteDatabase().beginTransaction();
                        String str = TAG;
                        StringBuilder sb = new StringBuilder("writedatabase success  ");
                        sb.append(this.mDatabase == null);
                        ConsoleLogUtils.logcatV(str, sb.toString());
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            ContentValues contentValuesByRecord = getContentValuesByRecord(it.next());
                            ConsoleLogUtils.logcatV(TAG, "ContentValues");
                            jInsert = getWriteDatabase().insert(this.mTableName, null, contentValuesByRecord);
                            ConsoleLogUtils.logcatV(TAG, "insert");
                            if (jInsert < 0 && getCount(getWriteDatabase()) > 0) {
                                deleteOldest(getWriteDatabase(), getCount(getWriteDatabase()) / 2);
                                jInsert = getWriteDatabase().insert(this.mTableName, null, contentValuesByRecord);
                            }
                            ConsoleLogUtils.logcatV(TAG, "insert: id=" + jInsert);
                        }
                        getWriteDatabase().setTransactionSuccessful();
                        String str2 = TAG;
                        StringBuilder sb2 = new StringBuilder("final ");
                        sb2.append(getWriteDatabase() == null);
                        ConsoleLogUtils.logcatV(str2, sb2.toString());
                        this.mDatabase.endTransaction();
                        this.semaphore.release();
                        close();
                        return jInsert >= 0;
                    }
                } catch (Exception e) {
                    throw new DbException("Insert record failed!", e);
                }
            } catch (Throwable unused) {
                String str3 = TAG;
                StringBuilder sb3 = new StringBuilder("final ");
                sb3.append(getWriteDatabase() == null);
                ConsoleLogUtils.logcatV(str3, sb3.toString());
                this.mDatabase.endTransaction();
                this.semaphore.release();
                close();
                return jInsert >= 0;
            }
        }
        String str4 = TAG;
        StringBuilder sb4 = new StringBuilder("final ");
        if (getWriteDatabase() != null) {
            z = false;
        }
        sb4.append(z);
        ConsoleLogUtils.logcatV(str4, sb4.toString());
        this.mDatabase.endTransaction();
        this.semaphore.release();
        close();
        return false;
    }

    protected abstract T parseDataFromCursor(Cursor cursor);

    public synchronized List<T> query(int i, int i2, String str) {
        String str2;
        String[] strArr;
        ArrayList arrayList;
        if (i2 >= 0) {
            try {
                str2 = "upload_flag = ?";
                strArr = new String[]{String.valueOf(i2)};
            } catch (Throwable unused) {
                close();
                return null;
            }
        } else {
            str2 = null;
            strArr = null;
        }
        ConsoleLogUtils.logcatV(TAG, "query: selection=" + str2);
        String strValueOf = i > 0 ? String.valueOf(i) : "";
        arrayList = new ArrayList();
        Cursor cursorQuery = getReadDatabase().query(this.mTableName, null, str2, strArr, null, null, str, strValueOf);
        while (cursorQuery.moveToNext()) {
            ACMRecord dataFromCursor = parseDataFromCursor(cursorQuery);
            if (dataFromCursor != null) {
                arrayList.add(dataFromCursor);
            }
        }
        cursorQuery.close();
        ConsoleLogUtils.logcatV(TAG, "query: result=" + arrayList + ", size=" + arrayList.size());
        close();
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0078 A[Catch: all -> 0x00d5, TryCatch #0 {all -> 0x00d5, blocks: (B:4:0x0002, B:10:0x0026, B:11:0x003b, B:17:0x0063, B:19:0x0078, B:20:0x007c, B:21:0x009e, B:23:0x00a4, B:25:0x00aa, B:26:0x00ae, B:13:0x0041, B:16:0x0054), top: B:37:0x0002, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a4 A[Catch: all -> 0x00d5, TryCatch #0 {all -> 0x00d5, blocks: (B:4:0x0002, B:10:0x0026, B:11:0x003b, B:17:0x0063, B:19:0x0078, B:20:0x007c, B:21:0x009e, B:23:0x00a4, B:25:0x00aa, B:26:0x00ae, B:13:0x0041, B:16:0x0054), top: B:37:0x0002, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.util.List<T> queryFailed(long r14, long r16, int r18) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nirvana.tools.logger.cache.db.AbstractDatabase.queryFailed(long, long, int):java.util.List");
    }

    public synchronized long queryFailedMaxId() {
        long j;
        try {
            Cursor cursorQuery = getReadDatabase().query(true, this.mTableName, new String[]{DBDefinition.ID}, "upload_flag=?", new String[]{"1"}, null, null, "_id desc", null);
            cursorQuery.moveToFirst();
            j = cursorQuery.getLong(0);
            cursorQuery.close();
            close();
        } catch (Throwable unused) {
            close();
            return -1L;
        }
        return j;
    }

    public synchronized void setMaxSizeLog(long j) {
        try {
            try {
                getWriteDatabase().setMaximumSize(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            close();
        }
    }

    public synchronized void updateUploadCount(List<T> list, long j, int i) throws DbException {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Long.valueOf(it.next().getId()));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(String.valueOf(j));
                    arrayList2.add(String.valueOf(i));
                    arrayList2.add("1");
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(String.valueOf((Long) it2.next()));
                    }
                    String[] strArr = new String[arrayList2.size()];
                    String str = String.format("Update %s SET %s=?, %s=?, %s= %s+? where %s in %s", this.mTableName, "timestamp", DBHelpTool.RecordEntry.COLUMN_UPLOAD_FLAG, DBHelpTool.RecordEntry.COLUMN_UPLOAD_COUNT, DBHelpTool.RecordEntry.COLUMN_UPLOAD_COUNT, DBDefinition.ID, contactIds(arrayList.size()));
                    arrayList2.toArray(strArr);
                    getWriteDatabase().execSQL(str, strArr);
                    return;
                }
            } finally {
            }
        }
    }
}
