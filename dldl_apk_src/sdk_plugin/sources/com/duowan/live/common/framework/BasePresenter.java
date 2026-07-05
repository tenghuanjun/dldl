package com.duowan.live.common.framework;

import android.arch.lifecycle.LifecycleOwner;
import com.huya.live.common.api.BaseApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BasePresenter extends AbsPresenter {
    protected boolean mIsRegister;

    public BasePresenter() {
    }

    public BasePresenter(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(new LifeCycleObserver(this));
        }
    }

    @Override // com.duowan.live.common.framework.AbsPresenter, com.duowan.live.common.framework.IPresenter
    public void onResume() {
        super.onResume();
        if (this.mIsRegister) {
            return;
        }
        this.mIsRegister = true;
        BaseApi.getSignalCenterApi().register(this);
    }

    @Override // com.duowan.live.common.framework.AbsPresenter, com.duowan.live.common.framework.IPresenter
    public void onPause() {
        super.onPause();
        BaseApi.getSignalCenterApi().unregister(this);
        this.mIsRegister = false;
    }
}
