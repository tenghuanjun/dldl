package com.igexin.push.core.a.b;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class h extends a {
    private static final String a = "ResponseDeviceidAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals("response_deviceid")) {
                return true;
            }
            com.igexin.push.core.e.e.a().a(jSONObject.getString("deviceid"));
            if (com.igexin.push.core.e.F != null) {
                com.igexin.push.core.a.b.d().i();
            }
            String str = com.igexin.push.core.e.F;
            return true;
        } catch (Exception unused) {
            return true;
        }
    }
}
