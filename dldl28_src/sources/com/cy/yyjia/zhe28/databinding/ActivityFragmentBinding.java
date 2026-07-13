package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityFragmentBinding extends ViewDataBinding {
    public final FrameLayout body;
    public final Navigation navigation;

    protected ActivityFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body, Navigation navigation) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
        this.navigation = navigation;
    }

    public static ActivityFragmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFragmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityFragmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_fragment, root, attachToRoot, component);
    }

    public static ActivityFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFragmentBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityFragmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_fragment, null, false, component);
    }

    public static ActivityFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFragmentBinding bind(View view, Object component) {
        return (ActivityFragmentBinding) bind(component, view, R.layout.activity_fragment);
    }
}
