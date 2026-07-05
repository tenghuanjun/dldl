package com.igexin.push.core.a.b;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f extends a {
    private static final String a = "ReceivedAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals(com.igexin.push.core.b.B)) {
                return true;
            }
            String string = jSONObject.getString("id");
            com.igexin.b.a.c.a.a("ReceivedAction received, cmd id :".concat(String.valueOf(string)), new Object[0]);
            try {
                com.igexin.push.core.e.d.a().a(Long.parseLong(string), false);
                com.igexin.push.core.a.b.d();
                com.igexin.push.core.a.b.g();
                return true;
            } catch (NumberFormatException e) {
                com.igexin.b.a.c.a.a("ReceivedAction|" + e.toString(), new Object[0]);
                return true;
            }
        } catch (Exception e2) {
            com.igexin.b.a.c.a.a("ReceivedAction|" + e2.toString(), new Object[0]);
            return true;
        }
    }
}
