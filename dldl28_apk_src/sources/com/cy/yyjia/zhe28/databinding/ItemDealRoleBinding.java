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
public abstract class ItemDealRoleBinding extends ViewDataBinding {

    @Bindable
    protected GMRoleBean mData;

    public abstract void setData(GMRoleBean data);

    protected ItemDealRoleBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GMRoleBean getData() {
        return this.mData;
    }

    public static ItemDealRoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealRoleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealRoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_role, root, attachToRoot, component);
    }

    public static ItemDealRoleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealRoleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealRoleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_role, null, false, component);
    }

    public static ItemDealRoleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealRoleBinding bind(View view, Object component) {
        return (ItemDealRoleBinding) bind(component, view, R.layout.item_deal_role);
    }
}
