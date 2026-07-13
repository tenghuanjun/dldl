package com.lzy.okgo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import com.lzy.okgo.utils.OkLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class BaseDao<T> {
    protected static String TAG;
    protected SQLiteDatabase database;
    protected SQLiteOpenHelper helper;
    protected Lock lock;

    public interface Action {
        void call(SQLiteDatabase sQLiteDatabase);
    }

    public abstract ContentValues getContentValues(T t);

    public abstract String getTableName();

    public abstract T parseCursorToBean(Cursor cursor);

    public abstract void unInit();

    public BaseDao(SQLiteOpenHelper sQLiteOpenHelper) {
        TAG = getClass().getSimpleName();
        this.lock = DBHelper.lock;
        this.helper = sQLiteOpenHelper;
        this.database = openWriter();
    }

    public SQLiteDatabase openReader() {
        return this.helper.getReadableDatabase();
    }

    public SQLiteDatabase openWriter() {
        return this.helper.getWritableDatabase();
    }

    protected final void closeDatabase(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        sQLiteDatabase.close();
    }

    public boolean insert(T t) {
        if (t == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                this.database.insert(getTableName(), null, getContentValues(t));
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " insertT");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " insertT");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " insertT");
            throw th;
        }
    }

    public long insert(SQLiteDatabase sQLiteDatabase, T t) {
        return sQLiteDatabase.insert(getTableName(), null, getContentValues(t));
    }

    public boolean insert(List<T> list) {
        if (list == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    this.database.insert(getTableName(), null, getContentValues(it.next()));
                }
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " insertList");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " insertList");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " insertList");
            throw th;
        }
    }

    public boolean insert(SQLiteDatabase sQLiteDatabase, List<T> list) {
        try {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                sQLiteDatabase.insert(getTableName(), null, getContentValues(it.next()));
            }
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        }
    }

    public boolean deleteAll() {
        return delete(null, null);
    }

    public long deleteAll(SQLiteDatabase sQLiteDatabase) {
        return delete(sQLiteDatabase, null, null);
    }

    public boolean delete(String str, String[] strArr) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                this.database.delete(getTableName(), str, strArr);
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " delete");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " delete");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " delete");
            throw th;
        }
    }

    public long delete(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        return sQLiteDatabase.delete(getTableName(), str, strArr);
    }

    public boolean deleteList(List<Pair<String, String[]>> list) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                for (Pair<String, String[]> pair : list) {
                    this.database.delete(getTableName(), (String) pair.first, (String[]) pair.second);
                }
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " deleteList");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " deleteList");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " deleteList");
            throw th;
        }
    }

    public boolean replace(T t) {
        if (t == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                this.database.replace(getTableName(), null, getContentValues(t));
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceT");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceT");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceT");
            throw th;
        }
    }

    public long replace(SQLiteDatabase sQLiteDatabase, T t) {
        return sQLiteDatabase.replace(getTableName(), null, getContentValues(t));
    }

    public boolean replace(ContentValues contentValues) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                this.database.replace(getTableName(), null, contentValues);
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceContentValues");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceContentValues");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceContentValues");
            throw th;
        }
    }

    public long replace(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
        return sQLiteDatabase.replace(getTableName(), null, contentValues);
    }

    public boolean replace(List<T> list) {
        if (list == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    this.database.replace(getTableName(), null, getContentValues(it.next()));
                }
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceList");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceList");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceList");
            throw th;
        }
    }

    public boolean replace(SQLiteDatabase sQLiteDatabase, List<T> list) {
        try {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                sQLiteDatabase.replace(getTableName(), null, getContentValues(it.next()));
            }
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        }
    }

    public boolean update(T t, String str, String[] strArr) {
        if (t == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                this.database.update(getTableName(), getContentValues(t), str, strArr);
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " updateT");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " updateT");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " updateT");
            throw th;
        }
    }

    public long update(SQLiteDatabase sQLiteDatabase, T t, String str, String[] strArr) {
        return sQLiteDatabase.update(getTableName(), getContentValues(t), str, strArr);
    }

    public boolean update(ContentValues contentValues, String str, String[] strArr) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                this.database.update(getTableName(), contentValues, str, strArr);
                this.database.setTransactionSuccessful();
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " updateContentValues");
                return true;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                this.database.endTransaction();
                this.lock.unlock();
                OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " updateContentValues");
                return false;
            }
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " updateContentValues");
            throw th;
        }
    }

    public long update(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, String str, String[] strArr) {
        return sQLiteDatabase.update(getTableName(), contentValues, str, strArr);
    }

    public List<T> queryAll(SQLiteDatabase sQLiteDatabase) {
        return query(sQLiteDatabase, null, null);
    }

    public List<T> query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        return query(sQLiteDatabase, null, str, strArr, null, null, null, null);
    }

    public T queryOne(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) throws Throwable {
        List<T> listQuery = query(sQLiteDatabase, null, str, strArr, null, null, null, "1");
        if (listQuery.size() > 0) {
            return listQuery.get(0);
        }
        return null;
    }

    public List<T> query(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) throws Throwable {
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                cursorQuery = sQLiteDatabase.query(getTableName(), strArr, str, strArr2, str2, str3, str4, str5);
                while (!cursorQuery.isClosed() && cursorQuery.moveToNext()) {
                    try {
                        arrayList.add(parseCursorToBean(cursorQuery));
                    } catch (Exception e) {
                        e = e;
                        OkLogger.printStackTrace(e);
                    }
                }
            } catch (Throwable th) {
                th = th;
                closeDatabase(null, null);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            closeDatabase(null, null);
            throw th;
        }
        closeDatabase(null, cursorQuery);
        return arrayList;
    }

    public List<T> queryAll() {
        return query(null, null);
    }

    public List<T> query(String str, String[] strArr) {
        return query(null, str, strArr, null, null, null, null);
    }

    public T queryOne(String str, String[] strArr) throws Throwable {
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<T> listQuery = query(null, str, strArr, null, null, null, "1");
        OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " queryOne");
        if (listQuery.size() > 0) {
            return listQuery.get(0);
        }
        return null;
    }

    public List<T> query(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) throws Throwable {
        Cursor cursorQuery;
        String str6;
        StringBuilder sb;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.lock.lock();
        ArrayList arrayList = new ArrayList();
        try {
            this.database.beginTransaction();
            cursorQuery = this.database.query(getTableName(), strArr, str, strArr2, str2, str3, str4, str5);
            while (!cursorQuery.isClosed() && cursorQuery.moveToNext()) {
                try {
                    try {
                        arrayList.add(parseCursorToBean(cursorQuery));
                    } catch (Exception e) {
                        e = e;
                        OkLogger.printStackTrace(e);
                        closeDatabase(null, cursorQuery);
                        this.database.endTransaction();
                        this.lock.unlock();
                        str6 = TAG;
                        sb = new StringBuilder();
                    }
                } catch (Throwable th) {
                    th = th;
                    closeDatabase(null, cursorQuery);
                    this.database.endTransaction();
                    this.lock.unlock();
                    OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " query");
                    throw th;
                }
            }
            this.database.setTransactionSuccessful();
            closeDatabase(null, cursorQuery);
            this.database.endTransaction();
            this.lock.unlock();
            str6 = TAG;
            sb = new StringBuilder();
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
            closeDatabase(null, cursorQuery);
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.v(TAG, (System.currentTimeMillis() - jCurrentTimeMillis) + " query");
            throw th;
        }
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        sb.append(" query");
        OkLogger.v(str6, sb.toString());
        return arrayList;
    }

    public void startTransaction(Action action) {
        this.lock.lock();
        try {
            try {
                this.database.beginTransaction();
                action.call(this.database);
                this.database.setTransactionSuccessful();
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
            }
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
        }
    }
}
