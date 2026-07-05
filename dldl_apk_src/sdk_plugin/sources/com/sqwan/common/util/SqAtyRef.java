package com.sqwan.common.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.sq.sdk.tool.util.SqLogUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqAtyRef {
    private static volatile SqAtyRef sInstance;
    private WeakReference<Activity> mActivityRef;
    private Class<? extends Activity> mAtyClass;
    private Application.ActivityLifecycleCallbacks mLifecycleCallback;

    public static SqAtyRef getInstance() {
        if (sInstance == null) {
            synchronized (SqAtyRef.class) {
                if (sInstance == null) {
                    sInstance = new SqAtyRef();
                }
            }
        }
        return sInstance;
    }

    private SqAtyRef() {
    }

    private void observeAty(Activity activity) {
        if (this.mLifecycleCallback != null) {
            return;
        }
        Context applicationContext = activity.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            SqLogUtil.w("获取Application失败, 不能监听");
            return;
        }
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.sqwan.common.util.SqAtyRef.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity2) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity2) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity2, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity2) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity2, Bundle bundle) {
                if (activity2.getClass().equals(SqAtyRef.this.mAtyClass)) {
                    SqLogUtil.i("刷新" + SqAtyRef.this.mAtyClass + "实例 " + activity2);
                    SqAtyRef.this.mActivityRef = new WeakReference(activity2);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity2) {
                if (activity2.getClass().equals(SqAtyRef.this.mAtyClass)) {
                    SqLogUtil.w("主Activity onStopped: " + activity2);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity2) {
                if (activity2.getClass().equals(SqAtyRef.this.mAtyClass) && activity2.equals(SqAtyRef.this.mActivityRef.get())) {
                    SqAtyRef.this.mActivityRef = null;
                    SqLogUtil.w("主Activity销毁: " + activity2);
                }
            }
        };
        this.mLifecycleCallback = activityLifecycleCallbacks;
        ((Application) applicationContext).registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void init(Activity activity) {
        this.mActivityRef = new WeakReference<>(activity);
        this.mAtyClass = activity.getClass();
        SqLogUtil.i("主Activity为" + this.mAtyClass);
        observeAty(activity);
    }

    public Class<? extends Activity> getMainAtyClass() {
        Class<? extends Activity> cls = this.mAtyClass;
        if (cls != null) {
            return cls;
        }
        throw new IllegalStateException("未初始化主Activity");
    }

    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.mActivityRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
