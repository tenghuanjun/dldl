package com.bytedance.bdtracker;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.applog.log.IAppLogLogger;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class n3 extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f297a;

    public n3(c0 c0Var, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(c0Var.b(), str, cursorFactory, i);
        this.f297a = c0Var;
    }

    public void a(Throwable th) {
        z1 z1Var = this.f297a.q;
        if (z1Var == null) {
            return;
        }
        ((d2) z1Var).a(new j2(th));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            Iterator<j3> it = j3.j().values().iterator();
            while (it.hasNext()) {
                String strA = it.next().a();
                if (strA != null) {
                    sQLiteDatabase.execSQL(strA);
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        IAppLogLogger iAppLogLogger = this.f297a.d.D;
        Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2)};
        iAppLogLogger.debug(5, "Database upgrade from:{} to:{}", objArr);
        try {
            sQLiteDatabase.beginTransaction();
            Iterator<j3> it = j3.j().values().iterator();
            while (it.hasNext()) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + it.next().f());
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            try {
            } catch (Throwable th) {
            }
        }
        n0.a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }
}
