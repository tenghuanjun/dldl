package com.mobile.auth.m;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, com.mobile.auth.f.b> f743a = new ConcurrentHashMap<>(16);
    private static ConcurrentHashMap<String, com.cmic.sso.sdk.a> b = new ConcurrentHashMap<>();

    public static void a(String str, com.cmic.sso.sdk.a aVar) {
        if (str == null || aVar == null) {
            return;
        }
        b.put(str, aVar);
    }

    public static void a(String str, com.mobile.auth.f.b bVar) {
        f743a.put(str, bVar);
    }

    public static boolean a() {
        return f743a.isEmpty();
    }

    public static boolean a(String str) {
        return !f743a.containsKey(str);
    }

    public static void b(String str) {
        f743a.remove(str);
    }

    public static com.mobile.auth.f.b c(String str) {
        return f743a.get(str);
    }

    public static com.cmic.sso.sdk.a d(String str) {
        return str != null ? b.get(str) : new com.cmic.sso.sdk.a(0);
    }
}
