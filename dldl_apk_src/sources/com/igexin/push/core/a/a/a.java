package com.igexin.push.core.a.a;

import com.igexin.push.c.c;
import com.igexin.push.core.k;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a extends com.igexin.push.core.a.a {
    private static final String b = "HeartbeatAction";

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (!(obj instanceof com.igexin.push.c.c.f)) {
            return true;
        }
        c.b.a.c();
        com.igexin.b.a.c.a.a("heartbeatRsp", new Object[0]);
        k.a().a(k.a.a);
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
