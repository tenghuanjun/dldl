package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipFlbBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemVipFlbBinding extends ViewDataBinding {

    @Bindable
    protected VipFlbBean mData;

    public abstract void setData(VipFlbBean data);

    protected ItemVipFlbBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public VipFlbBean getData() {
        return this.mData;
    }

    public static ItemVipFlbBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipFlbBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVipFlbBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_flb, root, attachToRoot, component);
    }

    public static ItemVipFlbBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipFlbBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVipFlbBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_flb, null, false, component);
    }

    public static ItemVipFlbBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipFlbBinding bind(View view, Object component) {
        return (ItemVipFlbBinding) bind(component, view, R.layout.item_vip_flb);
    }
}
