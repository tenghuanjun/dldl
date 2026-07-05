package com.ishumei.O000O00000OoO.O000O00000oO;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000o0O implements com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O {
    @Override // com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O
    public void O0000O000000oO(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS net_error_t(t VARCHAR(20),ex VARCHAR(10000),net VARCHAR(100),url VARCHAR(1000),dns VARCHAR(200))");
        } catch (SQLException e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("NetworkExceptionDBHelper", "fail to create table: net_error_t:\n" + Log.getStackTraceString(e));
        }
    }

    @Override // com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O
    public void O0000O000000oO(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 2) {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS net_error_t(t VARCHAR(20),ex VARCHAR(10000),net VARCHAR(100),url VARCHAR(1000),dns VARCHAR(200))");
            } catch (SQLException e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("NetworkExceptionDBHelper", "fail to create table: net_error_t:\n" + Log.getStackTraceString(e));
            }
        }
    }

    @Override // com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O
    public void O000O00000OoO(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
