package com.google.zxing.camera;

import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class FlashlightManager {
    private static final String TAG = "FlashlightManager";
    private static final Object iHardwareService = getHardwareService();
    private static final Method setFlashEnabledMethod = getSetFlashEnabledMethod(iHardwareService);

    static {
        if (iHardwareService == null) {
            Log.v(TAG, "This device does supports control of a flashlight");
        } else {
            Log.v(TAG, "This device does not support control of a flashlight");
        }
    }

    private FlashlightManager() {
    }

    static void enableFlashlight() {
        setFlashlight(false);
    }

    static void disableFlashlight() {
        setFlashlight(false);
    }

    private static Object getHardwareService() {
        Method methodMaybeGetMethod;
        Object objInvoke;
        Class<?> clsMaybeForName;
        Method methodMaybeGetMethod2;
        Class<?> clsMaybeForName2 = maybeForName("android.os.ServiceManager");
        if (clsMaybeForName2 == null || (methodMaybeGetMethod = maybeGetMethod(clsMaybeForName2, "getService", String.class)) == null || (objInvoke = invoke(methodMaybeGetMethod, null, "hardware")) == null || (clsMaybeForName = maybeForName("android.os.IHardwareService$Stub")) == null || (methodMaybeGetMethod2 = maybeGetMethod(clsMaybeForName, "asInterface", IBinder.class)) == null) {
            return null;
        }
        return invoke(methodMaybeGetMethod2, null, objInvoke);
    }

    private static Method getSetFlashEnabledMethod(Object obj) {
        if (obj == null) {
            return null;
        }
        return maybeGetMethod(obj.getClass(), "setFlashlightEnabled", Boolean.TYPE);
    }

    private static Class<?> maybeForName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected error while finding class " + str, e);
            return null;
        }
    }

    private static Method maybeGetMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected error while finding method " + str, e);
            return null;
        }
    }

    private static Object invoke(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.w(TAG, "Unexpected error while invoking " + method, e);
            return null;
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected error while invoking " + method, e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.w(TAG, "Unexpected error while invoking " + method, e3.getCause());
            return null;
        }
    }

    private static void setFlashlight(boolean z) {
        Object obj = iHardwareService;
        if (obj != null) {
            invoke(setFlashEnabledMethod, obj, Boolean.valueOf(z));
        }
    }
}
