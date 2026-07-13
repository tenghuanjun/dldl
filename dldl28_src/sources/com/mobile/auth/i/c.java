package com.mobile.auth.i;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f722a;
    private com.mobile.auth.l.c b;
    private final com.mobile.auth.h.a c = new com.mobile.auth.h.a();

    public void a(b bVar) {
        this.f722a = bVar;
    }

    @Override // com.mobile.auth.i.b
    public void a(com.mobile.auth.k.c cVar, com.mobile.auth.l.c cVar2, com.cmic.sso.sdk.a aVar) {
        b(cVar, cVar2, aVar);
    }

    public void b(final com.mobile.auth.k.c cVar, final com.mobile.auth.l.c cVar2, final com.cmic.sso.sdk.a aVar) {
        if (this.f722a != null) {
            this.b = new com.mobile.auth.l.c() { // from class: com.mobile.auth.i.c.1
                @Override // com.mobile.auth.l.c
                public void a(com.mobile.auth.l.a aVar2) {
                    if (!cVar.j()) {
                        cVar2.a(aVar2);
                        return;
                    }
                    com.mobile.auth.m.c.a("RetryAndRedirectInterceptor", "retry: " + cVar.a());
                    c.this.b(cVar, cVar2, aVar);
                }

                @Override // com.mobile.auth.l.c
                public void a(com.mobile.auth.l.b bVar) {
                    com.mobile.auth.k.c cVarB;
                    if (bVar.d()) {
                        cVarB = c.this.c.a(cVar, bVar, aVar);
                    } else {
                        if (TextUtils.isEmpty(c.this.c.a())) {
                            cVar2.a(bVar);
                            return;
                        }
                        cVarB = c.this.c.b(cVar, bVar, aVar);
                    }
                    c.this.b(cVarB, cVar2, aVar);
                }
            };
            if (cVar.g()) {
                this.f722a.a(cVar, this.b, aVar);
            } else {
                cVar2.a(com.mobile.auth.l.a.a(200025));
            }
        }
    }
}
