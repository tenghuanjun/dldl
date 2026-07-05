package net.vidageek.O0000O000000oO.O000O0000O0oO;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000oO implements net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O {
    private final Object O0000O000000oO;
    private final Class<?> O000O00000OoO;
    private final Method O000O00000o0O;
    private final O000O0000OOoO O000O00000oO;

    public O000O00000oO(O000O0000OOoO o000O0000OOoO, Object obj, Class<?> cls, Method method) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        if (method == null) {
            throw new IllegalArgumentException("method cannot be null");
        }
        if (method.getDeclaringClass().isAssignableFrom(cls)) {
            this.O000O00000oO = o000O0000OOoO;
            this.O0000O000000oO = obj;
            this.O000O00000OoO = cls;
            this.O000O00000o0O = method;
            return;
        }
        throw new IllegalArgumentException("method " + method + " cannot be invoked on clazz " + cls.getName());
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O
    public Object O0000O000000oO() {
        return O0000O000000oO(new Object[0]);
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O
    public Object O0000O000000oO(Object... objArr) {
        if (this.O0000O000000oO != null || Modifier.isStatic(this.O000O00000o0O.getModifiers())) {
            net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000oO o000O00000oOO0000O000000oO = this.O000O00000oO.O0000O000000oO(this.O0000O000000oO, this.O000O00000OoO, this.O000O00000o0O);
            o000O00000oOO0000O000000oO.O000O00000OoO();
            return o000O00000oOO0000O000000oO.O0000O000000oO(objArr);
        }
        throw new IllegalStateException("attempt to call instance method " + this.O000O00000o0O.getName() + " on class " + this.O000O00000OoO.getName());
    }
}
