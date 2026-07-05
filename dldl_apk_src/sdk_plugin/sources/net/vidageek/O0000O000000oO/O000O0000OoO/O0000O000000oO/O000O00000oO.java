package net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000oO<T> implements net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000OoO<T> {
    private final Class<T> O0000O000000oO;
    private final Constructor<T> O000O00000OoO;

    public O000O00000oO(Class<T> cls, Constructor<T> constructor) {
        this.O0000O000000oO = cls;
        this.O000O00000OoO = constructor;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000OoO
    public T O0000O000000oO(Object... objArr) {
        try {
            O000O00000OoO();
            return this.O000O00000OoO.newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("could not invoke constructor " + this.O000O00000OoO.toGenericString() + " on class " + this.O0000O000000oO.getName(), e);
        } catch (IllegalArgumentException e2) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("could not invoke constructor " + this.O000O00000OoO.toGenericString() + " on class " + this.O0000O000000oO.getName(), e2);
        } catch (InstantiationException e3) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("could not invoke constructor " + this.O000O00000OoO.toGenericString() + " on class " + this.O0000O000000oO.getName(), e3);
        } catch (InvocationTargetException e4) {
            String str = "could not invoke constructor " + this.O000O00000OoO.toGenericString() + " on class " + this.O0000O000000oO.getName();
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
