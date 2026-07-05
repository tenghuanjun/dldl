package com.mobile.auth.d;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class h {
    private static final String a = h.class.getSimpleName();

    public static String a(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", i);
            jSONObject.put("msg", str);
            return jSONObject.toString();
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a(a, "Json parse error", th);
                return "";
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return null;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return null;
                }
            }
        }
    }
}
