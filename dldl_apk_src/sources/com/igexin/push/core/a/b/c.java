package com.igexin.push.core.a.b;

import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.igexin.push.config.a.AnonymousClass5;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c extends a {
    private static final String a = "BlockClientAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has("action") && jSONObject.getString("action").equals("block_client") && jSONObject.has(MediationConstant.EXTRA_DURATION)) {
                long j = jSONObject.getLong(MediationConstant.EXTRA_DURATION) * 1000;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (j != 0) {
                    com.igexin.push.config.d.c = jCurrentTimeMillis + j;
                    com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.config.a.a().new AnonymousClass5(), false, true);
                    com.igexin.push.e.e.c().d();
                }
            }
        } catch (Exception unused) {
        }
        return true;
    }
}
