package com.alipay.deviceid.module.x;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class z implements ah, ai {
    @Override // com.alipay.deviceid.module.x.ai
    public final Object a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (Object[]) obj) {
            arrayList.add(ae.b(obj2));
        }
        return arrayList;
    }

    @Override // com.alipay.deviceid.module.x.ah
    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(w.class)) {
            return null;
        }
        w wVar = (w) obj;
        if (type instanceof GenericArrayType) {
            throw new IllegalArgumentException("Does not support generic array!");
        }
        Class<?> componentType = ((Class) type).getComponentType();
        int size = wVar.a.size();
        Object objNewInstance = Array.newInstance(componentType, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, ad.a(wVar.a(i), componentType));
        }
        return objNewInstance;
    }

    @Override // com.alipay.deviceid.module.x.ah, com.alipay.deviceid.module.x.ai
    public final boolean a(Class<?> cls) {
        return cls.isArray();
    }
}
