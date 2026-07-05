package com.huya.berry.sdkplayer.common.widgets;

import android.content.res.Configuration;
import android.os.Bundle;
import com.duowan.kiwi.barrage.config.GLBarrageAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class LifeCycleViewActivity extends KiwiBaseActivity {
    private List<WeakReference<LifeCycle>> mLifeCycleView = new ArrayList(16);

    private void executePassedLifeCycle(LifeCycle lifeCycle) {
    }

    @Override // com.huya.berry.sdkplayer.common.widgets.KiwiBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        List<WeakReference<LifeCycle>> list = this.mLifeCycleView;
        if (list == null) {
            return;
        }
        Iterator<WeakReference<LifeCycle>> it = list.iterator();
        while (it.hasNext()) {
            LifeCycle lifeCycle = it.next().get();
            if (lifeCycle != null) {
                lifeCycle.onCreate();
            }
        }
    }

    @Override // com.duowan.auk.ui.BaseActivity, android.app.Activity
    protected void onPause() {
        GLBarrageAdapter.pause();
        super.onPause();
        List<WeakReference<LifeCycle>> list = this.mLifeCycleView;
        if (list == null) {
            return;
        }
        Iterator<WeakReference<LifeCycle>> it = list.iterator();
        while (it.hasNext()) {
            LifeCycle lifeCycle = it.next().get();
            if (lifeCycle != null) {
                lifeCycle.onPause();
            }
        }
    }

    @Override // com.duowan.auk.ui.BaseActivity, android.app.Activity
    protected void onResume() {
        GLBarrageAdapter.resume();
        super.onResume();
        List<WeakReference<LifeCycle>> list = this.mLifeCycleView;
        if (list == null) {
            return;
        }
        Iterator<WeakReference<LifeCycle>> it = list.iterator();
        while (it.hasNext()) {
            LifeCycle lifeCycle = it.next().get();
            if (lifeCycle != null) {
                lifeCycle.onResume();
            }
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        List<WeakReference<LifeCycle>> list = this.mLifeCycleView;
        if (list == null) {
            return;
        }
        for (WeakReference<LifeCycle> weakReference : list) {
            if (weakReference.get() != null) {
                weakReference.get().onStart();
            }
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        List<WeakReference<LifeCycle>> list = this.mLifeCycleView;
        if (list == null) {
            return;
        }
        for (WeakReference<LifeCycle> weakReference : list) {
            if (weakReference.get() != null) {
                weakReference.get().onStop();
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        List<WeakReference<LifeCycle>> list = this.mLifeCycleView;
        if (list == null) {
            return;
        }
        for (WeakReference<LifeCycle> weakReference : list) {
            if (weakReference.get() != null) {
                weakReference.get().onDestroy();
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        for (WeakReference<LifeCycle> weakReference : this.mLifeCycleView) {
            if (weakReference.get() != null) {
                weakReference.get().onConfigurationChanged(configuration);
            }
        }
    }

    public void registerLifeCycleView(LifeCycle lifeCycle) {
        if (lifeCycle == null) {
            return;
        }
        executePassedLifeCycle(lifeCycle);
        this.mLifeCycleView.add(new WeakReference<>(lifeCycle));
    }
}
