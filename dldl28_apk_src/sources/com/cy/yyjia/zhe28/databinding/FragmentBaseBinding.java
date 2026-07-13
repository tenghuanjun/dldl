package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.ViewStubProxy;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentBaseBinding extends ViewDataBinding {
    public final ViewStubProxy vs;

    protected FragmentBaseBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewStubProxy vs) {
        super(_bindingComponent, _root, _localFieldCount);
        this.vs = vs;
    }

    public static FragmentBaseBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBaseBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentBaseBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_base, root, attachToRoot, component);
    }

    public static FragmentBaseBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBaseBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentBaseBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_base, null, false, component);
    }

    public static FragmentBaseBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBaseBinding bind(View view, Object component) {
        return (FragmentBaseBinding) bind(component, view, R.layout.fragment_base);
    }
}
