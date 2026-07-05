package net.vidageek.O0000O000000oO.O00O0000OooO;

import java.lang.reflect.Constructor;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000OoO<T> implements net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000OoO<T> {
    private final Class<T> O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O000O00000OoO(O000O0000OOoO o000O0000OOoO, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("argument class cannot be null.");
        }
        this.O000O00000OoO = o000O0000OOoO;
        this.O0000O000000oO = cls;
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000OoO
    public Constructor<T> O0000O000000oO(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.O000O00000OoO.O0000O000000oO(this.O0000O000000oO).O0000O000000oO(clsArr);
        }
        throw new IllegalArgumentException("classes cannot be null");
    }
}
