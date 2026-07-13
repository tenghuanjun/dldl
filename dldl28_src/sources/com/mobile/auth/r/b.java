package com.mobile.auth.r;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile JSONObject f785a;
    private static volatile long b;

    public static synchronized JSONObject a(Context context) {
        try {
            if (System.currentTimeMillis() - b > 1000 || f785a == null) {
                f785a = com.mobile.auth.f.a.a(context).c(context);
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
        return f785a;
    }
}
