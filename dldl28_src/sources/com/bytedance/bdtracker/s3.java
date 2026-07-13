package com.bytedance.bdtracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.applog.event.AutoTrackEventType;
import com.bytedance.framwork.core.sdklib.DBHelper;
import java.util.ArrayList;
import java.util.Arrays;
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
public class s3 extends j3 {
    public int A;
    public String B;
    public List<q3> s;
    public List<m3> t;
    public List<t3> u;
    public List<r3> v;
    public List<w3> w;
    public List<x3> x;
    public JSONObject y;
    public byte[] z;

    @Override // com.bytedance.bdtracker.j3
    public int a(Cursor cursor) {
        this.b = cursor.getLong(0);
        this.c = cursor.getLong(1);
        this.z = cursor.getBlob(2);
        this.A = cursor.getInt(3);
        this.l = cursor.getInt(4);
        this.m = cursor.getString(5);
        this.B = cursor.getString(6);
        this.e = "";
        return 7;
    }

    @Override // com.bytedance.bdtracker.j3
    public j3 a(JSONObject jSONObject) {
        d().error(4, this.f270a, "Not allowed", new Object[0]);
        return null;
    }

    public final JSONArray a(Set<String> set) {
        d dVarA = b.a(this.m);
        JSONArray jSONArray = new JSONArray();
        if (dVarA == null || !dVarA.isBavEnabled()) {
            List<t3> list = this.u;
            if (list != null) {
                for (t3 t3Var : list) {
                    if (t3Var.C) {
                        jSONArray.put(t3Var.h());
                        if (set != null) {
                            set.add(t3Var.p);
                        }
                    }
                }
            }
        } else if (this.u != null && (dVarA.getInitConfig() == null || AutoTrackEventType.a(dVarA.getInitConfig().getAutoTrackEventType(), 2))) {
            for (t3 t3Var2 : this.u) {
                jSONArray.put(t3Var2.h());
                if (set != null) {
                    set.add(t3Var2.p);
                }
            }
        }
        List<q3> list2 = this.s;
        if (list2 != null && !list2.isEmpty()) {
            for (q3 q3Var : this.s) {
                jSONArray.put(q3Var.h());
                if (set != null) {
                    set.add(q3Var.p);
                }
            }
        }
        List<x3> list3 = this.x;
        if (list3 != null && !list3.isEmpty()) {
            for (x3 x3Var : this.x) {
                jSONArray.put(x3Var.h());
                if (set != null) {
                    set.add(x3Var.p);
                }
            }
        }
        return jSONArray;
    }

    @Override // com.bytedance.bdtracker.j3
    public List<String> b() {
        return Arrays.asList(DBHelper.COL_ID, "integer primary key autoincrement", "local_time_ms", "integer", "_data", "blob", "_fail", "integer", "event_type", "integer", "_app_id", "varchar", "e_ids", "varchar");
    }

    @Override // com.bytedance.bdtracker.j3
    public void b(ContentValues contentValues) {
        byte[] bytes;
        contentValues.put("local_time_ms", Long.valueOf(this.c));
        try {
            bytes = h().toString().getBytes("UTF-8");
        } catch (Throwable th) {
            d().error(4, this.f270a, "Convert json to bytes failed", th, new Object[0]);
            bytes = null;
        }
        contentValues.put("_data", bytes);
        contentValues.put("event_type", Integer.valueOf(this.l));
        contentValues.put("_app_id", this.m);
        contentValues.put("e_ids", this.B);
    }

    @Override // com.bytedance.bdtracker.j3
    public void b(JSONObject jSONObject) {
        d().error(4, this.f270a, "Not allowed", new Object[0]);
    }

    @Override // com.bytedance.bdtracker.j3
    public String c() {
        return String.valueOf(this.b);
    }

    @Override // com.bytedance.bdtracker.j3
    public String f() {
        return "packV2";
    }

