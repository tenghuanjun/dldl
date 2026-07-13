package com.mobile.auth.z;

import android.util.Log;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f834a = true;
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
            b(StringUtils.LF + str + StringUtils.LF);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void a(boolean z) {
        try {
            f834a = z;
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
            if (f834a) {
                Log.d("UniAccount", p.b() + StringUtils.SPACE + str);
                b(str);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void d(String str) {
        try {
            if (f834a) {
                Log.e("UniAccount", p.b() + StringUtils.SPACE + str);
                b(str);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void e(String str) {
        try {
            Log.e("UniAccount", p.b() + StringUtils.SPACE + str);
            b(str);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
