package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogWelfare3RoleBinding extends ViewDataBinding {
    public final RecyclerView rv;
    public final TextView tvCancel;
    public final TextView tvGo;

    protected DialogWelfare3RoleBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, TextView tvCancel, TextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    public static DialogWelfare3RoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWelfare3RoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogWelfare3RoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_welfare3_role, root, attachToRoot, component);
    }

    public static DialogWelfare3RoleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWelfare3RoleBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogWelfare3RoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_welfare3_role, null, false, component);
    }

    public static DialogWelfare3RoleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWelfare3RoleBinding bind(View view, Object component) {
        return (DialogWelfare3RoleBinding) bind(component, view, R.layout.dialog_welfare3_role);
    }
}
