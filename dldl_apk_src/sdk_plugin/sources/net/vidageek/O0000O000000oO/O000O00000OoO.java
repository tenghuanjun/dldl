package net.vidageek.O0000O000000oO;

import net.vidageek.O0000O000000oO.O000O0000O0oO.O000O00000o0O;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;
import net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000oO;
import net.vidageek.O0000O000000oO.O00O0000OooO.O000O0000O0oO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000OoO<T> implements net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000OoO<T> {
    private final Class<T> O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O000O00000OoO(O000O0000OOoO o000O0000OOoO, Class<T> cls) {
        this.O000O00000OoO = o000O0000OOoO;
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.O0000O000000oO = cls;
    }

    @Override // net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000OoO
    public net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000OoO<T> O0000O000000oO() {
        return new O000O00000o0O(this.O000O00000OoO, (Class) this.O0000O000000oO);
    }

    @Override // net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000OoO
    public O000O00000oO<T> O000O00000OoO() {
        return new O000O0000O0oO(this.O000O00000OoO, this.O0000O000000oO);
    }

    @Override // net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000OoO
    public net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O0000O000000oO<T> O000O00000o0O() {
        return new net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO(this.O000O00000OoO, this.O0000O000000oO);
    }
}
