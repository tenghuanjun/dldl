package net.vidageek.O0000O000000oO.O000O0000Oo0O;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO {
    private static Map<Class<?>, Class<?>> O000O00000OoO;
    private final Class<?>[] O0000O000000oO;

    static {
        HashMap map = new HashMap();
        O000O00000OoO = map;
        map.put(Boolean.TYPE, Boolean.class);
        O000O00000OoO.put(Byte.TYPE, Byte.class);
        O000O00000OoO.put(Character.TYPE, Character.class);
        O000O00000OoO.put(Short.TYPE, Short.class);
        O000O00000OoO.put(Integer.TYPE, Integer.class);
        O000O00000OoO.put(Long.TYPE, Long.class);
        O000O00000OoO.put(Float.TYPE, Float.class);
        O000O00000OoO.put(Double.TYPE, Double.class);
    }

    public O0000O000000oO(Class<?>... clsArr) {
        if (clsArr == null) {
            throw new IllegalArgumentException("argument baseClasses cannot be null.");
        }
        this.O0000O000000oO = clsArr;
    }

    private boolean O0000O000000oO(Class<?> cls, Class<?> cls2) {
        return !(cls.isPrimitive() ^ cls2.isPrimitive()) ? cls.isAssignableFrom(cls2) : cls.isPrimitive() ? O000O00000OoO.get(cls).isAssignableFrom(cls2) : O000O00000OoO.get(cls2).isAssignableFrom(cls);
    }

    private boolean O000O00000OoO(Class<?> cls, Class<?> cls2) {
        return !(cls.isPrimitive() ^ cls2.isPrimitive()) ? cls.equals(cls2) : cls.isPrimitive() ? O000O00000OoO.get(cls).equals(cls2) : O000O00000OoO.get(cls2).equals(cls);
    }

    private boolean O000O00000OoO(Class<?>[] clsArr) {
        int i = 0;
        while (true) {
            Class<?>[] clsArr2 = this.O0000O000000oO;
            if (i >= clsArr2.length) {
                return true;
            }
            if (!O0000O000000oO(clsArr[i], clsArr2[i])) {
                return false;
            }
            i++;
        }
    }

    private boolean O000O00000o0O(Class<?>[] clsArr) {
        int i = 0;
        while (true) {
            Class<?>[] clsArr2 = this.O0000O000000oO;
            if (i >= clsArr2.length) {
                return true;
            }
            if (!O000O00000OoO(clsArr2[i], clsArr[i])) {
                return false;
            }
            i++;
        }
    }

    public O000O00000OoO O0000O000000oO(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.O0000O000000oO.length != clsArr.length ? O000O00000OoO.DONT_MATCH : O000O00000o0O(clsArr) ? O000O00000OoO.PERFECT : O000O00000OoO(clsArr) ? O000O00000OoO.MATCH : O000O00000OoO.DONT_MATCH;
        }
        throw new IllegalArgumentException("argument classes cannot be null.");
    }
}
