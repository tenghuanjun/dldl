package com.duowan.live.common.framework;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.AbstractPresenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbstractViewContainer<T extends AbstractPresenter> extends FrameLayout implements ILifeCycle {
    protected T mBasePresenter;

    public abstract T createPresenter();

    protected abstract void init();

    public abstract void onDestroy();

    public AbstractViewContainer(Context context) {
        super(context);
        initPresenter();
        init();
    }

    public AbstractViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initPresenter();
        init();
    }

    public AbstractViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
        T t = this.mBasePresenter;
        if (t != null) {
            t.onResume();
        }
    }

    @Override // com.duowan.live.common.framework.ILifeCycle
    public void onPause() {
        T t = this.mBasePresenter;
        if (t != null) {
            t.onPause();
        }
    }

    public void onCreate() {
        T t = this.mBasePresenter;
        if (t != null) {
            t.onCreate();
        }
    }

    protected FragmentManager getFragmentManager() {
        return ((Activity) getContext()).getFragmentManager();
    }
}
