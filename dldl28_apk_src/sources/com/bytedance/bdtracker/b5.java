package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.applog.log.LoggerImpl;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class b5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SharedPreferences f221a;

    public b5(Context context) {
        this.f221a = v3.a(context, "device_register_oaid_refine", 0);
    }

    public a5 a() {
        String string = this.f221a.getString("oaid", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            return new a5(jSONObject.optString("id", null), jSONObject.optString("req_id", null), jSONObject.has("is_track_limited") ? Boolean.valueOf(jSONObject.optBoolean("is_track_limited")) : null, jSONObject.has("take_ms") ? Long.valueOf(jSONObject.optLong("take_ms", -1L)) : null, jSONObject.has("time") ? Long.valueOf(jSONObject.optLong("time", -1L)) : null, jSONObject.has("query_times") ? Integer.valueOf(jSONObject.optInt("query_times", -1)) : null, jSONObject.has("hw_id_version_code") ? Long.valueOf(jSONObject.optLong("hw_id_version_code", -1L)) : null);
        } catch (Throwable th) {
            LoggerImpl.global().error(1, "Create model failed", th, new Object[0]);
            return null;
        }
    }

    public void a(a5 a5Var) {
        if (a5Var == null) {
            return;
        }
        this.f221a.edit().putString("oaid", a5Var.b().toString()).apply();
    }
}
