package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDevelopBinding extends ViewDataBinding {

    @Bindable
    protected String mTitle;

    public abstract void setTitle(String title);

    protected ActivityDevelopBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public static ActivityDevelopBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDevelopBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDevelopBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_develop, root, attachToRoot, component);
    }

    public static ActivityDevelopBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDevelopBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDevelopBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_develop, null, false, component);
    }

    public static ActivityDevelopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDevelopBinding bind(View view, Object component) {
        return (ActivityDevelopBinding) bind(component, view, R.layout.activity_develop);
    }
}
