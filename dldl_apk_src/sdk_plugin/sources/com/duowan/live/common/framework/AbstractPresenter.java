package com.duowan.live.common.framework;

import android.arch.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbstractPresenter extends BasePresenter {
    public AbstractPresenter() {
    }

    public AbstractPresenter(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(new LifeCycleObserver(this));
        }
    }
}
