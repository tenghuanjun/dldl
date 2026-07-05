package com.sqwan.common.util;

import android.os.Build;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CompatibilityUtil {
    public static void closeDetectedProblemApiDialog() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            Class.forName("android.content.pm.PackageParser$Package").getDeclaredConstructor(String.class).setAccessible(true);
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
            declaredField.setAccessible(true);
            declaredField.setBoolean(objInvoke, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
