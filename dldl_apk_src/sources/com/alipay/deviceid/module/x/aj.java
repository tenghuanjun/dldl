package com.alipay.deviceid.module.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class aj implements ah {
    @Override // com.alipay.deviceid.module.x.ah
    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(w.class)) {
            return null;
        }
        w wVar = (w) obj;
        HashSet hashSet = new HashSet();
        Class cls = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        for (int i = 0; i < wVar.a.size(); i++) {
            hashSet.add(ad.a(wVar.a(i), cls));
        }
        return hashSet;
    }

    @Override // com.alipay.deviceid.module.x.ah, com.alipay.deviceid.module.x.ai
    public final boolean a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
