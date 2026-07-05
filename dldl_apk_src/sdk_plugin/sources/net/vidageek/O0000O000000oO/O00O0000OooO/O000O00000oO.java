package net.vidageek.O0000O000000oO.O00O0000OooO;

import java.lang.reflect.Method;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000oO implements net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000o0O {
    private final String O0000O000000oO;
    private final Class<?> O000O00000OoO;
    private final O000O0000OOoO O000O00000o0O;

    public O000O00000oO(O000O0000OOoO o000O0000OOoO, String str, Class<?> cls) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName cannot be null or empty");
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannnot be null");
        }
        this.O000O00000o0O = o000O0000OOoO;
        this.O0000O000000oO = str.trim();
        this.O000O00000OoO = cls;
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000o0O
    public Method O0000O000000oO() {
        return O0000O000000oO(new Class[0]);
    }

    @Override // net.vidageek.O0000O000000oO.O00O0000OooO.O0000O000000oO.O000O00000o0O
    public Method O0000O000000oO(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.O000O00000o0O.O0000O000000oO(this.O000O00000OoO).O0000O000000oO(this.O0000O000000oO, clsArr);
        }
        throw new IllegalArgumentException("classes cannot be null");
    }
}
