package com.mobile.auth.z;

import android.util.Log;
import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class t {
    private static boolean a = true;
    private static int b = 2;
    private static long c;
    private static int d;

    public static void a() {
        try {
            d = 0;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void a(String str) {
        try {
            b("\n" + str + "\n");
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

    public static void b(String str) {
        try {
            d++;
            System.currentTimeMillis();
            System.currentTimeMillis();
            c = System.currentTimeMillis();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void c(String str) {
        try {
            if (a) {
                Log.d("UniAccount", p.b() + " " + str);
                b(str);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void d(String str) {
        try {
            if (a) {
                Log.e("UniAccount", p.b() + " " + str);
                b(str);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void e(String str) {
        try {
            Log.e("UniAccount", p.b() + " " + str);
            b(str);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
