package com.alipay.deviceid.module.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class al {
    public static Class<?> a(Type type) {
        while (!(type instanceof Class)) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalArgumentException("TODO");
            }
            type = ((ParameterizedType) type).getRawType();
        }
        return (Class) type;
    }

    public static boolean a(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(Boolean.class) || cls.equals(Short.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Void.class);
    }
}
