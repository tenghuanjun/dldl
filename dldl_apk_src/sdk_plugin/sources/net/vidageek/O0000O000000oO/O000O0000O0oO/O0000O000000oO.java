package net.vidageek.O0000O000000oO.O000O0000O0oO;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO<T> implements net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O0000O000000oO<T> {
    private final Class<T> O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O0000O000000oO(O000O0000OOoO o000O0000OOoO, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Argument class cannot be null");
        }
        this.O000O00000OoO = o000O0000OOoO;
        this.O0000O000000oO = cls;
    }

    private Constructor<T> O000O00000OoO(Object... objArr) {
        int length = objArr == null ? 0 : objArr.length;
        Class<?>[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new IllegalArgumentException("Cannot invoke a constructor by args if one of it's arguments is null. First reflect the constructor.");
            }
            clsArr[i] = objArr[i].getClass();
        }
        Constructor<T> constructorO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O(this.O000O00000OoO).O0000O000000oO((Class) this.O0000O000000oO).O000O00000OoO().O0000O000000oO().O0000O000000oO(clsArr);
        if (constructorO0000O000000oO != null) {
            return constructorO0000O000000oO;
        }
        throw new net.vidageek.O0000O000000oO.O000O00000o0O.O0000O000000oO("Could not find constructor with args " + Arrays.asList(clsArr) + " on class " + this.O0000O000000oO.getName());
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O0000O000000oO
    public T O0000O000000oO() {
        return O0000O000000oO(new Object[0]);
    }

    public T O0000O000000oO(Object... objArr) {
        return (T) new O000O00000OoO(this.O000O00000OoO, this.O0000O000000oO, O000O00000OoO(objArr)).O0000O000000oO(objArr);
    }
}
