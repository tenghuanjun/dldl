package com.mobile.auth.k;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.k.u;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class l {
    private static String b;
    private static String c;
    private static boolean a = s.a();
    private static long d = 0;

    static class a extends u.a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ long c;
        final /* synthetic */ String d;

        a(Context context, String str, long j, String str2) {
            this.a = context;
            this.b = str;
            this.c = j;
            this.d = str2;
        }

        @Override // com.mobile.auth.k.u.a
        protected void a() {
            f.b("PhoneScripUtils", "start save scrip to sp in sub thread");
            l.c(this.a, this.b, this.c, this.d);
        }
    }

    private static int a(String str) {
        String strB;
        if (TextUtils.isEmpty(c)) {
            strB = p.b("pre_sim_key", "");
            c = strB;
        } else {
            strB = c;
        }
        if (TextUtils.isEmpty(strB)) {
            return 0;
        }
        return strB.equals(str) ? 1 : 2;
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String strB = p.b("phonescripcache", "");
        if (TextUtils.isEmpty(strB)) {
            f.a("PhoneScripUtils", "null");
            return null;
        }
        d = p.b("phonescripstarttime", 0L);
        String strB2 = e.b(context, strB);
        b = strB2;
        return strB2;
    }

    public static void a(Context context, String str, long j, String str2) {
        b = str;
        d = j;
        c = str2;
        if (a || TextUtils.isEmpty(str2)) {
            return;
        }
        u.a(new a(context, str, j, str2));
    }

    public static void a(boolean z) {
        p.a("phonescripcache");
        p.a("phonescripstarttime");
        p.a("pre_sim_key");
        if (z) {
            b = null;
            c = null;
            d = 0L;
        }
    }

    public static boolean a() {
        return a;
    }

    private static boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        f.b("PhoneScripUtils", j + "");
        f.b("PhoneScripUtils", jCurrentTimeMillis + "");
        return j - jCurrentTimeMillis > com.igexin.push.config.c.l;
    }

    public static boolean a(com.cmic.sso.sdk.a aVar) {
        int iA = a(aVar.c(!aVar.a("keyIsSimKeyICCID", false) ? "imsi" : "iccid"));
        aVar.b("imsiState", iA + "");
        f.b("PhoneScripUtils", "simState = " + iA);
        if (iA != 1) {
            return false;
        }
        if (a) {
            f.b("PhoneScripUtils", "phone is root");
            a(false);
        }
        return b();
    }

    private static boolean b() {
        f.b("PhoneScripUtils", b + " " + c + " " + d);
        if (TextUtils.isEmpty(b)) {
            return !TextUtils.isEmpty(p.b("phonescripcache", "")) && a(p.b("phonescripstarttime", 0L));
        }
        return a(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str, long j, String str2) {
        String strA = e.a(context, str);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        p.a("phonescripcache", strA);
        p.a("phonescripstarttime", j);
        p.a("pre_sim_key", str2);
    }
}
