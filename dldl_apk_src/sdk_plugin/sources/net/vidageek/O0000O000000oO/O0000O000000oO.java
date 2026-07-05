package net.vidageek.O0000O000000oO;

import net.vidageek.O0000O000000oO.O000O0000O0oO.O000O00000o0O;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO implements net.vidageek.O0000O000000oO.O000O00000OoO.O0000O000000oO {
    private final Object O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O0000O000000oO(O000O0000OOoO o000O0000OOoO, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
        this.O000O00000OoO = o000O0000OOoO;
        this.O0000O000000oO = obj;
    }

    @Override // net.vidageek.O0000O000000oO.O000O00000OoO.O0000O000000oO
    public net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000OoO<Object> O0000O000000oO() {
        return new O000O00000o0O(this.O000O00000OoO, this.O0000O000000oO);
    }

    @Override // net.vidageek.O0000O000000oO.O000O00000OoO.O0000O000000oO
    public net.vidageek.O0000O000000oO.O000O00000oO.O0000O000000oO.O0000O000000oO O000O00000OoO() {
        return new net.vidageek.O0000O000000oO.O000O00000oO.O0000O000000oO(this.O000O00000OoO, this.O0000O000000oO);
    }
}
