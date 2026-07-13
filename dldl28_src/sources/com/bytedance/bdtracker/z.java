package com.bytedance.bdtracker;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.applog.IActiveCustomParamsCallback;
import com.bytedance.applog.Level;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.convert.BusinessConstant;
import com.bytedance.applog.util.SensitiveUtils;
import com.volcengine.common.contant.CommonConstants;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class z extends a0 {
    public z(c0 c0Var) {
        super(c0Var);
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean c() {
        String str;
        Map<String, String> params;
        k1 k1Var = this.e.i;
        boolean z = false;
        if (k1Var.i() != 0) {
            UriConfig uriConfigE = this.e.e();
            JSONObject jSONObjectE = k1Var.e();
            if (jSONObjectE != null) {
                String strA = this.f.j.a(k1Var.e(), uriConfigE.getActiveUri(), true, Level.L0);
                e3 e3Var = this.f.k;
                e3Var.b.D.debug(11, "Start to active to uri:{} with request:{}...", strA, jSONObjectE);
                StringBuilder sb = new StringBuilder(strA);
                g3 g3Var = e3Var.b.j;
                String str2 = null;
                e3.a(sb, "google_aid", (String) g3Var.a(jSONObjectE, "google_aid", (Object) null, (Class<Object>) String.class));
                float rawOffset = (TimeZone.getDefault().getRawOffset() * 1.0f) / 3600000.0f;
                if (rawOffset < -12.0f) {
                    rawOffset = -12.0f;
                }
                if (rawOffset > 12.0f) {
                    rawOffset = 12.0f;
                }
                e3.a(sb, "timezone", rawOffset + "");
                IActiveCustomParamsCallback activeCustomParams = e3Var.b.getActiveCustomParams();
                if (activeCustomParams != null && (params = activeCustomParams.getParams()) != null && !params.isEmpty()) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        if (entry != null) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                                e3.a(sb, "custom_" + key, value);
                            }
                        }
                    }
                }
                String str3 = (String) g3Var.a(jSONObjectE, "real_package_name", (Object) null, (Class<Object>) String.class);
                if (!TextUtils.isEmpty(str3)) {
                    e3.a(sb, "package", (String) g3Var.a(jSONObjectE, "package", (Object) null, (Class<Object>) String.class));
                    e3.a(sb, "real_package_name", str3);
                }
                e3.a(sb, "carrier", (String) g3Var.a(jSONObjectE, "carrier", (Object) null, (Class<Object>) String.class));
                e3.a(sb, "mcc_mnc", (String) g3Var.a(jSONObjectE, "mcc_mnc", (Object) null, (Class<Object>) String.class));
                e3.a(sb, "sim_region", (String) g3Var.a(jSONObjectE, "sim_region", (Object) null, (Class<Object>) String.class));
                e3.a(sb, "app_version_minor", (String) g3Var.a(jSONObjectE, "app_version_minor", (Object) null, (Class<Object>) String.class));
                SensitiveUtils.addSensitiveParamsForUrlQuery(g3Var, sb, jSONObjectE);
                String strA2 = x4.a((JSONObject) g3Var.a(jSONObjectE, "oaid", (Object) null, (Class<Object>) JSONObject.class));
                if (!TextUtils.isEmpty(strA2)) {
                    e3.a(sb, "oaid", strA2);
                }
                e3.a(sb, BusinessConstant.KEY_CLICK_ID, (String) g3Var.a(jSONObjectE, BusinessConstant.KEY_CLICK_ID, (Object) null, (Class<Object>) String.class));
                e3.a(sb, BusinessConstant.KEY_CLICK_ID_NATURE, (String) g3Var.a(jSONObjectE, BusinessConstant.KEY_CLICK_ID_NATURE, (Object) null, (Class<Object>) String.class));
                e3.a(sb, BusinessConstant.KEY_CLIENT_TUN, (String) g3Var.a(jSONObjectE, BusinessConstant.KEY_CLIENT_TUN, (Object) null, (Class<Object>) String.class));
                e3.a(sb, BusinessConstant.KEY_CLIENT_ANPI, (String) g3Var.a(jSONObjectE, BusinessConstant.KEY_CLIENT_ANPI, (Object) null, (Class<Object>) String.class));
                String string = sb.toString();
                String strA3 = n4.a();
                try {
                    if (!TextUtils.isEmpty("req_id") && !TextUtils.isEmpty(strA3)) {
                        string = Uri.parse(string).buildUpon().appendQueryParameter("req_id", strA3).build().toString();
                    }
                } catch (Throwable th) {
                    e3Var.b.D.error(11, "addQuery", th, new Object[0]);
                }
                try {
                    str = new String(e3Var.b.getNetClient().execute((byte) 0, e3Var.c.a(string), null, e3Var.a(), (byte) 0, true, 60000));
                    try {
                        e3Var.b.D.debug(11, "request active success: {}", str);
                    } catch (Exception e) {
                        e = e;
                        str2 = str;
                        e3Var.b.D.error(11, "request active error", e, new Object[0]);
                        str = str2;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
                JSONObject jSONObjectA = e3Var.a(str);
                if (jSONObjectA != null && "success".equals(jSONObjectA.optString(CommonConstants.KEY_MESSAGE, ""))) {
                    z = true;
                }
            } else {
                this.e.d.D.error("Device header is null", new Object[0]);
            }
        }
        if (z) {
            this.d = true;
        }
        return z;
    }

    @Override // com.bytedance.bdtracker.a0
    public String d() {
        return "Activator";
    }

    @Override // com.bytedance.bdtracker.a0
    public long[] e() {
        return f0.g;
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean f() {
        return true;
    }

    @Override // com.bytedance.bdtracker.a0
    public long g() {
        return DateUtils.MILLIS_PER_HOUR;
    }
}
