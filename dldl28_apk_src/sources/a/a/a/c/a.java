package a.a.a.c;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: AliDetectImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a.a.a.c.m.a f79a;
    public final /* synthetic */ c b;

    /* JADX INFO: renamed from: a.a.a.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AliDetectImpl.java */
    public class RunnableC0001a implements Runnable {
        public RunnableC0001a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                c.a(a.this.b, a.this.f79a);
            } catch (Throwable th) {
                d.b("AL", Log.getStackTraceString(th));
            }
        }
    }

    public a(c cVar, a.a.a.c.m.a aVar) {
        this.b = cVar;
        this.f79a = aVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            if (TextUtils.isEmpty(this.f79a.h)) {
                String strA = c.a(this.f79a, activity);
                this.f79a.h = strA;
                d.a("AL", "aa version:" + strA);
                a.a.a.c.n.a.a(new RunnableC0001a());
            }
        } catch (Throwable th) {
            d.d("AL", Log.getStackTraceString(th));
        }
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
