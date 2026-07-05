package com.taptap.sdk.base.utils.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapActivityLifecycleCallbacks.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lcom/taptap/sdk/base/utils/lifecycle/TapActivityLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onBackground", "context", "Landroid/content/Context;", "onForeground", "tap-base_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface TapActivityLifecycleCallbacks extends Application.ActivityLifecycleCallbacks {

    /* JADX INFO: compiled from: TapActivityLifecycleCallbacks.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void onActivityCreated(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity, Bundle bundle) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityDestroyed(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityPaused(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityResumed(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivitySaveInstanceState(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity, Bundle outState) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(outState, "outState");
        }

        public static void onActivityStarted(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityStopped(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onBackground(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
        }

        public static void onForeground(TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityCreated(Activity activity, Bundle savedInstanceState);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityDestroyed(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityPaused(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityResumed(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivitySaveInstanceState(Activity activity, Bundle outState);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityStarted(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityStopped(Activity activity);

    void onBackground(Context context);

    void onForeground(Context context);
}
