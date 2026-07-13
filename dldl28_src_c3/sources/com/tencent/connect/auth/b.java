package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    public static b a = null;
    static final /* synthetic */ boolean d = true;
    private static int e;
    public HashMap<String, a> b = new HashMap<>();
    public final String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /* JADX INFO: compiled from: ProGuard */
    public static class a {
        public IUiListener a;
        public com.tencent.connect.auth.a b;
        public String c;
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public static int b() {
        int i = e + 1;
        e = i;
        return i;
    }

    public String a(a aVar) {
        int iB = b();
        try {
            this.b.put("" + iB, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "" + iB;
    }

    public String c() {
        int iCeil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = charArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iCeil; i++) {
            stringBuffer.append(charArray[(int) (Math.random() * ((double) length))]);
        }
        return stringBuffer.toString();
    }
}
