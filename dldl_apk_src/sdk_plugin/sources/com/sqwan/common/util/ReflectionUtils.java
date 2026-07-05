package com.sqwan.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ReflectionUtils {
    public static Object getFieldValue(Object obj, String str) {
        Field declaredField = getDeclaredField(obj, str);
        if (declaredField == null) {
            throw new IllegalArgumentException("Could not find field [" + str + "] on target [" + obj + "]");
        }
        makeAccessible(declaredField);
        try {
            return declaredField.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        Field declaredField = getDeclaredField(obj, str);
        if (declaredField == null) {
            throw new IllegalArgumentException("Could not find field [" + str + "] on target [" + obj + "]");
        }
        makeAccessible(declaredField);
        try {
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Class getSuperClassGenricType(Class cls, int i) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        if (i >= actualTypeArguments.length || i < 0 || !(actualTypeArguments[i] instanceof Class)) {
            return Object.class;
        }
        return (Class) actualTypeArguments[i];
    }

    public static <T> Class<T> getSuperGenericType(Class cls) {
        return getSuperClassGenricType(cls, 0);
    }

    public static Method getDeclaredMethod(Object obj, String str, Class<?>[] clsArr) {
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                return superclass.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
            }
        }
        return null;
    }

    public static void makeAccessible(Field field) {
        if (Modifier.isPublic(field.getModifiers())) {
            return;
        }
        field.setAccessible(true);
    }

    public static Field getDeclaredField(Object obj, String str) {
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                return superclass.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
            }
        }
        return null;
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) throws InvocationTargetException {
        Method declaredMethod = getDeclaredMethod(obj, str, clsArr);
        if (declaredMethod == null) {
            throw new IllegalArgumentException("Could not find method [" + str + "] on target [" + obj + "]");
        }
        declaredMethod.setAccessible(true);
        try {
            return declaredMethod.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
