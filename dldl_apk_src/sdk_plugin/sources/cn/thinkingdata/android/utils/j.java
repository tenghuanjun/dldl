package cn.thinkingdata.android.utils;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class j {
    static String a = "ThinkingAnalytics.TAReflectUtils";

    public static Object a(Object obj, String str, Object[] objArr, Class<?>... clsArr) {
        Method methodA = a(obj, str, clsArr);
        if (methodA != null) {
            try {
                return methodA.invoke(obj, objArr);
            } catch (Exception e) {
                TDLog.e(a, e.getMessage());
                return null;
            }
        }
        TDLog.i(a, "Could not find method [" + str + "] on target [" + obj + "]");
        return null;
    }

    public static Object a(String str) {
        Class<?> cls;
        try {
            cls = Class.forName(str);
        } catch (Exception e) {
            e = e;
            cls = null;
        }
        try {
            return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return cls;
        }
    }

    public static Method a(Object obj, String str, Class<?>... clsArr) {
        if (obj == null) {
            TDLog.i(a, "obj is null!");
            return null;
        }
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        return null;
    }
}
