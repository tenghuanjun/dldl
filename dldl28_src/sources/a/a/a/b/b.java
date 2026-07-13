package a.a.a.b;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* JADX INFO: compiled from: AutomaticManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f68a;

    public b(a aVar) {
        this.f68a = aVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        a aVar = this.f68a;
        int i = aVar.b - 1;
        aVar.b = i;
        if (i == 0) {
            aVar.c = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        a aVar = this.f68a;
        aVar.b++;
        aVar.c = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        a aVar = this.f68a;
        int i = aVar.f65a + 1;
        aVar.f65a = i;
        if (i == 1 && aVar.d) {
            aVar.d = false;
            a.a(this.f68a);
            a.a.a.g.b.a("AutomaticManager", "onForeground");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        a aVar = this.f68a;
        int i = aVar.f65a - 1;
        aVar.f65a = i;
        if (i == 0 && aVar.c) {
            aVar.d = true;
            this.f68a.g.removeCallbacksAndMessages(null);
            a.a.a.g.b.a("AutomaticManager", "onBackground");
        }
    }
}
