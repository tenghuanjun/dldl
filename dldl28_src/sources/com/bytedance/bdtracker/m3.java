package com.bytedance.bdtracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class m3 extends j3 {
    public String s;
    public String t;

    public m3() {
        this.s = null;
        this.t = null;
    }

    public m3(String str, JSONObject jSONObject) {
        this.t = null;
        this.s = str;
        if (jSONObject != null) {
            this.t = jSONObject.toString();
        }
        this.l = 0;
    }

    @Override // com.bytedance.bdtracker.j3
    public int a(Cursor cursor) {
        super.a(cursor);
        this.t = cursor.getString(14);
        this.s = cursor.getString(15);
        return 16;
    }

    @Override // com.bytedance.bdtracker.j3
    public j3 a(JSONObject jSONObject) {
        super.a(jSONObject);
        this.t = jSONObject.optString(MetricsSQLiteCacheKt.METRICS_PARAMS, null);
        this.s = jSONObject.optString("category", null);
        return this;
    }

    @Override // com.bytedance.bdtracker.j3
    public List<String> b() {
        List<String> listB = super.b();
        ArrayList arrayList = new ArrayList(listB.size());
        arrayList.addAll(listB);
        arrayList.addAll(Arrays.asList(MetricsSQLiteCacheKt.METRICS_PARAMS, "varchar", "category", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.bdtracker.j3
    public void b(ContentValues contentValues) {
        super.b(contentValues);
        contentValues.put(MetricsSQLiteCacheKt.METRICS_PARAMS, this.t);
        contentValues.put("category", this.s);
    }

    @Override // com.bytedance.bdtracker.j3
    public void b(JSONObject jSONObject) throws JSONException {
        super.b(jSONObject);
        jSONObject.put(MetricsSQLiteCacheKt.METRICS_PARAMS, this.t);
        jSONObject.put("category", this.s);
    }

    @Override // com.bytedance.bdtracker.j3
    public String c() {
        StringBuilder sbA = a.a("param:");
        sbA.append(this.t);
        sbA.append(" category:");
        sbA.append(this.s);
        return sbA.toString();
    }

    @Override // com.bytedance.bdtracker.j3
    public String f() {
        return "custom_event";
    }

    @Override // com.bytedance.bdtracker.j3
    public JSONObject i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(MonitorCommonConstants.KEY_SESSION_ID, this.e);
        long j = this.f;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        jSONObject.put("user_unique_id", TextUtils.isEmpty(this.g) ? JSONObject.NULL : this.g);
        if (!TextUtils.isEmpty(this.h)) {
            jSONObject.put("$user_unique_id_type", this.h);
        }
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ssid", this.i);
        }
        if (n0.d(this.t)) {
            try {
                JSONObject jSONObject2 = new JSONObject(this.t);
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    Object obj = jSONObject2.get(next);
                    if (jSONObject.opt(next) != null) {
                        d().warn(4, this.f270a, "自定义事件存在重复的key", new Object[0]);
                    }
                    jSONObject.put(next, obj);
                }
            } catch (Exception e) {
                d().warn(4, this.f270a, "解析事件参数失败", e);
            }
        }
        return jSONObject;
    }
}
