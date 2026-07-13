package com.bytedance.bdtracker;

import com.bytedance.applog.log.EventBus;
import com.volcengine.common.contant.CommonConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class f3 implements EventBus.DataFetcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f250a;
    public final /* synthetic */ String b;
    public final /* synthetic */ JSONObject c;
    public final /* synthetic */ JSONObject d;
    public final /* synthetic */ byte e;
    public final /* synthetic */ long f;
    public final /* synthetic */ e3 g;

    public f3(e3 e3Var, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, byte b, long j) {
        this.g = e3Var;
        this.f250a = str;
        this.b = str2;
        this.c = jSONObject;
        this.d = jSONObject2;
        this.e = b;
        this.f = j;
    }

    @Override // com.bytedance.applog.log.EventBus.DataFetcher
    public Object fetch() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommonConstants.key_appId, this.g.b.m);
            jSONObject.put("nid", this.f250a);
            jSONObject.put("url", this.b);
            jSONObject.put("data", this.c);
            jSONObject.put("header", this.d);
            jSONObject.put("method", (int) this.e);
            jSONObject.put("time", this.f);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
