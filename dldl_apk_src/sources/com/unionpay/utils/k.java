package com.unionpay.utils;

import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class k {
    private static k f;
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";

    public static k a() {
        if (f == null) {
            f = Locale.getDefault().toString().startsWith("zh") ? new l() : new m();
        }
        return f;
    }
}
