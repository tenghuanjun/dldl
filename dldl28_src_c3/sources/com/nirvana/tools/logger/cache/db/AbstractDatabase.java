package com.nirvana.tools.logger.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.nirvana.tools.logger.model.ACMRecord;
import com.nirvana.tools.logger.utils.ConsoleLogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class AbstractDatabase<T extends ACMRecord> {
    public static final int DEFAULT_LIMIT = 5242880;
    private static final String TAG = "com.nirvana.tools.logger.cache.db.AbstractDatabase";
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
        return cursor.getLong(cursor.getColumnIndex("_id"));
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
            Cursor cursorQuery = sQLiteDatabase.query(this.mTableName, new String[]{"_id"}, null, null, null, null, "timestamp ASC", i > 0 ? String.valueOf(i) : null);
            ArrayList arrayList = new ArrayList();
            while (cursorQuery.moveToNext()) {
                long idFromCursor = parseIdFromCursor(cursorQuery);
                Long lValueOf = Long.valueOf(idFromCursor);
                lValueOf.getClass();
                if (idFromCursor != -1) {
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
                    String str = TAG;
                    ConsoleLogUtils.logcatV(str, "delete: size=" + list.size());
                    StringBuilder sb = new StringBuilder("_id in ");
                    sb.append(contactIds((long) list.size()));
                    ConsoleLogUtils.logcatV(str, "delete: selection=" + ((Object) sb));
                    String[] strArr = new String[list.size()];
                    numberList2StringArray(list, strArr);
                    int iDelete = sQLiteDatabase.delete(this.mTableName, sb.toString(), strArr);
                    ConsoleLogUtils.logcatV(str, "delete: count=" + iDelete);
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
        return (int) DatabaseUtils.longForQuery(sQLiteDatabase, String.format("SELECT COUNT(%s) FROM %s", "_id", this.mTableName), null);
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
        if (t == null) {
            close();
            return false;
        }
        long jInsert = -1;
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

    /* JADX WARN: Removed duplicated region for block: B:41:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0133 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0134 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean insertList(java.util.List<T> r12) throws com.nirvana.tools.logger.cache.db.DbException {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nirvana.tools.logger.cache.db.AbstractDatabase.insertList(java.util.List):boolean");
    }

    protected abstract T parseDataFromCursor(Cursor cursor);

    public synchronized List<T> query(int i, int i2, String str) {
        String[] strArr;
        String str2;
        ArrayList arrayList;
        if (i2 >= 0) {
            try {
                strArr = new String[]{String.valueOf(i2)};
                str2 = "upload_flag = ?";
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

    public synchronized List<T> queryFailed(long j, long j2, int i) {
        ArrayList arrayList;
        try {
            StringBuilder sb = new StringBuilder("upload_flag = ?");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("1");
            if (j >= 0 && j2 >= 0 && j2 >= j) {
                arrayList2.add(String.valueOf(j));
                arrayList2.add(String.valueOf(j2));
                sb.append(" and _id between ? and ?");
            } else if (j >= 0) {
                arrayList2.add(String.valueOf(j));
                sb.append(" and _id >= ?");
            } else if (j2 >= 0) {
                arrayList2.add(String.valueOf(j2));
                sb.append(" and _id <= ?");
            }
            ConsoleLogUtils.logcatV(TAG, "query: selection=" + ((Object) sb));
            String strValueOf = i > 0 ? String.valueOf(i) : "";
            String[] strArr = new String[arrayList2.size()];
            arrayList2.toArray(strArr);
            arrayList = new ArrayList();
            Cursor cursorQuery = getReadDatabase().query(this.mTableName, null, sb.toString(), strArr, null, null, "_id ASC", strValueOf);
            while (cursorQuery.moveToNext()) {
                ACMRecord dataFromCursor = parseDataFromCursor(cursorQuery);
                if (dataFromCursor != null) {
                    arrayList.add(dataFromCursor);
                }
            }
            cursorQuery.close();
            ConsoleLogUtils.logcatV(TAG, "query: result=" + arrayList + ", size=" + arrayList.size());
            close();
        } catch (Throwable unused) {
            close();
            return null;
        }
        return arrayList;
    }

    public synchronized long queryFailedMaxId() {
        long j;
        try {
            Cursor cursorQuery = getReadDatabase().query(true, this.mTableName, new String[]{"_id"}, "upload_flag=?", new String[]{"1"}, null, null, "_id desc", null);
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
                    String str = String.format("Update %s SET %s=?, %s=?, %s= %s+? where %s in %s", this.mTableName, DBHelpTool.RecordEntry.COLUMN_NAME_TIMESTAMP, DBHelpTool.RecordEntry.COLUMN_UPLOAD_FLAG, DBHelpTool.RecordEntry.COLUMN_UPLOAD_COUNT, DBHelpTool.RecordEntry.COLUMN_UPLOAD_COUNT, "_id", contactIds(arrayList.size()));
                    arrayList2.toArray(strArr);
                    getWriteDatabase().execSQL(str, strArr);
                    return;
                }
            } finally {
            }
        }
    }
}
