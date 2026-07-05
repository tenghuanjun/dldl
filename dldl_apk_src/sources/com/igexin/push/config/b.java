package com.igexin.push.config;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    private static b a;

    private b() {
    }

    public static synchronized b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0034, code lost:
    
        r2.getString(r4);
        com.igexin.b.a.c.a.a("PUSH_DOMAIN:" + r2.getString(r4), new java.lang.Object[0]);
        r2 = r2.getString(r4);
        com.igexin.push.config.SDKUrlConfig.setXfrAddressIps(new java.lang.String[]{"socket://xfr." + r2 + ":5224"});
        com.igexin.push.config.SDKUrlConfig.getXfrAddress();
        com.igexin.b.a.c.a.a("XFR_ADDRESS_IPS:" + com.igexin.push.config.SDKUrlConfig.getXfrAddress()[0], new java.lang.Object[0]);
        com.igexin.push.config.SDKUrlConfig.XFR_ADDRESS_BAK = new java.lang.String[]{"socket://xfr_bak." + r2 + ":5224"};
        r3 = new java.lang.StringBuilder("XFR_ADDRESS_IPS_BAK:");
        r3.append(com.igexin.push.config.SDKUrlConfig.XFR_ADDRESS_BAK[0]);
        com.igexin.b.a.c.a.a(r3.toString(), new java.lang.Object[0]);
        com.igexin.push.config.SDKUrlConfig.BI_ADDRESS_IPS = new java.lang.String[]{"http://bi." + r2 + "/api.php"};
        r3 = new java.lang.StringBuilder("BI_ADDRESS_IPS:");
        r3.append(com.igexin.push.config.SDKUrlConfig.BI_ADDRESS_IPS[0]);
        com.igexin.b.a.c.a.a(r3.toString(), new java.lang.Object[0]);
        com.igexin.push.config.SDKUrlConfig.CONFIG_ADDRESS_IPS = new java.lang.String[]{"http://config." + r2 + "/api.php"};
        r2 = new java.lang.StringBuilder("CONFIG_ADDRESS_IPS:");
        r2.append(com.igexin.push.config.SDKUrlConfig.CONFIG_ADDRESS_IPS[0]);
        com.igexin.b.a.c.a.a(r2.toString(), new java.lang.Object[0]);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b() {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.b.b():boolean");
    }

    private static String c() {
        return null;
    }

    private static int d() {
        return 0;
    }
}
