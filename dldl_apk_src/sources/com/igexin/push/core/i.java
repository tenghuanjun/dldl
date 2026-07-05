package com.igexin.push.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.igexin.push.core.p;
import com.igexin.sdk.PushConsts;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class i implements Application.ActivityLifecycleCallbacks {
    private static final String a = "GALC";
    private long b;
    private int c;

    private void a(Activity activity) {
        try {
            activity.getComponentName().getClassName();
            com.igexin.b.a.c.a.a("GALC|" + activity.getComponentName().getClassName() + " onAStart " + this.c, new Object[0]);
            if (this.c == 0) {
                com.igexin.b.a.c.a.a("GALC|>>>>>> FG", new Object[0]);
                Context applicationContext = activity.getApplicationContext();
                if (com.igexin.push.f.k.a(applicationContext) || System.currentTimeMillis() - this.b <= 20000) {
                    return;
                }
                com.igexin.push.core.a.b.d();
                Intent intent = new Intent(applicationContext, (Class<?>) com.igexin.push.core.a.b.a(applicationContext));
                intent.putExtra("action", PushConsts.ACTION_SERVICE_ONRESUME);
                p.a.a.a(applicationContext, intent);
                com.igexin.b.a.c.a.a("GALC|on fg, start>>>>>>", new Object[0]);
                this.b = System.currentTimeMillis();
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            activity.getComponentName().getClassName();
            com.igexin.b.a.c.a.a("GALC|" + activity.getComponentName().getClassName() + " onAStart " + this.c, new Object[0]);
            if (this.c == 0) {
                com.igexin.b.a.c.a.a("GALC|>>>>>> FG", new Object[0]);
                Context applicationContext = activity.getApplicationContext();
                if (!com.igexin.push.f.k.a(applicationContext) && System.currentTimeMillis() - this.b > 20000) {
                    com.igexin.push.core.a.b.d();
                    Intent intent = new Intent(applicationContext, (Class<?>) com.igexin.push.core.a.b.a(applicationContext));
                    intent.putExtra("action", PushConsts.ACTION_SERVICE_ONRESUME);
                    p.a.a.a(applicationContext, intent);
                    com.igexin.b.a.c.a.a("GALC|on fg, start>>>>>>", new Object[0]);
                    this.b = System.currentTimeMillis();
                }
            }
        } catch (Throwable unused) {
        }
        this.c++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        if (activity == null) {
            return;
        }
        this.c--;
        this.c = Math.max(this.c, 0);
        activity.getComponentName().getClassName();
        com.igexin.b.a.c.a.a("GALC|" + activity.getComponentName().getClassName() + " onAStopp " + this.c, new Object[0]);
        if (this.c == 0) {
            com.igexin.b.a.c.a.a("GALC|>>>>>> on bg", new Object[0]);
        }
    }
}
