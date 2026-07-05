package net.vidageek.O0000O000000oO.O000O0000O0oO;

import java.lang.reflect.Method;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O0000O0oO implements net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O {
    private final Object O0000O000000oO;
    private final String O000O00000OoO;
    private final Class<?> O000O00000o0O;
    private final O000O0000OOoO O000O00000oO;

    public O000O0000O0oO(O000O0000OOoO o000O0000OOoO, Object obj, Class<?> cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz can't be null");
        }
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName can't be null");
        }
        this.O000O00000oO = o000O0000OOoO;
        this.O0000O000000oO = obj;
        this.O000O00000o0O = cls;
        this.O000O00000OoO = str;
    }

    private Method O000O00000OoO(Object[] objArr) {
        int length = objArr == null ? 0 : objArr.length;
        Class<?>[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new IllegalArgumentException("Cannot invoke a method by name if one of it's arguments is null. First reflect the method.");
            }
            clsArr[i] = objArr[i].getClass();
        }
        Method methodO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O(this.O000O00000oO).O0000O000000oO((Class) this.O000O00000o0O).O000O00000OoO().O000O00000OoO(this.O000O00000OoO).O0000O000000oO(clsArr);
        if (methodO0000O000000oO != null) {
            return methodO0000O000000oO;
        }
        throw new net.vidageek.O0000O000000oO.O000O00000o0O.O0000O000000oO("Could not find method " + this.O000O00000OoO + " on class " + this.O000O00000o0O.getName());
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O
    public Object O0000O000000oO() {
        return O0000O000000oO(new Object[0]);
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O
    public Object O0000O000000oO(Object... objArr) {
        return new O000O00000oO(this.O000O00000oO, this.O0000O000000oO, this.O000O00000o0O, O000O00000OoO(objArr)).O0000O000000oO(objArr);
    }
}
