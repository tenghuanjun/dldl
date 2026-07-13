package com.bytedance.bdtracker;

import com.bytedance.applog.Level;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.volcengine.common.contant.CommonConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class y extends a0 {
    public long g;
    public JSONObject h;

    public y(c0 c0Var) {
        super(c0Var);
        this.g = 0L;
        this.h = null;
    }

    public synchronized JSONObject a(int i) {
        boolean z;
        s2 s2Var;
        JSONObject jSONObjectA;
        c0 c0Var = this.e;
        i1 i1Var = c0Var.e;
        k1 k1Var = c0Var.i;
        if (k1Var.i() != 0 && k1Var.e() != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = this.h;
            if (jSONObject != null && jCurrentTimeMillis - this.g < this.e.f223a) {
                return jSONObject;
            }
            this.g = jCurrentTimeMillis;
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("header", k1Var.e());
                jSONObject2.put("magic_tag", "ss_app_log");
                jSONObject2.put("_gen_time", jCurrentTimeMillis);
            } catch (Throwable th) {
                this.f.D.error(2, "Set header failed", th, new Object[0]);
            }
            String strA = this.f.j.a(k1Var.e(), this.e.e().getAbUri(), true, Level.L1);
            e3 e3Var = this.f.k;
            String strA2 = e3.a(strA, e4.b);
            e3Var.b.D.debug(11, "Start to get ab config to uri:{} with request:{}...", strA2, jSONObject2);
            try {
                String str = new String(e3Var.b.getNetClient().execute((byte) 1, strA2, jSONObject2, e3Var.a(), (byte) 0, true, i));
                e3Var.b.D.debug(11, "Get ab config with response:{}", str);
                jSONObjectA = e3Var.a(str);
            } finally {
                if (z) {
                }
            }
            JSONObject jSONObjectOptJSONObject = (jSONObjectA == null || !"success".equals(jSONObjectA.optString(CommonConstants.KEY_MESSAGE, ""))) ? null : jSONObjectA.optJSONObject("data");
            if (jSONObjectOptJSONObject != null) {
                this.h = jSONObjectOptJSONObject;
                JSONObject jSONObjectA2 = i1Var.a();
                boolean z2 = !(jSONObjectA2 != null ? jSONObjectA2.toString().equals(jSONObjectOptJSONObject.toString()) : n0.b((Object) jSONObjectA2, (Object) jSONObjectOptJSONObject));
                this.f.D.debug(2, "getAbConfig changed:{}", Boolean.valueOf(z2));
                k1Var.a(jSONObjectOptJSONObject);
                v0 v0Var = this.f.y;
                if (v0Var != null) {
                    v0Var.onRemoteAbConfigGet(z2, jSONObjectOptJSONObject);
                }
                return jSONObjectOptJSONObject;
            }
        }
        return null;
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean c() {
        try {
            return a(60000) != null;
        } catch (Throwable th) {
            this.f.D.error(2, "Do fetch config failed", th, new Object[0]);
            return false;
        }
    }

    @Override // com.bytedance.bdtracker.a0
    public String d() {
        return "AbConfigure";
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
        long j = this.e.e.f.getLong("abtest_fetch_interval", 0L);
        return j < MonitorCommonConstants.STOP_MORE_CHANNEL_INTERVAL ? MonitorCommonConstants.STOP_MORE_CHANNEL_INTERVAL : j;
    }
}
