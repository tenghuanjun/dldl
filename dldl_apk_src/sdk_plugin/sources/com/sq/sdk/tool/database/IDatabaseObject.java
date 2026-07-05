package com.sq.sdk.tool.database;

import android.content.ContentValues;
import android.database.Cursor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IDatabaseObject {
    void readObject(Cursor cursor, String str);

    void writeObject(ContentValues contentValues, String str);
}
