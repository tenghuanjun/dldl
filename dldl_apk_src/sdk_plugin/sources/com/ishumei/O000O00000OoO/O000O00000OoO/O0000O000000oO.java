package com.ishumei.O000O00000OoO.O000O00000OoO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ishumei.O000O0000OOoO.O000O00000oO;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO extends SQLiteOpenHelper {
    private SQLiteDatabase O0000O000000oO;

    public O0000O000000oO(Context context) {
        super(context, "tracker.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    public synchronized long O0000O000000oO(String str, ContentValues contentValues) {
        long jInsert;
        jInsert = -1;
        try {
            try {
                O0000O000000oO().beginTransaction();
                jInsert = O0000O000000oO().insert(str, null, contentValues);
                O0000O000000oO().setTransactionSuccessful();
                try {
                    O0000O000000oO().endTransaction();
                } catch (Exception e) {
                    e = e;
                    O000O00000oO.O0000O000000oO(e);
                }
            } catch (Exception e2) {
                O000O00000oO.O0000O000000oO(e2);
                try {
                    O0000O000000oO().endTransaction();
                } catch (Exception e3) {
                    e = e3;
                    O000O00000oO.O0000O000000oO(e);
                }
            }
        } finally {
        }
        return jInsert;
    }

    public long O0000O000000oO(String str, String str2, String[] strArr) {
        try {
            return DatabaseUtils.queryNumEntries(O0000O000000oO(), str, str2, strArr);
        } catch (Exception e) {
            O000O00000oO.O0000O000000oO(e);
            return -1L;
        }
    }

    public Cursor O0000O000000oO(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4) {
        try {
            return O0000O000000oO().query(str, strArr, str2, strArr2, null, null, str3, str4);
        } catch (Exception e) {
            O000O00000oO.O0000O000000oO(e);
            return null;
        }
    }

    public SQLiteDatabase O0000O000000oO() {
        if (this.O0000O000000oO == null) {
            synchronized (this) {
                if (this.O0000O000000oO == null) {
                    this.O0000O000000oO = getWritableDatabase();
                }
            }
        }
        return this.O0000O000000oO;
    }

    public synchronized int O000O00000OoO(String str, String str2, String[] strArr) {
        int iDelete;
        iDelete = -1;
        try {
            try {
                O0000O000000oO().beginTransaction();
                iDelete = O0000O000000oO().delete(str, str2, strArr);
                O0000O000000oO().setTransactionSuccessful();
                try {
                    O0000O000000oO().endTransaction();
                } catch (Exception e) {
                    e = e;
                    O000O00000oO.O0000O000000oO(e);
                }
            } catch (Exception e2) {
                O000O00000oO.O0000O000000oO(e2);
                try {
                    O0000O000000oO().endTransaction();
                } catch (Exception e3) {
                    e = e3;
                    O000O00000oO.O0000O000000oO(e);
                }
            }
        } finally {
        }
        return iDelete;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        List<O000O00000o0O> listO000O00000o0O = O000O00000OoO.O0000O000000oO().O000O00000o0O();
        if (listO000O00000o0O == null || listO000O00000o0O.size() == 0) {
            return;
        }
        Iterator<O000O00000o0O> it = listO000O00000o0O.iterator();
        while (it.hasNext()) {
            it.next().O0000O000000oO(sQLiteDatabase);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        List<O000O00000o0O> listO000O00000o0O = O000O00000OoO.O0000O000000oO().O000O00000o0O();
        if (listO000O00000o0O == null || listO000O00000o0O.size() == 0) {
            return;
        }
        Iterator<O000O00000o0O> it = listO000O00000o0O.iterator();
        while (it.hasNext()) {
            it.next().O000O00000OoO(sQLiteDatabase, i, i2);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        List<O000O00000o0O> listO000O00000o0O = O000O00000OoO.O0000O000000oO().O000O00000o0O();
        if (listO000O00000o0O == null || listO000O00000o0O.size() == 0) {
            return;
        }
        Iterator<O000O00000o0O> it = listO000O00000o0O.iterator();
        while (it.hasNext()) {
            it.next().O0000O000000oO(sQLiteDatabase, i, i2);
        }
    }
}
