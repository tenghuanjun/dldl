package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import com.mobile.auth.m.o;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static C0160a<String, String> f407a = new C0160a<>();

    /* JADX INFO: renamed from: com.cmic.sso.sdk.d.a$a, reason: collision with other inner class name */
    private static class C0160a<K, V> extends HashMap<K, V> {
        private C0160a() {
        }
    }

    public static void a(String str) {
        try {
            String str2 = f407a.get(str);
            f407a.put(str, String.valueOf((!TextUtils.isEmpty(str2) ? Integer.parseInt(str2) : 0) + 1));
            f407a.put(str + "Time", o.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2) {
        f407a.put(str, str2);
    }
}
