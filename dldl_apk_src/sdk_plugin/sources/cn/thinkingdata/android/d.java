package cn.thinkingdata.android;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class d {
    private final TimeUnit a;
    private long b = SystemClock.elapsedRealtime();
    private long c = 0;
    private long d;

    d(TimeUnit timeUnit) {
        this.a = timeUnit;
    }

    String a() {
        return a(this.d);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0048 A[Catch: Exception -> 0x000c, TryCatch #0 {Exception -> 0x000c, blocks: (B:4:0x0007, B:10:0x0015, B:12:0x001a, B:25:0x0042, B:28:0x0048, B:29:0x004d, B:31:0x0052, B:15:0x0021, B:17:0x0029, B:18:0x002c, B:20:0x0034, B:21:0x0036, B:22:0x0038, B:24:0x003e), top: B:35:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004d A[Catch: Exception -> 0x000c, TRY_LEAVE, TryCatch #0 {Exception -> 0x000c, blocks: (B:4:0x0007, B:10:0x0015, B:12:0x001a, B:25:0x0042, B:28:0x0048, B:29:0x004d, B:31:0x0052, B:15:0x0021, B:17:0x0029, B:18:0x002c, B:20:0x0034, B:21:0x0036, B:22:0x0038, B:24:0x003e), top: B:35:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.String a(long r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 >= 0) goto Le
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch: java.lang.Exception -> Lc
            return r6
        Lc:
            r6 = move-exception
            goto L5f
        Le:
            r1 = 86400000(0x5265c00, double:4.2687272E-316)
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 <= 0) goto L1a
            java.lang.String r6 = r5.a(r1)     // Catch: java.lang.Exception -> Lc
            return r6
        L1a:
            java.util.concurrent.TimeUnit r1 = r5.a     // Catch: java.lang.Exception -> Lc
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Exception -> Lc
            if (r1 != r2) goto L21
            goto L42
        L21:
            java.util.concurrent.TimeUnit r1 = r5.a     // Catch: java.lang.Exception -> Lc
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Exception -> Lc
            r3 = 1148846080(0x447a0000, float:1000.0)
            if (r1 != r2) goto L2c
            float r6 = (float) r6     // Catch: java.lang.Exception -> Lc
            float r6 = r6 / r3
            goto L43
        L2c:
            java.util.concurrent.TimeUnit r1 = r5.a     // Catch: java.lang.Exception -> Lc
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES     // Catch: java.lang.Exception -> Lc
            r4 = 1114636288(0x42700000, float:60.0)
            if (r1 != r2) goto L38
            float r6 = (float) r6     // Catch: java.lang.Exception -> Lc
            float r6 = r6 / r3
        L36:
            float r6 = r6 / r4
            goto L43
        L38:
            java.util.concurrent.TimeUnit r1 = r5.a     // Catch: java.lang.Exception -> Lc
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.HOURS     // Catch: java.lang.Exception -> Lc
            if (r1 != r2) goto L42
            float r6 = (float) r6     // Catch: java.lang.Exception -> Lc
            float r6 = r6 / r3
            float r6 = r6 / r4
            goto L36
        L42:
            float r6 = (float) r6     // Catch: java.lang.Exception -> Lc
        L43:
            r7 = 0
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 >= 0) goto L4d
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch: java.lang.Exception -> Lc
            goto L5e
        L4d:
            java.util.Locale r7 = java.util.Locale.CHINA     // Catch: java.lang.Exception -> Lc
            java.lang.String r1 = "%.3f"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> Lc
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch: java.lang.Exception -> Lc
            r2[r0] = r6     // Catch: java.lang.Exception -> Lc
            java.lang.String r6 = java.lang.String.format(r7, r1, r2)     // Catch: java.lang.Exception -> Lc
        L5e:
            return r6
        L5f:
            r6.printStackTrace()
            java.lang.String r6 = java.lang.String.valueOf(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.d.a(long):java.lang.String");
    }

    String b() {
        return a((SystemClock.elapsedRealtime() - this.b) + this.c);
    }

    void b(long j) {
        this.d = j;
    }

    long c() {
        return this.d;
    }

    void c(long j) {
        this.c = j;
    }

    long d() {
        return this.c;
    }

    void d(long j) {
        this.b = j;
    }

    long e() {
        return this.b;
    }
}
