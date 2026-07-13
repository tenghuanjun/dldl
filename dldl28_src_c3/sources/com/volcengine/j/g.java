package com.volcengine.j;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class g {
    private static Application a;

    public static Application a() {
        Application application = a;
        if (application != null) {
            return application;
        }
        Application applicationB = b();
        a(applicationB);
        return applicationB;
    }

    private static void a(Application application) {
        if (a == null) {
            if (application == null) {
                application = b();
            }
        } else if (application == null || application.getClass() == a.getClass()) {
            return;
        }
        a = application;
    }

    private static Application b() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("getApplication", null).invoke(cls.getMethod("currentActivityThread", null).invoke(null, null), null);
            if (objInvoke != null) {
                return (Application) objInvoke;
            }
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }
}
