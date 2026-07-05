package com.igexin.push.core.a.b;

import com.igexin.push.core.m;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class k extends a {
    private static final String a = com.igexin.push.config.c.a + "_UnBindAliasResultAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        com.igexin.b.a.c.a.a(a + "|unbind alias result resp data = " + jSONObject, new Object[0]);
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals("response_unbind")) {
                return true;
            }
            m.a().c(jSONObject.getString("sn"), jSONObject.getString("result"));
            return true;
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(a + "|" + e.toString(), new Object[0]);
            return true;
        }
    }
}
