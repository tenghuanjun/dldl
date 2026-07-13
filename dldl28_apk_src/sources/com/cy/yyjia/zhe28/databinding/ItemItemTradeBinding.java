package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemItemTradeBinding extends ViewDataBinding {

    @Bindable
    protected ItemTradeBean mData;

    public abstract void setData(ItemTradeBean data);

    protected ItemItemTradeBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public ItemTradeBean getData() {
        return this.mData;
    }

    public static ItemItemTradeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemItemTradeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_trade, root, attachToRoot, component);
    }

    public static ItemItemTradeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemItemTradeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_trade, null, false, component);
    }

    public static ItemItemTradeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeBinding bind(View view, Object component) {
        return (ItemItemTradeBinding) bind(component, view, R.layout.item_item_trade);
    }
}
