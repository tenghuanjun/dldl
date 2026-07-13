package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FunBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemFunBinding extends ViewDataBinding {

    @Bindable
    protected FunBean mData;

    public abstract void setData(FunBean data);

    protected ItemFunBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public FunBean getData() {
        return this.mData;
    }

    public static ItemFunBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFunBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemFunBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_fun, root, attachToRoot, component);
    }

    public static ItemFunBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFunBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemFunBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_fun, null, false, component);
    }

    public static ItemFunBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFunBinding bind(View view, Object component) {
        return (ItemFunBinding) bind(component, view, R.layout.item_fun);
    }
}
