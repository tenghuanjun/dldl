package com.unionpay.a;

import java.net.URL;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class d {
    private String b;
    private String e;
    private int a = 0;
    private HashMap c = null;
    private byte[] d = null;

    public d(String str) {
        this.b = str;
    }

    public d(String str, byte b) {
        this.b = str;
    }

    public final URL a() {
        try {
            return new URL(this.b);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void a(String str) {
        if (str != null) {
            this.d = str.getBytes();
            this.e = str;
        }
    }

    public final String b() {
        return this.a == 1 ? "POST" : "GET";
    }

    public final void b(String str) {
        this.b = str;
    }

    public final String c() {
        return this.e;
    }

    public final HashMap d() {
        return this.c;
    }
}
