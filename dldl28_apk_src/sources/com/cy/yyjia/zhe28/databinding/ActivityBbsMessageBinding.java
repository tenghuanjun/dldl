package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBbsMessageBinding extends ViewDataBinding {
    protected ActivityBbsMessageBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static ActivityBbsMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBbsMessageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_message, root, attachToRoot, component);
    }

    public static ActivityBbsMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsMessageBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBbsMessageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_message, null, false, component);
    }

    public static ActivityBbsMessageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsMessageBinding bind(View view, Object component) {
        return (ActivityBbsMessageBinding) bind(component, view, R.layout.activity_bbs_message);
    }
}
