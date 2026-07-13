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
public abstract class ItemDialogYunDeviceBinding extends ViewDataBinding {

    @Bindable
    protected YunIndexBean.Device mData;

    public abstract void setData(YunIndexBean.Device data);

    protected ItemDialogYunDeviceBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public YunIndexBean.Device getData() {
        return this.mData;
    }

    public static ItemDialogYunDeviceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDialogYunDeviceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDialogYunDeviceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_dialog_yun_device, root, attachToRoot, component);
    }

    public static ItemDialogYunDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDialogYunDeviceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDialogYunDeviceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_dialog_yun_device, null, false, component);
    }

    public static ItemDialogYunDeviceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDialogYunDeviceBinding bind(View view, Object component) {
        return (ItemDialogYunDeviceBinding) bind(component, view, R.layout.item_dialog_yun_device);
    }
}
