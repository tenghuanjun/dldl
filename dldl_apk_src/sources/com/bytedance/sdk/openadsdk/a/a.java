package com.bytedance.sdk.openadsdk.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
class a implements Application.ActivityLifecycleCallbacks {
    private static volatile boolean a;
    private int b = 0;
    private InterfaceC0024a c;

    /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0024a {
        void a();

        void b();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    a() {
    }

    public Boolean a() {
        return Boolean.valueOf(a);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.b++;
        a = false;
        InterfaceC0024a interfaceC0024a = this.c;
        if (interfaceC0024a != null) {
            interfaceC0024a.b();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i = this.b - 1;
        this.b = i;
        if (i == 0) {
            a = true;
            InterfaceC0024a interfaceC0024a = this.c;
            if (interfaceC0024a != null) {
                interfaceC0024a.a();
            }
        }
    }

    public void a(InterfaceC0024a interfaceC0024a) {
        this.c = interfaceC0024a;
    }
}
