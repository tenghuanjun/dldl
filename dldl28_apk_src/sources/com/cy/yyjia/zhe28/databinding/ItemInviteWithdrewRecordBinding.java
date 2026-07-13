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
public abstract class ItemInviteWithdrewRecordBinding extends ViewDataBinding {

    @Bindable
    protected InviteBillBean mData;

    public abstract void setData(InviteBillBean data);

    protected ItemInviteWithdrewRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public InviteBillBean getData() {
        return this.mData;
    }

    public static ItemInviteWithdrewRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteWithdrewRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemInviteWithdrewRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_withdrew_record, root, attachToRoot, component);
    }

    public static ItemInviteWithdrewRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteWithdrewRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemInviteWithdrewRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_invite_withdrew_record, null, false, component);
    }

    public static ItemInviteWithdrewRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteWithdrewRecordBinding bind(View view, Object component) {
        return (ItemInviteWithdrewRecordBinding) bind(component, view, R.layout.item_invite_withdrew_record);
    }
}
