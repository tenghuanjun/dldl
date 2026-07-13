package com.mobile.auth.z;

import android.util.Log;
import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f815a = false;

    public static void a(String str) {
        try {
            Log.e("uniaccount", "6.1.2 ".concat(String.valueOf(str)));
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void a(boolean z) {
        try {
            f815a = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
