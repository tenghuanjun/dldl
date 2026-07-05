package com.alipay.deviceid.module.x;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class ao implements bc {
    protected Method a;
    protected byte[] b;
    protected String c;
    protected int d;
    protected String e;
    protected boolean f;

    public ao(Method method, int i, String str, byte[] bArr, String str2, boolean z) {
        this.a = method;
        this.d = i;
        this.c = str;
        this.b = bArr;
        this.e = str2;
        this.f = z;
    }
}
