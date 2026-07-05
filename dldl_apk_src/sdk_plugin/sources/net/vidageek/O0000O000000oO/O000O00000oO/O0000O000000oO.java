package net.vidageek.O0000O000000oO.O000O00000oO;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO implements net.vidageek.O0000O000000oO.O000O00000oO.O0000O000000oO.O0000O000000oO {
    private final Object O0000O000000oO;
    private final Class<?> O000O00000OoO;
    private final O000O0000OOoO O000O00000o0O;

    public O0000O000000oO(O000O0000OOoO o000O0000OOoO, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
        this.O000O00000o0O = o000O0000OOoO;
        this.O000O00000OoO = obj.getClass();
        this.O0000O000000oO = obj;
    }

    private Field O000O00000OoO(String str) {
        Field fieldO0000O000000oO = new O000O00000o0O(this.O000O00000o0O).O0000O000000oO((Class) this.O000O00000OoO).O000O00000OoO().O0000O000000oO(str);
        if (fieldO0000O000000oO != null) {
            return fieldO0000O000000oO;
        }
        throw new net.vidageek.O0000O000000oO.O000O00000o0O.O0000O000000oO("could not find field " + str + " for class " + this.O000O00000OoO.getName());
    }

    @Override // net.vidageek.O0000O000000oO.O000O00000oO.O0000O000000oO.O0000O000000oO
    public Object O0000O000000oO(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or empty.");
        }
        return O0000O000000oO(O000O00000OoO(str));
    }

    public Object O0000O000000oO(Field field) {
        if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        }
        if (!field.getDeclaringClass().isAssignableFrom(this.O000O00000OoO)) {
            throw new IllegalArgumentException("field declaring class (" + field.getDeclaringClass().getName() + ") doesn't match clazz " + this.O000O00000OoO.getName());
        }
        if (this.O0000O000000oO != null || Modifier.isStatic(field.getModifiers())) {
            net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000o0O o000O00000o0OO0000O000000oO = this.O000O00000o0O.O0000O000000oO(this.O0000O000000oO, this.O000O00000OoO, field);
            o000O00000o0OO0000O000000oO.O000O00000OoO();
            return o000O00000o0OO0000O000000oO.O0000O000000oO();
        }
        throw new IllegalStateException("attempt to get instance field " + field.getName() + " on class " + this.O000O00000OoO.getName());
    }
}
