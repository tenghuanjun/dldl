package com.duowan.auk.http.v2.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class JsonNullTerminator {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Nullable {
    }

    public static <T> T terminate(T t) throws IllegalAccessException, InstantiationException {
        if (t == null) {
            return null;
        }
        return (T) realTerminate(t);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0134  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> T realTerminate(T r8) throws java.lang.IllegalAccessException, java.lang.InstantiationException {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.auk.http.v2.json.JsonNullTerminator.realTerminate(java.lang.Object):java.lang.Object");
    }

    private static boolean userType(Class cls) {
        return (cls == null || cls.isArray() || Collection.class.isAssignableFrom(cls) || cls.equals(Byte.class) || Map.class.isAssignableFrom(cls) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Boolean.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(String.class)) ? false : true;
    }
}