    @Override // com.bytedance.bdtracker.j3
    public JSONObject i() throws JSONException {
        int i;
        d dVarA = b.a(this.m);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("header", this.y);
        jSONObject.put("time_sync", e3.d);
        HashSet hashSet = new HashSet();
        List<r3> list = this.v;
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (r3 r3Var : this.v) {
                jSONArray.put(r3Var.h());
                hashSet.add(r3Var.p);
            }
            jSONObject.put("launch", jSONArray);
        }
        List<w3> list2 = this.w;
        int i2 = 0;
        if (list2 != null && !list2.isEmpty()) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<w3> it = this.w.iterator();
            while (it.hasNext()) {
                w3 next = it.next();
                JSONObject jSONObjectH = next.h();
                if (dVarA != null && (i = dVarA.l) > 0) {
                    jSONObjectH.put("launch_from", i);
                    dVarA.l = i2;
                }
                if (this.u != null) {
                    ArrayList arrayList = new ArrayList();
                    for (t3 t3Var : this.u) {
                        if (n0.a(t3Var.e, next.e)) {
                            arrayList.add(t3Var);
                        }
                    }
                    if (arrayList.size() != 0) {
                        int size = arrayList.size();
                        JSONArray jSONArray3 = new JSONArray();
                        long j = 0;
                        int i3 = 0;
                        while (i3 < size) {
                            t3 t3Var2 = (t3) arrayList.get(i3);
                            JSONArray jSONArray4 = new JSONArray();
                            d dVar = dVarA;
                            Iterator<w3> it2 = it;
                            jSONArray4.put(0, t3Var2.u);
                            ArrayList arrayList2 = arrayList;
                            int i4 = size;
                            jSONArray4.put(1, (t3Var2.s + 999) / 1000);
                            jSONArray3.put(jSONArray4);
                            long j2 = t3Var2.c;
                            if (j2 > j) {
                                jSONObjectH.put("$page_title", n0.a((Object) t3Var2.v));
                                jSONObjectH.put("$page_key", n0.a((Object) t3Var2.u));
                                j = j2;
                            }
                            i3++;
                            size = i4;
                            dVarA = dVar;
                            it = it2;
                            arrayList = arrayList2;
                        }
                        jSONObjectH.put("activites", jSONArray3);
                        jSONArray2.put(jSONObjectH);
                        hashSet.add(next.p);
                        dVarA = dVarA;
                        i2 = 0;
                    }
                }
            }
            jSONObject.put("terminate", jSONArray2);
        }
        JSONArray jSONArrayA = a(hashSet);
        if (jSONArrayA.length() > 0) {
            jSONObject.put("event_v3", jSONArrayA);
        }
        List<m3> list3 = this.t;
        if (list3 != null && !list3.isEmpty()) {
            HashMap map = new HashMap();
            for (m3 m3Var : this.t) {
                JSONArray jSONArray5 = (JSONArray) map.get(m3Var.s);
                if (jSONArray5 == null) {
                    jSONArray5 = new JSONArray();
                    map.put(m3Var.s, jSONArray5);
                }
                jSONArray5.put(m3Var.h());
                hashSet.add(m3Var.p);
            }
            for (Map.Entry entry : map.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        this.B = TextUtils.join(",", hashSet);
        d().debug(4, this.f270a, "Pack success ts:{}", Long.valueOf(this.c));
        return jSONObject;
    }

    public int k() {
        List<t3> list;
        List<r3> list2 = this.v;
        int size = list2 != null ? 200 - list2.size() : 200;
        List<w3> list3 = this.w;
        if (list3 != null) {
            size -= list3.size();
        }
        d dVarA = b.a(this.m);
        return (dVarA == null || !dVarA.isBavEnabled() || (list = this.u) == null) ? size : size - list.size();
    }

    public Set<String> l() {
        HashSet hashSet = new HashSet();
        if (TextUtils.isEmpty(this.B)) {
            return hashSet;
        }
        hashSet.addAll(Arrays.asList(this.B.split(",")));
        return hashSet;
    }

    public void m() {
        JSONObject jSONObject = this.y;
        if (jSONObject == null) {
            return;
        }
        jSONObject.remove("ssid");
        try {
            List<r3> list = this.v;
            if (list != null) {
                for (r3 r3Var : list) {
                    if (n0.d(r3Var.i)) {
                        this.y.put("ssid", r3Var.i);
                        return;
                    }
                }
            }
            List<t3> list2 = this.u;
            if (list2 != null) {
                for (t3 t3Var : list2) {
                    if (n0.d(t3Var.i)) {
                        this.y.put("ssid", t3Var.i);
                        return;
                    }
                }
            }
            List<m3> list3 = this.t;
            if (list3 != null) {
                for (m3 m3Var : list3) {
                    if (n0.d(m3Var.i)) {
                        this.y.put("ssid", m3Var.i);
                        return;
                    }
                }
            }
            List<q3> list4 = this.s;
            if (list4 != null) {
                for (q3 q3Var : list4) {
                    if (n0.d(q3Var.i)) {
                        this.y.put("ssid", q3Var.i);
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            d().error(4, this.f270a, "Reload ssid from event failed", th, new Object[0]);
        }
    }

    public void n() {
        JSONObject jSONObject = this.y;
        if (jSONObject == null) {
            return;
        }
        jSONObject.remove("user_unique_id_type");
        try {
            List<r3> list = this.v;
            if (list != null) {
                for (r3 r3Var : list) {
                    if (n0.d(r3Var.h)) {
                        this.y.put("user_unique_id_type", r3Var.h);
                        return;
                    }
                }
            }
            List<t3> list2 = this.u;
            if (list2 != null) {
                for (t3 t3Var : list2) {
                    if (n0.d(t3Var.h)) {
                        this.y.put("user_unique_id_type", t3Var.h);
                        return;
                    }
                }
            }
            List<m3> list3 = this.t;
            if (list3 != null) {
                for (m3 m3Var : list3) {
                    if (n0.d(m3Var.h)) {
                        this.y.put("user_unique_id_type", m3Var.h);
                        return;
                    }
                }
            }
            List<q3> list4 = this.s;
            if (list4 != null) {
                for (q3 q3Var : list4) {
                    if (n0.d(q3Var.h)) {
                        this.y.put("user_unique_id_type", q3Var.h);
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            d().error(4, this.f270a, "Reload uuid type from event failed", th, new Object[0]);
        }
    }

    @Override // com.bytedance.bdtracker.j3
    public String toString() {
        StringBuilder sb = new StringBuilder("Pack detail:");
        List<q3> list = this.s;
        int size = list != null ? list.size() : 0;
        List<m3> list2 = this.t;
        if (list2 != null) {
            size += list2.size();
        }
        if (size > 0) {
            sb.append("\teventCount=");
            sb.append(size);
        }
        List<t3> list3 = this.u;
        if (list3 != null && !list3.isEmpty()) {
            sb.append("\tpageCount=");
            sb.append(this.u.size());
        }
        List<r3> list4 = this.v;
        if (list4 != null && !list4.isEmpty()) {
            sb.append("\tlaunchCount=");
            sb.append(this.v.size());
        }
        List<w3> list5 = this.w;
        if (list5 != null && !list5.isEmpty()) {
            sb.append("\tterminateCount=");
            sb.append(this.w.size());
        }
        List<x3> list6 = this.x;
        if (list6 != null && !list6.isEmpty()) {
            sb.append("\ttraceCount=");
            sb.append(this.x.size());
        }
        if (this.A > 0) {
            sb.append("\tfailCount=");
            sb.append(this.A);
        }
        return sb.toString();
    }
}
