package com.alipay.sdk.util;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class l {
    public static final String a = "resultStatus";
    public static final String b = "memo";
    public static final String c = "result";

    public static Map<String, String> a(String str) {
        Map<String, String> mapA = a();
        try {
            return b(str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.g, th);
            return mapA;
        }
    }

    private static Map<String, String> a() {
        com.alipay.sdk.app.k kVarB = com.alipay.sdk.app.k.b(com.alipay.sdk.app.k.CANCELED.a());
        HashMap map = new HashMap();
        map.put(a, Integer.toString(kVarB.a()));
        map.put(b, kVarB.b());
        map.put("result", "");
        return map;
    }

    public static Map<String, String> b(String str) {
        String[] strArrSplit = str.split(i.b);
        HashMap map = new HashMap();
        for (String str2 : strArrSplit) {
            String strSubstring = str2.substring(0, str2.indexOf("={"));
            map.put(strSubstring, a(str2, strSubstring));
        }
        return map;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(i.d));
    }
}
