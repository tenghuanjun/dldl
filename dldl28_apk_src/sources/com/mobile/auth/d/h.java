package com.mobile.auth.d;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f522a = "h";

    public static String a(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", i);
            jSONObject.put("msg", str);
            return jSONObject.toString();
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a(f522a, "Json parse error", th);
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
