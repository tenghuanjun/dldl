package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealSellChildBinding extends ViewDataBinding {

    @Bindable
    protected DealBean mData;

    @Bindable
    protected String mIcon;

    public abstract void setData(DealBean data);

    public abstract void setIcon(String icon);

    protected ItemDealSellChildBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public String getIcon() {
        return this.mIcon;
    }

    public DealBean getData() {
        return this.mData;
    }

    public static ItemDealSellChildBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealSellChildBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealSellChildBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_sell_child, root, attachToRoot, component);
    }

    public static ItemDealSellChildBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealSellChildBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealSellChildBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_sell_child, null, false, component);
    }

    public static ItemDealSellChildBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealSellChildBinding bind(View view, Object component) {
        return (ItemDealSellChildBinding) bind(component, view, R.layout.item_deal_sell_child);
    }
}
