package com.bytedance.pangle.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.util.SimpleArrayMap;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class a {
    public static void a(Class cls) {
        try {
            if (Fragment.class.getName().contains("support")) {
                ((SimpleArrayMap) a(Fragment.class, "sClassMap").get(null)).put(cls.getName(), cls);
                return;
            }
            Field fieldA = a(Fragment.class, "sClassMap");
            if (fieldA != null) {
                ((SimpleArrayMap) fieldA.get(null)).put(cls.getName(), cls);
                return;
            }
            Class clsA = a("androidx.fragment.app.FragmentFactory");
            if (clsA == null) {
                return;
            }
            Field fieldA2 = a(clsA, "sClassMap");
            if (fieldA2 != null) {
                ((SimpleArrayMap) fieldA2.get(null)).put(cls.getName(), cls);
                return;
            }
            Field fieldA3 = a(clsA, "sClassCacheMap");
            if (fieldA3 != null) {
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) fieldA3.get(null);
                SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
                simpleArrayMap2.put(cls.getName(), cls);
                simpleArrayMap.put(cls.getClassLoader(), simpleArrayMap2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Field a(Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    private static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
