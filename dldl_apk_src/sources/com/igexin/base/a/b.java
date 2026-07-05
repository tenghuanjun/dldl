package com.igexin.base.a;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
final class b implements Runnable {
    private static b b;
    final List<c> a = new ArrayList();

    private b() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(this, 5L, 5L, TimeUnit.SECONDS);
    }

    public static synchronized b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(com.igexin.base.a.c r23) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.base.a.b.a(com.igexin.base.a.c):boolean");
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (c cVar : this.a) {
            if (cVar.isEnabled()) {
                if (cVar.a.size() >= cVar.b || SystemClock.elapsedRealtime() - cVar.d >= cVar.c) {
                    a(cVar);
                    cVar.d = SystemClock.elapsedRealtime();
                }
            }
        }
    }
}
