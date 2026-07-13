package com.bytedance.bdtracker;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class g1 extends d1 {
    public final i1 e;

    public g1(i1 i1Var) {
        super(true, false);
        this.e = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Cdid";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        String strA = x4.a(this.e.f);
        if (TextUtils.isEmpty(strA)) {
            return false;
        }
        jSONObject.put("cdid", strA);
        return true;
    }
}
