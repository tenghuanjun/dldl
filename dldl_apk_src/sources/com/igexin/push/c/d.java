package com.igexin.push.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d implements b {
    public static final String a = "NormalModel";

    @Override // com.igexin.push.c.b
    public final long a() {
        long j;
        long j2;
        long j3;
        boolean zA = com.igexin.push.f.c.a(System.currentTimeMillis());
        boolean zB = com.igexin.push.f.c.b();
        com.igexin.push.core.e.k = com.igexin.push.f.c.f();
        boolean z = com.igexin.push.core.e.m;
        boolean z2 = com.igexin.push.core.e.p;
        boolean z3 = com.igexin.push.core.e.k;
        com.igexin.b.a.c.a.a("NormalModel|isSdkOn = " + com.igexin.push.core.e.m + " isPushOn = " + com.igexin.push.core.e.p + " checkIsSilentTime = " + zA + " isBlockEndTime = " + zB + " isNetworkAvailable = " + com.igexin.push.core.e.k, new Object[0]);
        if (!com.igexin.push.core.e.k || !com.igexin.push.core.e.m || !com.igexin.push.core.e.p || zA || !zB) {
            com.igexin.b.a.c.a.a("NormalModel|reconnect stop, interval= 20min ++++", new Object[0]);
            return com.igexin.push.config.c.g;
        }
        if (com.igexin.push.core.e.L <= 0) {
            j3 = 1;
        } else {
            if (com.igexin.push.core.e.L <= 300) {
                j = com.igexin.push.core.e.L;
                j2 = 150;
            } else if (com.igexin.push.core.e.L <= com.igexin.push.config.c.i) {
                j = com.igexin.push.core.e.L;
                j2 = 500;
            } else if (com.igexin.push.core.e.L <= com.igexin.push.config.c.k) {
                j = com.igexin.push.core.e.L;
                j2 = com.igexin.push.config.c.j;
            } else {
                j = com.igexin.push.core.e.L;
                j2 = com.igexin.push.config.c.l;
            }
            j3 = j + j2;
        }
        com.igexin.push.core.e.L = j3;
        if (com.igexin.push.core.e.L > com.igexin.push.config.c.g) {
            com.igexin.push.core.e.L = com.igexin.push.config.c.g;
        }
        long j4 = com.igexin.push.core.e.L;
        com.igexin.b.a.c.a.a("NormalModel|after add auto reconnect delay time = ".concat(String.valueOf(j4)), new Object[0]);
        return j4;
    }
}
