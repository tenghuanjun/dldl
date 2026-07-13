package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunIndexBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemYunBlockBinding extends ViewDataBinding {

    @Bindable
    protected YunIndexBean.Block mData;

    public abstract void setData(YunIndexBean.Block data);

    protected ItemYunBlockBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public YunIndexBean.Block getData() {
        return this.mData;
    }

    public static ItemYunBlockBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunBlockBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemYunBlockBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_block, root, attachToRoot, component);
    }

    public static ItemYunBlockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunBlockBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemYunBlockBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_block, null, false, component);
    }

    public static ItemYunBlockBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunBlockBinding bind(View view, Object component) {
        return (ItemYunBlockBinding) bind(component, view, R.layout.item_yun_block);
    }
}
