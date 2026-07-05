package com.sy37sdk.account.db;

import android.database.sqlite.SQLiteDatabase;
import com.sqwan.common.util.SQContextWrapper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DBManager {
    private SQLiteDatabase db;
    private DataBaseHelper helper;

    public SQLiteDatabase getDb() {
        if (this.helper == null) {
            this.helper = new DataBaseHelper(SQContextWrapper.getApplicationContext());
        }
        if (this.db == null) {
            this.db = this.helper.getWritableDatabase();
        }
        return this.db;
    }
}
