package net.vidageek.O0000O000000oO.O00O0000OooO;

import java.lang.reflect.Field;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO<T> implements net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O0000O000000oO<T> {
    private final Class<T> O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O0000O000000oO(O000O0000OOoO o000O0000OOoO, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.O000O00000OoO = o000O0000OOoO;
        this.O0000O000000oO = cls;
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O0000O000000oO
    public net.vidageek.O0000O000000oO.O000O0000OOoO.O0000O000000oO.O0000O000000oO<Field> O0000O000000oO() {
        return new net.vidageek.O0000O000000oO.O000O0000OOoO.O0000O000000oO(this.O000O00000OoO.O0000O000000oO(this.O0000O000000oO).O000O00000OoO());
    }
}
