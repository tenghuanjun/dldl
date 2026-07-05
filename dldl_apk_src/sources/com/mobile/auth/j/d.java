package com.mobile.auth.j;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import com.mobile.auth.n.n;
import com.mobile.auth.n.r;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d implements b {
    private b a;

    public void a(b bVar) {
        this.a = bVar;
    }

    @Override // com.mobile.auth.j.b
    public void a(final com.mobile.auth.l.c cVar, final com.mobile.auth.m.c cVar2, final com.cmic.sso.sdk.a aVar) {
        if (!cVar.b()) {
            b(cVar, cVar2, aVar);
            return;
        }
        r rVarA = r.a((Context) null);
        if (Build.VERSION.SDK_INT >= 21) {
            rVarA.a(new r.a() { // from class: com.mobile.auth.j.d.1
                private final AtomicBoolean e = new AtomicBoolean(false);

                @Override // com.mobile.auth.n.r.a
                public void a(final Network network) {
                    if (this.e.getAndSet(true)) {
                        return;
                    }
                    n.a(new n.a(null, aVar) { // from class: com.mobile.auth.j.d.1.1
                        @Override // com.mobile.auth.n.n.a
                        protected void a() {
                            if (network == null) {
                                cVar2.a(com.mobile.auth.m.a.a(102508));
                            } else {
                                com.mobile.auth.n.c.b("WifiChangeInterceptor", "onAvailable");
                                cVar.a(network);
                                d.this.b(cVar, cVar2, aVar);
                            }
                        }
                    });
                }
            });
        } else {
            com.mobile.auth.n.c.a("WifiChangeInterceptor", "低版本不在支持wifi切换");
            cVar2.a(com.mobile.auth.m.a.a(102508));
        }
    }

    public void b(com.mobile.auth.l.c cVar, final com.mobile.auth.m.c cVar2, com.cmic.sso.sdk.a aVar) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(cVar, new com.mobile.auth.m.c() { // from class: com.mobile.auth.j.d.2
                @Override // com.mobile.auth.m.c
                public void a(com.mobile.auth.m.a aVar2) {
                    cVar2.a(aVar2);
                }

                @Override // com.mobile.auth.m.c
                public void a(com.mobile.auth.m.b bVar2) {
                    cVar2.a(bVar2);
                }
            }, aVar);
        }
    }
}
