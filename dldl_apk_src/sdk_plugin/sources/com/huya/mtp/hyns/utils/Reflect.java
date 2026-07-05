package com.huya.mtp.hyns.utils;

import com.sqwan.bugless.util.FileUtil;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Reflect {
    private static boolean DEFAULT_BOOLEAN;
    private static byte DEFAULT_BYTE;
    private static double DEFAULT_DOUBLE;
    private static float DEFAULT_FLOAT;
    private static int DEFAULT_INT;
    private static long DEFAULT_LONG;
    private static short DEFAULT_SHORT;
    private final Object object;
    private final Class<?> type;

    public static class ReflectException extends Exception {
        public ReflectException(Throwable th) {
            super(th);
        }

        public ReflectException() {
        }

        public ReflectException(String str) {
            super(str);
        }

        public ReflectException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static Reflect on(String str) throws ReflectException {
        return on(forName(str));
    }

    public static Reflect on(String str, ClassLoader classLoader) throws ReflectException {
        return on(forName(str, classLoader));
    }

    public static Reflect on(Class<?> cls) {
        return new Reflect(cls);
    }

    public static Reflect on(Object obj) {
        return new Reflect(obj == null ? Object.class : obj.getClass(), obj);
    }

    public static Reflect on(Type type, Object obj) {
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        if (!(type instanceof Class)) {
            type = null;
        }
        return new Reflect((Class) type, obj);
    }

    public static <T extends AccessibleObject> T accessible(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    private Reflect(Class<?> cls) {
        this(cls, cls);
    }

    private Reflect(Class<?> cls, Object obj) {
        this.type = cls;
        this.object = obj;
    }

    public <T> T get() {
        return (T) this.object;
    }

    public Reflect set(String str, Object obj) throws ReflectException {
        try {
            Field fieldField0 = field0(str);
            if ((fieldField0.getModifiers() & 16) == 16) {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                declaredField.setAccessible(true);
                declaredField.setInt(fieldField0, fieldField0.getModifiers() & (-17));
            }
            fieldField0.set(this.object, unwrap(obj));
            return this;
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public <T> T get(String str) throws ReflectException {
        return (T) field(str).get();
    }

    public Reflect field(String str) throws ReflectException {
        try {
            Field fieldField0 = field0(str);
            return on(fieldField0.getType(), fieldField0.get(this.object));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private Field field0(String str) throws ReflectException {
        Class<?> clsType = type();
        try {
            return (Field) accessible(clsType.getField(str));
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) accessible(clsType.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    clsType = clsType.getSuperclass();
                    if (clsType == null) {
                        throw new ReflectException(e);
                    }
                }
            } while (clsType == null);
            throw new ReflectException(e);
        }
    }

    public Map<String, Reflect> fields() throws ReflectException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class<?> clsType = type();
        do {
            for (Field field : clsType.getDeclaredFields()) {
                if ((this.type != this.object) ^ Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (!linkedHashMap.containsKey(name)) {
                        linkedHashMap.put(name, field(name));
                    }
                }
            }
            clsType = clsType.getSuperclass();
        } while (clsType != null);
        return linkedHashMap;
    }

    public Reflect call(String str) throws ReflectException {
        return call(str, new Object[0]);
    }

    public Reflect call(String str, Object... objArr) throws ReflectException {
        Class<?>[] clsArrTypes = types(objArr);
        try {
            try {
                return on(exactMethod(str, clsArrTypes), this.object, objArr);
            } catch (NoSuchMethodException e) {
                throw new ReflectException(e);
            }
        } catch (NoSuchMethodException unused) {
            return on(similarMethod(str, clsArrTypes), this.object, objArr);
        }
    }

    private Method exactMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> clsType = type();
        try {
            return clsType.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return clsType.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    clsType = clsType.getSuperclass();
                }
            } while (clsType != null);
            throw new NoSuchMethodException();
        }
    }

    private Method similarMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> clsType = type();
        for (Method method : clsType.getMethods()) {
            if (isSimilarSignature(method, str, clsArr)) {
                return method;
            }
        }
        do {
            for (Method method2 : clsType.getDeclaredMethods()) {
                if (isSimilarSignature(method2, str, clsArr)) {
                    return method2;
                }
            }
            clsType = clsType.getSuperclass();
        } while (clsType != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + FileUtil.FILE_EXTENSION_SEPARATOR);
    }

    private boolean isSimilarSignature(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    public Reflect create() throws ReflectException {
        return create(new Object[0]);
    }

    public Reflect createAuto(boolean z) throws ReflectException {
        Class<?> clsType = type();
        if (clsType == null) {
            throw new ReflectException("type is null when create new object");
        }
        return on(clsType, getInitValueForType(clsType, z));
    }

    public Reflect fillNull() throws ReflectException {
        Object initValueForType = get();
        Class<?> clsType = type();
        if (clsType == null) {
            throw new ReflectException("type is null when fill objects");
        }
        if (initValueForType == null) {
            initValueForType = getInitValueForType(type(), false);
        }
        realTerminate(initValueForType);
        return on(clsType, initValueForType);
    }

    public Reflect create(Object... objArr) throws ReflectException {
        Class<?>[] clsArrTypes = types(objArr);
        try {
            return on(type().getDeclaredConstructor(clsArrTypes), objArr);
        } catch (NoSuchMethodException e) {
            for (Constructor<?> constructor : type().getDeclaredConstructors()) {
                if (match(constructor.getParameterTypes(), clsArrTypes)) {
                    return on(constructor, objArr);
                }
            }
            throw new ReflectException(e);
        }
    }

    public <P> P as(Class<P> cls) {
        final boolean z = this.object instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.huya.mtp.hyns.utils.Reflect.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                String name = method.getName();
                try {
                    return Reflect.on(Reflect.this.type, Reflect.this.object).call(name, objArr).get();
                } catch (ReflectException e) {
                    if (z) {
                        Map map = (Map) Reflect.this.object;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(Reflect.property(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(Reflect.property(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(Reflect.property(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String property(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != NULL.class && !wrapper(clsArr[i]).isAssignableFrom(wrapper(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Reflect) {
            return this.object.equals(((Reflect) obj).get());
        }
        return false;
    }

    public String toString() {
        return this.object.toString();
    }

    private static Reflect on(Constructor<?> constructor, Object... objArr) throws ReflectException {
        try {
            return on(constructor.getDeclaringClass(), ((Constructor) accessible(constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Reflect on(Method method, Object obj, Object... objArr) throws ReflectException {
        try {
            accessible(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return on(obj);
            }
            return on(method.invoke(obj, objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Object unwrap(Object obj) {
        return obj instanceof Reflect ? ((Reflect) obj).get() : obj;
    }

    private static Class<?>[] types(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? NULL.class : obj.getClass();
        }
        return clsArr;
    }

    private static Class<?> forName(String str) throws ReflectException {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> forName(String str, ClassLoader classLoader) throws ReflectException {
        try {
            return Class.forName(str, true, classLoader);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public Class<?> type() {
        return this.type;
    }

    public static Class<?> wrapper(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (!cls.isPrimitive()) {
            return cls;
        }
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        if (Short.TYPE == cls) {
            return Short.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        return Void.TYPE == cls ? Void.class : cls;
    }

    private static class NULL {
        private NULL() {
        }
    }

    private static <T> T getInitValueForType(Class<? extends T> cls, boolean z) throws ReflectException {
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType != null) {
                return (T) Array.newInstance(componentType, 0);
            }
            return null;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return (T) new ArrayList();
        }
        if (cls.equals(Byte.class) || cls.equals(Byte.TYPE)) {
            return (T) Byte.valueOf(DEFAULT_BYTE);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return (T) new HashMap();
        }
        if (cls.equals(Short.class) || cls.equals(Short.TYPE)) {
            return (T) Short.valueOf(DEFAULT_SHORT);
        }
        if (cls.equals(Integer.class) || cls.equals(Integer.TYPE)) {
            return (T) Integer.valueOf(DEFAULT_INT);
        }
        if (cls.equals(Long.class) || cls.equals(Long.TYPE)) {
            return (T) Long.valueOf(DEFAULT_LONG);
        }
        if (cls.equals(Boolean.class) || cls.equals(Boolean.TYPE)) {
            return (T) Boolean.valueOf(DEFAULT_BOOLEAN);
        }
        if (cls.equals(Double.class) || cls.equals(Double.TYPE)) {
            return (T) Double.valueOf(DEFAULT_DOUBLE);
        }
        if (cls.equals(Float.class) || cls.equals(Float.TYPE)) {
            return (T) Float.valueOf(DEFAULT_FLOAT);
        }
        if (cls.equals(String.class)) {
            return "";
        }
        if (userType(cls)) {
            return (T) newInstanceForClass(cls, z);
        }
        return null;
    }

    private static <T> T newInstanceForClass(Class<? extends T> cls, boolean z) throws Exception {
        Class<?>[] parameterTypes = cls.getDeclaredConstructors()[0].getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            try {
                objArr[i] = getInitValueForType(parameterTypes[i], z);
            } catch (Exception e) {
                if (!z) {
                    throw e;
                }
            }
        }
        return (T) on((Class<?>) cls).create(objArr).get();
    }

    private static boolean userType(Class cls) {
        return (cls == null || cls.isArray() || Collection.class.isAssignableFrom(cls) || cls.equals(Byte.class) || Map.class.isAssignableFrom(cls) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Boolean.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(String.class)) ? false : true;
    }

    private static void realTerminate(Object obj) throws ReflectException {
        Class<?> cls = obj.getClass();
        if (cls.equals(Object.class) || !userType(cls)) {
            return;
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            Class<?> type = field.getType();
            if (type != null && !type.isPrimitive()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)) {
                    field.setAccessible(true);
                    try {
                        Object obj2 = field.get(obj);
                        if (obj2 == null) {
                            Object initValueForType = getInitValueForType(type, false);
                            realTerminate(initValueForType);
                            try {
                                field.set(obj, initValueForType);
                            } catch (IllegalAccessException e) {
                                throw new ReflectException(e);
                            }
                        } else if (Collection.class.isAssignableFrom(type)) {
                            Iterator it = ((Collection) obj2).iterator();
                            while (it.hasNext()) {
                                realTerminate(it.next());
                            }
                        } else if (type.isArray()) {
                            int length = Array.getLength(obj2);
                            for (int i = 0; i < length; i++) {
                                Object obj3 = Array.get(obj2, i);
                                if (obj3 != null) {
                                    realTerminate(obj3);
                                }
                            }
                        } else if (userType(type)) {
                            realTerminate(obj2);
                        }
                    } catch (IllegalAccessException e2) {
                        throw new ReflectException(e2);
                    }
                }
            }
        }
    }
}
