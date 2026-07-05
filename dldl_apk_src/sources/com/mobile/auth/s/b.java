package com.mobile.auth.s;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b {
    private static volatile JSONObject a;
    private static volatile long b;

    public static synchronized JSONObject a(Context context) {
        try {
            if (System.currentTimeMillis() - b > 1000 || a == null) {
                a = com.mobile.auth.g.a.a(context).c(context);
                b = System.currentTimeMillis();
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return a;
    }
}
