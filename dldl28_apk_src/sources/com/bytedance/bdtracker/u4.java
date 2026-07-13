package com.bytedance.bdtracker;

import android.view.View;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class u4 {
    public static Field b;
    public static Class c;
    public static Class d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f328a = Collections.singletonList("WindowHelper");
    public static boolean e = false;

    /* JADX WARN: Can't wrap try/catch for region: R(14:4|(6:47|5|6|7|(1:9)(1:10)|11)|15|(2:45|16)|(10:18|41|19|43|29|(1:31)(1:34)|32|35|39|40)(1:24)|22|25|43|29|(0)(0)|32|35|39|40) */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0082, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0083, code lost:
    
        com.bytedance.applog.log.LoggerImpl.global().error(com.bytedance.bdtracker.u4.f328a, "Get popup view failed", r0, new java.lang.Object[0]);
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a() {
        /*
            boolean r0 = com.bytedance.bdtracker.u4.e
            if (r0 != 0) goto L92
            java.lang.String r0 = "android.view.WindowManagerGlobal"
            r1 = 1
            r2 = 0
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = "sDefaultWindowManager"
            java.lang.String r4 = "mViews"
            java.lang.reflect.Field r4 = r0.getDeclaredField(r4)     // Catch: java.lang.Throwable -> L39
            com.bytedance.bdtracker.u4.b = r4     // Catch: java.lang.Throwable -> L39
            java.lang.reflect.Field r0 = r0.getDeclaredField(r3)     // Catch: java.lang.Throwable -> L39
            java.lang.reflect.Field r3 = com.bytedance.bdtracker.u4.b     // Catch: java.lang.Throwable -> L39
            r3.setAccessible(r1)     // Catch: java.lang.Throwable -> L39
            java.lang.reflect.Field r3 = com.bytedance.bdtracker.u4.b     // Catch: java.lang.Throwable -> L39
            java.lang.Class r3 = r3.getType()     // Catch: java.lang.Throwable -> L39
            java.lang.Class<java.util.ArrayList> r4 = java.util.ArrayList.class
            if (r3 != r4) goto L2a
            goto L31
        L2a:
            java.lang.reflect.Field r3 = com.bytedance.bdtracker.u4.b     // Catch: java.lang.Throwable -> L39
            r3.getType()     // Catch: java.lang.Throwable -> L39
            java.lang.Class<android.view.View[]> r3 = android.view.View[].class
        L31:
            r0.setAccessible(r1)     // Catch: java.lang.Throwable -> L39
            r3 = 0
            r0.get(r3)     // Catch: java.lang.Throwable -> L39
            goto L47
        L39:
            r0 = move-exception
            com.bytedance.applog.log.IAppLogLogger r3 = com.bytedance.applog.log.LoggerImpl.global()
            java.util.List<java.lang.String> r4 = com.bytedance.bdtracker.u4.f328a
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "Get window manager views failed"
            r3.error(r4, r6, r0, r5)
        L47:
            r0 = 23
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L63
            if (r3 < r0) goto L5d
            java.lang.String r3 = "com.android.internal.policy.PhoneWindow$DecorView"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.ClassNotFoundException -> L56 java.lang.Throwable -> L63
            com.bytedance.bdtracker.u4.c = r3     // Catch: java.lang.ClassNotFoundException -> L56 java.lang.Throwable -> L63
            goto L71
        L56:
            java.lang.String r3 = "com.android.internal.policy.DecorView"
        L58:
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Throwable -> L63
            goto L60
        L5d:
            java.lang.String r3 = "com.android.internal.policy.impl.PhoneWindow$DecorView"
            goto L58
        L60:
            com.bytedance.bdtracker.u4.c = r3     // Catch: java.lang.Throwable -> L63
            goto L71
        L63:
            r3 = move-exception
            com.bytedance.applog.log.IAppLogLogger r4 = com.bytedance.applog.log.LoggerImpl.global()
            java.util.List<java.lang.String> r5 = com.bytedance.bdtracker.u4.f328a
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "Get DecorView failed"
            r4.error(r5, r7, r3, r6)
        L71:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L82
            if (r3 < r0) goto L7c
            java.lang.String r0 = "android.widget.PopupWindow$PopupDecorView"
        L77:
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L82
            goto L7f
        L7c:
            java.lang.String r0 = "android.widget.PopupWindow$PopupViewContainer"
            goto L77
        L7f:
            com.bytedance.bdtracker.u4.d = r0     // Catch: java.lang.Throwable -> L82
            goto L90
        L82:
            r0 = move-exception
            com.bytedance.applog.log.IAppLogLogger r3 = com.bytedance.applog.log.LoggerImpl.global()
            java.util.List<java.lang.String> r4 = com.bytedance.bdtracker.u4.f328a
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r5 = "Get popup view failed"
            r3.error(r4, r5, r0, r2)
        L90:
            com.bytedance.bdtracker.u4.e = r1
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.u4.a():void");
    }

    public static boolean a(View view) {
        if (!e) {
            a();
        }
        Class<?> cls = view.getClass();
        return cls == c || cls == d;
    }
}
