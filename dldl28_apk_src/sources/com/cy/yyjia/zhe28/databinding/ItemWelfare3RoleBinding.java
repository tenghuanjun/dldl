package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RoleBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfare3RoleBinding extends ViewDataBinding {

    @Bindable
    protected RoleBean mData;

    public abstract void setData(RoleBean data);

    protected ItemWelfare3RoleBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public RoleBean getData() {
        return this.mData;
    }

    public static ItemWelfare3RoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3RoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfare3RoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_role, root, attachToRoot, component);
    }

    public static ItemWelfare3RoleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3RoleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfare3RoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_role, null, false, component);
    }

    public static ItemWelfare3RoleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3RoleBinding bind(View view, Object component) {
        return (ItemWelfare3RoleBinding) bind(component, view, R.layout.item_welfare3_role);
    }
}
