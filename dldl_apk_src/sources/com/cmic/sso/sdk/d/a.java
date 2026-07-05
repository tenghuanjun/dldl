package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import com.mobile.auth.n.o;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class a {
    private static C0032a<String, String> a = new C0032a<>();

    /* JADX INFO: renamed from: com.cmic.sso.sdk.d.a$a, reason: collision with other inner class name */
    private static class C0032a<K, V> extends HashMap<K, V> {
        private C0032a() {
        }
    }

    public static void a(String str) {
        try {
            String str2 = a.get(str);
            a.put(str, String.valueOf((TextUtils.isEmpty(str2) ? 0 : Integer.parseInt(str2)) + 1));
            a.put(str + "Time", o.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2) {
        a.put(str, str2);
    }
}
