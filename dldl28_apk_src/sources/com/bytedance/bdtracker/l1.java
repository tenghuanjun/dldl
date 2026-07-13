package com.bytedance.bdtracker;

import com.bytedance.applog.log.EventBus;
import com.volcengine.common.contant.CommonConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class l1 implements EventBus.DataFetcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ JSONObject f287a;
    public final /* synthetic */ k1 b;

    public l1(k1 k1Var, JSONObject jSONObject) {
        this.b = k1Var;
        this.f287a = jSONObject;
    }

    @Override // com.bytedance.applog.log.EventBus.DataFetcher
    public Object fetch() {
        JSONObject jSONObject = new JSONObject();
        n0.b(this.f287a, jSONObject);
        try {
            jSONObject.put(CommonConstants.key_appId, this.b.i.m);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
