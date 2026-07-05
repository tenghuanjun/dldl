package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class d {
    public static synchronized Map<String, String> a() {
        HashMap map;
        map = new HashMap();
        try {
            new com.alipay.apmobilesecuritysdk.c.b();
            map.put("AE16", "");
        } catch (Throwable unused) {
        }
        return map;
    }

    public static synchronized Map<String, String> a(Context context) {
        HashMap map;
        com.alipay.security.mobile.module.deviceinfo.d dVarA = com.alipay.security.mobile.module.deviceinfo.d.a();
        com.alipay.security.mobile.module.deviceinfo.b bVarA = com.alipay.security.mobile.module.deviceinfo.b.a();
        map = new HashMap();
        map.put("AE1", dVarA.b());
        StringBuilder sb = new StringBuilder();
        sb.append(dVarA.c() ? "1" : "0");
        map.put("AE2", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(dVarA.a(context) ? "1" : "0");
        map.put("AE3", sb2.toString());
        map.put("AE4", dVarA.d());
        map.put("AE5", dVarA.e());
        map.put("AE6", dVarA.f());
        map.put("AE7", dVarA.g());
        map.put("AE8", dVarA.h());
        map.put("AE9", dVarA.i());
        map.put("AE10", dVarA.j());
        map.put("AE11", dVarA.k());
        map.put("AE12", dVarA.l());
        map.put("AE13", dVarA.m());
        map.put("AE14", dVarA.n());
        map.put("AE15", dVarA.o());
        map.put("AE21", bVarA.h());
        return map;
    }
}
