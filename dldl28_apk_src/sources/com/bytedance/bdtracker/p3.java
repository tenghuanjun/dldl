package com.bytedance.bdtracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class p3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n3 f306a;
    public final c0 b;
    public final o3 c;

    public p3(c0 c0Var, String str) {
        n3 n3Var = new n3(c0Var, str, null, 51);
        this.f306a = n3Var;
        this.b = c0Var;
        this.c = new o3(c0Var, n3Var);
    }

    public final List<Long> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("local_time_ms")) {
                    try {
                        arrayList.add(Long.valueOf(jSONObjectOptJSONObject.getLong("local_time_ms")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized void a() {
        SQLiteDatabase writableDatabase;
        Throwable th;
        try {
            writableDatabase = this.f306a.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                for (j3 j3Var : j3.j().values()) {
                    if (n0.d(j3Var.a())) {
                        writableDatabase.delete(j3Var.f(), null, null);
                    }
                }
                writableDatabase.setTransactionSuccessful();
                n0.a(writableDatabase);
            } catch (Throwable th2) {
                th = th2;
                try {
                    this.b.d.D.error(5, "Clear database failed", th, new Object[0]);
                    this.f306a.a(th);
                } finally {
                    if (writableDatabase != null) {
                        n0.a(writableDatabase);
                    }
                }
            }
        } catch (Throwable th3) {
            writableDatabase = null;
            th = th3;
        }
    }

    public synchronized void b(List<s3> list) {
        if (list == null) {
            return;
        }
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = this.f306a.getWritableDatabase();
            writableDatabase.beginTransaction();
            for (s3 s3Var : list) {
                if (s3Var.A != 0 && (s3Var.A <= 0 || Math.abs(System.currentTimeMillis() - s3Var.c) <= 864000000)) {
                    int i = s3Var.A;
                    if (i > 0) {
                        writableDatabase.execSQL("UPDATE packV2 SET _fail= ? WHERE _id= ?", new Object[]{Integer.valueOf(i), Long.valueOf(s3Var.b)});
                    }
                }
                writableDatabase.execSQL("DELETE FROM packV2 WHERE _id=?", new Object[]{Long.valueOf(s3Var.b)});
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            try {
            } finally {
            }
        }
    }

    public final List<t3> c(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = str2 == null ? sQLiteDatabase.rawQuery("SELECT * FROM page WHERE _app_id= ? and user_unique_id is null order by duration desc", new String[]{str}) : sQLiteDatabase.rawQuery("SELECT * FROM page WHERE _app_id= ? and user_unique_id = ? order by duration desc", new String[]{str, str2});
            while (cursorRawQuery.moveToNext()) {
                t3 t3Var = new t3();
                t3Var.a(cursorRawQuery);
                arrayList.add(t3Var);
            }
        } catch (Throwable th) {
            try {
                this.b.d.D.error(5, "Query pages by userId:{} failed", th, str2);
                this.f306a.a(th);
            } finally {
                n0.a(cursorRawQuery);
            }
        }
        return arrayList;
    }

    public final void d(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (Long l : list) {
            n3 n3Var = this.f306a;
            long jLongValue = jCurrentTimeMillis - l.longValue();
            z1 z1Var = n3Var.f297a.q;
            if (z1Var != null) {
                ((d2) z1Var).a(new l2(jLongValue));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.bytedance.bdtracker.w3> a(java.util.List<com.bytedance.bdtracker.t3> r19, java.util.List<com.bytedance.bdtracker.t3> r20) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.p3.a(java.util.List, java.util.List):java.util.List");
    }

    public synchronized void b(String str, JSONObject jSONObject) {
        this.b.d.D.debug(5, "Pack trace events for appId:{} start...", str);
        try {
            SQLiteDatabase writableDatabase = this.f306a.getWritableDatabase();
            List<x3> listA = a(writableDatabase, str);
            if (listA.isEmpty()) {
                return;
            }
            s3 s3Var = new s3();
            JSONObject jSONObject2 = new JSONObject();
            n0.a(jSONObject2, jSONObject);
            jSONObject2.remove("user_unique_id");
            jSONObject2.remove("user_unique_id_type");
            s3Var.y = jSONObject2;
            s3Var.m = str;
            s3Var.x = listA;
            a(writableDatabase, s3Var);
        } catch (Throwable th) {
            this.b.d.D.error(5, "Pack trace events for appId:{} failed", th, str);
            this.f306a.a(th);
        }
    }

    public synchronized void c(List<u3> list) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = this.f306a.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                Iterator<u3> it = list.iterator();
                ContentValues contentValuesA = null;
                while (it.hasNext()) {
                    contentValuesA = it.next().a(contentValuesA);
                    writableDatabase.insert("profile", null, contentValuesA);
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = writableDatabase;
                try {
                    this.b.d.D.error(5, "Save profiles failed", th, new Object[0]);
                    this.f306a.a(th);
                    writableDatabase = sQLiteDatabase;
                } finally {
                    n0.a(sQLiteDatabase);
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final List<q3> b(SQLiteDatabase sQLiteDatabase, String str, String str2, int i) {
        Throwable th;
        Cursor cursorRawQuery;
        if (i <= 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        try {
            cursorRawQuery = str2 == null ? sQLiteDatabase.rawQuery("SELECT * FROM eventv3 WHERE _app_id= ? and user_unique_id is null limit 0, ?", new String[]{str, String.valueOf(i)}) : sQLiteDatabase.rawQuery("SELECT * FROM eventv3 WHERE _app_id= ? and user_unique_id = ? limit 0, ?", new String[]{str, str2, String.valueOf(i)});
            while (cursorRawQuery.moveToNext()) {
                try {
                    q3 q3Var = new q3();
                    q3Var.a(cursorRawQuery);
                    arrayList.add(q3Var);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        this.b.d.D.error(5, "Query v3 event by uuid:{} for appId:{} failed", th, str2, str);
                        this.f306a.a(th);
                    } finally {
                        n0.a(cursorRawQuery);
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
        }
        return arrayList;
    }

    public final int a(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        if (sQLiteDatabase == null) {
            return 0;
        }
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("SELECT count(1) FROM " + str + " WHERE " + str2, strArr);
            if (cursorRawQuery.moveToNext()) {
                return cursorRawQuery.getInt(0);
            }
        } catch (Throwable th) {
            try {
                this.b.d.D.error(5, "Count table:{} failed", th, str);
                this.f306a.a(th);
            } finally {
                n0.a(cursorRawQuery);
            }
        }
        return 0;
    }

    public final List<r3> b(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = str2 == null ? sQLiteDatabase.rawQuery("SELECT * FROM launch WHERE _app_id= ? and user_unique_id is null", new String[]{str}) : sQLiteDatabase.rawQuery("SELECT * FROM launch WHERE _app_id= ? and user_unique_id = ?", new String[]{str, str2});
            while (cursorRawQuery.moveToNext()) {
                r3 r3Var = new r3();
                r3Var.a(cursorRawQuery);
                arrayList.add(r3Var);
                r3Var.u = !(n0.d(r3Var.e) && a(sQLiteDatabase, "page", "session_id = ? LIMIT 1", new String[]{r3Var.e}) > 0);
            }
        } catch (Throwable th) {
            try {
                this.b.d.D.error(5, "Query launch by uuid:{} for appId:{} failed", th, str2, str);
                this.f306a.a(th);
            } finally {
                n0.a(cursorRawQuery);
            }
        }
        return arrayList;
    }

    public synchronized void a(List<u3> list) {
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = this.f306a.getWritableDatabase();
            writableDatabase.beginTransaction();
            Iterator<u3> it = list.iterator();
            while (it.hasNext()) {
                writableDatabase.delete("profile", "_id=?", new String[]{String.valueOf(it.next().b)});
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            try {
            } finally {
            }
        }
    }

    public final Set<String> a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        HashSet hashSet = new HashSet();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("SELECT `user_unique_id` FROM " + str + " WHERE _app_id= ?", new String[]{str2});
            while (cursorRawQuery.moveToNext()) {
                hashSet.add(cursorRawQuery.getString(0));
            }
        } catch (Throwable th) {
            try {
                this.b.d.D.error(5, "Query uuid set from table:{} for appId:{} failed", th, str, str2);
                this.f306a.a(th);
            } finally {
                n0.a(cursorRawQuery);
            }
        }
        return hashSet;
    }

    public synchronized void a(String str, JSONObject jSONObject) {
        SQLiteDatabase readableDatabase;
        List<w3> list;
        List<m3> list2;
        this.b.d.D.debug(5, "Pack events for appId:{} start...", str);
        try {
            readableDatabase = this.f306a.getReadableDatabase();
        } catch (Throwable th) {
            this.b.d.D.error(5, "Open db failed", th, new Object[0]);
            this.f306a.a(th);
            readableDatabase = null;
        }
        HashSet<String> hashSet = new HashSet();
        if (readableDatabase != null) {
            hashSet.addAll(a(readableDatabase, "launch", str));
            hashSet.addAll(a(readableDatabase, "page", str));
            hashSet.addAll(a(readableDatabase, "eventv3", str));
            hashSet.addAll(a(readableDatabase, "custom_event", str));
        }
        if (hashSet.isEmpty()) {
            return;
        }
        try {
            SQLiteDatabase writableDatabase = this.f306a.getWritableDatabase();
            for (String str2 : hashSet) {
                s3 s3Var = new s3();
                s3Var.m = str;
                JSONObject jSONObject2 = new JSONObject();
                n0.a(jSONObject2, jSONObject);
                jSONObject2.remove("ssid");
                jSONObject2.put("user_unique_id", n0.c(str2) ? JSONObject.NULL : str2);
                s3Var.y = jSONObject2;
                s3Var.v = b(writableDatabase, str, str2);
                List<t3> listC = c(writableDatabase, str, str2);
                ArrayList arrayList = new ArrayList();
                List<w3> listA = a(listC, arrayList);
                s3Var.u = arrayList;
                s3Var.w = listA;
                s3Var.t = a(writableDatabase, str, str2, s3Var.k());
                int iK = s3Var.k();
                List<m3> list3 = s3Var.t;
                if (list3 != null) {
                    iK -= list3.size();
                }
                s3Var.s = b(writableDatabase, str, str2, iK);
                List<r3> list4 = s3Var.v;
                if ((list4 != null && !list4.isEmpty()) || (((list = s3Var.w) != null && !list.isEmpty()) || s3Var.a((Set<String>) null).length() != 0 || ((list2 = s3Var.t) != null && !list2.isEmpty()))) {
                    s3Var.m();
                    s3Var.n();
                    if (!this.b.a(jSONObject2)) {
                        this.b.d.D.warn(5, "Register to get ssid by temp header failed.", new Object[0]);
                    } else {
                        this.b.d.D.debug(5, s3Var.toString(), new Object[0]);
                        a(writableDatabase, s3Var);
                    }
                }
            }
        } catch (Throwable th2) {
            this.b.d.D.warn(5, "Pack events for appId:{} failed", th2, str);
            this.f306a.a(th2);
        }
    }

    public final List<m3> a(SQLiteDatabase sQLiteDatabase, String str, String str2, int i) {
        Throwable th;
        Cursor cursorRawQuery;
        if (i <= 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        try {
            cursorRawQuery = str2 == null ? sQLiteDatabase.rawQuery("SELECT * FROM custom_event WHERE _app_id= ? and user_unique_id is null limit 0, ?", new String[]{str, String.valueOf(i)}) : sQLiteDatabase.rawQuery("SELECT * FROM custom_event WHERE _app_id= ? and user_unique_id = ? limit 0, ?", new String[]{str, str2, String.valueOf(i)});
            while (cursorRawQuery.moveToNext()) {
                try {
                    m3 m3Var = new m3();
                    m3Var.a(cursorRawQuery);
                    arrayList.add(m3Var);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        this.b.d.D.error(5, "Query custom event by uuid:{} for appId:{} failed", th, str2, str);
                        this.f306a.a(th);
                    } finally {
                        n0.a(cursorRawQuery);
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
        }
        return arrayList;
    }

    public synchronized Map<String, List<u3>> a(String str) {
        HashMap map;
        map = new HashMap();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.f306a.getWritableDatabase().rawQuery("SELECT * FROM profile WHERE _app_id=? ORDER BY _id DESC LIMIT 200", new String[]{str});
            while (cursorRawQuery.moveToNext()) {
                u3 u3Var = new u3();
                u3Var.a(cursorRawQuery);
                String strA = n0.a((Object) u3Var.g);
                List arrayList = (List) map.get(strA);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map.put(strA, arrayList);
                }
                arrayList.add(u3Var);
            }
            n0.a(cursorRawQuery);
        } catch (Throwable th) {
            try {
                this.b.d.D.error(5, "Query profiles for appId:{} failed", th, str);
                this.f306a.a(th);
            } finally {
                n0.a(cursorRawQuery);
            }
        }
        return map;
    }

    public final List<x3> a(SQLiteDatabase sQLiteDatabase, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM trace WHERE _app_id= ? ", new String[]{str});
            while (cursorRawQuery.moveToNext()) {
                x3 x3Var = new x3();
                x3Var.a(cursorRawQuery);
                arrayList.add(x3Var);
            }
        } catch (Throwable th) {
            try {
                this.b.d.D.error(5, "Query trace for appId:{} failed", th, str);
                this.f306a.a(th);
            } finally {
                n0.a(cursorRawQuery);
            }
        }
        return arrayList;
    }

    public final synchronized void a(SQLiteDatabase sQLiteDatabase, s3 s3Var) {
        try {
            sQLiteDatabase.beginTransaction();
        } finally {
            try {
            } finally {
            }
        }
        if (sQLiteDatabase.insert("packV2", null, s3Var.a((ContentValues) null)) < 0) {
            return;
        }
        List<r3> list = s3Var.v;
        if (list != null) {
            for (r3 r3Var : list) {
                sQLiteDatabase.delete("launch", "_id = ?", new String[]{String.valueOf(r3Var.b)});
                a1.a("event_pack", r3Var);
            }
        }
        List<t3> list2 = s3Var.u;
        if (list2 != null) {
            for (t3 t3Var : list2) {
                sQLiteDatabase.delete("page", "session_id = ? and page_key = ?", new String[]{String.valueOf(t3Var.e), n0.a((Object) t3Var.u)});
                a1.a("event_pack", t3Var);
            }
        }
        List<m3> list3 = s3Var.t;
        if (list3 != null) {
            for (m3 m3Var : list3) {
                sQLiteDatabase.delete("custom_event", "_id = ?", new String[]{String.valueOf(m3Var.b)});
                a1.a("event_pack", m3Var);
            }
        }
        List<q3> list4 = s3Var.s;
        if (list4 != null) {
            for (q3 q3Var : list4) {
                sQLiteDatabase.delete("eventv3", "_id = ?", new String[]{String.valueOf(q3Var.b)});
                a1.a("event_pack", q3Var);
            }
        }
        if (s3Var.x != null) {
            sQLiteDatabase.delete("trace", "_app_id= ? ", new String[]{String.valueOf(s3Var.m)});
            Iterator<x3> it = s3Var.x.iterator();
            while (it.hasNext()) {
                a1.a("event_pack", it.next());
            }
        }
        sQLiteDatabase.setTransactionSuccessful();
    }

    public synchronized void a(String str, String str2) {
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = this.f306a.getWritableDatabase();
            writableDatabase.beginTransaction();
            writableDatabase.execSQL("UPDATE launch SET ssid = ? WHERE user_unique_id = ? AND LENGTH(ssid) = 0", new String[]{str2, str});
            writableDatabase.execSQL("UPDATE page SET ssid = ? WHERE user_unique_id = ? AND LENGTH(ssid) = 0", new String[]{str2, str});
            writableDatabase.execSQL("UPDATE eventv3 SET ssid = ? WHERE user_unique_id = ? AND LENGTH(ssid) = 0", new String[]{str2, str});
            writableDatabase.execSQL("UPDATE profile SET ssid = ? WHERE user_unique_id = ? AND LENGTH(ssid) = 0", new String[]{str2, str});
            writableDatabase.execSQL("UPDATE trace SET ssid = ? WHERE user_unique_id = ? AND LENGTH(ssid) = 0", new String[]{str2, str});
            writableDatabase.setTransactionSuccessful();
        } finally {
            try {
            } finally {
            }
        }
    }
}
