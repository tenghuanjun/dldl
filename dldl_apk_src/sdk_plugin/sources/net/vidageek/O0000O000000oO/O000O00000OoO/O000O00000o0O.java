package net.vidageek.O0000O000000oO.O000O00000OoO;

import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000o0O {
    private static final O000O0000OOoO O0000O000000oO = new net.vidageek.O0000O000000oO.O0000O000000oO.O000O00000OoO(O000O00000o0O.class.getResourceAsStream("/mirror.properties")).O0000O000000oO();
    private final O000O0000OOoO O000O00000OoO;

    public O000O00000o0O() {
        this(O0000O000000oO);
    }

    public O000O00000o0O(O000O0000OOoO o000O0000OOoO) {
        this.O000O00000OoO = o000O0000OOoO;
    }

    public Class<?> O0000O000000oO(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("className cannot be null or empty");
        }
        return this.O000O00000OoO.O0000O000000oO(str).O0000O000000oO();
    }

    public O0000O000000oO O0000O000000oO(Object obj) {
        return new net.vidageek.O0000O000000oO.O0000O000000oO(this.O000O00000OoO, obj);
    }

    public <T> O000O00000OoO<T> O0000O000000oO(Class<T> cls) {
        return new net.vidageek.O0000O000000oO.O000O00000OoO(this.O000O00000OoO, cls);
    }

    public O000O00000OoO<?> O000O00000OoO(String str) {
        return O0000O000000oO((Class) O0000O000000oO(str));
    }
}
