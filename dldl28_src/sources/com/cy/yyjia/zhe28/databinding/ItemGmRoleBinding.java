package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMRoleBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGmRoleBinding extends ViewDataBinding {

    @Bindable
    protected GMRoleBean mData;

    public abstract void setData(GMRoleBean data);

    protected ItemGmRoleBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GMRoleBean getData() {
        return this.mData;
    }

    public static ItemGmRoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmRoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGmRoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_role, root, attachToRoot, component);
    }

    public static ItemGmRoleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmRoleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGmRoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_role, null, false, component);
    }

    public static ItemGmRoleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmRoleBinding bind(View view, Object component) {
        return (ItemGmRoleBinding) bind(component, view, R.layout.item_gm_role);
    }
}
