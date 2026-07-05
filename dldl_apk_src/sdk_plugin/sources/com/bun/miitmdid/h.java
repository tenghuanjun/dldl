package com.bun.miitmdid;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class h {
    public Class<?> a;
    public Object b;
    public String c;
    public Class<?>[] d;
    public Object[] e;
    public Class<?> f;
    public boolean g = false;

    public h(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        this.a = cls;
        this.b = obj;
        this.c = str;
        this.d = clsArr;
        this.e = objArr;
    }

    public h(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr, Class<?> cls2) {
        this.a = cls;
        this.b = obj;
        this.c = str;
        this.d = clsArr;
        this.e = objArr;
        this.f = cls2;
    }

    public native Object a();

    public Class<?> b() {
        return this.f;
    }

    public native boolean c();
}
