package com.sy.window;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
final class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private WindowX<?> mWindow;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
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

    ActivityLifecycle(WindowX<?> windowX, Activity activity) {
        this.mActivity = activity;
        this.mWindow = windowX;
    }

    void register() {
        if (this.mActivity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mActivity.registerActivityLifecycleCallbacks(this);
        } else {
            this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    void unregister() {
        if (this.mActivity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mActivity.unregisterActivityLifecycleCallbacks(this);
        } else {
            this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        WindowX<?> windowX;
        Activity activity2 = this.mActivity;
        if (activity2 == activity && activity2.isFinishing() && (windowX = this.mWindow) != null && windowX.isShowing()) {
            this.mWindow.cancel();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (this.mActivity != activity) {
            return;
        }
        this.mActivity = null;
        WindowX<?> windowX = this.mWindow;
        if (windowX == null) {
            return;
        }
        windowX.recycle();
        this.mWindow = null;
    }
}
