package com.bytedance.bdtracker;

import com.bytedance.applog.log.EventBus;
import com.volcengine.common.contant.CommonConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class j1 implements EventBus.DataFetcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ JSONObject f268a;
    public final /* synthetic */ i1 b;

    public j1(i1 i1Var, JSONObject jSONObject) {
        this.b = i1Var;
        this.f268a = jSONObject;
    }

    @Override // com.bytedance.applog.log.EventBus.DataFetcher
    public Object fetch() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        n0.b(this.f268a, jSONObject2);
        try {
            jSONObject.put(CommonConstants.key_appId, this.b.b.m);
            jSONObject.put("config", jSONObject2);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
