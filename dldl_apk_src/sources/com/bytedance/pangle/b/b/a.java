package com.bytedance.pangle.b.b;

import com.bytedance.pangle.log.ZeusLogger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private static Method a;
    private static Method b;
    private static Method c;
    private static Method d;

    static {
        try {
            a = Class.class.getDeclaredMethod("getDeclaredField", String.class);
            b = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            c = Class.class.getDeclaredMethod("getDeclaredConstructor", Class[].class);
            d = Class.class.getDeclaredMethod("forName", String.class);
        } catch (Throwable th) {
            ZeusLogger.errReport(ZeusLogger.TAG_INIT, "DoubleReflectorinit failed", th);
        }
    }

    public static Field a(Class<?> cls, String str) {
        Throwable th;
        Field field;
        Method method = a;
        Field field2 = null;
        if (method != null) {
            try {
                field = (Field) method.invoke(cls, str);
                if (field != null) {
                    try {
                        field.setAccessible(true);
                    } catch (Throwable th2) {
                        th = th2;
                        ZeusLogger.w(ZeusLogger.TAG, "DoubleReflector" + String.format("getField %s#%s failed !!!", cls.getName(), str), th);
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                field = null;
            }
            field2 = field;
        }
        return field2 != null ? field2 : com.bytedance.pangle.b.a.a.a(cls, str);
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Throwable th;
        Method method;
        Method method2 = b;
        Method method3 = null;
        if (method2 != null) {
            try {
                method = (Method) method2.invoke(cls, str, clsArr);
                if (method != null) {
                    try {
                        method.setAccessible(true);
                    } catch (Throwable th2) {
                        th = th2;
                        ZeusLogger.w(ZeusLogger.TAG, "DoubleReflector" + String.format("getMethod %s#%s failed !!!", cls.getName(), str), th);
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                method = null;
            }
            method3 = method;
        }
        return method3 != null ? method3 : com.bytedance.pangle.b.a.a.a(cls, str, clsArr);
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) {
        Constructor constructor;
        Method method = c;
        if (method != null) {
            try {
                constructor = (Constructor) method.invoke(cls, clsArr);
            } catch (Throwable th) {
                ZeusLogger.w(ZeusLogger.TAG, "DoubleReflector" + String.format("getConstructor %s<init>%s failed !!!", cls.getName(), 1), th);
                constructor = null;
            }
        } else {
            constructor = null;
        }
        return constructor != null ? constructor : com.bytedance.pangle.b.a.a.a(cls, clsArr);
    }
}
