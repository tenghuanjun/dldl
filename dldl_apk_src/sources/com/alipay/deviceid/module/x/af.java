package com.alipay.deviceid.module.x;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class af implements ah, ai {
    @Override // com.alipay.deviceid.module.x.ai
    public final Object a(Object obj) throws IllegalAccessException {
        TreeMap treeMap = new TreeMap();
        Class<?> superclass = obj.getClass();
        while (true) {
            Field[] declaredFields = superclass.getDeclaredFields();
            if (superclass.equals(Object.class)) {
                return treeMap;
            }
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Object objB = null;
                    if (field != null && obj != null && !"this$0".equals(field.getName())) {
                        boolean zIsAccessible = field.isAccessible();
                        field.setAccessible(true);
                        Object obj2 = field.get(obj);
                        if (obj2 != null) {
                            field.setAccessible(zIsAccessible);
                            objB = ae.b(obj2);
                        }
                    }
                    if (objB != null) {
                        treeMap.put(field.getName(), objB);
                    }
                }
            }
            superclass = superclass.getSuperclass();
        }
    }

    @Override // com.alipay.deviceid.module.x.ah
    public final Object a(Object obj, Type type) throws IllegalAccessException, InstantiationException {
        if (!obj.getClass().equals(x.class)) {
            return null;
        }
        x xVar = (x) obj;
        Class superclass = (Class) type;
        Object objNewInstance = superclass.newInstance();
        while (!superclass.equals(Object.class)) {
            Field[] declaredFields = superclass.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    String name = field.getName();
                    Type genericType = field.getGenericType();
                    if (xVar.a.containsKey(name)) {
                        field.setAccessible(true);
                        field.set(objNewInstance, ad.a(xVar.a(name), genericType));
                    }
                }
            }
            superclass = superclass.getSuperclass();
        }
        return objNewInstance;
    }

    @Override // com.alipay.deviceid.module.x.ah, com.alipay.deviceid.module.x.ai
    public final boolean a(Class<?> cls) {
        return true;
    }
}
