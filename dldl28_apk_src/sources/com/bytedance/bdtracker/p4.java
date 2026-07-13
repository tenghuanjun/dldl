package com.bytedance.bdtracker;

import android.os.SystemProperties;
import com.bytedance.applog.log.LoggerImpl;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class p4 {
    public static volatile Object b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<String> f307a = Collections.singletonList("SystemPropertiesProxy");

    public final Object a() {
        if (b == null) {
            synchronized (p4.class) {
                if (b == null) {
                    try {
                        b = Class.forName("android.os.SystemProperties").newInstance();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return b;
    }

    public String a(String str) {
        try {
            return SystemProperties.get(str);
        } catch (Throwable th) {
            LoggerImpl.global().error(this.f307a, "Get key:{} value failed", th, str);
            try {
                Object objA = a();
                return (String) objA.getClass().getMethod("get", String.class).invoke(objA, str);
            } catch (Throwable th2) {
                LoggerImpl.global().error(this.f307a, "Get key:{} value by reflection failed", th2, str);
                return "";
            }
        }
    }
}
