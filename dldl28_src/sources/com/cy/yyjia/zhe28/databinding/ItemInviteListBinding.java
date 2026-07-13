package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemInviteListBinding extends ViewDataBinding {

    @Bindable
    protected InviteListBean mData;

    public abstract void setData(InviteListBean data);

    protected ItemInviteListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public InviteListBean getData() {
        return this.mData;
    }

    public static ItemInviteListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemInviteListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_list, root, attachToRoot, component);
    }

    public static ItemInviteListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemInviteListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_list, null, false, component);
    }

    public static ItemInviteListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteListBinding bind(View view, Object component) {
        return (ItemInviteListBinding) bind(component, view, R.layout.item_invite_list);
    }
}
