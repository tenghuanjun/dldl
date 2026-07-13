package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivitySplashBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected UpdateBean.BaseInfoBean mInfo;
    public final ShapeTextView tvGo;
    public final ShapeTextView tvSkip;

    public abstract void setInfo(UpdateBean.BaseInfoBean info);

    protected ActivitySplashBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, ShapeTextView tvGo, ShapeTextView tvSkip) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.tvGo = tvGo;
        this.tvSkip = tvSkip;
    }

    public UpdateBean.BaseInfoBean getInfo() {
        return this.mInfo;
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySplashBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySplashBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_splash, root, attachToRoot, component);
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySplashBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySplashBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_splash, null, false, component);
    }

    public static ActivitySplashBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySplashBinding bind(View view, Object component) {
        return (ActivitySplashBinding) bind(component, view, R.layout.activity_splash);
    }
}
