package com.mobile.auth.z;

import android.util.Log;
import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class a {
    private static boolean a = false;

    public static void a(String str) {
        try {
            Log.e("uniaccount", "6.1.2 ".concat(String.valueOf(str)));
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void a(boolean z) {
        try {
            a = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
