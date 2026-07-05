package com.sq.sdk.tool.database;

import android.content.Context;
import android.database.Cursor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseDataProvider {
    public static final String SQL_MATH_MAX = "max(%s) as %s ";
    public static final String SQL_MIN_MAX = "min(%s) as %s ";
    protected BaseDatabaseHelper mDBOpenHelper;
    protected Object mLock = new Object();

    public BaseDataProvider(Context context) {
    }

    public boolean isNewDB() {
        return this.mDBOpenHelper.isNewDB();
    }

    public Cursor rawQuery(String str, String[] strArr) {
        Cursor cursorRawQuery;
        synchronized (this.mLock) {
            cursorRawQuery = this.mDBOpenHelper.rawQuery(str, strArr);
        }
        return cursorRawQuery;
    }

    public boolean execSQL(String... strArr) {
        boolean zExecSQL;
        synchronized (this.mLock) {
            zExecSQL = this.mDBOpenHelper.execSQL(strArr);
        }
        return zExecSQL;
    }

    public boolean execSQL(Object... objArr) {
        boolean zExecSQL;
        synchronized (this.mLock) {
            zExecSQL = this.mDBOpenHelper.execSQL(objArr);
        }
        return zExecSQL;
    }
}
