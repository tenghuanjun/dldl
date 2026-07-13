package com.volcengine.j;

import com.volcengine.androidcloud.common.log.AcLog;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class f1150a;
    private Method b;
    private Method c;
    private Method d;

    public o() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            this.f1150a = cls;
            Method declaredMethod = cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            this.b = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = this.f1150a.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            this.c = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Method declaredMethod3 = this.f1150a.getDeclaredMethod("get", String.class, String.class);
            this.d = declaredMethod3;
            declaredMethod3.setAccessible(true);
        } catch (Throwable th) {
            AcLog.e("SystemPropertiesWrapper", "SystemPropertiesWrapper: ", th);
        }
    }

    public int a(String str, int i) {
        Method method = this.c;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.f1150a, str, Integer.valueOf(i))).intValue();
            } catch (Throwable th) {
                AcLog.e("SystemPropertiesWrapper", "Failed to invoke SystemProperties.getInt()", th);
            }
        }
        return i;
    }

    public String a(String str, String str2) {
        Method method = this.d;
        if (method != null) {
            try {
                return (String) method.invoke(this.f1150a, str, str2);
            } catch (Throwable th) {
                AcLog.e("SystemPropertiesWrapper", "Failed to invoke SystemProperties.get()", th);
            }
        }
        return str2;
    }

    public boolean a(String str, boolean z) {
        Method method = this.b;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(this.f1150a, str, Boolean.valueOf(z))).booleanValue();
            } catch (Throwable th) {
                AcLog.e("SystemPropertiesWrapper", "Failed to invoke SystemProperties.getBoolean()", th);
            }
        }
        return z;
    }
}
