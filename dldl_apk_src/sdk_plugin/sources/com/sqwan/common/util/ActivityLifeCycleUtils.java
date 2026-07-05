package com.sqwan.common.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ActivityLifeCycleUtils {
    private static final ActivityLifeCycleUtils ourInstance = new ActivityLifeCycleUtils();
    int appCount;
    Object application;
    private Activity resumedActivity;
    private Activity topActivity;
    public CopyOnWriteArrayList<Activity> activities = new CopyOnWriteArrayList<>();
    final String tag = getClass().getSimpleName();
    List<AppVisibilityCallback> appVisibilityCallbacks = new ArrayList();
    AppVisibilityCallback appVisibilityCallback = new AppVisibilityCallbackAdapter() { // from class: com.sqwan.common.util.ActivityLifeCycleUtils.1
        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onActivityCreated " + activity);
            ActivityLifeCycleUtils.this.activities.add(activity);
            ActivityLifeCycleUtils.this.topActivity = activity;
        }

        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onActivityStarted " + activity);
            Iterator<AppVisibilityCallback> it = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onActivityStarted(activity);
            }
            int i = ActivityLifeCycleUtils.this.appCount;
            ActivityLifeCycleUtils.this.appCount++;
            ActivityLifeCycleUtils.this.logAppCount();
            Iterator<AppVisibilityCallback> it2 = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onActivityStarted(activity);
            }
            if (i != 0 || ActivityLifeCycleUtils.this.appCount == 0) {
                return;
            }
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onForeground");
            Iterator<AppVisibilityCallback> it3 = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it3.hasNext()) {
                it3.next().onForeground();
            }
        }

        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onActivityStopped " + activity);
            Iterator<AppVisibilityCallback> it = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onActivityStopped(activity);
            }
            ActivityLifeCycleUtils activityLifeCycleUtils = ActivityLifeCycleUtils.this;
            activityLifeCycleUtils.appCount--;
            if (ActivityLifeCycleUtils.this.appCount < 0) {
                ActivityLifeCycleUtils.this.appCount = 0;
            }
            ActivityLifeCycleUtils.this.logAppCount();
            Iterator<AppVisibilityCallback> it2 = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onActivityStopped(activity);
            }
            if (ActivityLifeCycleUtils.this.appCount == 0) {
                LogUtil.i(ActivityLifeCycleUtils.this.tag, "onBackground");
                Iterator<AppVisibilityCallback> it3 = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
                while (it3.hasNext()) {
                    it3.next().onBackground();
                }
            }
            if (ActivityLifeCycleUtils.this.resumedActivity == activity) {
                ActivityLifeCycleUtils.this.resumedActivity = null;
            }
        }

        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onActivityPaused " + activity);
            Iterator<AppVisibilityCallback> it = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onActivityPaused(activity);
            }
        }

        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onActivityResumed " + activity);
            Iterator<AppVisibilityCallback> it = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onActivityResumed(activity);
            }
            ActivityLifeCycleUtils.this.topActivity = activity;
            ActivityLifeCycleUtils.this.resumedActivity = activity;
        }

        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            LogUtil.i(ActivityLifeCycleUtils.this.tag, "onActivityDestroyed " + activity);
            Iterator<AppVisibilityCallback> it = ActivityLifeCycleUtils.this.appVisibilityCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onActivityDestroyed(activity);
            }
            ActivityLifeCycleUtils.this.activities.remove(activity);
            if (ActivityLifeCycleUtils.this.topActivity == activity) {
                ActivityLifeCycleUtils.this.topActivity = null;
            }
        }
    };
    public final String PluginActivityName = "com.host.PluginActivity";
    public final String PluginExActivityName = "com.host.PluginExActivity";

    public static abstract class AppVisibilityCallback extends ActivityLifecycleAdapter {
        public abstract void onBackground();

        public abstract void onForeground();
    }

    public static class AppVisibilityCallbackAdapter extends AppVisibilityCallback {
        @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
        public void onBackground() {
        }

        @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
        public void onForeground() {
        }
    }

    public static ActivityLifeCycleUtils getInstance() {
        return ourInstance;
    }

    private ActivityLifeCycleUtils() {
    }

    public boolean isForeground() {
        logAppCount();
        return this.appCount > 0;
    }

    public Activity getTopActivity() {
        return this.topActivity;
    }

    public Activity getResumedActivity() {
        return this.resumedActivity;
    }

    public void init(Object obj) {
        CompatibilityUtil.closeDetectedProblemApiDialog();
        if (this.application == null) {
            this.application = obj;
            if (obj instanceof Application) {
                LogUtil.e(this.tag, "registerActivityLifecycleCallbacks " + this.appVisibilityCallback);
                ((Application) obj).registerActivityLifecycleCallbacks(this.appVisibilityCallback);
                return;
            }
            LogUtil.e(this.tag, "registerActivityLifecycleCallbacks error");
        }
    }

    public void registerActivityListener(AppVisibilityCallback appVisibilityCallback) {
        LogUtil.i(this.tag, "registerActivityListener " + this.application);
        Object obj = this.application;
        if (obj == null || !(obj instanceof Application)) {
            return;
        }
        this.appVisibilityCallbacks.add(appVisibilityCallback);
    }

    public void unRegisterActivityListener(AppVisibilityCallback appVisibilityCallback) {
        LogUtil.i(this.tag, "unRegisterActivityListener " + this.application);
        Object obj = this.application;
        if (obj == null || !(obj instanceof Application)) {
            return;
        }
        this.appVisibilityCallbacks.remove(appVisibilityCallback);
    }

    public boolean equalActivity(Activity activity, Class cls) {
        if (TextUtils.equals("com.host.PluginActivity", activity.getComponentName().getClassName()) || TextUtils.equals("com.host.PluginExActivity", activity.getComponentName().getClassName())) {
            return TextUtils.equals(activity.getIntent().getStringExtra("activity"), cls.getName());
        }
        return activity.getClass() == cls;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAppCount() {
        LogUtil.i(this.tag, "appCount:" + this.appCount);
    }
}
