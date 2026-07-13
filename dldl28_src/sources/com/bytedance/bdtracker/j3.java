package com.bytedance.bdtracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.applog.log.AbstractAppLogLogger;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j3 implements Cloneable {
    public static final SimpleDateFormat q = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    public static final y3<HashMap<String, j3>> r = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<String> f270a;
    public long b;
    public long c;
    public long d;
    public String e;
    public long f;
    public String g;
    public String h;
    public String i;
    public String j;
    public int k;
    public int l;
    public String m;
    public String n;
    public JSONObject o;
    public String p;

    public static class a extends y3<HashMap<String, j3>> {
        @Override // com.bytedance.bdtracker.y3
        public HashMap<String, j3> a(Object[] objArr) {
            return j3.j();
        }
    }

    public j3() {
        a(0L);
        this.f270a = Collections.singletonList(f());
        this.p = n0.c();
    }

    public static j3 a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return r.b(new Object[0]).get(jSONObject.optString("k_cls", "")).m6355clone().a(jSONObject);
        } catch (Throwable th) {
            LoggerImpl.global().error(4, "JSON handle failed", th, new Object[0]);
            return null;
        }
    }

    public static String b(long j) {
        return q.format(new Date(j));
    }

    public static HashMap<String, j3> j() {
        HashMap<String, j3> map = new HashMap<>();
        map.put("page", new t3());
        map.put("launch", new r3());
        map.put("terminate", new w3());
        map.put("packV2", new s3());
        map.put("eventv3", new q3());
        map.put("custom_event", new m3());
        map.put("profile", new u3(null, null));
        map.put("trace", new x3());
        return map;
    }

    public int a(Cursor cursor) {
        this.b = cursor.getLong(0);
        this.c = cursor.getLong(1);
        this.d = cursor.getLong(2);
        this.k = cursor.getInt(3);
        this.f = cursor.getLong(4);
        this.e = cursor.getString(5);
        this.g = cursor.getString(6);
        this.h = cursor.getString(7);
        this.i = cursor.getString(8);
        this.j = cursor.getString(9);
        this.l = cursor.getInt(10);
        this.m = cursor.getString(11);
        String string = cursor.getString(12);
        this.p = cursor.getString(13);
        this.o = new JSONObject();
        if (TextUtils.isEmpty(string)) {
            return 14;
        }
        try {
            this.o = new JSONObject(string);
            return 14;
        } catch (Exception unused) {
            return 14;
        }
    }

    public final ContentValues a(ContentValues contentValues) {
        if (contentValues == null) {
            contentValues = new ContentValues();
        } else {
            contentValues.clear();
        }
        b(contentValues);
        return contentValues;
    }

    public j3 a(JSONObject jSONObject) {
        this.c = jSONObject.optLong("local_time_ms", 0L);
        this.b = 0L;
        this.d = 0L;
        this.k = 0;
        this.f = 0L;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.m = jSONObject.optString("_app_id");
        this.o = jSONObject.optJSONObject("properties");
        this.p = jSONObject.optString("local_event_id", n0.c());
        return this;
    }

    public final String a() {
        List<String> listB = b();
        if (listB == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("create table if not exists ");
        sb.append(f());
        sb.append("(");
        for (int i = 0; i < listB.size(); i += 2) {
            sb.append(listB.get(i));
            sb.append(StringUtils.SPACE);
            sb.append(listB.get(i + 1));
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(")");
        return sb.toString();
    }

    public void a(long j) {
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        this.c = j;
    }

    public void a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            a(jSONObject, new JSONObject());
            return;
        }
        try {
            a(jSONObject, new JSONObject(str));
        } catch (Throwable th) {
            d().error(4, this.f270a, "Merge params failed", th, new Object[0]);
        }
    }

    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObject3 = new JSONObject();
        if (jSONObject2 != null && jSONObject2.length() > 0) {
            n0.b(jSONObject2, jSONObject3);
        }
        JSONObject jSONObject4 = this.o;
        if (jSONObject4 != null && jSONObject4.length() > 0) {
            n0.b(this.o, jSONObject3);
        }
        try {
            jSONObject.put(MetricsSQLiteCacheKt.METRICS_PARAMS, jSONObject3);
        } catch (Throwable th) {
            d().error(4, this.f270a, "Merge params failed", th, new Object[0]);
        }
    }

    public List<String> b() {
        return Arrays.asList(DBHelper.COL_ID, "integer primary key autoincrement", "local_time_ms", "integer", "tea_event_index", "integer", "nt", "integer", "user_id", "integer", MonitorCommonConstants.KEY_SESSION_ID, "varchar", "user_unique_id", "varchar", "user_unique_id_type", "varchar", "ssid", "varchar", "ab_sdk_version", "varchar", "event_type", "integer", "_app_id", "varchar", "properties", "varchar", "local_event_id", "varchar");
    }

    public void b(ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.c));
        contentValues.put("tea_event_index", Long.valueOf(this.d));
        contentValues.put("nt", Integer.valueOf(this.k));
        contentValues.put("user_id", Long.valueOf(this.f));
        contentValues.put(MonitorCommonConstants.KEY_SESSION_ID, this.e);
        contentValues.put("user_unique_id", n0.a((Object) this.g));
        contentValues.put("user_unique_id_type", this.h);
        contentValues.put("ssid", this.i);
        contentValues.put("ab_sdk_version", this.j);
        contentValues.put("event_type", Integer.valueOf(this.l));
        contentValues.put("_app_id", this.m);
        JSONObject jSONObject = this.o;
        contentValues.put("properties", jSONObject != null ? jSONObject.toString() : "");
        contentValues.put("local_event_id", this.p);
    }

    public void b(JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.c);
        jSONObject.put("_app_id", this.m);
        jSONObject.put("properties", this.o);
        jSONObject.put("local_event_id", this.p);
    }

    public String c() {
        StringBuilder sbA = com.bytedance.bdtracker.a.a("sid:");
        sbA.append(this.e);
        return sbA.toString();
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public j3 m6355clone() {
        try {
            j3 j3Var = (j3) super.clone();
            j3Var.p = n0.c();
            return j3Var;
        } catch (CloneNotSupportedException e) {
            d().error(4, this.f270a, "Clone data failed", e, new Object[0]);
            return null;
        }
    }

    public IAppLogLogger d() {
        IAppLogLogger logger = AbstractAppLogLogger.getLogger(this.m);
        return logger != null ? logger : LoggerImpl.global();
    }

    public String e() {
        return null;
    }

    public abstract String f();

    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("k_cls", f());
            b(jSONObject);
        } catch (JSONException e) {
            d().error(4, this.f270a, "JSON handle failed", e, new Object[0]);
        }
        return jSONObject;
    }

    public final JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        try {
            this.n = b(this.c);
            return i();
        } catch (JSONException e) {
            d().error(4, this.f270a, "JSON handle failed", e, new Object[0]);
            return jSONObject;
        }
    }

    public abstract JSONObject i();

    public String toString() {
        String strF = f();
        if (!getClass().getSimpleName().equalsIgnoreCase(strF)) {
            strF = strF + ", " + getClass().getSimpleName();
        }
        String strSubstring = this.e;
        if (strSubstring != null) {
            int iIndexOf = strSubstring.indexOf("-");
            if (iIndexOf >= 0) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
        } else {
            strSubstring = "-";
        }
        return "{" + strF + ", " + c() + ", " + strSubstring + ", " + this.c + "}";
    }
}
