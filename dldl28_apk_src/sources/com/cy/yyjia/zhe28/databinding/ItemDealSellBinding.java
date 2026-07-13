package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.AccountListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealSellBinding extends ViewDataBinding {

    @Bindable
    protected AccountListBean mData;
    public final LinearLayout name;
    public final RecyclerView rv;

    public abstract void setData(AccountListBean data);

    protected ItemDealSellBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout name, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.name = name;
        this.rv = rv;
    }

    public AccountListBean getData() {
        return this.mData;
    }

    public static ItemDealSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_sell, root, attachToRoot, component);
    }

    public static ItemDealSellBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealSellBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_sell, null, false, component);
    }

    public static ItemDealSellBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealSellBinding bind(View view, Object component) {
        return (ItemDealSellBinding) bind(component, view, R.layout.item_deal_sell);
    }
}
