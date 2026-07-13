package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentCancellation4Binding extends ViewDataBinding {
    protected FragmentCancellation4Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static FragmentCancellation4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCancellation4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation4, root, attachToRoot, component);
    }

    public static FragmentCancellation4Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation4Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCancellation4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation4, null, false, component);
    }

    public static FragmentCancellation4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation4Binding bind(View view, Object component) {
        return (FragmentCancellation4Binding) bind(component, view, R.layout.fragment_cancellation4);
    }
}
