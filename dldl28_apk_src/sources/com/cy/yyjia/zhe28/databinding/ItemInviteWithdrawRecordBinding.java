package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteBillBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemInviteWithdrawRecordBinding extends ViewDataBinding {

    @Bindable
    protected InviteBillBean mData;

    public abstract void setData(InviteBillBean data);

    protected ItemInviteWithdrawRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public InviteBillBean getData() {
        return this.mData;
    }

    public static ItemInviteWithdrawRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteWithdrawRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemInviteWithdrawRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_withdraw_record, root, attachToRoot, component);
    }

    public static ItemInviteWithdrawRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteWithdrawRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemInviteWithdrawRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_withdraw_record, null, false, component);
    }

    public static ItemInviteWithdrawRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteWithdrawRecordBinding bind(View view, Object component) {
        return (ItemInviteWithdrawRecordBinding) bind(component, view, R.layout.item_invite_withdraw_record);
    }
}
