package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alipay.sdk.util.c;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class a extends SQLiteOpenHelper {
    private static final String a = "msp.db";
    private static final int b = 1;
    private WeakReference<Context> c;

    a(Context context) {
        super(context, a, (SQLiteDatabase.CursorFactory) null, 1);
        this.c = new WeakReference<>(context);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
    }

    void a() {
        SQLiteDatabase writableDatabase = null;
        try {
            try {
                writableDatabase = getWritableDatabase();
                writableDatabase.execSQL("drop table if exists tb_tid");
            } catch (Exception e) {
                c.a(e);
                if (writableDatabase != null && writableDatabase.isOpen()) {
                }
            }
            if (writableDatabase != null && writableDatabase.isOpen()) {
                writableDatabase.close();
            }
        } catch (Throwable th) {
            if (writableDatabase != null && writableDatabase.isOpen()) {
                writableDatabase.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.String a(java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.String r0 = "select tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L4c
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            java.lang.String r5 = r4.c(r5, r6)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            boolean r0 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L33
            if (r0 == 0) goto L1f
            java.lang.String r1 = r5.getString(r6)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L33
        L1f:
            if (r5 == 0) goto L24
            r5.close()
        L24:
            if (r2 == 0) goto L5c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L5c
        L2c:
            r2.close()
            goto L5c
        L30:
            r6 = move-exception
            r1 = r5
            goto L3b
        L33:
            goto L4e
        L35:
            r6 = move-exception
            goto L3b
        L37:
            r5 = r1
            goto L4e
        L39:
            r6 = move-exception
            r2 = r1
        L3b:
            if (r1 == 0) goto L40
            r1.close()
        L40:
            if (r2 == 0) goto L4b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L4b
            r2.close()
        L4b:
            throw r6
        L4c:
            r5 = r1
            r2 = r5
        L4e:
            if (r5 == 0) goto L53
            r5.close()
        L53:
            if (r2 == 0) goto L5c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L5c
            goto L2c
        L5c:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L72
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.c
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = com.alipay.sdk.util.a.c(r5)
            java.lang.String r1 = com.alipay.sdk.encrypt.b.b(r1, r5)
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    String b(String str, String str2) throws Throwable {
        SQLiteDatabase readableDatabase;
        Cursor cursorRawQuery;
        Cursor cursor = null;
        string = null;
        string = null;
        String string = null;
        cursor = null;
        try {
            readableDatabase = getReadableDatabase();
            try {
                cursorRawQuery = readableDatabase.rawQuery("select key_tid from tb_tid where name=?", new String[]{c(str, str2)});
                try {
                    string = cursorRawQuery.moveToFirst() ? cursorRawQuery.getString(0) : null;
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (Exception unused) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                    }
                    return string;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorRawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                cursorRawQuery = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused3) {
            cursorRawQuery = null;
            readableDatabase = null;
        } catch (Throwable th3) {
            th = th3;
            readableDatabase = null;
        }
        if (readableDatabase != null && readableDatabase.isOpen()) {
            readableDatabase.close();
        }
        return string;
    }

    private String c(String str, String str2) {
        return str + str2;
    }
}
