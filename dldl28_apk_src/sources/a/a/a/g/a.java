package a.a.a.g;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: JavaCalls.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f133a;

    /* JADX INFO: renamed from: a.a.a.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JavaCalls.java */
    public static class C0011a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Class<? extends T> f134a;
        public final T b;
    }

    static {
        HashMap map = new HashMap();
        f133a = map;
        map.put(Boolean.class, Boolean.TYPE);
        map.put(Byte.class, Byte.TYPE);
        map.put(Character.class, Character.TYPE);
        map.put(Short.class, Short.TYPE);
        map.put(Integer.class, Integer.TYPE);
        map.put(Float.class, Float.TYPE);
        map.put(Long.class, Long.TYPE);
        map.put(Double.class, Double.TYPE);
        Class cls = Boolean.TYPE;
        map.put(cls, cls);
        Class cls2 = Byte.TYPE;
        map.put(cls2, cls2);
        Class cls3 = Character.TYPE;
        map.put(cls3, cls3);
        Class cls4 = Short.TYPE;
        map.put(cls4, cls4);
        Class cls5 = Integer.TYPE;
        map.put(cls5, cls5);
        Class cls6 = Float.TYPE;
        map.put(cls6, cls6);
        Class cls7 = Long.TYPE;
        map.put(cls7, cls7);
        Class cls8 = Double.TYPE;
        map.put(cls8, cls8);
    }

    public static <T> T a(Object obj, String str) {
        try {
            Class<?> superclass = obj.getClass();
            Field declaredField = null;
            while (declaredField == null) {
                try {
                    declaredField = superclass.getDeclaredField(str);
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException unused) {
                    superclass = superclass.getSuperclass();
                }
                if (superclass == null) {
                    throw new NoSuchFieldException();
                }
            }
            declaredField.setAccessible(true);
            return (T) declaredField.get(obj);
        } catch (Throwable th) {
            a(th);
            return null;
        }
    }

    public static Object[] b(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null || !(obj instanceof C0011a)) {
                objArr2[i] = obj;
            } else {
                objArr2[i] = ((C0011a) obj).b;
            }
        }
        return objArr2;
    }

    public static void a(Class<?> cls, String str, Object obj) {
        Field declaredField = null;
        while (declaredField == null) {
            try {
                try {
                    declaredField = cls.getDeclaredField(str);
                } catch (NoSuchFieldException unused) {
                    cls = cls.getSuperclass();
                }
                if (cls == null) {
                    throw new NoSuchFieldException();
                }
            } catch (Throwable th) {
                a(th);
                return;
            }
        }
        declaredField.setAccessible(true);
        declaredField.set(null, obj);
    }

    public static <T> T a(String str, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return (T) declaredField.get(null);
        } catch (Throwable th) {
            a(th);
            return null;
        }
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        try {
            return (T) a(obj.getClass(), str, a(objArr)).invoke(obj, b(objArr));
        } catch (Throwable th) {
            a(th);
            return null;
        }
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return (T) a(Class.forName(str), str2, a(objArr)).invoke(null, b(objArr));
        } catch (Throwable th) {
            a(th);
            return null;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Method method;
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        loop0: while (true) {
            if (i >= length) {
                method = null;
                break;
            }
            method = declaredMethods[i];
            if (method.getName().equals(str)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes == null) {
                    if (clsArr == null || clsArr.length == 0) {
                        break;
                    }
                } else if (clsArr == null) {
                    if (parameterTypes.length == 0) {
                        break;
                    }
                } else {
                    if (parameterTypes.length == clsArr.length) {
                        for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                            if (!parameterTypes[i2].isAssignableFrom(clsArr[i2])) {
                                Map<Class<?>, Class<?>> map = f133a;
                                if (!map.containsKey(parameterTypes[i2]) || !map.get(parameterTypes[i2]).equals(map.get(clsArr[i2]))) {
                                    break;
                                }
                            }
                        }
                        break loop0;
                    }
                    continue;
                }
            }
            i++;
        }
        if (method == null) {
            if (cls.getSuperclass() != null) {
                return a((Class<?>) cls.getSuperclass(), str, clsArr);
            }
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        return method;
    }

    public static Class<?>[] a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj != null && (obj instanceof C0011a)) {
                clsArr[i] = ((C0011a) obj).f134a;
            } else {
                clsArr[i] = obj == null ? null : obj.getClass();
            }
        }
        return clsArr;
    }

    public static void a(Throwable th) {
        b.b("JavaCalls", Log.getStackTraceString(th));
    }
}
