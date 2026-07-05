package com.sy37sdk.utils;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ReflectionUtils {
    private static Class<?>[] getTypeClass(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        int length = objArr.length;
        Class<?>[] clsArr = new Class[length];
        if (length <= 0) {
            return clsArr;
        }
        for (int i = 0; i < length; i++) {
            clsArr[i] = rawType(objArr[i].getClass());
        }
        return clsArr;
    }

    public static Class<?> rawType(Class<?> cls) {
        if (cls.equals(Boolean.class)) {
            cls = Boolean.TYPE;
        }
        return cls.equals(Integer.class) ? Integer.TYPE : cls.equals(Float.class) ? Float.TYPE : cls.equals(Double.class) ? Double.TYPE : cls.equals(Short.class) ? Short.TYPE : cls.equals(Long.class) ? Long.TYPE : cls.equals(Byte.class) ? Byte.TYPE : cls.equals(Character.class) ? Character.TYPE : cls;
    }

    public static Object invoke(Object obj, Class<?> cls, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object invoke(Object obj, String str, Object[] objArr) {
        return invoke(obj, obj.getClass(), str, getTypeClass(objArr), objArr);
    }
}
