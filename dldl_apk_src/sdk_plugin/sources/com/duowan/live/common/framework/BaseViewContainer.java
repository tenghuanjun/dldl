package com.duowan.live.common.framework;

import android.app.Activity;
import android.app.FragmentManager;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.AbsPresenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class BaseViewContainer<T extends AbsPresenter> extends FrameLayout implements ILifeCycle, LifecycleOwner {
    protected T mBasePresenter;
    private LifecycleRegistry mLifecycleRegistry;
    protected boolean mPause;

    public abstract T createPresenter();

    protected abstract void init();

    public void requestData() {
    }

    public BaseViewContainer(Context context) {
        super(context);
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        initPresenter();
        init();
    }

    public BaseViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        initPresenter();
        init();
    }

    public BaseViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        initPresenter();
        init();
    }

    protected void initPresenter() {
        this.mBasePresenter = (T) createPresenter();
        onCreate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        if (!isInEditMode()) {
            L.info(this, "lifecycle | ViewContainer | onAttachedToWindow");
        }
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        L.info(this, "lifecycle | ViewContainer | onDetachedFromWindow");
        onStop();
        onDestroy();
        T t = this.mBasePresenter;
        if (t != null) {
            t.onDestroy();
            this.mBasePresenter = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // com.duowan.live.common.framework.ILifeCycle
    public void onResume() {
        L.info(this, "onResume...");
        this.mPause = false;
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        T t = this.mBasePresenter;
        if (t != null) {
            t.onResume();
        }
    }

    @Override // com.duowan.live.common.framework.ILifeCycle
    public void onPause() {
        L.info(this, "onPause...");
        this.mPause = true;
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        T t = this.mBasePresenter;
        if (t != null) {
            t.onPause();
        }
    }

    public void onStop() {
        L.info(this, "onStop...");
        T t = this.mBasePresenter;
        if (t != null) {
            t.onPause();
        }
    }

    protected void onCreate() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        T t = this.mBasePresenter;
        if (t != null) {
            t.onCreate();
        }
    }

    protected void onDestroy() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    protected FragmentManager getFragmentManager() {
        return ((Activity) getContext()).getFragmentManager();
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
