package com.mobile.auth.k;

import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class y {
    public static String a() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean a(com.mobile.auth.f.a aVar) {
        return (aVar == null || aVar.q() == 0 || aVar.p() == 0 || System.currentTimeMillis() > p.b("logCloseTime", 0L) + ((long) (((aVar.q() * 60) * 60) * 1000))) ? false : true;
    }

    public static String b() {
        return c().replace("-", "");
    }

    private static String c() {
        return UUID.randomUUID().toString();
    }
}
