package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class v1 extends d1 {
    public final i1 e;

    public v1(Context context, i1 i1Var, k1 k1Var) {
        super(true, false, false);
        this.e = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "ServerId";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        SharedPreferences sharedPreferences = this.e.f;
        String string = sharedPreferences.getString("bd_did", null);
        k1.a(jSONObject, "bd_did", string);
        String string2 = sharedPreferences.getString("install_id", null);
        String string3 = sharedPreferences.getString(this.e.f(), null);
        k1.a(jSONObject, "install_id", string2);
        k1.a(jSONObject, "ssid", string3);
        long j = 0;
        long j2 = sharedPreferences.getLong("register_time", 0L);
        if ((n0.a(string2) && n0.a(string) && n0.a(string3)) || j2 == 0) {
            j = j2;
        } else {
            this.e.f.edit().putLong("register_time", 0L).apply();
        }
        jSONObject.put("register_time", j);
        return true;
    }
}
