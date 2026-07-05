package net.vidageek.O0000O000000oO.O00O0000OooO;

import java.lang.reflect.Field;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000o0O {
    private final String O0000O000000oO;
    private final O000O0000OOoO O000O00000OoO;

    public O000O00000o0O(O000O0000OOoO o000O0000OOoO, String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or blank");
        }
        this.O000O00000OoO = o000O0000OOoO;
        this.O0000O000000oO = str;
    }

    public Field O0000O000000oO(Class cls) {
        if (cls != null) {
            return this.O000O00000OoO.O0000O000000oO(cls).O0000O000000oO(this.O0000O000000oO);
        }
        throw new IllegalArgumentException("argument clazz cannot be null.");
    }
}
