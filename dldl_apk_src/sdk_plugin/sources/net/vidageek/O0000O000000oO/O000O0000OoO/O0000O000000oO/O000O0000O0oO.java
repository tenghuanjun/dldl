package net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O0000O0oO implements net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000o0O {
    private final Object O0000O000000oO;
    private final Class<?> O000O00000OoO;
    private final Field O000O00000o0O;

    public O000O0000O0oO(Object obj, Class<?> cls, Field field) {
        this.O0000O000000oO = obj;
        this.O000O00000OoO = cls;
        this.O000O00000o0O = field;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000o0O
    public Object O0000O000000oO() {
        try {
            O000O00000OoO();
            return this.O000O00000o0O.get(this.O0000O000000oO);
        } catch (IllegalAccessException unused) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("could not get value for field " + this.O000O00000o0O.getName() + " of class " + this.O000O00000OoO.getName());
        }
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000O0oO
    public void O000O00000OoO() {
        this.O000O00000o0O.setAccessible(true);
    }
}
