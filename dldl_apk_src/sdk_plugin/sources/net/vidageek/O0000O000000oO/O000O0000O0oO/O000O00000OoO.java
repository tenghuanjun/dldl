package net.vidageek.O0000O000000oO.O000O0000O0oO;

import java.lang.reflect.Constructor;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000OoO<T> implements net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O0000O000000oO<T> {
    private final Constructor<T> O0000O000000oO;
    private final Class<T> O000O00000OoO;
    private final O000O0000OOoO O000O00000o0O;

    public O000O00000OoO(O000O0000OOoO o000O0000OOoO, Class<T> cls, Constructor<T> constructor) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        if (constructor == null) {
            throw new IllegalArgumentException("constructor cannot be null");
        }
        if (cls.equals(constructor.getDeclaringClass())) {
            this.O000O00000o0O = o000O0000OOoO;
            this.O000O00000OoO = cls;
            this.O0000O000000oO = constructor;
        } else {
            throw new IllegalArgumentException("constructor declaring type should be " + cls.getName() + " but was " + constructor.getDeclaringClass().getName());
        }
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O0000O000000oO
    public T O0000O000000oO() {
        return O0000O000000oO(new Object[0]);
    }

    public T O0000O000000oO(Object... objArr) {
        net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000OoO<T> o000O00000OoOO0000O000000oO = this.O000O00000o0O.O0000O000000oO(this.O000O00000OoO, this.O0000O000000oO);
        o000O00000OoOO0000O000000oO.O000O00000OoO();
        return o000O00000OoOO0000O000000oO.O0000O000000oO(objArr);
    }
}
