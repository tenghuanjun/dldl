package com.igexin.push.f;

import android.content.Context;
import android.util.Log;
import com.igexin.assist.sdk.AssistPushConsts;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    private static final String a = "com.igexin.assist.control.stp.ManufacturePushManager";

    public static boolean a(Context context) {
        try {
            try {
                Method declaredMethod = Class.forName(a).getDeclaredMethod("checkDevice", Context.class);
                declaredMethod.setAccessible(true);
                boolean zBooleanValue = ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
                Log.i("Assist_UPS", "isSupportStp: ".concat(String.valueOf(zBooleanValue)));
                return zBooleanValue;
            } catch (Exception unused) {
                Class<?> cls = Class.forName(a);
                Object objNewInstance = cls.getConstructor(Context.class).newInstance(context);
                Method declaredMethod2 = cls.getDeclaredMethod("isSupport", new Class[0]);
                declaredMethod2.setAccessible(true);
                Log.i("Assist_UPS", "isSupportStp: ".concat(String.valueOf(((Boolean) declaredMethod2.invoke(objNewInstance, new Object[0])).booleanValue())));
                return false;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        String strConcat = AssistPushConsts.LOG_TAG.concat(String.valueOf(str));
        boolean zBooleanValue = false;
        try {
            Class<?> cls = Class.forName("com.igexin.assist.control." + str + ".ManufacturePushManager");
            Object objNewInstance = cls.getConstructor(Context.class).newInstance(context);
            Field declaredField = cls.getDeclaredField("context");
            boolean zIsAccessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            declaredField.set(objNewInstance, context);
            declaredField.setAccessible(zIsAccessible);
            zBooleanValue = ((Boolean) cls.getDeclaredMethod("isSupport", new Class[0]).invoke(objNewInstance, new Object[0])).booleanValue();
            Log.i(strConcat, "isSupport " + str + " = " + zBooleanValue);
            return zBooleanValue;
        } catch (Throwable unused) {
            return zBooleanValue;
        }
    }
}
