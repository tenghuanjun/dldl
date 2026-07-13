package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.applog.InitConfig;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class r1 extends d1 {
    public final Context e;
    public final i1 f;

    public r1(Context context, i1 i1Var) {
        super(true, false);
        this.e = context;
        this.f = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Oaid";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        i1 i1Var = this.f;
        SharedPreferences sharedPreferences = i1Var.f;
        InitConfig initConfig = i1Var.c;
        if (initConfig != null && !initConfig.isOaidEnabled()) {
            return true;
        }
        Map mapA = x4.a(this.e);
        if (mapA == null) {
            return false;
        }
        jSONObject.put("oaid", new JSONObject(mapA));
        return true;
    }
}
