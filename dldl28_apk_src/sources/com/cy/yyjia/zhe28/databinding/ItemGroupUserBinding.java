package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGroupUserBinding extends ViewDataBinding {

    @Bindable
    protected UserBean mData;

    public abstract void setData(UserBean data);

    protected ItemGroupUserBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public UserBean getData() {
        return this.mData;
    }

    public static ItemGroupUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGroupUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGroupUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_group_user, root, attachToRoot, component);
    }

    public static ItemGroupUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGroupUserBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGroupUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_group_user, null, false, component);
    }

    public static ItemGroupUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGroupUserBinding bind(View view, Object component) {
        return (ItemGroupUserBinding) bind(component, view, R.layout.item_group_user);
    }
}
