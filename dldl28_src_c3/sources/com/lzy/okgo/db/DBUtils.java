package com.lzy.okgo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.lzy.okgo.utils.OkLogger;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class DBUtils {
    public static boolean isNeedUpgradeTable(SQLiteDatabase sQLiteDatabase, TableEntity tableEntity) {
        if (!isTableExists(sQLiteDatabase, tableEntity.tableName)) {
            return true;
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + tableEntity.tableName, null);
        if (cursorRawQuery == null) {
            return false;
        }
        try {
            int columnCount = tableEntity.getColumnCount();
            if (columnCount != cursorRawQuery.getColumnCount()) {
                return true;
            }
            for (int i = 0; i < columnCount; i++) {
                if (tableEntity.getColumnIndex(cursorRawQuery.getColumnName(i)) == -1) {
                    return true;
                }
            }
            return false;
        } finally {
            cursorRawQuery.close();
        }
    }

    public static boolean isTableExists(SQLiteDatabase sQLiteDatabase, String str) {
        int i;
        if (str == null || sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return false;
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", str});
                if (!cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return false;
                }
                i = cursorRawQuery.getInt(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                i = 0;
            }
            return i > 0;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public static boolean isFieldExists(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        boolean z = false;
        if (str == null || sQLiteDatabase == null || str2 == null || !sQLiteDatabase.isOpen()) {
            return false;
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
                if (cursorRawQuery != null) {
                    if (cursorRawQuery.getColumnIndex(str2) != -1) {
                        z = true;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return z;
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }
}
