package com.huya.mtp.utils;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import com.huya.mtp.utils.bind.DependencyProperty;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetworkMonitor {
    private final DependencyProperty<Boolean> mNetworkAvailable = new DependencyProperty<>(true);
    private final DependencyProperty<String> mNetworkType = new DependencyProperty<>("unknown");

    NetworkMonitor(Application application) {
        checkNetworkStatus(false);
        application.registerReceiver(new BroadcastReceiver() { // from class: com.huya.mtp.utils.NetworkMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                NetworkMonitor.this.checkNetworkStatus(true);
            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.huya.mtp.utils.NetworkMonitor.2
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
                NetworkMonitor.this.checkNetworkStatus(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNetworkStatus(boolean z) {
        boolean zIsNetworkAvailable = NetworkUtils.isNetworkAvailable();
        if (z && zIsNetworkAvailable == this.mNetworkAvailable.get().booleanValue()) {
            this.mNetworkAvailable.reNotify();
        } else {
            this.mNetworkAvailable.set(Boolean.valueOf(zIsNetworkAvailable));
        }
        String netWorkType = NetworkUtils.getNetWorkType();
        String str = this.mNetworkType.get();
        if (z && TextUtils.equals(netWorkType, str)) {
            this.mNetworkType.reNotify();
        } else {
            this.mNetworkType.set(netWorkType);
        }
    }

    public DependencyProperty.Entity<Boolean> networkAvailable() {
        return this.mNetworkAvailable.getEntity();
    }

    public DependencyProperty.Entity<String> networkType() {
        return this.mNetworkType.getEntity();
    }
}
