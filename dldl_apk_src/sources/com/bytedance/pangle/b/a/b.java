package com.bytedance.pangle.b.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    public static Field a(Class<?> cls, String str) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        if (declaredField != null && !declaredField.isAccessible()) {
            declaredField.setAccessible(true);
        }
        return declaredField;
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (declaredMethod != null && !declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod;
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) throws NoSuchMethodException {
        Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
        if (declaredConstructor != null && !declaredConstructor.isAccessible()) {
            declaredConstructor.setAccessible(true);
        }
        return declaredConstructor;
    }
}
