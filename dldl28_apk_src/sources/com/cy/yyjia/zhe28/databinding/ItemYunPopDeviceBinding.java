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
public abstract class ItemYunPopDeviceBinding extends ViewDataBinding {

    @Bindable
    protected YunIndexBean.Device mData;

    public abstract void setData(YunIndexBean.Device data);

    protected ItemYunPopDeviceBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public YunIndexBean.Device getData() {
        return this.mData;
    }

    public static ItemYunPopDeviceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunPopDeviceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemYunPopDeviceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_pop_device, root, attachToRoot, component);
    }

    public static ItemYunPopDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunPopDeviceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemYunPopDeviceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_pop_device, null, false, component);
    }

    public static ItemYunPopDeviceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunPopDeviceBinding bind(View view, Object component) {
        return (ItemYunPopDeviceBinding) bind(component, view, R.layout.item_yun_pop_device);
    }
}
