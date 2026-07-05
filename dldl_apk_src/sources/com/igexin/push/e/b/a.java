package com.igexin.push.e.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a extends f {
    private static volatile a b;
    private List<c> a;

    private a() {
        super(60000L, (byte) 0);
        this.p = true;
        this.a = new ArrayList();
    }

    public static a g() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private void h() {
        a(360000L, TimeUnit.MILLISECONDS);
    }

    public final boolean a(c cVar) {
        List<c> list = this.a;
        return (list == null || list.contains(cVar) || !this.a.add(cVar)) ? false : true;
    }

    @Override // com.igexin.push.e.b.f
    protected final void b() {
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.m();
        for (c cVar : this.a) {
            if (cVar.b()) {
                cVar.a();
                cVar.a(System.currentTimeMillis());
            }
        }
        a(360000L, TimeUnit.MILLISECONDS);
        com.igexin.b.a.b.e.a().a((Object) this);
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return 0;
    }
}
