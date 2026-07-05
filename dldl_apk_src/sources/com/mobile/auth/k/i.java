package com.mobile.auth.k;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class i {
    private static ConcurrentHashMap<String, com.mobile.auth.e.e> a = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, com.cmic.sso.sdk.a> b = new ConcurrentHashMap<>();

    public static void a(String str, com.cmic.sso.sdk.a aVar) {
        if (str == null || aVar == null) {
            return;
        }
        b.put(str, aVar);
    }

    public static void a(String str, com.mobile.auth.e.e eVar) {
        if (str == null || eVar == null) {
            return;
        }
        a.put(str, eVar);
    }

    public static boolean a() {
        return a.isEmpty();
    }

    public static boolean a(String str) {
        if (str != null) {
            return !a.containsKey(str);
        }
        return true;
    }

    public static void b(String str) {
        if (str != null) {
            a.remove(str);
            b.remove(str);
        }
    }

    public static com.mobile.auth.e.e c(String str) {
        if (str != null) {
            return a.get(str);
        }
        return null;
    }

    public static com.cmic.sso.sdk.a d(String str) {
        return str != null ? b.get(str) : new com.cmic.sso.sdk.a(0);
    }
}
