package com.igexin.push.b;

import android.text.TextUtils;
import com.igexin.push.b.b;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class g extends h implements i {
    private static g e;

    private g() {
        super(com.igexin.push.core.e.ap, com.igexin.push.core.e.ar);
        this.d.j = false;
    }

    public static synchronized g a() {
        if (e == null) {
            e = new g();
        }
        return e;
    }

    @Override // com.igexin.push.b.i
    public final void a(int i, d dVar) {
        e eVarA;
        if (dVar == null || TextUtils.isEmpty(dVar.a()) || (eVarA = a(dVar.a())) == null) {
            return;
        }
        a(dVar);
        eVarA.a();
        m();
        if (i == b.a.a) {
            l();
        }
    }

    @Override // com.igexin.push.b.i
    public final void b() {
    }

    @Override // com.igexin.push.b.h
    public final int c() {
        return b.EnumC0059b.b;
    }

    @Override // com.igexin.push.b.h
    public final i d() {
        return this;
    }
}
