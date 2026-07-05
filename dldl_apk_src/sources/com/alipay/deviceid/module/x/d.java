package com.alipay.deviceid.module.x;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class d {
    private static d b;
    public Map<String, String> a = null;

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    public final String a(Context context) {
        String string;
        b(context);
        Map<String, String> map = this.a;
        if (map == null) {
            string = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList);
            for (int i = 0; i < arrayList.size(); i++) {
                String str = (String) arrayList.get(i);
                String str2 = map.get(str);
                String str3 = "";
                if (str2 == null) {
                    str2 = "";
                }
                StringBuilder sb = new StringBuilder();
                if (i != 0) {
                    str3 = com.alipay.sdk.sys.a.b;
                }
                sb.append(str3);
                sb.append(str);
                sb.append("=");
                sb.append(str2);
                stringBuffer.append(sb.toString());
            }
            string = stringBuffer.toString();
        }
        return h.a(string);
    }

    public final void b(Context context) {
        TreeMap treeMap = new TreeMap();
        this.a = treeMap;
        HashMap map = new HashMap();
        map.put("AC4", bx.b(context));
        treeMap.putAll(map);
        Map<String, String> map2 = this.a;
        l.a();
        HashMap map3 = new HashMap();
        map3.put("AE1", l.b());
        StringBuilder sb = new StringBuilder();
        sb.append(l.c() ? "1" : "0");
        map3.put("AE2", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(l.a(context) ? "1" : "0");
        map3.put("AE3", sb2.toString());
        map3.put("AE4", l.d());
        map3.put("AE5", l.e());
        map3.put("AE6", l.f());
        map3.put("AE7", l.g());
        map3.put("AE8", l.h());
        map3.put("AE9", l.i());
        map3.put("AE10", l.j());
        map3.put("AE11", l.k());
        map3.put("AE12", l.l());
        map3.put("AE13", l.m());
        map3.put("AE14", l.n());
        map3.put("AE15", l.a("ro.kernel.qemu", "0"));
        map3.put("AE18", "FC_RELEASE");
        map2.putAll(map3);
        Map<String, String> map4 = this.a;
        k kVarA = k.a();
        HashMap map5 = new HashMap();
        bv bvVarA = bu.a(context);
        String strA = k.a(context);
        String strB = k.b(context);
        String strE = k.e();
        String strL = k.l(context);
        boolean z = true;
        if (bvVarA != null && e.a(e.c(bvVarA.a), strA) && e.a(e.c(bvVarA.b), strB) && e.a(e.c(bvVarA.c), strE) && e.a(e.c(bvVarA.e), strL)) {
            z = false;
        }
        if (bvVarA != null) {
            if (e.a(strA)) {
                strA = e.c(bvVarA.a);
            }
            if (e.a(strB)) {
                strB = e.c(bvVarA.b);
            }
            if (e.a(strE)) {
                strE = e.c(bvVarA.c);
            }
            if (e.a(strL)) {
                strL = e.c(bvVarA.e);
            }
        }
        if (z) {
            bu.a(context, new bv(strA, strB, strE, "", strL));
        }
        map5.put("AD1", strA);
        map5.put("AD2", strB);
        map5.put("AD3", k.f(context));
        map5.put("AD5", k.h(context));
        map5.put("AD6", k.i(context));
        map5.put("AD7", k.j(context));
        map5.put("AD8", strE);
        map5.put("AD9", k.k(context));
        map5.put("AD10", strL);
        map5.put("AD11", k.f());
        map5.put("AD12", kVarA.g());
        map5.put("AD13", k.h());
        map5.put("AD14", k.i());
        map5.put("AD15", k.j());
        map5.put("AD16", k.k());
        map5.put("AD20", k.l());
        map5.put("AD23", k.n());
        map5.put("AD24", e.f(k.g(context)));
        map5.put("AD26", k.e(context));
        map5.put("AD27", k.s());
        map5.put("AD28", k.u());
        map5.put("AD29", k.w());
        map5.put("AD30", k.t());
        map5.put("AD31", k.v());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(k.q() - (k.q() % 1000));
        map5.put("AD32", sb3.toString());
        map5.put("AD34", k.o(context));
        map5.put("AD37", k.p());
        map5.put("AD38", k.o());
        map5.put("AD39", k.c(context));
        map4.putAll(map5);
        Map<String, String> map6 = this.a;
        HashMap map7 = new HashMap();
        map7.put("AA1", context.getPackageName());
        j.a();
        map7.put("AA2", j.a(context));
        map7.put("AA3", "APPSecuritySDK-FC");
        map7.put("AA4", "6.0.7.20211109");
        map6.putAll(map7);
    }
}
