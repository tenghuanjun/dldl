package net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000o0O<T> implements net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO<T> {
    private Class<T> O0000O000000oO;

    public O000O00000o0O(Class<T> cls) {
        this.O0000O000000oO = cls;
    }

    public O000O00000o0O(String str) {
        try {
            this.O0000O000000oO = (Class<T>) Class.forName(str, false, O000O00000o0O.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            Class<T> cls = (Class<T>) O000O00000OoO.O0000O000000oO(str);
            this.O0000O000000oO = cls;
            if (cls != null) {
                return;
            }
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO("class " + str + " could not be found.", e);
        }
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO
    public Class<T> O0000O000000oO() {
        return this.O0000O000000oO;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO
    public Constructor<T> O0000O000000oO(Class<?>[] clsArr) {
        net.vidageek.O0000O000000oO.O000O0000Oo0O.O0000O000000oO o0000O000000oO = new net.vidageek.O0000O000000oO.O000O0000Oo0O.O0000O000000oO(clsArr);
        Constructor<T> constructor = null;
        for (Constructor<T> constructor2 : O000O00000oO()) {
            net.vidageek.O0000O000000oO.O000O0000Oo0O.O000O00000OoO o000O00000OoOO0000O000000oO = o0000O000000oO.O0000O000000oO(constructor2.getParameterTypes());
            if (net.vidageek.O0000O000000oO.O000O0000Oo0O.O000O00000OoO.PERFECT.equals(o000O00000OoOO0000O000000oO)) {
                return constructor2;
            }
            if (net.vidageek.O0000O000000oO.O000O0000Oo0O.O000O00000OoO.MATCH.equals(o000O00000OoOO0000O000000oO)) {
                constructor = constructor2;
            }
        }
        return constructor;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO
    public Field O0000O000000oO(String str) {
        for (Field field : O000O00000OoO()) {
            if (field.getName().equals(str)) {
                return field;
            }
        }
        return null;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO
    public Method O0000O000000oO(String str, Class<?>[] clsArr) {
        net.vidageek.O0000O000000oO.O000O0000Oo0O.O0000O000000oO o0000O000000oO = new net.vidageek.O0000O000000oO.O000O0000Oo0O.O0000O000000oO(clsArr);
        Method method = null;
        for (Method method2 : O000O00000o0O()) {
            if (method2.getName().equals(str)) {
                net.vidageek.O0000O000000oO.O000O0000Oo0O.O000O00000OoO o000O00000OoOO0000O000000oO = o0000O000000oO.O0000O000000oO(method2.getParameterTypes());
                if (net.vidageek.O0000O000000oO.O000O0000Oo0O.O000O00000OoO.PERFECT.equals(o000O00000OoOO0000O000000oO)) {
                    return method2;
                }
                if (net.vidageek.O0000O000000oO.O000O0000Oo0O.O000O00000OoO.MATCH.equals(o000O00000OoOO0000O000000oO)) {
                    method = method2;
                }
            }
        }
        return method;
    }

    @Override // net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO
    public List<Field> O000O00000OoO() {
        ArrayList arrayList = new ArrayList();
        for (Class<T> superclass = this.O0000O000000oO; superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.addAll(Arrays.asList(superclass.getDeclaredFields()));
            for (Class<?> cls : superclass.getInterfaces()) {
                arrayList.addAll(Arrays.asList(cls.getFields()));
            }
        }
        return arrayList;
    }

    public List<Method> O000O00000o0O() {
        ArrayList arrayList = new ArrayList();
        for (Class<T> superclass = this.O0000O000000oO; superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.addAll(Arrays.asList(superclass.getDeclaredMethods()));
        }
        return arrayList;
    }

    public List<Constructor<T>> O000O00000oO() {
        return Arrays.asList(this.O0000O000000oO.getDeclaredConstructors());
    }
}
