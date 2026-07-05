package com.huya.mtp.hyns.protocol;

import com.huya.mtp.hyns.NSProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSSimpleProtocol<E> extends NSProtocol {
    private E mApi;

    public void setApi(E e) {
        this.mApi = e;
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public boolean accept(Class<?> cls) {
        E e = this.mApi;
        if (e != null) {
            return cls.isAssignableFrom(e.getClass());
        }
        return false;
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public <T> T get(Class<T> cls) {
        E e = this.mApi;
        if (e == null) {
            throw new RuntimeException("api is null, did you forget to call setApi?");
        }
        Class<?> cls2 = e.getClass();
        if (!cls.isAssignableFrom(cls2)) {
            throw new RuntimeException("api is incompatible for class:" + cls + " while instance class is:" + cls2);
        }
        return this.mApi;
    }
}
