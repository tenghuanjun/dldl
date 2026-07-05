package com.nirvana.tools.requestqueue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class a {
    private static volatile a a;
    private MessageDigest b;

    private a() {
        this.b = null;
        try {
            this.b = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public final synchronized String a(String str) {
        if (this.b == null) {
            return str;
        }
        try {
            this.b.update(str.getBytes("UTF-8"));
            return new String(this.b.digest());
        } catch (Exception unused) {
            return str;
        }
    }
}
