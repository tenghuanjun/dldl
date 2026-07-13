package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealIndexBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealFunBinding extends ViewDataBinding {

    @Bindable
    protected DealIndexBean.FunBean mData;

    public abstract void setData(DealIndexBean.FunBean data);

    protected ItemDealFunBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public DealIndexBean.FunBean getData() {
        return this.mData;
    }

    public static ItemDealFunBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealFunBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealFunBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_fun, root, attachToRoot, component);
    }

    public static ItemDealFunBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealFunBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealFunBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_fun, null, false, component);
    }

    public static ItemDealFunBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealFunBinding bind(View view, Object component) {
        return (ItemDealFunBinding) bind(component, view, R.layout.item_deal_fun);
    }
}
