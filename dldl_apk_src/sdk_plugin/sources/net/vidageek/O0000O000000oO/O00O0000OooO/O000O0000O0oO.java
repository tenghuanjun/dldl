package net.vidageek.O0000O000000oO.O00O0000OooO;

import java.lang.reflect.Field;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O0000O0oO<T> implements net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000oO<T> {
    private final Class<T> O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O000O0000O0oO(O000O0000OOoO o000O0000OOoO, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.O000O00000OoO = o000O0000OOoO;
        this.O0000O000000oO = cls;
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000oO
    public Field O0000O000000oO(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or empty.");
        }
        return new O000O00000o0O(this.O000O00000OoO, str).O0000O000000oO(this.O0000O000000oO);
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000oO
    public net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000OoO<T> O0000O000000oO() {
        return new O000O00000OoO(this.O000O00000OoO, this.O0000O000000oO);
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000oO
    public net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000o0O O000O00000OoO(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName cannot be null or empty.");
        }
        return new O000O00000oO(this.O000O00000OoO, str, this.O0000O000000oO);
    }
}
