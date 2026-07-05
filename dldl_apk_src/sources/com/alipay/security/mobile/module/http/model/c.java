package com.alipay.security.mobile.module.http.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class c extends a {
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final String f = "APPKEY_ERROR";
    public static final String g = "SUCCESS";
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p = "";

    public int a() {
        return this.a ? com.alipay.security.mobile.module.a.a.a(this.h) ? 2 : 1 : f.equals(this.b) ? 3 : 2;
    }

    public boolean b() {
        return "1".equals(this.j);
    }

    public String c() {
        String str = this.k;
        return str == null ? "0" : str;
    }
}
