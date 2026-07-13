package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityEmptyBinding extends ViewDataBinding {
    public final FrameLayout body;
    public final LinearLayout ll;
    public final Navigation navigation;

    protected ActivityEmptyBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body, LinearLayout ll, Navigation navigation) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
        this.ll = ll;
        this.navigation = navigation;
    }

    public static ActivityEmptyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEmptyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityEmptyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_empty, root, attachToRoot, component);
    }

    public static ActivityEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEmptyBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityEmptyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_empty, null, false, component);
    }

    public static ActivityEmptyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEmptyBinding bind(View view, Object component) {
        return (ActivityEmptyBinding) bind(component, view, R.layout.activity_empty);
    }
}
