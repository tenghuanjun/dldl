package com.unicom.online.account.kernel;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class ad {
    private static boolean a;

    public static boolean a(Context context) {
        if (a) {
            return true;
        }
        Long lB = f.b(context, "success_limit_time");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (lB == null) {
            f.a(context, "success_limit_time", Long.valueOf(jCurrentTimeMillis));
            return true;
        }
        if (jCurrentTimeMillis - lB.longValue() > 600000) {
            f.a(context, "success_limit_time", Long.valueOf(jCurrentTimeMillis));
            f.a(context, "success_limit_count", (Long) 0L);
            return true;
        }
        Long lB2 = f.b(context, "success_limit_count");
        if (lB2 != null) {
            return lB2.longValue() <= 50;
        }
        f.a(context, "success_limit_count", (Long) 0L);
        return true;
    }

    public static void b(Context context) {
        Long lB = f.b(context, "success_limit_count");
        f.a(context, "success_limit_count", Long.valueOf(lB == null ? 0L : lB.longValue() + 1));
    }

    public static boolean c(Context context) {
        if (a) {
            return true;
        }
        Long lB = f.b(context, "failed_limit_time");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (lB == null) {
            f.a(context, "failed_limit_time", Long.valueOf(jCurrentTimeMillis));
            return true;
        }
        if (jCurrentTimeMillis - lB.longValue() > 600000) {
            f.a(context, "failed_limit_time", Long.valueOf(jCurrentTimeMillis));
            f.a(context, "count_limit_count", (Long) 0L);
            return true;
        }
        Long lB2 = f.b(context, "count_limit_count");
        if (lB2 != null) {
            return lB2.longValue() <= 50;
        }
        f.a(context, "count_limit_count", (Long) 0L);
        return true;
    }

    public static void d(Context context) {
        Long lB = f.b(context, "count_limit_count");
        f.a(context, "count_limit_count", Long.valueOf(lB == null ? 0L : lB.longValue() + 1));
    }
}
