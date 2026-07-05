package com.bytedance.sdk.openadsdk;

import android.app.Application;
import android.content.Context;
import com.bytedance.sdk.openadsdk.api.c;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class TTAppContextHolder {
    private static volatile Context a;

    public static Context getContext() {
        if (a == null) {
            setContext(null);
        }
        return a;
    }

    public static synchronized void setContext(Context context) {
        if (a == null) {
            if (context != null) {
                a = context.getApplicationContext();
            } else if (a.a() != null) {
                try {
                    a = a.a();
                    if (a != null) {
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    private static class a {
        private static volatile Application a;

        public static Application a() {
            return a;
        }

        static {
            try {
                Object objB = b();
                a = (Application) objB.getClass().getMethod("getApplication", new Class[0]).invoke(objB, new Object[0]);
                c.d("MyApplication", "application get success");
            } catch (Throwable th) {
                c.c("MyApplication", "application get failed", th);
            }
        }

        private static Object b() {
            try {
                Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]);
                method.setAccessible(true);
                return method.invoke(null, new Object[0]);
            } catch (Throwable th) {
                c.c("MyApplication", "ActivityThread get error, maybe api level <= 4.2.2", th);
                return null;
            }
        }
    }
}
