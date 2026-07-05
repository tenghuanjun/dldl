package com.sq.sdk.tool.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DatabaseUtils {
    public static final String TYPE_BLOB = "blob";
    public static final String TYPE_NUMERIC = "numeric";
    public static final String TYPE_TEXT = "text";

    public static boolean isExistTable(SQLiteDatabase sQLiteDatabase, String str) {
        String str2 = "type='table' and name='" + str + "'";
        boolean z = false;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabase.query("sqlite_master", null, str2, null, null, null, null);
                if (cursorQuery != null) {
                    if (cursorQuery.getCount() > 0) {
                        z = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursorQuery != null) {
                }
            }
            return z;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isExistColumnInTable(android.database.sqlite.SQLiteDatabase r11, java.lang.String r12, java.lang.String r13) {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            r5[r1] = r13     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r11
            r4 = r12
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            if (r2 == 0) goto L1b
            int r11 = r2.getColumnIndex(r13)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            if (r11 < 0) goto L1b
            goto L1c
        L1b:
            r0 = 0
        L1c:
            if (r2 == 0) goto L21
            r2.close()
        L21:
            r1 = r0
            goto L2e
        L23:
            r11 = move-exception
            goto L2f
        L25:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L23
            if (r2 == 0) goto L2e
            r2.close()
        L2e:
            return r1
        L2f:
            if (r2 == 0) goto L34
            r2.close()
        L34:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.sdk.tool.database.DatabaseUtils.isExistColumnInTable(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }

    public static void addColumnToTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        if (isExistColumnInTable(sQLiteDatabase, str, str2)) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD " + str2 + " " + str3);
                    if (str4 != null) {
                        if (str3.equals("text")) {
                            str4 = "'" + str4 + "'";
                        }
                        sQLiteDatabase.execSQL("update " + str + " set " + str2 + " = " + str4);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (sQLiteDatabase == null) {
                        return;
                    } else {
                        sQLiteDatabase.endTransaction();
                    }
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static boolean execSQL(SQLiteOpenHelper sQLiteOpenHelper, String... strArr) {
        return execSQL(sQLiteOpenHelper.getWritableDatabase(), strArr);
    }

    public static boolean execSQL(SQLiteDatabase sQLiteDatabase, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        try {
            try {
                sQLiteDatabase.beginTransaction();
                for (String str : strArr) {
                    sQLiteDatabase.execSQL(str);
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                sQLiteDatabase.endTransaction();
                return false;
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    public static boolean execSQL(SQLiteOpenHelper sQLiteOpenHelper, Object... objArr) {
        return execSQL(sQLiteOpenHelper.getWritableDatabase(), objArr);
    }

    public static boolean execSQL(SQLiteDatabase sQLiteDatabase, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return false;
        }
        try {
            try {
                sQLiteDatabase.beginTransaction();
                for (Object obj : objArr) {
                    if (obj != null) {
                        if (obj instanceof String) {
                            sQLiteDatabase.execSQL(obj.toString());
                        } else if (obj instanceof UpdateParams) {
                            UpdateParams updateParams = (UpdateParams) obj;
                            sQLiteDatabase.update(updateParams.table, updateParams.contentValues, updateParams.where, null);
                        } else if (obj instanceof InsertParams) {
                            InsertParams insertParams = (InsertParams) obj;
                            sQLiteDatabase.insert(insertParams.table, null, insertParams.contentValues);
                        }
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                sQLiteDatabase.endTransaction();
                return false;
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    public static Cursor rawQuery(SQLiteOpenHelper sQLiteOpenHelper, String str, String[] strArr) {
        try {
            return sQLiteOpenHelper.getReadableDatabase().rawQuery(str, strArr);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static class UpdateParams {
        public ContentValues contentValues;
        public String table;
        public String where;

        public UpdateParams(ContentValues contentValues, String str, String str2) {
            this.contentValues = contentValues;
            this.where = str;
            this.table = str2;
        }

        public String toString() {
            return "UpdateParams[" + this.table + ", " + this.contentValues + ", " + this.where + "]";
        }
    }

    public static class InsertParams {
        public ContentValues contentValues;
        public String table;

        public InsertParams(ContentValues contentValues, String str) {
            this.contentValues = contentValues;
            this.table = str;
        }

        public String toString() {
            return "InsertParams[" + this.table + ", " + this.contentValues + "]";
        }
    }
}
