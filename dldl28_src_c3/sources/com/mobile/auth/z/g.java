package com.mobile.auth.z;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class g {
    private static boolean a = false;

    public static boolean a(Context context) {
        try {
            if (a) {
                return true;
            }
            Long lB = h.b(context, "success_limit_time");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (lB == null) {
                h.a(context, "success_limit_time", Long.valueOf(jCurrentTimeMillis));
                return true;
            }
            if (jCurrentTimeMillis - lB.longValue() > 600000) {
                h.a(context, "success_limit_time", Long.valueOf(jCurrentTimeMillis));
                h.a(context, "success_limit_count", (Long) 0L);
                return true;
            }
            Long lB2 = h.b(context, "success_limit_count");
            if (lB2 != null) {
                return lB2.longValue() <= 50;
            }
            h.a(context, "success_limit_count", (Long) 0L);
            return true;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static void b(Context context) {
        try {
            Long lB = h.b(context, "success_limit_count");
            if (lB == null) {
                h.a(context, "success_limit_count", (Long) 0L);
            } else {
                h.a(context, "success_limit_count", Long.valueOf(lB.longValue() + 1));
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static boolean c(Context context) {
        try {
            if (a) {
                return true;
            }
            Long lB = h.b(context, "failed_limit_time");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (lB == null) {
                h.a(context, "failed_limit_time", Long.valueOf(jCurrentTimeMillis));
                return true;
            }
            if (jCurrentTimeMillis - lB.longValue() > 600000) {
                h.a(context, "failed_limit_time", Long.valueOf(jCurrentTimeMillis));
                h.a(context, "count_limit_count", (Long) 0L);
                return true;
            }
            Long lB2 = h.b(context, "count_limit_count");
            if (lB2 != null) {
                return lB2.longValue() <= 50;
            }
            h.a(context, "count_limit_count", (Long) 0L);
            return true;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static void d(Context context) {
        try {
            Long lB = h.b(context, "count_limit_count");
            if (lB == null) {
                h.a(context, "count_limit_count", (Long) 0L);
            } else {
                h.a(context, "count_limit_count", Long.valueOf(lB.longValue() + 1));
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
