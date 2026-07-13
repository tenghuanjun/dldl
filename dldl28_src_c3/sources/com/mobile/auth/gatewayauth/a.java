package com.mobile.auth.gatewayauth;

import android.content.res.Configuration;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a {
    public static float a() {
        try {
            try {
                try {
                    Configuration configuration = new Configuration();
                    Class<?> cls = Class.forName("android.app.ActivityManagerNative");
                    Object objInvoke = cls.getMethod("getDefault", null).invoke(cls, null);
                    configuration.updateFrom((Configuration) objInvoke.getClass().getMethod("getConfiguration", null).invoke(objInvoke, null));
                    return configuration.fontScale;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return 1.0f;
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                    return 1.0f;
                }
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
                return 1.0f;
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
                return 1.0f;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return -1.0f;
        }
    }
}
