package com.bytedance.bdtracker;

import com.bytedance.applog.log.EventBus;
import com.volcengine.common.contant.CommonConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b0 extends a0 {

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f217a;

        public a(JSONObject jSONObject) {
            this.f217a = jSONObject;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            n0.b(this.f217a, jSONObject);
            try {
                jSONObject.put(CommonConstants.key_appId, b0.this.f.m);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public b0(c0 c0Var) {
        super(c0Var, c0Var.e.f.getLong("app_log_last_config_time", 0L));
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x015c  */
    @Override // com.bytedance.bdtracker.a0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c() throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 735
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.b0.c():boolean");
    }

    @Override // com.bytedance.bdtracker.a0
    public String d() {
        return "Configure";
    }

    @Override // com.bytedance.bdtracker.a0
    public long[] e() {
        return f0.h;
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean f() {
        return true;
    }

    @Override // com.bytedance.bdtracker.a0
    public long g() {
        return this.e.e.f.getLong("fetch_interval", 21600000L);
    }
}
