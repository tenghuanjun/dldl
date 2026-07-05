package com.ishumei.O000O00000OoO.O000O0000O0oO;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ishumei.O000O0000OOoO.O000O00000oO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000o0O implements com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O {
    @Override // com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O
    public void O0000O000000oO(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS upload_checker(id INTEGER PRIMARY KEY AUTOINCREMENT,upload_data VARCHAR(100000),upload_url VARCHAR(1000))");
        } catch (SQLException e) {
            O000O00000oO.O000O00000oO("UploadDBHelper", "fail to create table: upload_checker:\n" + Log.getStackTraceString(e));
        }
    }

    @Override // com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O
    public void O0000O000000oO(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000o0O
    public void O000O00000OoO(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
