package com.huya.berry.sdkplayer.common.widgets;

import android.content.res.Configuration;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class LifeCycle {
    public void onConfigurationChanged(Configuration configuration) {
    }

    public abstract void onCreate();

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onStart();

    public abstract void onStop();

    public LifeCycle(LifeCycleViewActivity lifeCycleViewActivity) {
        if (lifeCycleViewActivity != null) {
            lifeCycleViewActivity.registerLifeCycleView(this);
        }
    }
}
