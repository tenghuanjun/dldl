package com.sq.pluginex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Reflector {
    public static final String LOG_TAG = "VA.Reflector";
    protected Object mCaller;
    protected Constructor mConstructor;
    protected Field mField;
    protected Method mMethod;
    protected Class<?> mType;

    public static class ReflectedException extends Exception {
        public ReflectedException(String str) {
            super(str);
        }

        public ReflectedException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static Reflector on(String str) throws ReflectedException {
        return on(str, true, Reflector.class.getClassLoader());
    }

    public static Reflector on(String str, boolean z) throws ReflectedException {
        return on(str, z, Reflector.class.getClassLoader());
    }

    public static Reflector on(String str, boolean z, ClassLoader classLoader) throws ReflectedException {
        try {
            return on(Class.forName(str, z, classLoader));
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public static Reflector on(Class<?> cls) {
        Reflector reflector = new Reflector();
        reflector.mType = cls;
        return reflector;
    }

    public static Reflector with(Object obj) throws ReflectedException {
        return on(obj.getClass()).bind(obj);
    }

    protected Reflector() {
    }

    public Reflector constructor(Class<?>... clsArr) throws ReflectedException {
        try {
            Constructor<?> declaredConstructor = this.mType.getDeclaredConstructor(clsArr);
            this.mConstructor = declaredConstructor;
            declaredConstructor.setAccessible(true);
            this.mField = null;
            this.mMethod = null;
            return this;
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public <R> R newInstance(Object... objArr) throws ReflectedException {
        Constructor constructor = this.mConstructor;
        if (constructor == null) {
            throw new ReflectedException("Constructor was null!");
        }
        try {
            return (R) constructor.newInstance(objArr);
        } catch (InvocationTargetException e) {
            throw new ReflectedException("Oops!", e.getTargetException());
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    protected Object checked(Object obj) throws ReflectedException {
        if (obj == null || this.mType.isInstance(obj)) {
            return obj;
        }
        throw new ReflectedException("Caller [" + obj + "] is not a instance of type [" + this.mType + "]!");
    }

    protected void check(Object obj, Member member, String str) throws ReflectedException {
        if (member == null) {
            throw new ReflectedException(str + " was null!");
        }
        if (obj == null && !Modifier.isStatic(member.getModifiers())) {
            throw new ReflectedException("Need a caller!");
        }
        checked(obj);
    }

    public Reflector bind(Object obj) throws ReflectedException {
        this.mCaller = checked(obj);
        return this;
    }

    public Reflector unbind() {
        this.mCaller = null;
        return this;
    }

    public Reflector field(String str) throws ReflectedException {
        try {
            Field fieldFindField = findField(str);
            this.mField = fieldFindField;
            fieldFindField.setAccessible(true);
            this.mConstructor = null;
            this.mMethod = null;
            return this;
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    protected Field findField(String str) throws NoSuchFieldException {
        try {
            return this.mType.getField(str);
        } catch (NoSuchFieldException e) {
            for (Class<?> superclass = this.mType; superclass != null; superclass = superclass.getSuperclass()) {
                try {
                    return superclass.getDeclaredField(str);
                } catch (NoSuchFieldException unused) {
                }
            }
            throw e;
        }
    }

    public <R> R get() throws ReflectedException {
        return (R) get(this.mCaller);
    }

    public <R> R get(Object obj) throws ReflectedException {
        check(obj, this.mField, "Field");
        try {
            return (R) this.mField.get(obj);
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public Reflector set(Object obj) throws ReflectedException {
        return set(this.mCaller, obj);
    }

    public Reflector set(Object obj, Object obj2) throws ReflectedException {
        check(obj, this.mField, "Field");
        try {
            this.mField.set(obj, obj2);
            return this;
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public Reflector method(String str, Class<?>... clsArr) throws ReflectedException {
        try {
            Method methodFindMethod = findMethod(str, clsArr);
            this.mMethod = methodFindMethod;
            methodFindMethod.setAccessible(true);
            this.mConstructor = null;
            this.mField = null;
            return this;
        } catch (NoSuchMethodException e) {
            throw new ReflectedException("Oops!", e);
        }
    }

    protected Method findMethod(String str, Class<?>... clsArr) throws NoSuchMethodException {
        try {
            return this.mType.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            for (Class<?> superclass = this.mType; superclass != null; superclass = superclass.getSuperclass()) {
                try {
                    return superclass.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                }
            }
            throw e;
        }
    }

    public <R> R call(Object... objArr) throws ReflectedException {
        return (R) callByCaller(this.mCaller, objArr);
    }

    public <R> R callByCaller(Object obj, Object... objArr) throws ReflectedException {
        check(obj, this.mMethod, "Method");
        try {
            return (R) this.mMethod.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throw new ReflectedException("Oops!", e.getTargetException());
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public static class QuietReflector extends Reflector {
        protected Throwable mIgnored;

        @Override // com.sq.pluginex.Reflector
        public /* bridge */ /* synthetic */ Reflector constructor(Class[] clsArr) throws ReflectedException {
            return constructor((Class<?>[]) clsArr);
        }

        @Override // com.sq.pluginex.Reflector
        public /* bridge */ /* synthetic */ Reflector method(String str, Class[] clsArr) throws ReflectedException {
            return method(str, (Class<?>[]) clsArr);
        }

        public static QuietReflector on(String str) {
            return on(str, true, QuietReflector.class.getClassLoader());
        }

        public static QuietReflector on(String str, boolean z) {
            return on(str, z, QuietReflector.class.getClassLoader());
        }

        public static QuietReflector on(String str, boolean z, ClassLoader classLoader) {
            Class<?> cls = null;
            try {
                Class<?> cls2 = Class.forName(str, z, classLoader);
                try {
                    return on(cls2, (Throwable) null);
                } catch (Throwable th) {
                    th = th;
                    cls = cls2;
                    return on(cls, th);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public static QuietReflector on(Class<?> cls) {
            return on(cls, cls == null ? new ReflectedException("Type was null!") : null);
        }

        private static QuietReflector on(Class<?> cls, Throwable th) {
            QuietReflector quietReflector = new QuietReflector();
            quietReflector.mType = cls;
            quietReflector.mIgnored = th;
            return quietReflector;
        }

        public static QuietReflector with(Object obj) {
            if (obj == null) {
                return on((Class<?>) null);
            }
            return on(obj.getClass()).bind(obj);
        }

        protected QuietReflector() {
        }

        public Throwable getIgnored() {
            return this.mIgnored;
        }

        protected boolean skip() {
            return skipAlways() || this.mIgnored != null;
        }

        protected boolean skipAlways() {
            return this.mType == null;
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector constructor(Class<?>... clsArr) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.constructor(clsArr);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public <R> R newInstance(Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.newInstance(objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector bind(Object obj) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.bind(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector unbind() {
            super.unbind();
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector field(String str) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.field(str);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public <R> R get() {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.get();
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.sq.pluginex.Reflector
        public <R> R get(Object obj) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.get(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector set(Object obj) {
            if (skip()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.set(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector set(Object obj, Object obj2) {
            if (skip()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.set(obj, obj2);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public QuietReflector method(String str, Class<?>... clsArr) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.method(str, clsArr);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.sq.pluginex.Reflector
        public <R> R call(Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.call(objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.sq.pluginex.Reflector
        public <R> R callByCaller(Object obj, Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.callByCaller(obj, objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }
    }
}
