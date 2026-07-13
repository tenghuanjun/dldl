package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogLogoutBinding extends ViewDataBinding {
    public final TextView btn;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f449tv;

    protected DialogLogoutBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.f449tv = tv2;
    }

    public static DialogLogoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLogoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogLogoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_logout, root, attachToRoot, component);
    }

    public static DialogLogoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLogoutBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogLogoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_logout, null, false, component);
    }

    public static DialogLogoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLogoutBinding bind(View view, Object component) {
        return (DialogLogoutBinding) bind(component, view, R.layout.dialog_logout);
    }
}
