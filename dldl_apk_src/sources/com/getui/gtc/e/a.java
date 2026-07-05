package com.getui.gtc.e;

import android.content.ContentValues;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.getui.gtc.base.db.AbstractTable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a extends AbstractTable {
    private SparseArray<Long> a = new SparseArray<>();
    private SparseArray<Long> b = new SparseArray<>();
    private SparseIntArray c = new SparseIntArray();

    public final long a(int i) {
        Long l = this.a.get(i);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public final void a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ei", Integer.valueOf(i));
        contentValues.put("elt", String.valueOf(j));
        if (replace(null, contentValues) != -1) {
            this.a.put(i, Long.valueOf(j));
        }
    }

    public final void a(int i, long j, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ei", Integer.valueOf(i));
        contentValues.put("est", String.valueOf(j));
        contentValues.put("esn", Integer.valueOf(i2));
        if (replace(null, contentValues) != -1) {
            this.b.put(i, Long.valueOf(j));
            this.c.put(i, i2);
        }
    }

    public final long b(int i) {
        Long l = this.b.get(i);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public final int c(int i) {
        return this.c.get(i);
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS e (ei INTEGER PRIMARY KEY, elt TEXT, est TEXT, esn INTEGER)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "e";
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    @Override // com.getui.gtc.base.db.AbstractTable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initCache() {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "ei"
            java.lang.String r2 = "elt"
            java.lang.String r3 = "est"
            java.lang.String r4 = "esn"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3, r4}     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            android.database.Cursor r0 = r5.query(r1, r0, r0)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            if (r0 != 0) goto L19
            if (r0 == 0) goto L18
            r0.close()
        L18:
            return
        L19:
            boolean r1 = r0.moveToNext()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            if (r1 == 0) goto L67
            java.lang.String r1 = "ei"
            int r1 = r0.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            int r1 = r0.getInt(r1)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            java.lang.String r2 = "elt"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L6d
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L6d
            long r2 = java.lang.Long.parseLong(r2)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L6d
            android.util.SparseArray<java.lang.Long> r4 = r5.a     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L6d
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L6d
            r4.put(r1, r2)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L6d
        L40:
            java.lang.String r2 = "est"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L6d
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L6d
            long r2 = java.lang.Long.parseLong(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L6d
            android.util.SparseArray<java.lang.Long> r4 = r5.b     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L6d
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L6d
            r4.put(r1, r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L6d
        L57:
            java.lang.String r2 = "esn"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            int r2 = r0.getInt(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            android.util.SparseIntArray r3 = r5.c     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            r3.put(r1, r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L6f
            goto L19
        L67:
            if (r0 == 0) goto L78
            r0.close()
            return
        L6d:
            r1 = move-exception
            goto L79
        L6f:
            r1 = move-exception
            com.getui.gtc.i.c.a.a(r1)     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L78
            r0.close()
        L78:
            return
        L79:
            if (r0 == 0) goto L7e
            r0.close()
        L7e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.e.a.initCache():void");
    }
}
