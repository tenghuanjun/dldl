package com.bytedance.bdtracker;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.b;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class s implements b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t3 f320a;

    public s(t3 t3Var) {
        this.f320a = t3Var;
    }

    @Override // com.bytedance.bdtracker.b.e
    public j3 a() {
        t3 t3Var = (t3) this.f320a.m6355clone();
        JSONObject jSONObjectOptJSONObject = t3Var.h().optJSONObject(MetricsSQLiteCacheKt.METRICS_PARAMS);
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
        }
        try {
            jSONObjectOptJSONObject.put("$page_duration", t3Var.s);
        } catch (Throwable th) {
            LoggerImpl.global().error("JSON handle failed", th, new Object[0]);
        }
        q3 q3Var = new q3("$bav2b_page_leave");
        q3Var.a(0L);
        q3Var.o = jSONObjectOptJSONObject;
        return q3Var;
    }
}
