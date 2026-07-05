package com.unionpay.a;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class a {
    private SSLContext a = null;
    private String b;

    public a(String str) {
        this.b = str;
    }

    private static SSLContext a(String str) throws IOException {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{new b(str)}, null);
            return sSLContext;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public final SSLContext a() {
        if (this.a == null) {
            this.a = a(this.b);
        }
        return this.a;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(a.class);
    }

    public int hashCode() {
        return a.class.hashCode();
    }
}
