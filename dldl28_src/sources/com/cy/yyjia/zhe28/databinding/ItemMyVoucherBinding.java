package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VoucherBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMyVoucherBinding extends ViewDataBinding {

    @Bindable
    protected VoucherBean mData;

    public abstract void setData(VoucherBean data);

    protected ItemMyVoucherBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public VoucherBean getData() {
        return this.mData;
    }

    public static ItemMyVoucherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyVoucherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMyVoucherBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_voucher, root, attachToRoot, component);
    }

    public static ItemMyVoucherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyVoucherBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMyVoucherBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_voucher, null, false, component);
    }

    public static ItemMyVoucherBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyVoucherBinding bind(View view, Object component) {
        return (ItemMyVoucherBinding) bind(component, view, R.layout.item_my_voucher);
    }
}
