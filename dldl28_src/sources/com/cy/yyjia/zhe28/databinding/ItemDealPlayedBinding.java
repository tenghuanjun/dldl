package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealPlayedBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;

    public abstract void setData(GameBean data);

    protected ItemDealPlayedBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemDealPlayedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealPlayedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealPlayedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_played, root, attachToRoot, component);
    }

    public static ItemDealPlayedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealPlayedBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealPlayedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_played, null, false, component);
    }

    public static ItemDealPlayedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealPlayedBinding bind(View view, Object component) {
        return (ItemDealPlayedBinding) bind(component, view, R.layout.item_deal_played);
    }
}
