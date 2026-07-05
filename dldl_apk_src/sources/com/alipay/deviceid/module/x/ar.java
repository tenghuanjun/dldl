package com.alipay.deviceid.module.x;

import android.content.Context;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class ar extends bd {
    Context a;

    public ar(Context context) {
        this.a = context;
    }

    @Override // com.alipay.deviceid.module.x.bd
    public final <T> T a(Class<T> cls, final bh bhVar) {
        be beVar = new be(new aq() { // from class: com.alipay.deviceid.module.x.ar.1
            @Override // com.alipay.deviceid.module.x.aq
            public final String a() {
                return bhVar.a;
            }

            @Override // com.alipay.deviceid.module.x.aq
            public final bi b() {
                return au.a(ar.this.a.getApplicationContext());
            }

            @Override // com.alipay.deviceid.module.x.aq
            public final bh c() {
                return bhVar;
            }

            @Override // com.alipay.deviceid.module.x.aq
            public final boolean d() {
                return bhVar.c;
            }
        });
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new bf(beVar.a, cls, beVar.b));
    }
}
