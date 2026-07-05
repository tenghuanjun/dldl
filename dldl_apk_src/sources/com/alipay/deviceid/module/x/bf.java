package com.alipay.deviceid.module.x;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class bf implements InvocationHandler {
    protected aq a;
    protected Class<?> b;
    protected bg c;

    public bf(aq aqVar, Class<?> cls, bg bgVar) {
        this.a = aqVar;
        this.b = cls;
        this.c = bgVar;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.c.a(method, objArr);
    }
}
