package net.vidageek.O0000O000000oO.O000O0000O0oO;

import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000o0O<T> implements net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000OoO<T> {
    private final Object O0000O000000oO;
    private final Class<?> O000O00000OoO;
    private final O000O0000OOoO O000O00000o0O;

    /* JADX WARN: Multi-variable type inference failed */
    public O000O00000o0O(O000O0000OOoO o000O0000OOoO, Class<T> cls) {
        if (cls == 0) {
            throw new IllegalArgumentException("target can't be null");
        }
        this.O000O00000o0O = o000O0000OOoO;
        this.O000O00000OoO = cls;
        this.O0000O000000oO = null;
    }

    public O000O00000o0O(O000O0000OOoO o000O0000OOoO, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("target can't be null");
        }
        this.O000O00000o0O = o000O0000OOoO;
        this.O0000O000000oO = obj;
        this.O000O00000OoO = obj.getClass();
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000OoO
    public net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O0000O000000oO<T> O0000O000000oO() {
        if (this.O0000O000000oO == null) {
            return new O0000O000000oO(this.O000O00000o0O, this.O000O00000OoO);
        }
        throw new IllegalStateException("must use constructor InvocationHandler(Class<T>) instead of InvocationHandler(Object).");
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000OoO
    public net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O O0000O000000oO(String str) {
        if (str != null) {
            return new O000O0000O0oO(this.O000O00000o0O, this.O0000O000000oO, this.O000O00000OoO, str);
        }
        throw new IllegalArgumentException("methodName can't be null");
    }
}
