package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameToolBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemVipFunBinding extends ViewDataBinding {

    @Bindable
    protected GameToolBean mData;

    public abstract void setData(GameToolBean data);

    protected ItemVipFunBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameToolBean getData() {
        return this.mData;
    }

    public static ItemVipFunBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipFunBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVipFunBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_fun, root, attachToRoot, component);
    }

    public static ItemVipFunBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipFunBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVipFunBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_fun, null, false, component);
    }

    public static ItemVipFunBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipFunBinding bind(View view, Object component) {
        return (ItemVipFunBinding) bind(component, view, R.layout.item_vip_fun);
    }
}
