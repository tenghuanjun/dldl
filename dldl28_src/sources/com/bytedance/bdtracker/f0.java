package com.bytedance.bdtracker;

import com.bytedance.applog.Level;
import com.bytedance.applog.game.GameReportHelper;
import com.bytedance.applog.log.EventBus;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.volcengine.common.contant.CommonConstants;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class f0 extends a0 {
    public static final long[] g = {60000, 60000, 60000, 120000, 120000, 180000, 180000, 360000, 360000, 540000, 540000};
    public static final long[] h = {180000, 180000, 360000, 360000, 540000, 540000, 720000, 720000};
    public static final long[] i = {10000, 10000, 20000, 20000, 60000, 60000, 120000, 120000, 180000, 180000, 360000, 360000, 540000, 540000};

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f249a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ String d;
        public final /* synthetic */ String e;
        public final /* synthetic */ String f;

        public a(String str, String str2, String str3, String str4, String str5, String str6) {
            this.f249a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, f0.this.f.m);
                jSONObject.put("did", this.f249a);
                jSONObject.put("installId", this.b);
                jSONObject.put("ssid", this.c);
                jSONObject.put("bdDid", this.d);
                jSONObject.put("uuid", this.e);
                jSONObject.put("uuidType", this.f);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public f0(c0 c0Var) {
        super(c0Var, c0Var.i.d.optLong("register_time", 0L));
    }

    public synchronized boolean a(JSONObject jSONObject) {
        this.e.d.D.debug(1, "Start do register work", new Object[0]);
        String strOptString = jSONObject.optString("user_unique_id");
        String strOptString2 = jSONObject.optString("user_unique_id_type");
        c0 c0Var = this.e;
        k1 k1Var = c0Var.i;
        i1 i1Var = c0Var.e;
        i1Var.c.getPreInstallCallback();
        Map<String, Object> commonHeader = i1Var.c.getCommonHeader();
        jSONObject.put("req_id", n4.f298a.b(new Object[0]));
        if (i1Var.j()) {
            try {
                boolean z = x4.f339a.b(this.f.n).c;
                this.e.d.D.debug(1, "Oaid maySupport: {}", Boolean.valueOf(z));
                jSONObject.put("oaid_may_support", z);
            } catch (Throwable th) {
                this.e.d.D.error(1, "Check oaid maySupport failed.", th, new Object[0]);
            }
        }
        if (commonHeader != null) {
            for (Map.Entry<String, Object> entry : commonHeader.entrySet()) {
                if (entry.getValue() != null) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
        }
        JSONObject jSONObjectB = b(jSONObject);
        if (jSONObjectB == null) {
            this.e.d.D.debug(1, "Register finished", new Object[0]);
            return false;
        }
        String strOptString3 = jSONObjectB.optString(MonitorConstants.KEY_DEVICE_ID, "");
        String strOptString4 = jSONObjectB.optString("install_id", "");
        String strOptString5 = jSONObjectB.optString("ssid", "");
        String strOptString6 = jSONObjectB.optString("bd_did", "");
        String strOptString7 = jSONObjectB.optString("cd", "");
        if (n0.d(strOptString5)) {
            this.e.c().a(strOptString, strOptString5);
        }
        boolean zA = k1Var.a(jSONObjectB, strOptString, strOptString3, strOptString4, strOptString5, strOptString6, strOptString7);
        if (zA) {
            c0 c0Var2 = this.e;
            c0Var2.a(c0Var2.m);
            if (this.e.e.c.isReportOaidEnable()) {
                this.e.a();
            }
            a1.a("device_register_end", (EventBus.DataFetcher) new a(strOptString3, strOptString4, strOptString5, strOptString6, strOptString, strOptString2));
        }
        return zA;
    }

    public JSONObject b(JSONObject jSONObject) {
        this.e.d.D.debug(1, "Start to invokeRegister", new Object[0]);
        try {
            if (jSONObject.opt("oaid") instanceof String) {
                jSONObject.remove("oaid");
                if (this.e.i != null && this.e.i.e() != null) {
                    jSONObject.put("oaid", this.e.i.e().opt("oaid"));
                }
            }
            JSONObject jSONObjectB = e3.b(jSONObject);
            return this.f.k.a(this.f.j.a(jSONObject, this.e.e().getRegisterUri(), true, Level.L1), jSONObjectB);
        } catch (Throwable th) {
            this.e.d.D.error(1, "Request to register server failed.", th, new Object[0]);
            return null;
        }
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean c() {
        JSONObject jSONObject = new JSONObject();
        n0.a(jSONObject, this.e.i.e());
        return a(jSONObject);
    }

    @Override // com.bytedance.bdtracker.a0
    public String d() {
        return GameReportHelper.REGISTER;
    }

    @Override // com.bytedance.bdtracker.a0
    public long[] e() {
        int i2 = this.e.i.i();
        if (i2 == 0) {
            return i;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                return g;
            }
            this.e.d.D.error(1, "Unknown register state", new Object[0]);
        }
        return h;
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean f() {
        return true;
    }

    @Override // com.bytedance.bdtracker.a0
    public long g() {
        return this.e.n.i ? 21600000L : 43200000L;
    }

    public JSONObject c(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectB = e3.b(jSONObject);
            return this.f.k.b(this.e.e().getReportOaidUri(), jSONObjectB);
        } catch (Throwable th) {
            this.e.d.D.error(1, "Report oaid failed.", th, new Object[0]);
            return null;
        }
    }
}
