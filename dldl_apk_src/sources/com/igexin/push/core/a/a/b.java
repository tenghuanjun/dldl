package com.igexin.push.core.a.a;

import com.igexin.push.c.c.h;
import com.igexin.push.core.l;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends com.igexin.push.core.a.a {
    private static final String b = "KeyNegotiateResultAction";

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (obj instanceof h) {
            h hVar = (h) obj;
            boolean z = hVar.b == 0;
            com.igexin.b.a.c.a.a("KeyNegotiateResultAction|KeyNego result = " + ((int) hVar.b), new Object[0]);
            if (z) {
                com.igexin.b.a.c.a.a("KeyNegotiateResultAction|KeyNego success and login", new Object[0]);
                l.a();
                l.b();
            }
        }
        return true;
    }

    @Override // com.igexin.push.core.a.a
    public final void b() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean c() {
        return false;
    }
}
