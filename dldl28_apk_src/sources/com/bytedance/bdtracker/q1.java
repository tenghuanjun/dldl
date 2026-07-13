package com.bytedance.bdtracker;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class q1 extends d1 {
    public final Context e;

    public q1(Context context) {
        super(true, true);
        this.e = context;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Net";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        k1.a(jSONObject, "access", i4.a(this.e, true));
        return true;
    }
}
