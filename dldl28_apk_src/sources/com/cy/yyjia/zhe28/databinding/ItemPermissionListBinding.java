package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.PermissionListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemPermissionListBinding extends ViewDataBinding {

    @Bindable
    protected PermissionListBean mData;

    public abstract void setData(PermissionListBean data);

    protected ItemPermissionListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public PermissionListBean getData() {
        return this.mData;
    }

    public static ItemPermissionListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPermissionListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemPermissionListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_permission_list, root, attachToRoot, component);
    }

    public static ItemPermissionListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPermissionListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemPermissionListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_permission_list, null, false, component);
    }

    public static ItemPermissionListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPermissionListBinding bind(View view, Object component) {
        return (ItemPermissionListBinding) bind(component, view, R.layout.item_permission_list);
    }
}
