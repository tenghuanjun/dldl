package com.sqwan.msdk.api;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BeanUtils {
    private BeanUtils() {
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        Field declaredField = getDeclaredField(obj, str);
        if (declaredField == null) {
            throw new IllegalArgumentException("Could not find field [" + str + "] on target [" + obj + "]");
        }
        makeAccessible(declaredField);
        try {
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException unused) {
        }
    }

    protected static Field getDeclaredField(Object obj, String str) {
        return getDeclaredField((Class) obj.getClass(), str);
    }

    protected static Field getDeclaredField(Class cls, String str) {
        while (cls != Object.class) {
            try {
                return cls.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    protected static void makeAccessible(Field field) {
        if (Modifier.isPublic(field.getModifiers()) && Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            return;
        }
        field.setAccessible(true);
    }
}
