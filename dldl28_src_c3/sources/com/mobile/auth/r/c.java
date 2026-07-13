package com.mobile.auth.r;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c implements com.mobile.auth.f.b {
    private Map<String, String> a = new HashMap();
    private com.mobile.auth.f.b b;

    public c(com.mobile.auth.f.b bVar) {
        this.b = bVar;
    }

    public Map<String, String> a() {
        try {
            return this.a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    @Override // com.mobile.auth.f.b
    public void a(int i, JSONObject jSONObject) {
        try {
            Map<String, String> map = this.a;
            if (map != null && !map.isEmpty()) {
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                for (Map.Entry<String, String> entry : this.a.entrySet()) {
                    try {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            com.mobile.auth.f.b bVar = this.b;
            if (bVar != null) {
                bVar.a(i, jSONObject);
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
