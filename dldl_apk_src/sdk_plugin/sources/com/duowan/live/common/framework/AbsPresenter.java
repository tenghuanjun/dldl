package com.duowan.live.common.framework;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AbsPresenter implements IPresenter, LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    public AbsPresenter() {
    }

    public AbsPresenter(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(new LifeCycleObserver(this));
        }
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onResume() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onPause() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onCreate() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onDestroy() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
