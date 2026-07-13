package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityUserBinding extends ViewDataBinding {
    public final FrameLayout body;

    protected ActivityUserBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
    }

    public static ActivityUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_user, root, attachToRoot, component);
    }

    public static ActivityUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUserBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_user, null, false, component);
    }

    public static ActivityUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUserBinding bind(View view, Object component) {
        return (ActivityUserBinding) bind(component, view, R.layout.activity_user);
    }
}
