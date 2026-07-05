package com.duowan.live.one.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.os.Bundle;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FixBugUtil {
    private static final Application.ActivityLifecycleCallbacks msCallback = new Application.ActivityLifecycleCallbacks() { // from class: com.duowan.live.one.util.FixBugUtil.1
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
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if ("com.tencent.connect.common.AssistActivity".equals(activity.getLocalClassName())) {
                ArkValue.gMainHandler.postDelayed(FixBugUtil.msCleanAssistActivity, 300L);
            }
        }
    };
    private static final Runnable msCleanAssistActivity = new Runnable() { // from class: com.duowan.live.one.util.FixBugUtil.2
        /* JADX WARN: Type inference failed for: r0v9, types: [com.duowan.live.one.util.FixBugUtil$2$1] */
        @Override // java.lang.Runnable
        public void run() {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) ArkValue.gContext.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks.isEmpty()) {
                return;
            }
            ComponentName componentName = runningTasks.get(0).topActivity;
            if (componentName.getPackageName().equals(ArkValue.gContext.getPackageName()) && componentName.getClassName().equals("com.tencent.connect.common.AssistActivity")) {
                new Thread() { // from class: com.duowan.live.one.util.FixBugUtil.2.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Exception e) {
                            L.error(this, e);
                        }
                    }
                }.start();
            }
        }
    };
    private static final int msDelayTime = 300;

    public static void fixBugRegister() {
        ArkValue.gContext.registerActivityLifecycleCallbacks(msCallback);
    }

    public static void fixBugUnRegister() {
        ArkValue.gContext.unregisterActivityLifecycleCallbacks(msCallback);
    }
}
