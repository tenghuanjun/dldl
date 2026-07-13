package com.bytedance.bdtracker;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class a5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f215a;
    public final String b;
    public final Boolean c;
    public final Long d;
    public final Long e;
    public final Integer f;
    public final Long g;

    public a5(String str, String str2, Boolean bool, Long l, Long l2, Integer num, Long l3) {
        this.f215a = str;
        this.b = str2;
        this.c = bool;
        this.d = l;
        this.e = l2;
        this.f = num;
        this.g = l3;
    }

    public Map<String, String> a() {
        HashMap map = new HashMap();
        z4.a(map, "id", this.f215a);
        z4.a(map, "req_id", this.b);
        z4.a(map, "is_track_limited", String.valueOf(this.c));
        z4.a(map, "take_ms", String.valueOf(this.d));
        z4.a(map, "time", String.valueOf(this.e));
        z4.a(map, "query_times", String.valueOf(this.f));
        z4.a(map, "hw_id_version_code", String.valueOf(this.g));
        return map;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        z4.a(jSONObject, "id", this.f215a);
        z4.a(jSONObject, "req_id", this.b);
        z4.a(jSONObject, "is_track_limited", this.c);
        z4.a(jSONObject, "take_ms", this.d);
        z4.a(jSONObject, "time", this.e);
        z4.a(jSONObject, "query_times", this.f);
        z4.a(jSONObject, "hw_id_version_code", this.g);
        return jSONObject;
    }

    public String toString() {
        return b().toString();
    }
}
