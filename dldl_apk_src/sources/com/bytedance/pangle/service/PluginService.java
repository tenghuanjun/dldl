package com.bytedance.pangle.service;

import android.app.Service;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class PluginService extends Service implements a {
    private static final String TAG = "PluginService";

    @Override // com.bytedance.pangle.service.a
    public void attach(Plugin plugin) {
        attachBaseContext(ZeusTransformUtils.wrapperContext2Application(Zeus.getAppApplication(), plugin.mPkgName));
        try {
            FieldUtils.writeField(this, "mActivityManager", createActivityManagerProxy());
            FieldUtils.writeField(this, "mClassName", getClass().getName());
            FieldUtils.writeField(this, "mApplication", Zeus.getAppApplication());
            FieldUtils.writeField(this, "mStartCompatibility", Boolean.valueOf(getApplicationInfo().targetSdkVersion < 5));
        } catch (Exception e) {
            ZeusLogger.errReport(ZeusLogger.TAG_SERVICE, "hook activityManager failed!", e);
        }
    }

    protected Object createActivityManagerProxy() {
        return Proxy.newProxyInstance(getClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new InvocationHandler() { // from class: com.bytedance.pangle.service.PluginService.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
            @Override // java.lang.reflect.InvocationHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r3, java.lang.reflect.Method r4, java.lang.Object[] r5) {
                /*
                    r2 = this;
                    java.lang.String r3 = r4.getName()
                    int r4 = r3.hashCode()
                    r5 = 39551382(0x25b8196, float:1.6126768E-37)
                    r0 = 0
                    r1 = 2
                    if (r4 == r5) goto L2e
                    r5 = 690954390(0x292f2096, float:3.888607E-14)
                    if (r4 == r5) goto L24
                    r5 = 1930712422(0x73145566, float:1.1752198E31)
                    if (r4 == r5) goto L1a
                    goto L38
                L1a:
                    java.lang.String r4 = "stopServiceToken"
                    boolean r3 = r3.equals(r4)
                    if (r3 == 0) goto L38
                    r3 = 0
                    goto L39
                L24:
                    java.lang.String r4 = "getForegroundServiceType"
                    boolean r3 = r3.equals(r4)
                    if (r3 == 0) goto L38
                    r3 = 2
                    goto L39
                L2e:
                    java.lang.String r4 = "setServiceForeground"
                    boolean r3 = r3.equals(r4)
                    if (r3 == 0) goto L38
                    r3 = 1
                    goto L39
                L38:
                    r3 = -1
                L39:
                    if (r3 == 0) goto L44
                    if (r3 == r1) goto L3f
                    r3 = 0
                    return r3
                L3f:
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
                    return r3
                L44:
                    com.bytedance.pangle.service.a.a r3 = com.bytedance.pangle.service.a.a.b()
                    android.content.ComponentName r4 = new android.content.ComponentName
                    com.bytedance.pangle.service.PluginService r5 = com.bytedance.pangle.service.PluginService.this
                    java.lang.Class r0 = r5.getClass()
                    java.lang.String r0 = r0.getName()
                    r4.<init>(r5, r0)
                    boolean r3 = r3.a(r4)
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.service.PluginService.AnonymousClass1.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
            }
        });
    }
}
