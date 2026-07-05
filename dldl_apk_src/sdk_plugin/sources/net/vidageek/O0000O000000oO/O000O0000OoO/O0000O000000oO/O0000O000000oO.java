package net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO implements net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO {
    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO
    public <T> net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO<T> O0000O000000oO(Class<T> cls) {
        return new O000O00000o0O(cls);
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO
    public net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO<?> O0000O000000oO(String str) {
        return new O000O00000o0O(str);
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO
    public <T> net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000OoO<T> O0000O000000oO(Class<T> cls, Constructor<T> constructor) {
        return new O000O00000oO(cls, constructor);
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO
    public net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000o0O O0000O000000oO(Object obj, Class<?> cls, Field field) {
        return new O000O0000O0oO(obj, cls, field);
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO
    public net.vidageek.O0000O000000oO.O000O0000OoO.O000O00000oO O0000O000000oO(Object obj, Class<?> cls, Method method) {
        return new O000O0000OOoO(obj, cls, method);
    }
}
