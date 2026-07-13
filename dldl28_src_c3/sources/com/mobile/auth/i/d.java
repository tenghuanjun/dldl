package com.mobile.auth.i;

import android.content.Context;
import android.net.Network;
import com.mobile.auth.m.n;
import com.mobile.auth.m.r;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class d implements b {
    private b a;

    public void a(b bVar) {
        this.a = bVar;
    }

    @Override // com.mobile.auth.i.b
    public void a(final com.mobile.auth.k.c cVar, final com.mobile.auth.l.c cVar2, final com.cmic.sso.sdk.a aVar) {
        if (cVar.b()) {
            r.a((Context) null).a(new r.a() { // from class: com.mobile.auth.i.d.1
                private final AtomicBoolean e = new AtomicBoolean(false);

                @Override // com.mobile.auth.m.r.a
                public void a(final Network network) {
                    if (this.e.getAndSet(true)) {
                        return;
                    }
                    n.a(new n.a(null, aVar) { // from class: com.mobile.auth.i.d.1.1
                        @Override // com.mobile.auth.m.n.a
                        protected void a() {
                            if (network == null) {
                                cVar2.a(com.mobile.auth.l.a.a(102508));
                            } else {
                                com.mobile.auth.m.c.b("WifiChangeInterceptor", "onAvailable");
                                cVar.a(network);
                                d.this.b(cVar, cVar2, aVar);
                            }
                        }
                    });
                }
            });
        } else {
            b(cVar, cVar2, aVar);
        }
    }

    public void b(com.mobile.auth.k.c cVar, final com.mobile.auth.l.c cVar2, com.cmic.sso.sdk.a aVar) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(cVar, new com.mobile.auth.l.c() { // from class: com.mobile.auth.i.d.2
                @Override // com.mobile.auth.l.c
                public void a(com.mobile.auth.l.a aVar2) {
                    cVar2.a(aVar2);
                }

                @Override // com.mobile.auth.l.c
                public void a(com.mobile.auth.l.b bVar2) {
                    cVar2.a(bVar2);
                }
            }, aVar);
        }
    }
}
