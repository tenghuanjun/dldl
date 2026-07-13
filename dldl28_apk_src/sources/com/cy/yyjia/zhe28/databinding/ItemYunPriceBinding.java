package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunPrice;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemYunPriceBinding extends ViewDataBinding {

    @Bindable
    protected YunPrice mData;

    public abstract void setData(YunPrice data);

    protected ItemYunPriceBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public YunPrice getData() {
        return this.mData;
    }

    public static ItemYunPriceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunPriceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemYunPriceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_price, root, attachToRoot, component);
    }

    public static ItemYunPriceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunPriceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemYunPriceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_price, null, false, component);
    }

    public static ItemYunPriceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunPriceBinding bind(View view, Object component) {
        return (ItemYunPriceBinding) bind(component, view, R.layout.item_yun_price);
    }
}
