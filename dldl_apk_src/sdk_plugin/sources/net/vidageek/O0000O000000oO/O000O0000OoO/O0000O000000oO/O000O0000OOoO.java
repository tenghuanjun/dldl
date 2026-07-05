package net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O0000OOoO implements net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000oO {
    private final Object O0000O000000oO;
    private final Method O000O00000OoO;

    public O000O0000OOoO(Object obj, Class<?> cls, Method method) {
        this.O0000O000000oO = obj;
        this.O000O00000OoO = method;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000oO
    public Object O0000O000000oO(Object[] objArr) {
        try {
            O000O00000OoO();
            return this.O000O00000OoO.invoke(this.O0000O000000oO, objArr);
        } catch (IllegalAccessException e) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("Could not invoke method " + this.O000O00000OoO.getName(), e);
        } catch (IllegalArgumentException e2) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("Could not invoke method " + this.O000O00000OoO.getName(), e2);
        } catch (NullPointerException e3) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("Attempt to call an instance method ( " + this.O000O00000OoO.getName() + ") on a null object.", e3);
        } catch (InvocationTargetException e4) {
            String str = "Could not invoke method " + this.O000O00000OoO.getName();
            Throwable cause = e4.getCause();
            InvocationTargetException cause2 = e4;
            if (cause != null) {
                cause2 = e4.getCause();
            }
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO(str, cause2);
        }
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000O0oO
    public void O000O00000OoO() {
        this.O000O00000OoO.setAccessible(true);
    }
}
