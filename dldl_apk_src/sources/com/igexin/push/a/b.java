package com.igexin.push.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.igexin.push.core.e.e;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends SQLiteOpenHelper {
    private static final String a = "DownDBHelper";
    private static final String b = "pushsdk.db";
    private static final int c = 5;
    private static final String d = "create table if not exists config (id integer primary key,value text)";
    private static final String e = "create table if not exists runtime (id integer primary key,value text)";
    private static final String f = "create table if not exists ral (id integer primary key,data text,type integer,time integer,send_times integer)";
    private static final String g = "create table if not exists ca (pkgname text primary key,signature text,permissions text, accesstoken blob, expire integer)";
    private static final String h = "create table if not exists config (key integer primary key, value text)";
    private static final String i = "create table if not exists bi(id integer primary key autoincrement, start_service_count integer, login_count integer, loginerror_nonetwork_count integer, loginerror_timeout_count integer, loginerror_connecterror_count integer, loginerror_other_count integer, online_time long, network_time long, running_time long, create_time text, type integer)";
    private static final String j = "create table if not exists message (id integer primary key autoincrement,messageid text,taskid text,appid text,info text,msgextra blob,key text,status integer,createtime integer)";
    private static final String k = "drop table if exists config";
    private static final String l = "drop table if exists runtime";
    private static final String m = "drop table if exists ral";
    private static final String n = "drop table if exists ca";
    private static final String o = "drop table if exists bi";
    private static final String p = "drop table if exists message";
    private static final String q = "drop table if exists st";
    private static final String r = "drop table if exists config";
    private SQLiteDatabase s;

    public b(Context context) {
        super(context, "pushsdk.db", (SQLiteDatabase.CursorFactory) null, 5);
        this.s = null;
    }

    private b(Context context, String str, int i2) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i2);
        this.s = null;
    }

    private Cursor a(String str, String[] strArr) {
        this.s = getReadableDatabase();
        try {
            return this.s.rawQuery(str, strArr);
        } catch (Exception unused) {
            return null;
        }
    }

    private Cursor a(String str, String[] strArr, String str2) {
        try {
            this.s = getReadableDatabase();
            return this.s.query(str, strArr, str2, null, null, null, null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String a(String[] strArr, String[] strArr2, int i2) {
        StringBuilder sb = new StringBuilder(" ");
        if (strArr.length == 1) {
            for (int i3 = 0; i3 < i2; i3++) {
                sb.append(strArr[0]);
                sb.append(" = '");
                sb.append(strArr2[i3]);
                sb.append("'");
                if (i3 < i2 - 1) {
                    sb.append(" or ");
                }
            }
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                sb.append(strArr[i4]);
                sb.append(" = '");
                sb.append(strArr2[i4]);
                sb.append("'");
                if (i4 < i2 - 1) {
                    sb.append(" and ");
                }
            }
        }
        return sb.toString();
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                if (sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            } catch (Exception unused) {
            }
        }
    }

    private void a(String str, String str2, ContentValues contentValues) {
        this.s = getWritableDatabase();
        try {
            this.s.replace(str, str2, contentValues);
        } catch (Exception unused) {
        }
    }

    private boolean a(String str) {
        this.s = getWritableDatabase();
        this.s.beginTransaction();
        try {
            this.s.execSQL(str);
            this.s.setTransactionSuccessful();
            try {
                this.s.endTransaction();
                return true;
            } catch (Throwable unused) {
                return true;
            }
        } catch (Exception unused2) {
            try {
                this.s.endTransaction();
            } catch (Throwable unused3) {
            }
            return false;
        } finally {
            try {
                this.s.endTransaction();
            } catch (Throwable unused4) {
            }
        }
    }

    private static String b(String str, String str2) {
        return "delete from " + str + " where " + str2;
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.endTransaction();
        } catch (Throwable unused) {
        }
    }

    public final int a(String str, String str2) {
        int iDelete;
        this.s = getWritableDatabase();
        this.s.beginTransaction();
        try {
            try {
                iDelete = this.s.delete(str, str2, null);
                try {
                    com.igexin.b.a.c.a.a("DownDBHelper|del " + iDelete + " msg", new Object[0]);
                    this.s.setTransactionSuccessful();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                iDelete = 0;
            }
            try {
                this.s.endTransaction();
            } catch (Throwable unused3) {
            }
            return iDelete;
        } finally {
            try {
                this.s.endTransaction();
            } catch (Throwable unused4) {
            }
        }
    }

    public final Cursor a(String str, String[] strArr, String[] strArr2, String str2) {
        Cursor cursorQuery;
        SQLiteDatabase sQLiteDatabase;
        String[] strArr3;
        String strA;
        this.s = getReadableDatabase();
        this.s.beginTransaction();
        try {
            try {
                if (strArr == null) {
                    cursorQuery = this.s.query(str, null, null, null, null, null, str2);
                } else {
                    if (strArr.length != 1) {
                        sQLiteDatabase = this.s;
                        strArr3 = null;
                        strA = a(strArr, strArr2, strArr.length);
                    } else if (strArr2.length == 1) {
                        cursorQuery = this.s.query(str, null, strArr[0] + "= ?", strArr2, null, null, str2);
                    } else {
                        sQLiteDatabase = this.s;
                        strArr3 = null;
                        strA = a(strArr, strArr2, strArr2.length);
                    }
                    cursorQuery = sQLiteDatabase.query(str, strArr3, strA, null, null, null, str2);
                }
                try {
                    this.s.setTransactionSuccessful();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                cursorQuery = null;
            }
            try {
                this.s.endTransaction();
            } catch (Throwable unused3) {
            }
            return cursorQuery;
        } finally {
            try {
                this.s.endTransaction();
            } catch (Throwable unused4) {
            }
        }
    }

    public final void a(String str, ContentValues contentValues, String[] strArr, String[] strArr2) {
        this.s = getWritableDatabase();
        this.s.beginTransaction();
        try {
            this.s.update(str, contentValues, strArr[0] + "='" + strArr2[0] + "'", null);
            this.s.setTransactionSuccessful();
            try {
                this.s.endTransaction();
            } catch (Throwable unused) {
            }
        } catch (Exception unused2) {
            try {
                this.s.endTransaction();
            } catch (Throwable unused3) {
            }
        } finally {
            try {
                this.s.endTransaction();
            } catch (Throwable unused4) {
            }
        }
    }

    public final void a(String str, String[] strArr, String[] strArr2) {
        this.s = getWritableDatabase();
        this.s.beginTransaction();
        try {
            if (strArr2.length == 1) {
                com.igexin.b.a.c.a.a("DownDBHelper|del " + str + " cnt = " + this.s.delete(str, strArr[0] + " = ?", strArr2), new Object[0]);
            } else {
                this.s.execSQL(b(str, a(strArr, strArr2, strArr2.length)));
            }
            this.s.setTransactionSuccessful();
            try {
                this.s.endTransaction();
            } catch (Throwable unused) {
            }
        } catch (Exception unused2) {
            try {
                this.s.endTransaction();
            } catch (Throwable unused3) {
            }
        } finally {
            try {
                this.s.endTransaction();
            } catch (Throwable unused4) {
            }
        }
    }

    public final boolean a(String str, ContentValues contentValues) {
        boolean z;
        this.s = getWritableDatabase();
        this.s.beginTransaction();
        try {
            this.s.insert(str, null, contentValues);
            this.s.setTransactionSuccessful();
            z = true;
        } catch (Exception unused) {
            z = false;
        } catch (Throwable th) {
            try {
                this.s.endTransaction();
            } catch (Throwable unused2) {
            }
            throw th;
        }
        try {
            this.s.endTransaction();
        } catch (Throwable unused3) {
        }
        return z;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            try {
                sQLiteDatabase.execSQL(d);
                sQLiteDatabase.execSQL(e);
                sQLiteDatabase.execSQL(j);
                sQLiteDatabase.execSQL(f);
                sQLiteDatabase.execSQL(g);
                sQLiteDatabase.execSQL(i);
                sQLiteDatabase.execSQL(h);
                sQLiteDatabase.setTransactionSuccessful();
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
        } catch (Exception unused3) {
            sQLiteDatabase.endTransaction();
        } catch (Throwable th) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable unused4) {
            }
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) throws Throwable {
        onUpgrade(sQLiteDatabase, i3, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) throws Throwable {
        e.a().b = true;
        e.c(sQLiteDatabase);
        byte[] bArrA = e.a(sQLiteDatabase, 1);
        if (bArrA != null) {
            try {
                String str = new String(bArrA);
                com.igexin.push.core.e.w = str.equals("null") ? 0L : Long.parseLong(str);
            } catch (Exception unused) {
            }
            com.igexin.b.a.c.a.a(e.a + "|db version changed, save session = " + com.igexin.push.core.e.w, new Object[0]);
        }
        byte[] bArrA2 = e.a(sQLiteDatabase, 20);
        if (bArrA2 != null) {
            String str2 = new String(bArrA2);
            if (str2.equals("null")) {
                str2 = null;
            }
            com.igexin.push.core.e.y = str2;
            com.igexin.push.core.e.x = str2;
            com.igexin.b.a.c.a.a(e.a + "|db version changed, save cid = " + str2, new Object[0]);
        }
        String strB = e.b(sQLiteDatabase, 3);
        if (!TextUtils.isEmpty(strB)) {
            if (strB.equals("null")) {
                strB = null;
            }
            com.igexin.push.core.e.I = strB;
        }
        String str3 = com.igexin.push.core.e.I;
        String strB2 = e.b(sQLiteDatabase, 2);
        if (!TextUtils.isEmpty(strB2)) {
            if (strB2.equals("null")) {
                strB2 = null;
            }
            com.igexin.push.core.e.F = strB2;
        }
        String strB3 = e.b(sQLiteDatabase, 46);
        if (!TextUtils.isEmpty(strB3)) {
            if (strB3.equals("null")) {
                strB3 = null;
            }
            com.igexin.push.core.e.G = strB3;
        }
        String strB4 = e.b(sQLiteDatabase, 48);
        if (!TextUtils.isEmpty(strB4)) {
            if (strB4.equals("null")) {
                strB4 = null;
            }
            com.igexin.push.core.e.H = strB4;
        }
        String strB5 = e.b(sQLiteDatabase, 51);
        if (!TextUtils.isEmpty(strB5)) {
            if (strB5.equals("null")) {
                strB5 = null;
            }
            com.igexin.push.core.e.z = strB5;
        }
        try {
            sQLiteDatabase.execSQL("drop table if exists config");
        } catch (Exception unused2) {
        }
        try {
            sQLiteDatabase.execSQL(l);
        } catch (Exception unused3) {
        }
        try {
            sQLiteDatabase.execSQL(p);
        } catch (Exception unused4) {
        }
        try {
            sQLiteDatabase.execSQL(m);
        } catch (Exception unused5) {
        }
        try {
            sQLiteDatabase.execSQL(n);
        } catch (Exception unused6) {
        }
        try {
            sQLiteDatabase.execSQL(o);
        } catch (Exception unused7) {
        }
        try {
            sQLiteDatabase.execSQL(q);
        } catch (Exception unused8) {
        }
        try {
            sQLiteDatabase.execSQL("drop table if exists config");
        } catch (Exception unused9) {
        }
        onCreate(sQLiteDatabase);
    }
}
