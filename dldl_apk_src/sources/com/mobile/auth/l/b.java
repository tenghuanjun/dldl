package com.mobile.auth.l;

import com.mobile.auth.k.e;
import com.mobile.auth.n.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b extends c {
    private final e b;
    private boolean c;

    b(String str, e eVar, String str2, String str3) {
        super(str, eVar, str2, str3);
        this.c = false;
        this.b = eVar;
    }

    public void a(com.cmic.sso.sdk.a aVar) {
        if (this.c) {
            return;
        }
        com.mobile.auth.k.a aVarC = this.b.c();
        if (!aVar.b("isCloseIpv4", false)) {
            aVarC.q(p.a(true));
        }
        if (!aVar.b("isCloseIpv6", false)) {
            aVarC.r(p.b(true));
        }
        aVarC.n(aVarC.u(aVar.b("appkey")));
        this.b.a(aVarC);
        this.b.a(true);
        this.a = this.b.b().toString();
        this.c = true;
    }
}
