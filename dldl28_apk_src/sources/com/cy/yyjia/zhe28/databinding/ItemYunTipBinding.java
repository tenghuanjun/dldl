package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunTipBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemYunTipBinding extends ViewDataBinding {

    @Bindable
    protected YunTipBean mData;

    public abstract void setData(YunTipBean data);

    protected ItemYunTipBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public YunTipBean getData() {
        return this.mData;
    }

    public static ItemYunTipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunTipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemYunTipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_tip, root, attachToRoot, component);
    }

    public static ItemYunTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunTipBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemYunTipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_tip, null, false, component);
    }

    public static ItemYunTipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunTipBinding bind(View view, Object component) {
        return (ItemYunTipBinding) bind(component, view, R.layout.item_yun_tip);
    }
}
