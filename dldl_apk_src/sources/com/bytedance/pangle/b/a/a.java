package com.bytedance.pangle.b.a;

import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private static Map<String, Field> a = new HashMap();
    private static Map<String, Method> b = new HashMap();
    private static Map<String, Constructor> c = new HashMap();
    private static Map<String, Class> d = new HashMap();

    static {
        try {
            FieldUtils.writeField(b.class, "classLoader", (Object) null);
            ZeusLogger.w(ZeusLogger.TAG_INIT, "HackHelper HackHelperImpl use BootClassLoader");
        } catch (Exception e) {
            ZeusLogger.errReport(ZeusLogger.TAG_INIT, "HackHelperinit failed", e);
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        String strB = b(cls, str, clsArr);
        synchronized (b) {
            method = b.get(strB);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method;
        }
        try {
            Method methodA = b.a(cls, str, clsArr);
            if (methodA != null) {
                synchronized (b) {
                    b.put(strB, methodA);
                }
            }
            return methodA;
        } catch (Throwable th) {
            ZeusLogger.w(ZeusLogger.TAG, "HackHelper" + String.format("getMethod %s#%s failed !!!", cls.getName(), str), th);
            return null;
        }
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) {
        Constructor constructor;
        String strB = b(cls, "clinit", clsArr);
        synchronized (c) {
            constructor = c.get(strB);
        }
        if (constructor != null) {
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor;
        }
        try {
            Constructor constructorA = b.a(cls, clsArr);
            if (constructorA != null) {
                synchronized (c) {
                    c.put(strB, constructorA);
                }
            }
            return constructorA;
        } catch (Throwable th) {
            ZeusLogger.w(ZeusLogger.TAG, "HackHelper" + String.format("getConstructor %s failed !!!", cls.getName()), th);
            return null;
        }
    }

    private static String b(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append("#");
        sb.append(str);
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.getName());
                sb.append("#");
            }
        } else {
            sb.append(Void.class.getName());
        }
        return sb.toString();
    }

    public static Field a(Class<?> cls, String str) {
        Field field;
        String str2 = cls.getName() + "#" + str;
        synchronized (a) {
            field = a.get(str2);
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        try {
            Field fieldA = b.a(cls, str);
            if (fieldA != null) {
                synchronized (a) {
                    a.put(str2, fieldA);
                }
            }
            return fieldA;
        } catch (Throwable th) {
            ZeusLogger.w(ZeusLogger.TAG, "HackHelper" + String.format("getField %s#%s failed !!!", cls.getName(), str), th);
            return null;
        }
    }
}
