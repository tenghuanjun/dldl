package com.igexin.b.a.b.a.a;

import android.text.TextUtils;
import com.igexin.b.a.b.a.a.a;
import com.igexin.push.config.SDKUrlConfig;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends a {
    public static final int a = -2037;
    private static final String j = "GS-C";
    private static final int k = 10000;
    private Socket P;
    private com.igexin.b.a.b.a.a.a.d l;

    public b(com.igexin.b.a.b.a.a.a.d dVar) {
        super(-2037, null);
        this.l = dVar;
    }

    @Override // com.igexin.b.a.b.f, com.igexin.b.a.d.f, com.igexin.b.a.d.a.a
    public final void a() {
        Socket socket;
        super.a();
        com.igexin.b.a.c.a.a("GS-C|sc dispose", new Object[0]);
        if (this.l != null) {
            if (this.g == a.EnumC0055a.c) {
                this.l.a();
            } else if (this.g == a.EnumC0055a.b) {
                if (!TextUtils.isEmpty(this.h)) {
                    this.l.a(new Exception(this.h));
                }
            } else if (this.g == a.EnumC0055a.a && (socket = this.P) != null) {
                this.l.a(socket);
            }
        }
        this.l = null;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        com.igexin.push.b.c.a().d().a();
        String connectAddress = SDKUrlConfig.getConnectAddress();
        try {
            String[] strArrA = com.igexin.b.a.b.g.a(connectAddress);
            String str = strArrA[1];
            int i = Integer.parseInt(strArrA[2]);
            com.igexin.b.a.c.a.a("GS-C|start connect :  " + connectAddress + " *********", new Object[0]);
            com.igexin.b.a.b.a.a.a.d dVar = this.l;
            if (dVar != null) {
                dVar.b();
            }
            this.P = new Socket();
            try {
                this.P.connect(new InetSocketAddress(str, i), 10000);
                com.igexin.b.a.c.a.a("GS-C|connected :  " + connectAddress + " #########", new Object[0]);
                this.P.getLocalAddress();
                this.P.getLocalPort();
                com.igexin.b.a.c.a.a("GS-C|local-" + this.P.getLocalAddress() + ":" + this.P.getLocalPort(), new Object[0]);
                if (this.g != a.EnumC0055a.c) {
                    this.g = a.EnumC0055a.a;
                }
            } catch (Exception e) {
                if (this.g != a.EnumC0055a.c) {
                    this.g = a.EnumC0055a.b;
                    this.h = e.toString();
                }
            }
            this.f = true;
        } catch (Exception e2) {
            com.igexin.b.a.c.a.a("GS-C|ips invalid, " + e2.toString(), new Object[0]);
            throw e2;
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return -2037;
    }

    @Override // com.igexin.b.a.b.a.a.a
    public final void c_() {
        boolean z = this.i;
        boolean z2 = this.f;
        this.g = a.EnumC0055a.c;
    }
}
