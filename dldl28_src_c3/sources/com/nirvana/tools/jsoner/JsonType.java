package com.nirvana.tools.jsoner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class JsonType<T> {
    private Constructor mConstructor;
    private Class<? super T> mGenericsClz;

    static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return JsonHelper.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    private void setup() {
        Class<? super T> cls = (Class<? super T>) JsonHelper.getRawType(getSuperclassTypeParameter(getClass()));
        this.mGenericsClz = cls;
        if (cls == null) {
            throw new IllegalArgumentException("JsonType's generics is not recognizable!");
        }
        try {
            this.mConstructor = cls.getDeclaredConstructor(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public T newInstance() {
        if (this.mConstructor == null) {
            setup();
        }
        Constructor constructor = this.mConstructor;
        if (constructor != null) {
            try {
                constructor.setAccessible(true);
                return (T) this.mConstructor.newInstance(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }
}
