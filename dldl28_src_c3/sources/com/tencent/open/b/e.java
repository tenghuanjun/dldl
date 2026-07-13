package com.tencent.open.b;

import com.tencent.open.utils.m;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class e {
    protected static e a;

    public void a(int i, String str, String str2, String str3, String str4, Long l, int i2, int i3, String str5) {
    }

    protected e() {
    }

    public static synchronized e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        h.a().a(m.a(str, str3, str4, str5, str2, str6), str2, true);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        h.a().a(m.a(str, str4, str5, str3, str2, str6, "", str7, str8, "", "", ""), str2, false);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        h.a().a(m.a(str, str4, str5, str3, str2, str6, str7, "", "", str8, str9, str10), str2, false);
    }
}
