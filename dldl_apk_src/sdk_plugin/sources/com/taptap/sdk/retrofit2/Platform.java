package com.taptap.sdk.retrofit2;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
final class Platform {
    static final BuiltInFactories builtInFactories;

    @Nullable
    static final Executor callbackExecutor;
    static final Reflection reflection;

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    static {
        /*
            java.lang.String r0 = "java.vm.name"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            int r1 = r0.hashCode()
            r2 = -1841837151(0xffffffff9237cba1, float:-5.799561E-28)
            r3 = 1
            if (r1 == r2) goto L20
            r2 = 2039697993(0x79935249, float:9.561707E34)
            if (r1 == r2) goto L16
            goto L2a
        L16:
            java.lang.String r1 = "Dalvik"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L2a
            r0 = 0
            goto L2b
        L20:
            java.lang.String r1 = "RoboVM"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L2a
            r0 = 1
            goto L2b
        L2a:
            r0 = -1
        L2b:
            if (r0 == 0) goto L52
            r1 = 0
            if (r0 == r3) goto L41
            com.taptap.sdk.retrofit2.Platform.callbackExecutor = r1
            com.taptap.sdk.retrofit2.Reflection$Java8 r0 = new com.taptap.sdk.retrofit2.Reflection$Java8
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.reflection = r0
            com.taptap.sdk.retrofit2.BuiltInFactories$Java8 r0 = new com.taptap.sdk.retrofit2.BuiltInFactories$Java8
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.builtInFactories = r0
            goto L7c
        L41:
            com.taptap.sdk.retrofit2.Platform.callbackExecutor = r1
            com.taptap.sdk.retrofit2.Reflection r0 = new com.taptap.sdk.retrofit2.Reflection
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.reflection = r0
            com.taptap.sdk.retrofit2.BuiltInFactories r0 = new com.taptap.sdk.retrofit2.BuiltInFactories
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.builtInFactories = r0
            goto L7c
        L52:
            com.taptap.sdk.retrofit2.AndroidMainExecutor r0 = new com.taptap.sdk.retrofit2.AndroidMainExecutor
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.callbackExecutor = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 < r1) goto L6e
            com.taptap.sdk.retrofit2.Reflection$Android24 r0 = new com.taptap.sdk.retrofit2.Reflection$Android24
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.reflection = r0
            com.taptap.sdk.retrofit2.BuiltInFactories$Java8 r0 = new com.taptap.sdk.retrofit2.BuiltInFactories$Java8
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.builtInFactories = r0
            goto L7c
        L6e:
            com.taptap.sdk.retrofit2.Reflection r0 = new com.taptap.sdk.retrofit2.Reflection
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.reflection = r0
            com.taptap.sdk.retrofit2.BuiltInFactories r0 = new com.taptap.sdk.retrofit2.BuiltInFactories
            r0.<init>()
            com.taptap.sdk.retrofit2.Platform.builtInFactories = r0
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.retrofit2.Platform.<clinit>():void");
    }

    private Platform() {
    }
}
