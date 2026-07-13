package com.bytedance.bdtracker;

import android.content.ContentValues;
import android.text.TextUtils;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class w3 extends j3 {
    public long s;
    public long t;
    public String u;

    @Override // com.bytedance.bdtracker.j3
    public j3 a(JSONObject jSONObject) {
        d().error(4, this.f270a, "Not allowed", new Object[0]);
        return this;
    }

    @Override // com.bytedance.bdtracker.j3
    public List<String> b() {
        return null;
    }

    @Override // com.bytedance.bdtracker.j3
    public void b(ContentValues contentValues) {
        d().error(4, this.f270a, "Not allowed", new Object[0]);
    }

    @Override // com.bytedance.bdtracker.j3
    public void b(JSONObject jSONObject) {
        d().error(4, this.f270a, "Not allowed", new Object[0]);
    }

    @Override // com.bytedance.bdtracker.j3
    public String c() {
        return String.valueOf(this.s);
    }

    @Override // com.bytedance.bdtracker.j3
    public String f() {
        return "terminate";
    }

    @Override // com.bytedance.bdtracker.j3
    public JSONObject i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(MonitorCommonConstants.KEY_SESSION_ID, this.e);
        jSONObject.put("stop_timestamp", this.t / 1000);
        jSONObject.put("duration", this.s / 1000);
        jSONObject.put("datetime", this.n);
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
        if (!TextUtils.isEmpty(this.j)) {
            jSONObject.put("ab_sdk_version", this.j);
        }
        if (!TextUtils.isEmpty(this.u)) {
            jSONObject.put("uuid_changed", true);
            if (!TextUtils.equals(this.u, this.e)) {
                jSONObject.put("original_session_id", this.u);
            }
        }
        a(jSONObject, "");
        return jSONObject;
    }
}
