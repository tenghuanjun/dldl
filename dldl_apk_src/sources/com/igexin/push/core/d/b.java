package com.igexin.push.core.d;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements d {
    private static final String a = "GTConfigProxy";
    private static volatile b b;
    private d c = new a();

    private b() {
    }

    private void a(d dVar) {
        this.c = dVar;
    }

    public static b c() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    @Override // com.igexin.push.core.d.d
    public final Map<String, String> a() {
        d dVar = this.c;
        if (dVar != null) {
            return dVar.a();
        }
        return null;
    }

    @Override // com.igexin.push.core.d.d
    public final boolean a(Map<String, String> map) {
        d dVar = this.c;
        if (dVar != null) {
            return dVar.a(map);
        }
        return false;
    }

    @Override // com.igexin.push.core.d.d
    public final boolean b() {
        d dVar = this.c;
        if (dVar != null) {
            return dVar.b();
        }
        return false;
    }
}
