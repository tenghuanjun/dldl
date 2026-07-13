package com.bytedance.bdtracker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class q4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p4 f311a = new p4();
    public static final Map<String, String> b = new ConcurrentHashMap();

    public static String a(String str) {
        Map<String, String> map = b;
        String str2 = map.get(str);
        if (str2 != null) {
            return str2;
        }
        String strA = f311a.a(str);
        if (strA != null) {
            map.put(str, strA);
        }
        return strA;
    }
}
