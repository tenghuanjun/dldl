package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutTabTextBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f475tv;

    protected LayoutTabTextBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f475tv = tv2;
    }

    public static LayoutTabTextBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTabTextBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutTabTextBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_tab_text, root, attachToRoot, component);
    }

    public static LayoutTabTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTabTextBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutTabTextBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_tab_text, null, false, component);
    }

    public static LayoutTabTextBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTabTextBinding bind(View view, Object component) {
        return (LayoutTabTextBinding) bind(component, view, R.layout.layout_tab_text);
    }
}
