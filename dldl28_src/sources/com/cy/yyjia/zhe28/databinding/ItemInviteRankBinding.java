package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteRankBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemInviteRankBinding extends ViewDataBinding {

    @Bindable
    protected InviteRankBean mData;

    public abstract void setData(InviteRankBean data);

    protected ItemInviteRankBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public InviteRankBean getData() {
        return this.mData;
    }

    public static ItemInviteRankBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteRankBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemInviteRankBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_rank, root, attachToRoot, component);
    }

    public static ItemInviteRankBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteRankBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemInviteRankBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_rank, null, false, component);
    }

    public static ItemInviteRankBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteRankBinding bind(View view, Object component) {
        return (ItemInviteRankBinding) bind(component, view, R.layout.item_invite_rank);
    }
}
