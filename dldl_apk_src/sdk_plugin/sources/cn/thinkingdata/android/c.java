package cn.thinkingdata.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import cn.thinkingdata.android.utils.TDLog;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class c {
    private static final String b = "CREATE TABLE " + EnumC0005c.EVENTS.a() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, clickdata TEXT NOT NULL, creattime INTEGER NOT NULL, token TEXT NOT NULL DEFAULT '')";
    private static final String c;
    private static final Map<Context, c> d;
    private final a a;

    private static class a extends SQLiteOpenHelper {
        private final File a;
        private final int b;

        public a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
            this.a = context.getDatabasePath(str);
            this.b = m.a(context).c();
        }

        boolean a() {
            return !this.a.exists() || b() < this.b;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x003c A[PHI: r0 r1
  0x003c: PHI (r0v3 android.database.Cursor) = (r0v1 android.database.Cursor), (r0v4 android.database.Cursor) binds: [B:12:0x003a, B:6:0x0031] A[DONT_GENERATE, DONT_INLINE]
  0x003c: PHI (r1v3 int) = (r1v0 int), (r1v5 int) binds: [B:12:0x003a, B:6:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int b() {
            /*
                r5 = this;
                r0 = 0
                r1 = 0
                android.database.sqlite.SQLiteDatabase r2 = r5.getReadableDatabase()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                r3.<init>()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r4 = "SELECT count(*) FROM "
                r3.append(r4)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                cn.thinkingdata.android.c$c r4 = cn.thinkingdata.android.c.EnumC0005c.EVENTS     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                r3.append(r4)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                android.database.Cursor r0 = r2.rawQuery(r3, r0)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                if (r2 == 0) goto L31
                java.lang.String r2 = "count(*)"
                int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                int r1 = r0.getInt(r2)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            L31:
                if (r0 == 0) goto L3f
                goto L3c
            L34:
                r1 = move-exception
                goto L40
            L36:
                r2 = move-exception
                r2.printStackTrace()     // Catch: java.lang.Throwable -> L34
                if (r0 == 0) goto L3f
            L3c:
                r0.close()
            L3f:
                return r1
            L40:
                if (r0 == 0) goto L45
                r0.close()
            L45:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.c.a.b():int");
        }

        void c() {
            close();
            this.a.delete();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            TDLog.d("ThinkingAnalytics.DatabaseAdapter", "Creating a new ThinkingData events database");
            sQLiteDatabase.execSQL(c.b);
            sQLiteDatabase.execSQL(c.c);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            TDLog.d("ThinkingAnalytics.DatabaseAdapter", "Upgrading ThinkingData events database");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EnumC0005c.EVENTS.a());
            sQLiteDatabase.execSQL(c.b);
            sQLiteDatabase.execSQL(c.c);
        }
    }

    private class b extends SQLiteOpenHelper {
        b(c cVar, Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0064 A[PHI: r3
  0x0064: PHI (r3v3 android.database.Cursor) = (r3v2 android.database.Cursor), (r3v4 android.database.Cursor) binds: [B:16:0x0062, B:9:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        org.json.JSONArray a() {
            /*
                r7 = this;
                java.lang.String r0 = "clickdata"
                java.lang.String r1 = "creattime"
                org.json.JSONArray r2 = new org.json.JSONArray
                r2.<init>()
                r3 = 0
                android.database.sqlite.SQLiteDatabase r4 = r7.getReadableDatabase()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                r5.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.String r6 = "SELECT * FROM "
                r5.append(r6)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                cn.thinkingdata.android.c$c r6 = cn.thinkingdata.android.c.EnumC0005c.EVENTS     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                r5.append(r6)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.String r6 = " ORDER BY ?"
                r5.append(r6)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.String[] r6 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                android.database.Cursor r3 = r4.rawQuery(r5, r6)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            L2e:
                boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                if (r4 == 0) goto L53
                org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                r4.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                int r5 = r3.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                r4.put(r1, r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                int r5 = r3.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                r4.put(r0, r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                r2.put(r4)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
                goto L2e
            L53:
                r7.close()
                if (r3 == 0) goto L67
                goto L64
            L59:
                r0 = move-exception
                goto L68
            L5b:
                r0 = move-exception
                r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
                r7.close()
                if (r3 == 0) goto L67
            L64:
                r3.close()
            L67:
                return r2
            L68:
                r7.close()
                if (r3 == 0) goto L70
                r3.close()
            L70:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.c.b.a():org.json.JSONArray");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    /* JADX INFO: renamed from: cn.thinkingdata.android.c$c, reason: collision with other inner class name */
    public enum EnumC0005c {
        EVENTS("events");

        private final String a;

        EnumC0005c(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE INDEX IF NOT EXISTS time_idx ON ");
        sb.append(EnumC0005c.EVENTS.a());
        sb.append(" (");
        sb.append("creattime");
        sb.append(");");
        c = sb.toString();
        d = new HashMap();
    }

    c(Context context) {
        this(context, "thinkingdata");
    }

    c(Context context, String str) {
        this.a = new a(context, str);
        try {
            File databasePath = context.getDatabasePath(context.getPackageName());
            if (databasePath.exists()) {
                JSONArray jSONArrayA = new b(this, context, context.getPackageName()).a();
                for (int i = 0; i < jSONArrayA.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArrayA.getJSONObject(i);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("clickdata", jSONObject.getString("clickdata"));
                        contentValues.put("creattime", jSONObject.getString("creattime"));
                        TDLog.d("ThinkingAnalytics.DatabaseAdapter", contentValues.toString());
                        this.a.getWritableDatabase().insert(EnumC0005c.EVENTS.a(), null, contentValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                databasePath.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static c a(Context context) {
        c cVar;
        synchronized (d) {
            Context applicationContext = context.getApplicationContext();
            if (d.containsKey(applicationContext)) {
                cVar = d.get(applicationContext);
            } else {
                cVar = new c(applicationContext);
                d.put(applicationContext, cVar);
            }
        }
        return cVar;
    }

    private boolean c() {
        return this.a.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008c A[PHI: r0 r7
  0x008c: PHI (r0v3 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v4 android.database.Cursor) binds: [B:21:0x008a, B:10:0x0063] A[DONT_GENERATE, DONT_INLINE]
  0x008c: PHI (r7v5 int) = (r7v4 int), (r7v9 int) binds: [B:21:0x008a, B:10:0x0063] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(java.lang.String r7, cn.thinkingdata.android.c.EnumC0005c r8, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r8 = r8.a()
            r0 = 0
            cn.thinkingdata.android.c$a r1 = r6.a     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.String r3 = "_id <= ?"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            if (r9 == 0) goto L23
            java.lang.String r3 = " AND "
            r2.append(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.String r3 = "token"
            r2.append(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.String r3 = " = ?"
            r2.append(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
        L23:
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r4 = 0
            r3[r4] = r7     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r7 = 1
            r3[r7] = r9     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r1.delete(r8, r2, r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r3.<init>()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.String r5 = "SELECT COUNT(*) FROM "
            r3.append(r5)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r3.append(r8)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            if (r9 == 0) goto L50
            java.lang.String r3 = " WHERE token= ?"
            r2.append(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
        L50:
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r7[r4] = r9     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            android.database.Cursor r0 = r1.rawQuery(r2, r7)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            r0.moveToFirst()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            int r7 = r0.getInt(r4)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteException -> L68
            if (r0 == 0) goto L8f
            goto L8c
        L66:
            r7 = move-exception
            goto L90
        L68:
            r7 = move-exception
            java.lang.String r9 = "ThinkingAnalytics.DatabaseAdapter"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
            r1.<init>()     // Catch: java.lang.Throwable -> L66
            java.lang.String r2 = "could not clean data from "
            r1.append(r2)     // Catch: java.lang.Throwable -> L66
            r1.append(r8)     // Catch: java.lang.Throwable -> L66
            java.lang.String r8 = r1.toString()     // Catch: java.lang.Throwable -> L66
            cn.thinkingdata.android.utils.TDLog.e(r9, r8, r7)     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L84
            r0.close()     // Catch: java.lang.Throwable -> L66
        L84:
            cn.thinkingdata.android.c$a r7 = r6.a     // Catch: java.lang.Throwable -> L66
            r7.c()     // Catch: java.lang.Throwable -> L66
            r7 = -1
            if (r0 == 0) goto L8f
        L8c:
            r0.close()
        L8f:
            return r7
        L90:
            if (r0 == 0) goto L95
            r0.close()
        L95:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.c.a(java.lang.String, cn.thinkingdata.android.c$c, java.lang.String):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd A[PHI: r0 r3
  0x00cd: PHI (r0v3 int) = (r0v1 int), (r0v5 int) binds: [B:27:0x00cb, B:18:0x00a2] A[DONT_GENERATE, DONT_INLINE]
  0x00cd: PHI (r3v3 android.database.Cursor) = (r3v2 android.database.Cursor), (r3v4 android.database.Cursor) binds: [B:27:0x00cb, B:18:0x00a2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(org.json.JSONObject r10, cn.thinkingdata.android.c.EnumC0005c r11, java.lang.String r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.c.a(org.json.JSONObject, cn.thinkingdata.android.c$c, java.lang.String):int");
    }

    public void a(long j, EnumC0005c enumC0005c) {
        String strA = enumC0005c.a();
        try {
            this.a.getWritableDatabase().delete(strA, "creattime <= ?", new String[]{j + ""});
        } catch (SQLiteException e) {
            TDLog.e("ThinkingAnalytics.DatabaseAdapter", "Could not clean timed-out records. Re-initializing database.", e);
            this.a.c();
        }
    }

    public void a(EnumC0005c enumC0005c, String str) {
        try {
            this.a.getWritableDatabase().delete(enumC0005c.a(), "token = ?", new String[]{str});
        } catch (SQLiteException e) {
            TDLog.e("ThinkingAnalytics.DatabaseAdapter", "Could not clean records. Re-initializing database.", e);
            this.a.c();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0100 A[PHI: r6 r12 r14
  0x0100: PHI (r6v2 java.lang.String) = (r6v1 java.lang.String), (r6v7 java.lang.String) binds: [B:49:0x00fe, B:40:0x00df] A[DONT_GENERATE, DONT_INLINE]
  0x0100: PHI (r12v8 java.lang.String) = (r12v7 java.lang.String), (r12v11 java.lang.String) binds: [B:49:0x00fe, B:40:0x00df] A[DONT_GENERATE, DONT_INLINE]
  0x0100: PHI (r14v4 android.database.Cursor) = (r14v3 android.database.Cursor), (r14v6 android.database.Cursor) binds: [B:49:0x00fe, B:40:0x00df] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String[] a(cn.thinkingdata.android.c.EnumC0005c r12, java.lang.String r13, int r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.c.a(cn.thinkingdata.android.c$c, java.lang.String, int):java.lang.String[]");
    }
}
